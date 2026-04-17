package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cucumber.pages_sample.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SamplePOSteps {
    private WebDriver driver;
    static AgePage agePage;
    static AgeSubmittedPage ageSubmittedPage;

    public SamplePOSteps() {
        this.driver = Hooks.driver;
        agePage = PageFactory.initElements(Hooks.driver, AgePage.class);
        ageSubmittedPage = PageFactory.initElements(Hooks.driver, AgeSubmittedPage.class);
    }

    @Given("^I (?:am on|open) age page using PO$")
    public void iAmOnAgePage() {
        driver.get(agePage.getPageUrl());
    }

    @When("^I enter name: \"([^\"]*)\" using PO$")
    public void iEnterName(String name) {
        agePage.enterName(name);
    }

    @And("^I enter age: (\\d+) using PO$")
    public void iEnterAge(int age) {
        agePage.enterAge(age);
    }

    @And("^I click submit age using PO$")
    public void iClickSubmitAge() {
        agePage.clickSubmit();
    }

    @Then("^I see message: \"(.*)\" using PO$")
    public void iSeeMessage(String message) {
        ageSubmittedPage.checkMessageText(message);
    }

    @When("^I enter values using PO:$")
    public void iEnterValues(Map<String, String> valuesToEnter) {
        agePage.enterName(valuesToEnter.get("name"));
        agePage.enterAge(valuesToEnter.get("age"));
    }

    @Then("^I see error: \"([^\"]*)\" using PO$")
    public void iSeeError(String errorMessage) {
        agePage.checkErrorMessage(errorMessage);
    }

    @And("^I remain in age page using PO$")
    public void iRemainInAgePage() {
        assertTrue(driver.getCurrentUrl().contains("/examples/age"));
    }
}