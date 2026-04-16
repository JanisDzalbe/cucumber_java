package cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.util.Map;

public class FeedbackFormStepDefinitions {

    // NO @Given("I am on age page") here — it's already in AgeStepDefinitions
    // NO @When("I enter values:") here — it's already in AgeStepDefinitions
    // NO @Then("I see message:") here — it's already in AgeStepDefinitions
    // NO @And("I click submit age") here — it's already in AgeStepDefinitions

    @Given("I am on feedback page")
    public void iAmOnFeedbackPage() {
        // your implementation
    }

    @When("I fill in feedback form:")
    public void iFillInFeedbackForm(Map<String, String> formData) {
        // your implementation
    }

    @And("I click send feedback")
    public void iClickSendFeedback() {
        // your implementation
    }

    @Then("I verify submitted feedback:")
    public void iVerifySubmittedFeedback(Map<String, String> expectedData) {
        // your implementation
    }
}