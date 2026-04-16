package cucumber.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;
import java.util.Map;

public class FeedbackFormStepDefinitions {

    private static final String BASE_URL = "https://janisdzalbe.github.io/example-site";
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // --- Background ---

    @Given("I am on age page")
    public void iAmOnAgePage() {
        driver.get(BASE_URL + "/examples/age");
    }

    // --- Existing age page steps ---

    @When("I enter values:")
    public void iEnterValues(Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            WebElement input = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id(entry.getKey()))
            );
            input.clear();
            input.sendKeys(entry.getValue());
        }
    }

    @And("I click submit age")
    public void iClickSubmitAge() {
        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("submit"))
        );
        submitButton.click();
    }

    @Then("I see message: {string}")
    public void iSeeMessage(String expectedMessage) {
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );
        Assert.assertEquals(expectedMessage, message.getText());
    }

    // --- Feedback page steps ---

    @Given("I am on feedback page")
    public void iAmOnFeedbackPage() {
        driver.get(BASE_URL + "/tasks/provide_feedback");
    }

    @When("I fill in feedback form:")
    public void iFillInFeedbackForm(Map<String, String> formData) {
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            switch (field) {
                case "name":
                    enterTextField("name", value);
                    break;
                case "age":
                    enterTextField("age", value);
                    break;
                case "genre":
                    selectRadioButton("genre", value);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unknown feedback form field: " + field
                    );
            }
        }
    }

    @And("I click send feedback")
    public void iClickSendFeedback() {
        WebElement sendButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Send'] | //input[@value='Send']")
                )
        );
        sendButton.click();
    }

    @Then("I verify submitted feedback:")
    public void iVerifySubmittedFeedback(Map<String, String> expectedData) {
        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            String field = entry.getKey();
            String expectedValue = entry.getValue();

            WebElement resultElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("submitted-" + field)
                    )
            );
            Assert.assertEquals(
                    "Mismatch for field '" + field + "'",
                    expectedValue,
                    resultElement.getText()
            );
        }
    }

    // --- Helper methods ---

    private void enterTextField(String fieldId, String value) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(fieldId))
        );
        field.clear();
        field.sendKeys(value);
    }

    private void selectRadioButton(String groupName, String value) {
        WebElement radio = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//input[@type='radio' and @name='" + groupName
                                        + "' and @value='" + value + "']"
                                        + " | //label[contains(text(),'" + value
                                        + "')]/input[@type='radio']"
                                        + " | //label[contains(text(),'" + value
                                        + "')]/preceding-sibling::input[@type='radio']"
                                        + " | //label[contains(text(),'" + value
                                        + "')]/following-sibling::input[@type='radio']"
                        )
                )
        );
        radio.click();
    }
}