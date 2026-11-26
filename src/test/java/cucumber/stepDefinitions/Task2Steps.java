package cucumber.stepDefinitions;

import cucumber.pages_sample.PersonPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

    public class Task2Steps {
        private WebDriver driver;

        private PersonPage personPage;

        public Task2Steps()
        {
            this.driver = Hooks.driver;
            this.personPage = new PersonPage(this.driver);
        }

        @Given("I am on list of people page")
        public void iAmOnListOfPeoplePage()
        {
            driver.get(personPage.getPageUrl());
        }

        @When("I click add person button")
        public void iClickAddPersonButton()
        {
            personPage.clickAddPerson();
        }

        @And("I enter person name {string} and job {string}")
        public void iEnterPersonNameAndJob(String name, String job)
        {
            personPage.enterNameAndJob(name, job);
        }

        @And("I click add button")
        public void iClickAddButton()
        {
            personPage.clickModalSaveButton();
        }

        @When("I click edit button for person {string}")
        public void iClickEditButtonForPerson(String name)
        {
            personPage.clickEditForPerson(name);
        }

        @And("I update person name to {string} and job to {string}")
        public void iUpdatePersonNameToAndJobTo(String name, String job)
        {
            personPage.enterNameAndJob(name, job);
        }

        @And("I click edit save button")
        public void iClickEditSaveButton()
        {
            personPage.clickModalSaveButton();
        }

        @When("I click remove button for person {string}")
        public void iClickRemoveButtonForPerson(String name)
        {
            personPage.clickRemoveForPerson(name);
        }

        @And("I click reset list button")
        public void iClickResetListButton()
        {
            personPage.clickResetList();
        }

        @Then("I can see person {string} with job {string} in the list")
        public void iCanSeePersonWithJobInTheList(String name, String job)
        {
            personPage.checkPersonWithJobExists(name, job);
        }

        @Then("I can see person {string} in the list")
        public void iCanSeePersonInTheList(String name)

        {
            personPage.checkPersonExists(name);
        }

        @Then("I cannot see person {string} in the list")
        public void iCannotSeePersonInTheList(String name)
        {
            personPage.checkPersonNotExists(name);
        }
    }
