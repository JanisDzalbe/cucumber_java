package cucumber.stepDefinitions;

import cucumber.pages_sample.Task2PeopleWithJobs;
import cucumber.pages_sample.Task2Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Task2Steps {
    private WebDriver driver;
    static Task2Page jobsPage;
    static Task2PeopleWithJobs addPersonPage;

    public Task2Steps() {
        this.driver = Hooks.driver;
        jobsPage = PageFactory.initElements(Hooks.driver, Task2Page.class);
        addPersonPage = PageFactory.initElements(Hooks.driver, Task2PeopleWithJobs.class);
    }
    @Given("^I (?:am on|open) People with jobs page$")
    public void iAmOnPeopleWithJobsPage() throws Throwable {
        driver.get(jobsPage.getPageUrl());
    }
    @When("^I click Add person button$")
    public void iClickAddPersonButton() throws Throwable {
        jobsPage.clickAddPersonButton();
    }

    @And("^I click Clear all fields button$")
    public void iClickClearAllFields() throws Throwable {
        addPersonPage.clickClearFields();
        addPersonPage.assertFieldsAreEmpty();
    }

    @And("^I am navigated to the (?:Edit|Add) person page$")
    public void iAmNavigatedToTheAddPersonPage() throws Throwable {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
    }

    @And("^I write name: \"([^\"]*)\"$")
    public void iWriteName(String name) throws Throwable {
        addPersonPage.enterName(name);
    }

    @And("^I write job: \"([^\"]*)\"$")
    public void iWriteJob(String job) throws Throwable {
        addPersonPage.enterJob(job);
    }

    @And("^I click (?:add|Add) button$")
    public void iClickAddButton() throws Throwable {
        addPersonPage.clickAddButton();
    }

    @And("^I click (?:edit|Edit) button$")
    public void iClickEditButton() throws Throwable {
        addPersonPage.clickEditButton();
    }

    @When("^I click (?:reset|Reset) list button$")
    public void iClickResetListButton() throws Throwable {
        jobsPage.clickResetListButton();
    }

    @Then("^I see default list of people$")
    public void iSeeDefaultList() throws Throwable {
        jobsPage.assertDefaultList();
    }

    @Then("^I go back to People with jobs page$")
    public void iGoBackToPeopleWithJobsPage() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs", jobsPage.getPageUrl());
    }

    @Then("^I see new instance of a person \"([^\"]*)\" who is \"([^\"]*)\"$")
    public void iSeeNewInstanceOfPersonWithJob(String name, String job) throws Throwable {
        jobsPage.assertPersonExists(name, job);
    }

    @When("^I click Edit person button for \"([^\"]*)\" who is \"([^\"]*)\"$")
    public void iClickEditPersonButton(String name, String job) throws Throwable {
        jobsPage.clickEditPerson(name, job);
    }

    @When("^I click Remove person button for \"([^\"]*)\" who is \"([^\"]*)\"$")
    public void iClickRemovePersonButton(String name, String job) throws Throwable {
        jobsPage.clickRemovePerson(name, job);
    }

    @Then("^I do not see \"([^\"]*)\" who is \"([^\"]*)\" in the list$")
    public void iDoNotSeePersonInList(String name, String job) throws Throwable {
        jobsPage.assertPersonNotExists(name, job);
    }
    @When("I add the following people:")
    public void iAddTheFollowingPeople(DataTable table) {
        List<Map<String,String>> rows = table.asMaps();
        for (Map<String,String> row : rows) {
            String name = row.get("name");
            String job  = row.get("job");
            jobsPage.clickAddPersonButton();
            addPersonPage.enterName(name);
            addPersonPage.enterJob(job);
            addPersonPage.clickAddButton();
        }
    }

    @Then("I see people:")
    public void iSeePeople(DataTable table) {
        List<Map<String,String>> expected = table.asMaps();
        for (Map<String,String> row : expected) {
            jobsPage.assertPersonExists(row.get("name"), row.get("job"));
        }
    }

    @Then("I sleep")
    public void iSleep() throws Throwable {
        Thread.sleep(1000);
    }
}
