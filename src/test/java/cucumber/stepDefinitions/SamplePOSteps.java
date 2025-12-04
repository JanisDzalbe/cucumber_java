package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cucumber.pages_sample.*;

import java.util.Map;

import static org.junit.Assert.*;

public class SamplePOSteps {
    private WebDriver driver;
    static AgePage agePage;
    static AgeSubmittedPage ageSubmittedPage;
    static PeopleWithJobsPage peopleWithJobsPage;

    public SamplePOSteps() {
        this.driver = Hooks.driver;
        agePage = PageFactory.initElements(Hooks.driver, AgePage.class);
        ageSubmittedPage = PageFactory.initElements(Hooks.driver, AgeSubmittedPage.class);
        peopleWithJobsPage = PageFactory.initElements(Hooks.driver, PeopleWithJobsPage.class);
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
    // tstk 2
    @Then("^I see error: \"(.*)\" using PO$")
    public void iSeeError(String message) throws Throwable {
        assertEquals("You haven't entered anything in age field", driver.findElement(By.id("error")).getText());
    }

    @And("^I remain in age page using PO$")
    public void iRemainOnPage() throws Throwable {
        assertEquals(agePage.getPageUrl(), driver.getCurrentUrl());
    }
    @Given("^I am on people with jobs page using PO$")
    public void iAmOnPeopleWithJobsPage() throws Throwable {
        // URL берём из Page Object, как и для agePage
        driver.get(peopleWithJobsPage.getPageUrl());
    }

    @Then("^I see original people list using PO$")
    public void iSeeOriginalPeopleList() throws Throwable {
        peopleWithJobsPage.checkOriginalListPresent();
    }

    @When("^I add a new person with name \"([^\"]*)\" and job \"([^\"]*)\" using PO$")
    public void iAddNewPersonWithNameAndJob(String name, String job) throws Throwable {
        peopleWithJobsPage.clickAddPerson();
        peopleWithJobsPage.fillPersonName(name);
        peopleWithJobsPage.fillPersonJob(job);
        peopleWithJobsPage.savePerson();
    }

    @When("^I edit person with name \"([^\"]*)\" changing name to \"([^\"]*)\" and job to \"([^\"]*)\" using PO$")
    public void iEditPersonChangingNameAndJob(String oldName, String newName, String newJob) throws Throwable {
        peopleWithJobsPage.clickEditForPerson(oldName);
        peopleWithJobsPage.fillPersonName(newName);
        peopleWithJobsPage.fillPersonJob(newJob);
        peopleWithJobsPage.savePerson();

    }

    @When("^I remove person with name \"([^\"]*)\" using PO$")
    public void iRemovePersonWithName(String name) throws Throwable {
        peopleWithJobsPage.clickRemoveForPerson(name);
    }

    @When("^I click reset list using PO$")
    public void iClickResetListUsingPO() throws Throwable {
        peopleWithJobsPage.clickResetList();
    }

    @Then("^I see person with name \"([^\"]*)\" and job \"([^\"]*)\" in list using PO$")
    public void iSeePersonWithNameAndJobInList(String name, String job) throws Throwable {
        assertTrue(
                "Expected to see person \"" + name + "\" with job \"" + job + "\" in the list",
                peopleWithJobsPage.isPersonPresent(name, job)
        );
    }

    @Then("^I do not see person with name \"([^\"]*)\" in list using PO$")
    public void iDoNotSeePersonWithNameInList(String name) throws Throwable {
        assertFalse(
                "Expected not to see person \"" + name + "\" in the list",
                peopleWithJobsPage.isPersonPresent(name)
        );
    }

    @Then("^I see that list is reset to original state using PO$")
    public void iSeeThatListIsResetToOriginalStateUsingPO() throws Throwable {
        peopleWithJobsPage.checkOriginalListPresent();
    }
}
