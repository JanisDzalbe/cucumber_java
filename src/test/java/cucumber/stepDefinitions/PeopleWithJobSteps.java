package cucumber.stepDefinitions;

import cucumber.pages_sample.EditAPersonPage;
import cucumber.pages_sample.EnterANewPersonPage;
import cucumber.pages_sample.PeopleWithJobPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PeopleWithJobSteps {

    private WebDriver driver;
    static PeopleWithJobPage peopleWithJobPage;
    static EnterANewPersonPage enterANewPersonPage;
    static EditAPersonPage editAPersonPage;
    Map<String, String> mapOfPeople = new LinkedHashMap<String, String>() {
        {
            put("Mike", "Web Designer");
            put("Jill", "Support");
            put("Jane", "Accountant");
        }
    };

    public PeopleWithJobSteps() {
        this.driver = Hooks.driver;
        peopleWithJobPage = PageFactory.initElements(Hooks.driver, PeopleWithJobPage.class);
        enterANewPersonPage = PageFactory.initElements(Hooks.driver, EnterANewPersonPage.class);
        editAPersonPage = PageFactory.initElements(Hooks.driver, EditAPersonPage.class);
    }

    @Given("^I am on List Of People Page$")
    public void iAmOnAgePage() throws Throwable {
        driver.get(peopleWithJobPage.getPageUrl());
    }

    @When("^I press Add Person button$")
    public void iPressAddPersonButton() throws Throwable {
        peopleWithJobPage.clickAddPersonButton();
    }

    @When("^I press Edit Person button of \"([^\"]*)\" field$")
    public void iPressEditPersonButton(String targetName) throws Throwable {
        peopleWithJobPage.clickEditPersonButton(targetName);
    }

    @When("^I press Remove Person button of: \"([^\"]*)\"$")
    public void iPressRemovePersonButtonOf() throws Throwable {
        peopleWithJobPage.clickAddPersonButton();
    }

    @When("^I fulfill person information with: \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iFulfillPersonInformation(String name, String job) throws Throwable {
        enterANewPersonPage.inputName(name);
        enterANewPersonPage.inputJob(job);
    }

    @When("^I press Remove Person button$")
    public void iPressRemovePersonButton(List<String> people) throws Throwable {
        peopleWithJobPage.clickDeletePersonButton(people);
    }


    @And("^I press Add button$")
    public void iPressAddButton() throws Throwable {
        enterANewPersonPage.clickAddButton();
    }

    @And("^I press Edit button$")
    public void iPressEditButton() throws Throwable {
        editAPersonPage.clickEditButton();
    }

    @And("^I press Reset List button$")
    public void iPressResetListButton() throws Throwable {
        peopleWithJobPage.clickRestListButton();
    }

    @And("^I press Clear Button$")
    public void iPressClearButton() throws Throwable {
        enterANewPersonPage.clickClearButton();
    }

    @And("^I edit name to: \"([^\"]*)\" and job to: \"([^\"]*)\"$")
    public void iEditNameAndJob(String name, String job) throws Throwable {
        editAPersonPage.editName(name);
        editAPersonPage.editJob(job);
    }

    @Then("^I see original list of people$")
    public void iSeeOriginalListOfPeople() throws Throwable {
        peopleWithJobPage.checkListValues(mapOfPeople);
    }

    @Then("^I see extra field with \"([^\"]*)\" and \"([^\"]*)\" in person list$")
    public void iSeeOriginalListOfPeople(String name, String job) throws Throwable {
        mapOfPeople.put(name, job);
        peopleWithJobPage.checkListValues(mapOfPeople);
    }

    @Then("^I see updated Person field of \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iSeeUpdatedPersonField(String name, String age) throws Throwable {
        peopleWithJobPage.checkNameAndJobFromList(name, age);
    }

    @Then("^I see list of people without$")
    public void iSeeListOfPeopleWithout(List<String> people) throws Throwable {
        for (String person : people) {
            mapOfPeople.remove(person);
        }
        peopleWithJobPage.checkListValues(mapOfPeople);
    }

    @Then("^I see empty text-fields$")
    public void iSeeEmptyTextFields() throws Throwable {
        enterANewPersonPage.checkNameForEmptyTextField();
        enterANewPersonPage.checkJobForEmptyTextField();
    }
}
