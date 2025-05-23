package cucumber.stepDefinitions;

import cucumber.pages_sample.PeopleAddPage;
import cucumber.pages_sample.PeoplePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task2Steps {
    private WebDriver driver;
    static PeoplePage peoplePage;
    static PeopleAddPage peopleAddPage;

    public Task2Steps() {
        this.driver = Hooks.driver;
        peoplePage = PageFactory.initElements(Hooks.driver, PeoplePage.class);
        peopleAddPage = PageFactory.initElements(Hooks.driver, PeopleAddPage.class);
    }

    @Given("^I enter people with jobs page$")
    public void iEnterPeopleWithJobsPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click Add person button$")
    public void iClickAddPersonButton() {
        peoplePage.clickAddPersonButton();
    }

    @Then("^I enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iEnterNameAndJob(String name, String job) {
        peopleAddPage.enterName(name);
        peopleAddPage.enterJob(job);
    }

    @And("^I click add$")
    public void iClickAdd() {
        peopleAddPage.clickModalButton();
    }

    @Then("^I check that a person with \"([^\"]*)\" and \"([^\"]*)\" is on the list$")
    public void checkNameAndJobOnList(String name, String job) {
        peoplePage.checkPersonIsOnList(name, job);
    }

    @When("^I click edit on \"([^\"]*)\", \"([^\"]*)\"$")
    public void iClickEditOnNameAndJob(String name, String job) {
        peoplePage.clickEditButton(name, job);
    }

    @And("^I click edit$")
    public void iClickEdit() {
        peopleAddPage.clickEditButton();
    }

    @And("^I check that a person \"([^\"]*)\" and \"([^\"]*)\" is not on the list$")
    public void checkNameAndJobNotOnList(String name, String job) {
        peoplePage.checkPersonNotOnList(name, job);
    }

    @When("^I click delete button on \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iClickDeleteButtonOnNameAndJob(String name, String job) {
        peoplePage.clickDeleteButton(name, job);
    }

    @And("^I click Reset List$")
    public void iClickResetList() {
        peoplePage.clickResetButton();
    }

    @Then("^I check that a list is reset$")
    public void iCheckListIsReset() {
        peoplePage.checkThatListIsReset();
    }

    @And("^I click clear all fields$")
    public void iClickClearAllFields() {
        peopleAddPage.clickClearButton();
    }

    @Then("^I check fields are cleared$")
    public void iCheckFieldsAreCleared() {
        peopleAddPage.checkName("");
        peopleAddPage.checkJob("");
    }
}
