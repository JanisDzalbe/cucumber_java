package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

import static org.junit.Assert.*;


public class Task2Steps {
    private WebDriver driver;
    public Task2Steps(){
        this.driver = Hooks.driver;

    }

    @Given("people with jobs page is displayed")
    public void peopleWithJobsPageIsDisplayed() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");

    }

    @When("press add person button")
    public void pressAddPersonButton() throws Throwable {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @And("enter {string} and {string}")
    public void enterAnd(String name, String job) throws Throwable {
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("press add button")
    public void pressAddButton() throws Throwable {
        driver.findElement(By.id("modal_button")).click();
    }


    @Then("New person with {string} and {string} is displayed")
    public void newPersonWithAndIsDisplayed(String name, String job) throws Throwable {
        assertEquals(name, driver.findElement(By.cssSelector("#person3 > span.w3-xlarge.name")).getText());
        assertEquals(job, driver.findElement(By.cssSelector("#person3 > span.job")).getText());
    }


    @When("press pencil button")
    public void pressPencilButton() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"person0\"]/span[2]/i")).click();
    }

    @And("enter new name and job")
    public void enterNewNameAndJob() throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Asko");
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Pilot");
    }

    @And("press edit button")
    public void pressEditButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @Then("new name and job is displayed")
    public void newNameAndJobIsDisplayed() {
        assertEquals("Asko", driver.findElement(By.xpath("//*[text()='Asko']")).getText());
        assertEquals("Pilot", driver.findElement(By.xpath("//*[text()='Pilot']")).getText());
    }

    @When("press delete button")
    public void pressDeleteButton() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"person0\"]/span[1]")).click();
    }

    @Then("person deleted and is not displayed")
    public void personDeletedAndIsNotDisplayed() throws Throwable {
        List<WebElement> personListNamesAfter = driver.findElements(By.cssSelector("#listOfPeople li .name"));
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
        List<WebElement> personListNamesBefore = driver.findElements(By.cssSelector("#listOfPeople li .name"));
       assertNotEquals(personListNamesAfter, personListNamesBefore);

    }


    @And("press reset button and return page to primary state")
    public void pressResetButtonAndReturnPageToPrimaryState() throws InterruptedException {
       List<WebElement> personNamesAfterAdd = driver.findElements(By.cssSelector("#listOfPeople li .name"));
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
        List<WebElement> personNamesAfterReset = driver.findElements(By.cssSelector("#listOfPeople li .name"));
       // assertNotEquals(personNamesAfterAdd, personNamesAfterReset);


    }

    @And("press clear all fields button")
    public void pressClearAllFieldsButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Clear all fields']")).click();
    }

    @Then("all fields are clear")
    public void allFieldsAreClear() throws Throwable {
       assertEquals("", driver.findElement(By.id("name")).getText());
       assertEquals("", driver.findElement(By.id("job")).getText());

    }
}
