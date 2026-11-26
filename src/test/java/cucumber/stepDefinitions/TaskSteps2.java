package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSteps2 {
    private WebDriver driver;

    public TaskSteps2() {
        this.driver = Hooks.driver;
    }

    @Given("^I navigate to list of people with jobs page$")
    public void iNavigateToListOfPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

    }


    @When("I click Add person button")
    public void iClickAddPersonButton() {
        driver.findElement(By.id("addPersonBtn")).click();
    }


    @And("^I add a new person with details:$")
    public void iAddANewPersonWithDetails(Map<String, String> personDetails) {
        String name = personDetails.get("name");
        String job = personDetails.get("job");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//button[@onclick='openModalForAddPersonWithJob()']")).click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.clear();
        nameField.sendKeys(name);

        WebElement jobField = driver.findElement(By.id("job"));
        jobField.clear();
        jobField.sendKeys(job);

     //   driver.findElement(By.id("modal_button")).click();
    }

    @And("I click Add button")
    public void iClickAddButton() {
        driver.findElement(By.id("modal_button")).click();

    }

    @Then("^I should see \"([^\"]*)\" with job \"([^\"]*)\" in the people list$")
    public void iShouldSeePersonWithDetailsInThePeopleList(String expectedName, String expectedJob) {
        String listText = driver.findElement(By.tagName("body")).getText();

        assertTrue(listText.contains(expectedName), "Expected name not found: " + expectedName);
        assertTrue(listText.contains(expectedJob), "Expected job not found: " + expectedJob);
    }

    @When("^I press the edit button for \"([^\"]*)\"$")
    public void iPressTheEditButtonFor(String name) {
        driver.findElement(By.xpath("//li[span[contains(@class,'name') and text()='" + name + "']]//span[contains(@class,'editbtn')]")).click();
    }

    @And("^I enter the person name: \"([^\"]*)\"$")
    public void iEnterThePersonName(String name) {
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.clear();
        nameField.sendKeys(name);
    }

    @And("^I enter the person job: \"([^\"]*)\"$")
    public void iEnterThePersonJob(String job) {
        WebElement jobField = driver.findElement(By.id("job"));
        jobField.clear();
        jobField.sendKeys(job);
    }

    @And("^I press the Edit button$")
    public void iPressTheEditButton() {
        driver.findElement(By.id("modal_button")).click();
    }

    @Then("^I see \"([^\"]*)\" with job \"([^\"]*)\" in the list$")
    public void iSeeWithJobInTheList(String name, String job) {
        WebElement person = driver.findElement(By.xpath("//li[span[contains(@class,'name') and text()='" + name + "']]"));
        String actualJob = person.findElement(By.className("job")).getText();
        assertEquals(job, actualJob);
    }

    @And("^I see \"([^\"]*)\" is invalid in the list$")
    public void iSeeinvalidlist(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople .name"));
        for (WebElement person : people) {
            assertFalse(person.getText().equals(name), "Name should not be in the list: " + name);
        }
    }

    @When("^I remove person \"([^\"]*)\" from the list$")
    public void iRemovePersonFromTheList(String name) {
        WebElement removeButton = driver.findElement(
                By.xpath("//li[.//*[contains(@class,'name') and normalize-space()='" + name + "']]//span[contains(@class,'closebtn')]")
        );
        removeButton.click();
    }

    @Then("^I should not see \"([^\"]*)\" in the people list$")
    public void iShouldNotSeePersonInThePeopleList(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople .name"));
        for (WebElement person : people) {
            assertFalse(person.getText().trim().equals(name), "Name should not be in the people list: " + name);
        }
    }


    @And("^I click reset list button$")
    public void iClickResetListButton() {
      //  driver.findElement(By.id("addPersonBtn")).click();
        driver.findElement(By.xpath("//button[@onclick='resetListOfPeople()']")).click();
    }

    @Then("^I should see the original number of list of people restored$")
    public void iShouldSeeTheOriginalListOfPeopleRestored() {
        int expectedOriginalCount = 10;
        int actualCount = driver.findElements(By.cssSelector("#listOfPeople li")).size();
        assertEquals(expectedOriginalCount, actualCount);
    }

    @And("^I should see \"([^\"]*)\" in the people list$")
    public void iShouldSeePersonInThePeopleList(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople .name"));
        boolean found = false;
        for (WebElement person : people) {
            if (person.getText().trim().equals(name)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Expected name to be in the people list: " + name);
    }
}
