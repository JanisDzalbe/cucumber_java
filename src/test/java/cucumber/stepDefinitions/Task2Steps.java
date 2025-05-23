package cucumber.stepDefinitions;

import cucumber.pages_sample.AddPersonPage;
import cucumber.pages_sample.AgePage;
import cucumber.pages_sample.AgeSubmittedPage;
import cucumber.pages_sample.ListOfPeoplePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.*;

public class Task2Steps {
    private WebDriver driver;
    static ListOfPeoplePage listOfPeoplePage;
    static AddPersonPage addPersonPage;

    List<Map<String, String>> peopleList;
    Map<String, String> newPerson;
    Map<String, String> removedPerson;

    public Task2Steps() {
        this.driver = Hooks.driver;
        listOfPeoplePage = PageFactory.initElements(Hooks.driver, ListOfPeoplePage.class);
        addPersonPage = PageFactory.initElements(Hooks.driver, AddPersonPage.class);
    }

    @Given("^I am on the list of people page$")
    public void iAmOnTheListOfPeoplePage() {
        driver.get(listOfPeoplePage.getPageUrl());
    }


    @When("^I get list of all people$")
    public void iGetListOfAllPeople() {
        peopleList = listOfPeoplePage.getAllPeople();
    }


    @And("^I click add person button$")
    public void iClickAddPersonButton() {
        listOfPeoplePage.clickAddPersonButton();
    }

    @Then("I enter information:")
    public void iEnterInformation(Map<String, String> personInformation) {
        newPerson = personInformation;

        addPersonPage.enterName(personInformation.get("name"));
        addPersonPage.enterSurname(personInformation.get("surname"));
        addPersonPage.enterJob(personInformation.get("job"));
        addPersonPage.enterDOBText(personInformation.get("dob"));
        addPersonPage.selectLanguages(personInformation.get("languages"));
        addPersonPage.selectGender(personInformation.get("gender"));
        addPersonPage.selectStatus(personInformation.get("status"));
        //casting as lowercase because the capitalization is differenet between pages, why??
    }

    @And("I click add")
    public void iClickAdd() {
        addPersonPage.clickAddButton();
    }

    // It took me TWO HOURS to get this to pass. All because the languages are implemented in such a horrible manner here.
    @Then("I make sure the list contains the person I added")
    public void iMakeSureTheListContainsThePersonIAdded() {
        assertTrue(listOfPeoplePage.getAllPeople().size() != peopleList.size());
        listOfPeoplePage.assertPersonInList(newPerson);
    }

    @Then("I enter information with calendar selector:")
    public void iEnterInformationWithCalendarSelector(Map<String, String> personInformation) {
        newPerson = personInformation;

        addPersonPage.enterName(personInformation.get("name"));
        addPersonPage.enterSurname(personInformation.get("surname"));
        addPersonPage.enterJob(personInformation.get("job"));
        addPersonPage.selectDOBDate(personInformation.get("dob"));
        addPersonPage.selectLanguages(personInformation.get("languages"));
        addPersonPage.selectGender(personInformation.get("gender"));
        addPersonPage.selectStatus(personInformation.get("status"));
    }

    @And("I click edit person button for {string} person in list")
    public void iClickEditPersonButtonForPersonInList(String indexStr) {
        int index = Integer.parseInt(indexStr);
        listOfPeoplePage.clickEditPerson(index);
    }

    @Then("I enter edit information:")
    public void iEnterEditInformation(Map<String, String> personInformation) {
        newPerson = personInformation;

        addPersonPage.enterName(personInformation.get("name"));
        addPersonPage.enterSurname(personInformation.get("surname"));
        addPersonPage.enterJob(personInformation.get("job"));
        addPersonPage.enterDOBText(personInformation.get("dob"));
        addPersonPage.selectLanguages(personInformation.get("languages"));
        addPersonPage.selectGender(personInformation.get("gender"));
        addPersonPage.selectStatus(personInformation.get("status"));
    }

    @Then("I make sure the list of same size contains the person I added")
    public void iMakeSureTheListOfSameSizeContainsThePersonIAdded() {
        assertEquals(listOfPeoplePage.getAllPeople().size(), peopleList.size());
        listOfPeoplePage.assertPersonInList(newPerson);
    }

    @And("I click edit")
    public void iClickEdit() {
        addPersonPage.clickEditButton();
    }

    @And("I click remove person for {string} person in list")
    public void iClickRemovePersonForPersonInList(String indexStr) {
        int index = Integer.parseInt(indexStr);

        removedPerson = listOfPeoplePage.getPersonAllInfo(index);

        listOfPeoplePage.clickDeletePerson(index);
    }

    @Then("I make sure the list is smaller and the person is no longer in it")
    public void iMakeSureTheListIsSmallerAndThePersonIsNoLongerInIt() {
        assertTrue(listOfPeoplePage.getAllPeople().size() < peopleList.size());
        assertFalse(listOfPeoplePage.getAllPeople().contains(removedPerson));
    }

    @Then("I make sure the list is the same as in the begginning")
    public void iMakeSureTheListIsTheSameAsInTheBegginning() {
        assertEquals(listOfPeoplePage.getAllPeople(), peopleList);
    }

    @And("I click reset list")
    public void iClickResetList() {
        listOfPeoplePage.clickResetListButton();
    }

    @And("I click clear all fields")
    public void iClickClearAllFields() {
        addPersonPage.clickClearAllFieldsButton();
    }

    @Then("I make sure the list does not contain the person I wanted to add")
    public void iMakeSureTheListDoesNotContainThePersonIWantedToAdd() {
        assertFalse(listOfPeoplePage.getAllPeople().contains(newPerson));
    }
}
