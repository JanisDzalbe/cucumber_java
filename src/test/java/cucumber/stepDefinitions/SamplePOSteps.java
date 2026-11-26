package cucumber.stepDefinitions;

import cucumber.pages_sample.AgePage;
import cucumber.pages_sample.AgeSubmittedPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Step definitions using Page Object classes ({@link AgePage} and {@link AgeSubmittedPage})
 * initialized via Selenium's {@link PageFactory}.
 */
public class SamplePOSteps {
    private WebDriver driver;
    static AgePage agePage;
    static AgeSubmittedPage ageSubmittedPage;

    /**
     * Binds the shared WebDriver instance from Hooks and initializes Page Objects.
     */
    public SamplePOSteps() {
        this.driver = Hooks.driver;
        agePage = PageFactory.initElements(Hooks.driver, AgePage.class);
        ageSubmittedPage = PageFactory.initElements(Hooks.driver, AgeSubmittedPage.class);
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

    /**
     * Verifies that the expected validation error message is displayed on the AgePage.
     *
     * @param errorMessage the expected error text captured from the Gherkin step
     */
    @Then("^I see error: \"([^\"]*)\" using PO$")
    public void iSeeError(String errorMessage) {
        agePage.checkErrorMessage(errorMessage);
    }

    /**
     * Verifies that the browser remained on the AgePage URL and did not navigate away.
     */
    @And("^I remain in age page using PO$")
    public void iRemainInAgePage() {
        assertEquals(agePage.getPageUrl(), driver.getCurrentUrl());
    }
}