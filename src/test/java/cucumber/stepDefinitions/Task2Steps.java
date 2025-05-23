package cucumber.stepDefinitions;

import cucumber.pages_sample.AddPersonPage;
import cucumber.pages_sample.AgePage;
import cucumber.pages_sample.AgeSubmittedPage;
import cucumber.pages_sample.PeoplePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static cucumber.stepDefinitions.SamplePOSteps.agePage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Task2Steps {
    private WebDriver driver;
    static PeoplePage peoplePage;
    static AddPersonPage addPersonPage;

    public Task2Steps() {
        this.driver = Hooks.driver;
        peoplePage = PageFactory.initElements(Hooks.driver, PeoplePage.class);
        addPersonPage = PageFactory.initElements(Hooks.driver, AddPersonPage.class);
    }

    @Given("^I am on People with jobs page$")
    public void iAmOnPeopleWithJobsPage() {
        peoplePage.open();
    }

    @When("^I click Add person$")
    public void iClickAddPerson() throws Throwable {
        peoplePage.clickAddPerson();
    }

    @When("^I enter name: \"([^\"]*)\" and job: \"([^\"]*)\" on the add page$")
    public void iEnterNameAndJob(String name, String job) throws Throwable {
        addPersonPage.enterNameAndJob(name, job);
    }

    @When("^I click Add on the add page$")
    public void iClickAdd() throws Throwable {
        addPersonPage.clickAdd();
    }

    @Then("^I see name: \"([^\"]*)\" with job: \"([^\"]*)\" in the table$")
    public void iSeeNameAndJob(String name, String job) throws Throwable {
        peoplePage.checkPersonExists(name, job);
    }

    @When("^I click edit next to Mike$")
    public void iClickEditMike() throws Throwable {
        peoplePage.clickEditMike();

    }

    @And("^I change job to \"([^\"]*)\"$")
    public void iChangeJob(String job) throws Throwable {
        addPersonPage.enterJob("CEO");
    }

    @When("^I click edit on Edit page$")
    public void iClickEdit() throws Throwable {
        addPersonPage.clickEditButton();
    }

    @Then("^I see Mike with \"([^\"]*)\" job title$")
    public void iSeeMikeTitle(String job) throws Throwable {
        peoplePage.checkMike(job);
    }

    @When("^I click remove next to Mike$")
    public void iClickRemoveMike() throws Throwable {
        peoplePage.clickRemoveMike();
    }

    @Then("^I see no Mike in the list$")
    public void iSeeNoMike() throws Throwable {
        assertFalse("Mike should not be in the list!", peoplePage.isPersonPresent("Mike"));
    }

    @When("^I click Reset List$")
    public void iClickReset() throws Throwable {
        peoplePage.clickResetListButton();
    }

    @Then("^I see (\\d+) persons on the list$")
    public void iSee3People(int expectedCount) throws Throwable {
        peoplePage.checkPeopleCount(expectedCount);
    }

    @When("^I click Clear all fields on the add page$")
    public void iClickClearButton() throws Throwable {
        addPersonPage.clickClearButton();
    }

    @Then("^I see name and job fields empty$")
    public void iSeeFieldsEmpty () throws Throwable {
        addPersonPage.checkNameAndJobAreEmpty();
    }

    }


