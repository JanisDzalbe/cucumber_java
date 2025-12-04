package cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Task2Steps {

    private final WebDriver driver;

    private final By addPersonButton = By.xpath("//button[contains(.,'Add person')]");
    private final By resetListButton = By.xpath("//button[contains(.,'Reset List')]");
    private final By addButton = By.xpath("//button[normalize-space(.)='Add']");

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    private WebElement findPerson(String name, String job) {
        return driver.findElement(
                By.xpath("//li[.//span[text()='" + name + "'] and .//span[text()='" + job + "']]")
        );
    }

    private boolean isPersonPresent(String name, String job) {
        List<WebElement> found = driver.findElements(
                By.xpath("//li[.//span[text()='" + name + "'] and .//span[text()='" + job + "']]")
        );
        return !found.isEmpty();
    }

    private WebElement nameInput() {
        return driver.findElement(
                By.xpath("//label[contains(.,'Name')]/following-sibling::input"));
    }

    private WebElement jobInput() {
        return driver.findElement(
                By.xpath("//label[contains(.,'Job')]/following-sibling::input"));
    }

    private void assertInitialList() {
        WebElement addBtn = driver.findElement(addPersonButton);
        WebElement resetBtn = driver.findElement(resetListButton);

        assertTrue(addBtn.isDisplayed());
        assertTrue(addBtn.isEnabled());
        assertTrue(resetBtn.isDisplayed());
        assertTrue(resetBtn.isEnabled());

        String[][] expected = {
                {"Mike", "Web Designer"},
                {"Jill", "Support"},
                {"Jane", "Accountant"},
                {"John", "Software Engineer"},
                {"Sarah", "Product Manager"},
                {"Carlos", "Data Analyst"},
                {"Emily", "UX Designer"},
                {"David", "Project Manager"},
                {"Maria", "QA Engineer"},
                {"Alex", "DevOps Engineer"}
        };

        for (String[] person : expected) {
            String name = person[0];
            String job = person[1];
            WebElement row = findPerson(name, job);
            assertTrue("Row for " + name + " not found", row.isDisplayed());
        }
    }


    @Given("I am on list of people with jobs page")
    public void iAmOnListOfPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @Then("I should see initial list of 10 people with correct names and jobs")
    public void iShouldSeeInitialListOf10PeopleWithCorrectNamesAndJobs() {
        assertInitialList();
    }

    @When("I add a person with name {string} and job {string}")
    public void iAddAPersonWithNameAndJob(String name, String job) {
        driver.findElement(addPersonButton).click();

        nameInput().clear();
        nameInput().sendKeys(name);

        jobInput().clear();
        jobInput().sendKeys(job);

        driver.findElement(addButton).click();
    }

    @Then("the list should contain a person with name {string} and job {string}")
    public void theListShouldContainAPersonWithNameAndJob(String name, String job) {
        assertTrue("Expected person not found: " + name + " - " + job,
                isPersonPresent(name, job));
    }

    @Then("the list should not contain a person with name {string} and job {string}")
    public void theListShouldNotContainAPersonWithNameAndJob(String name, String job) {
        assertFalse("Person should not be present: " + name + " - " + job,
                isPersonPresent(name, job));
    }

    @Given("the list contains a person with name {string} and job {string}")
    public void theListContainsAPersonWithNameAndJob(String name, String job) {
        assertTrue("Precondition failed, person not found: " + name + " - " + job,
                isPersonPresent(name, job));
    }

    @When("I change job for person {string} from {string} to {string}")
    public void iChangeJobForPersonFromTo(String name, String oldJob, String newJob) {
        WebElement personRow = driver.findElement(
                By.xpath("//li[" +
                        ".//span[contains(@class,'name') and normalize-space(.)='" + name + "'] and " +
                        ".//span[contains(@class,'job') and normalize-space(.)='" + oldJob + "']]")
        );

        WebElement editBtn = personRow.findElement(By.cssSelector("span.editbtn"));
        editBtn.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField  = driver.findElement(By.id("job"));

        nameField.clear();
        nameField.sendKeys(name);

        jobField.clear();
        jobField.sendKeys(newJob);

        driver.findElement(By.id("modal_button")).click();
    }


    @When("I remove a person with name {string} and job {string}")
    public void iRemoveAPersonWithNameAndJob(String name, String job) {
        WebElement row = findPerson(name, job);

        WebElement deleteBtn = row.findElement(
                By.xpath(".//span[contains(@onclick,'deletePerson')]"));
        deleteBtn.click();
    }

    @When("I reset the list of people with jobs")
    public void iResetTheListOfPeopleWithJobs() {
        driver.findElement(resetListButton).click();
    }
}