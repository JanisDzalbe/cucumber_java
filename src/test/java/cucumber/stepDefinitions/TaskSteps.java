package cucumber.stepDefinitions;

import cucumber.pages_sample.PeopleWJobsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {
    private WebDriver driver;
    private PeopleWJobsPage peopleWJobsPage;

    public TaskSteps() {
        this.driver = Hooks.driver;
        peopleWJobsPage = PageFactory.initElements(this.driver, PeopleWJobsPage.class);
    }

    @Given("^I am on enter number page$")
    public void iAmOnEnterNumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter a number: ([^\"]*)$")
    public void iEnterANumber(String number) {
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys(number);
    }

    @And("^I click submit$")
    public void iClickSubmit() {
        WebElement submitButton = driver.findElement(By.className("w3-btn"));
        submitButton.click();
    }

    @Then("^I see that the error message matches \"([^\"]*)\"$")
    public void iSeeThatTheErrorMessageMatches(String errorMessage) {
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        assertEquals(errorMessage, errorText.getText());
    }

    @Then("^I see that the alert shows the correct sqrt for (\\d+)$")
    public void iSeeThatTheAlertShowsTheCorrectSqrtFor(int number) {
        Alert alert = driver.switchTo().alert();
        assertEquals(String.format("Square root of %d is %.2f", number, Math.sqrt(number)), alert.getText());
    }

    @Given("I am on people with jobs page")
    public void iAmOnPeopleWithJobsPage() {
        driver.get(PeopleWJobsPage.getPageURL());
    }

    @Given("I check the list matches the initial list")
    public void iCheckTheListMatchesTheInitialList() {
        assertTrue(peopleWJobsPage.peopleListMatchesExpected(peopleWJobsPage.getInitialPersonList()));
    }

    @Given("I check the list does not match the initial list")
    public void iCheckTheListDoesNotMatchTheInitialList() {
        assertFalse(peopleWJobsPage.peopleListMatchesExpected(peopleWJobsPage.getInitialPersonList()));
    }

    @When("I add a new person")
    public void iAddANewPerson(Map<String, String> personMap) {
        peopleWJobsPage.addPerson(personMap.get("name"), personMap.get("jobTitle"));
    }

    @And("I reset the entire list")
    public void iResetTheEntireList() {
        peopleWJobsPage.resetList();
    }

    @And("^I check that (\\w+) the (\\w+) does exist in the list$")
    public void iCheckThatPersonDoesExistInTheList(String name, String jobTitle) {
        assertTrue(peopleWJobsPage.peopleListContainsPerson(name, jobTitle));
    }

    @When("I add a prefix to first person's job title")
    public void iAddAPrefixToFirstPersonSJobTitle(Map<String, String> prefixMap) {
        peopleWJobsPage.addPrefixToFirstPersonsJobTitle(prefixMap.get("jobPrefix"));
    }

    @Then("^I check that the first person's job title starts with (\\w+)$")
    public void iCheckThatTheFirstPersonSJobTitleStartsWithSenior(String prefix) {
        assertTrue(peopleWJobsPage.getCurrentPersonList().getFirst().jobTitle().startsWith(prefix));
    }

    @When("I remove the first person")
    public void iRemoveTheFirstPerson() {
        peopleWJobsPage.removeFirstPerson();
    }
}
