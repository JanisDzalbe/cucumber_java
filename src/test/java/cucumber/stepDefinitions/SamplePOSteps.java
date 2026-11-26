package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cucumber.pages_sample.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SamplePOSteps {
    private final WebDriver driver;
    static AgePage agePage;
    static AgeSubmittedPage ageSubmittedPage;

    // Page Object of the people list page
    static PeoplePage peoplePage;
    // Snapshot of the original list, taken after Reset
    private Map<String, String> originalPeople;

    public SamplePOSteps() {
        this.driver = Hooks.driver;
        agePage = PageFactory.initElements(Hooks.driver, AgePage.class);
        ageSubmittedPage = PageFactory.initElements(Hooks.driver, AgeSubmittedPage.class);
        peoplePage = PageFactory.initElements(Hooks.driver, PeoplePage.class);
    }

    @When("^I enter name: \"([^\"]*)\" using PO$")
    public void iEnterName(String name) throws Throwable {
        agePage.enterName(name);
    }

    @And("^I enter age: (\\d+) using PO$")
    public void iEnterAge(int age) throws Throwable {
        agePage.enterAge(age);
    }

    @Given("^I (?:am on|open) age page using PO$")
    public void iAmOnAgePage() throws Throwable {
        driver.get(agePage.getPageUrl());
    }

    @And("^I click submit age using PO$")
    public void iClickSubmitAge() throws Throwable {
        agePage.clickSubmit();
    }

    @Then("^I see message: \"(.*)\" using PO$")
    public void iSeeMessage(String message) throws Throwable {
        ageSubmittedPage.checkMessageText(message);
    }

    @When("^I enter values using PO:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        agePage.enterName(valuesToEnter.get("name"));
        agePage.enterAge(valuesToEnter.get("age"));
    }

    @Then("^I see error: \"([^\"]*)\" using PO$")
    public void iSeeErrorMessageInAgePage(String message) throws Throwable {
        agePage.checkErrorMessage(message);
    }

    @Then("^I remain in age page using PO$")
    public void iRemainInInAgePage() throws Throwable {
        assertEquals(agePage.getPageUrl(), driver.getCurrentUrl());
    }

    //----------------------------------------------
    // People page
    //----------------------------------------------

    // Open the page, reset the list and remember it
    @Given("^I open people list page using PO$")
    public void iOpenPeopleListPage() {
        driver.get(peoplePage.getPageUrl());
        peoplePage.resetList();
        originalPeople = new LinkedHashMap<>(peoplePage.getPeople());
    }

    @When("^I add person \"([^\"]*)\" with job \"([^\"]*)\" using PO$")
    public void iAddPerson(String name, String job) {
        peoplePage.addPerson(name, job);
    }

    // Rename: the job stays the same
    @When("^I rename person \"([^\"]*)\" to \"([^\"]*)\" using PO$")
    public void iRenamePerson(String oldName, String newName) {
        peoplePage.renamePerson(oldName, newName);
    }

    @When("^I delete person \"([^\"]*)\" using PO$")
    public void iDeletePerson(String name) {
        peoplePage.removePerson(name);
    }

    @When("^I reset the list using PO$")
    public void iResetTheList() {
        peoplePage.resetList();
    }

    // Person is in the list and has the expected job
    @Then("^the list contains person \"([^\"]*)\" with job \"([^\"]*)\" using PO$")
    public void theListContainsPerson(String name, String job) {
        Map<String, String> people = peoplePage.getPeople();
        assertTrue(people.containsKey(name), "Person not found: " + name);
        assertEquals(job, people.get(name));
    }

    @Then("^the list does not contain person \"([^\"]*)\" using PO$")
    public void theListDoesNotContainPerson(String name) {
        assertFalse(peoplePage.getPeople().containsKey(name), "Person still in list: " + name);
    }

    // List size changed (delta: +/-1)
    @Then("^the list size changed by (-?\\d+) using PO$")
    public void theListSizeChangedBy(int delta) {
        assertEquals(originalPeople.size() + delta, peoplePage.getPeople().size());
    }

    // List fully equals the snapshot
    @Then("^the list equals the original one using PO$")
    public void theListEqualsTheOriginalOne() {
        assertEquals(new ArrayList<>(originalPeople.entrySet()),
                new ArrayList<>(peoplePage.getPeople().entrySet()));
    }
}