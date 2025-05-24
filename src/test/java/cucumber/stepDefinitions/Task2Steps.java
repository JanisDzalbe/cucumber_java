package cucumber.stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("I am on the list of people page")
    public void iAmOnTheListOfPeoplePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
    }


    @When("I click add person button")
    public void iClickAddPersonButton() {
        driver.findElement(By.cssSelector("[onclick='openModalForAddPersonWithJob()']")).click();
    }

    @And("I enter values: {string} and {string}")
    public void iEnterValuesAnd(String name, String job) {
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("I click add button")
    public void iClickAddButton() {
        driver.findElement(By.cssSelector("[onclick='addPersonWithJobToList()']")).click();

    }

    @Then("I should see the person with name {string} and  job {string} in the list")
    public void iShouldSeeThePersonWithNameAndJobInTheList(String name, String job) {
        List<WebElement> names_list = driver.findElements(By.className("name"));
        List<WebElement> jobs_list = driver.findElements(By.className("job"));
        for (int i = 0; i <= names_list.size()-1; i++){
            if (names_list.get(i).getText().equals(name) && jobs_list.get(i).getText().equals(job)){
                assertEquals(name, names_list.get(i).getText());
                assertEquals(job, jobs_list.get(i).getText());
            }
        }
    }

    @When("I click edit {int} person")
    public void iClickEditPerson(int arg0) {
        driver.findElement(By.cssSelector(String.format("[onclick='openModalForEditPersonWithJob(%d)']", arg0))).click();
    }

    @And("I change name to {string} and job {string}")
    public void iChangeNameToAndJob(String name, String job) {
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("I click button Edit")
    public void iClickButtonEdit() {
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Edit']")).click();
    }

    @When("I click remove person with name {string}")
    public void iClickRemovePerson(String name) {
        WebElement deleteButton = driver.findElement(By.xpath(
                String.format("//span[text()='%s']/preceding-sibling::span[contains(@onclick, 'deletePerson')]", name)
        ));
        deleteButton.click();
    }


    @When("I click reset list button")
    public void iClickResetListButton() {
        driver.findElement(By.cssSelector("[onclick='resetListOfPeople()']")).click();
    }

    @And("I click clear all fields button")
    public void iClickClearAllFieldsButton() {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @Then("I should see that all fields are empty")
    public void iShouldSeeThatAllFieldsAreEmpty() {
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("job")).getText());
    }

    @Then("I should see that person with name {string} is not in the list")
    public void iShouldSeeThatPersonIsNotInTheList(String name) {
        List<WebElement> names_list = driver.findElements(By.className("name"));
        for (int i = 0; i <= names_list.size()-1; i++){
            if (!names_list.get(i).getText().equals(name)){
                assertNotEquals(name, names_list.get(i).getText());
            }
        }
    }
}
