package cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class FeedbackStepDefinitions {

    // Use the same driver/wait from your Hooks class
    // Adjust these based on how your project shares the driver
    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL = "https://janisdzalbe.github.io/example-site";

    // --- Navigation ---

    @Given("I am on provide feedback page")
    public void iAmOnProvideFeedbackPage() {
        driver.get(BASE_URL + "/tasks/provide_feedback");
    }

    @Given("I am on feedback page")
    public void iAmOnFeedbackPage() {
        driver.get(BASE_URL + "/tasks/provide_feedback");
    }

    // --- Language checkboxes (type="checkbox", name="language") ---

    @When("I select language: {string}")
    public void iSelectLanguage(String language) {
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[type='checkbox'][name='language'][value='" + language + "']")
                )
        );
        checkbox.click();
    }

    @When("I select feedback languages")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String language : languages) {
            WebElement checkbox = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("input[type='checkbox'][name='language'][value='" + language + "']")
                    )
            );
            checkbox.click();
        }
    }

    // --- Genre radio buttons (type="radio") ---

    @When("I select genre: {string}")
    public void iSelectGenre(String genre) {
        WebElement radio = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[type='radio'][value='" + genre + "']")
                )
        );
        radio.click();
    }

    // --- Rating dropdown ---

    @When("I select rating: {string}")
    public void iSelectRating(String rating) {
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("rating"))
        );
        new Select(dropdown).selectByVisibleText(rating);
    }

    // --- Comment ---

    @When("I enter comment: {string}")
    public void iEnterComment(String comment) {
        WebElement commentField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("comment"))
        );
        commentField.clear();
        commentField.sendKeys(comment);
    }

    // --- Send button ---

    @And("I click Send button")
    public void iClickSendButton() {
        WebElement sendButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[type='submit'], input[type='submit']")
                )
        );
        sendButton.click();
    }

    @And("I click send feedback")
    public void iClickSendFeedback() {
        iClickSendButton();
    }

    // --- 2-column map input (for Part 4) ---

    @When("I fill in feedback form:")
    public void iFillInFeedbackForm(Map<String, String> formData) {
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            switch (field) {
                case "name":
                    WebElement nameField = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(By.name("name"))
                    );
                    nameField.clear();
                    nameField.sendKeys(value);
                    break;
                case "age":
                    WebElement ageField = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(By.name("age"))
                    );
                    ageField.clear();
                    ageField.sendKeys(value);
                    break;
                case "genre":
                    iSelectGenre(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + field);
            }
        }
    }

    // --- Verification steps ---

    @Then("I see submitted language: {string}")
    public void iSeeSubmittedLanguage(String expected) {
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-language"))
        );
        Assert.assertEquals(expected, result.getText());
    }

    @Then("I see submitted genre: {string}")
    public void iSeeSubmittedGenre(String expected) {
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-genre"))
        );
        Assert.assertEquals(expected, result.getText());
    }

    @Then("I see submitted rating: {string}")
    public void iSeeSubmittedRating(String expected) {
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-rating"))
        );
        Assert.assertEquals(expected, result.getText());
    }

    @Then("I see submitted comment: {string}")
    public void iSeeSubmittedComment(String expected) {
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-comment"))
        );
        Assert.assertEquals(expected, result.getText());
    }

    @Then("I can see languages {string} in feedback check")
    public void iCanSeeLanguagesInFeedbackCheck(String expected) {
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-language"))
        );
        Assert.assertEquals(expected, result.getText());
    }

    @Then("I verify submitted feedback:")
    public void iVerifySubmittedFeedback(Map<String, String> expectedData) {
        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            WebElement result = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("submitted-" + entry.getKey())
                    )
            );
            Assert.assertEquals(
                    "Mismatch for '" + entry.getKey() + "'",
                    entry.getValue(), result.getText()
            );
        }
    }
}