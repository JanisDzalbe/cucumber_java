package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I (?:am on|open) people with jobs page$")
    public void iAmOnPeopleWithJobsPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click the add person button$")
    public void iClickAddPersonButton() {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @When("^I enter the following details:$")
    public void iEnterDetails(Map<String, String> data) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(data.get("name"));
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(data.get("job"));
    }

    @And("^I click the confirm button$")
    public void iClickConfirmButton() {
        driver.findElement(By.cssSelector("button[onclick='addPersonWithJobToList()']")).click();
    }

    @Then("^I should see \"([^\"]*)\" with job \"([^\"]*)\" in the list$")
    public void iShouldSeePersonInList(String name, String job) {
        String pageText = driver.findElement(By.id("listOfPeople")).getText();
        assertTrue(pageText.contains(name));
        assertTrue(pageText.contains(job));
    }

    @Given("^I have added a person \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iHaveAddedPersonWithJob(String name, String job) {
        driver.findElement(By.id("addPersonBtn")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
        driver.findElement(By.xpath("//button[@onclick='addPersonWithJobToList()']")).click();
    }

    @When("^I edit the job for \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iEditJobForPerson(String name, String newJob) throws InterruptedException {
        WebElement editBtn = driver.findElement(By.xpath("//li[contains(., '" + name + "')]/span[contains(@onclick, 'openModalForEditPersonWithJob')]"));
        editBtn.click();
        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        jobInput.sendKeys(newJob);
    }

    @And("^I click edit button$")
    public void iClickEditButton() {
        driver.findElement(By.xpath("//button[contains(text(), 'Edit')]")).click();
    }

    @When("^I remove \"([^\"]*)\" from the list$")
    public void iRemovePersonFromList(String name) {
        WebElement removeBtn = driver.findElement(By.xpath("//li[contains(., '" + name + "')]//span[contains(@onclick, 'deletePerson')]"));
        removeBtn.click();
    }

    @Then("^\"([^\"]*)\" should no longer be in the list$")
    public void personShouldNoLongerBeInList(String name) {
        String pageText = driver.findElement(By.id("listOfPeople")).getText();
        assertFalse(pageText.contains(name));
    }

    @When("^I click the reset list button$")
    public void iClickResetListButton() {
        driver.findElement(By.xpath("//button[contains(text(),'Reset List')]")).click();
    }

    @When("^I click the clear all fields button$")
    public void iClickTheClearButton() {
        driver.findElement(By.xpath("//button[contains(text(), 'Clear all fields')]")).click();
    }

    @Then("^the name and job fields should be empty$")
    public void theNameAndJobFieldsShouldBeEmpty() {
        String nameValue = driver.findElement(By.id("name")).getAttribute("value");
        String jobValue = driver.findElement(By.id("job")).getAttribute("value");
        assertTrue(nameValue.isEmpty());
        assertTrue(jobValue.isEmpty());
    }
}
