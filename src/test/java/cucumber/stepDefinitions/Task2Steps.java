package cucumber.stepDefinitions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I navigate to list of people page$")
    public void iNavigateToList(){
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I add a new person \"([^\"]*)\" with job \"([^\"]*)\"")
    public void seeNameJob(String name, String job){
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @Then("^new person \"([^\"]*)\" is in the list with job \"([^\"]*)\"$")
    public void newPerson(String name, String job){
        assertEquals(name, driver.findElement(By.xpath("//span[text()='"+ name +"']")).getText());
        assertEquals(job, driver.findElement(By.xpath("//span[text()='" + name + "']/ancestor::li//span[@class='job']")).getText());
    }

    @And("^I edit person \"([^\"]*)\" job to \"([^\"]*)\"$")
    public void editPerson(String name, String job){
        driver.findElement(By.xpath("//span[text()='" + name + "']/ancestor::li")).findElement(By.className("editbtn")).click();
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @Then("^person \"([^\"]*)\" has job \"([^\"]*)\"$")
    public void seeEditPerson(String name, String job){
        assertEquals(name, driver.findElement(By.xpath("//span[text()='" + name + "']")).getText());
        assertEquals(job, driver.findElement(By.xpath("//span[text()='" + name + "']/ancestor::li//span[@class='job']")).getText());
    }

    @And("^I remove person \"([^\"]*)\"$")
    public void removePerson(String name){
        driver.findElement(By.xpath("//span[text()='" + name + "']/ancestor::li")).findElement(By.className("closebtn")).click();
    }

    @Then("^person \"([^\"]*)\" is not in the list$")
    public void personNotInList(String name){
        assertEquals(0, driver.findElements(By.xpath("//span[text()='" + name +"']")).size());
    }

    @And("^I reset the list$")
    public void resetList(){
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @Then("^the list has 10 original people$")
    public void originalList(){
        assertEquals(10, driver.findElements(By.cssSelector("#listOfPeople li")).size());
    }
}
