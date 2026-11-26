
package cucumber.stepDefinitions;

import cucumber.pages_sample.PeoplePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task2Steps {

    private PeoplePage peoplePage;

    // OPEN PEOPLE PAGE

    @Given("^I am on people with jobs page$")
    public void iAmOnPeopleWithJobsPage() {

        peoplePage = new PeoplePage(Hooks.driver);

        peoplePage.openPage();
    }

    // ADD PERSON

    @When("^I add person \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iAddPersonWithJob(String name, String job) {

        peoplePage.addPerson(name, job);
    }

    // EDIT PERSON

    @When("^I edit person \"([^\"]*)\" to name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iEditPerson(String currentName,
                            String newName,
                            String newJob) {

        peoplePage.editPerson(currentName, newName, newJob);
    }

    // REMOVE PERSON

    @When("^I remove person \"([^\"]*)\"$")
    public void iRemovePerson(String name) {

        peoplePage.removePerson(name);
    }

    // VERIFY PERSON EXISTS

    @Then("^I should see person \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iShouldSeePersonWithJob(String name, String job) {

        assertTrue(
                peoplePage.isPersonPresent(name, job),
                "Person was not added or updated correctly: "
                        + name + " - " + job
        );
    }

    // VERIFY PERSON DOES NOT EXIST

    @Then("^person \"([^\"]*)\" should not be in the list$")
    public void personShouldNotBeInList(String name) {

        assertFalse(
                peoplePage.isPersonPresent(name),
                "Person should have been removed: " + name
        );
    }

    // RESET LIST

    @When("^I reset the people list$")
    public void iResetThePeopleList() {

        peoplePage.resetList();
    }

    // VERIFY ORIGINAL LIST

    @Then("^the original people list should be restored$")
    public void originalPeopleListShouldBeRestored() {

        assertTrue(
                peoplePage.isOriginalListRestored(),
                "The original people list was not restored correctly"
        );
    }
}
