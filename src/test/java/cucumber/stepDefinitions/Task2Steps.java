package cucumber.stepDefinitions;

import cucumber.pages_sample.AgePage;
import cucumber.pages_sample.AgeSubmittedPage;
import cucumber.pages_sample.ListOfPeoplePage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Steps {
    private WebDriver driver;
    static ListOfPeoplePage listOfPeoplePage;
    private int initialPeopleCount;

    public Task2Steps() {
        this.driver = Hooks.driver;
        listOfPeoplePage = PageFactory.initElements(Hooks.driver, ListOfPeoplePage.class);
    }
    @When("^I am on list of people page$")
    public void iAmOnListOfPeoplePage() throws Throwable {
        driver.get(listOfPeoplePage.getPageUrl());
    }


    @When("^I click the Add person button$")
    public void iClickTheAddPersonButton() {
        listOfPeoplePage.clickAddPerson();
    }

    @And("^I enter name in page: \"([^\"]*)\"$")
    public void iEnterNameInPage(String name) {
        listOfPeoplePage.enterName(name);
    }

    @And("^I enter job: \"([^\"]*)\"$")
    public void iEnterJob(String job) {
        listOfPeoplePage.enterJob(job);
    }

    @And("^I click the Add button$")
    public void iClickTheAddButton() {
        listOfPeoplePage.clickAdd();
    }

    @Then("^I should see \"([^\"]*)\" in the list with job \"([^\"]*)\"$")
    public void iShouldSeeInTheListWithJob(String name, String job) {
        assertTrue(listOfPeoplePage.isPersonDisplayed(name, job));
    }

    @When("^I click the edit icon for \"([^\"]*)\"$")
    public void iClickTheEditIconFor(String name) {
        listOfPeoplePage.clickEditPerson(name);
    }

    @Then("^I should see \"([^\"]*)\" in the name field$")
    public void iShouldSeeInTheNameField(String name) {
        assertEquals(name, listOfPeoplePage.getNameFieldValue());
    }

    @And("^I should see \"([^\"]*)\" in the job field$")
    public void iShouldSeeInTheJobField(String job) {
        assertEquals(job, listOfPeoplePage.getJobFieldValue());
    }

    @When("^I change the job to \"([^\"]*)\"$")
    public void iChangeTheJobTo(String job) {
        listOfPeoplePage.changeJob(job);
    }

    @And("^I click the Edit button$")
    public void iClickTheEditButton() {
        listOfPeoplePage.clickEdit();
    }
    @When("^I click the remove icon for \"([^\"]*)\"$")
    public void iClickTheRemoveIconFor(String name) {
        listOfPeoplePage.removePerson(name);
    }

    @Then("^I do not see \"([^\"]*)\" in the list$")
    public void iDoNotSeeInTheList(String name) {
        assertTrue(listOfPeoplePage.isPersonRemoved(name), name + " was not removed from the list");
    }
    @Given("^I remember the initial number of people$")
    public void iRememberTheInitialNumberOfPeople() {
        initialPeopleCount = listOfPeoplePage.getPeopleCount();
    }

    @When("^I modify the people list by \"([^\"]*)\"$")
    public void iModifyThePeopleListBy(String action) {
        if (action.equals("adding a new person")) {
            listOfPeoplePage.clickAddPerson();
            listOfPeoplePage.enterName("ResetPerson");
            listOfPeoplePage.enterJob("QA Tester");
            listOfPeoplePage.clickAdd();

        } else if (action.equals("editing an existing person")) {

            listOfPeoplePage.clickEditPerson("Mike");
            listOfPeoplePage.changeJob("Senior Web Designer");
            listOfPeoplePage.clickEdit();

        } else if (action.equals("removing an existing person")) {

            listOfPeoplePage.removePerson("Mike");
        }
    }

    @And("I click the Reset List button")
    public void iClickTheResetListButton() {
        listOfPeoplePage.clickResetList();
    }

    @Then("the original number of people is restored")
    public void theOriginalNumberOfPeopleIsRestored() {
        assertEquals(initialPeopleCount, listOfPeoplePage.getPeopleCount());
    }
}