package cucumber.stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {

    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    // =========================
    // NAVIGATION
    // =========================
    @Given("I am on number page")
    public void iAmOnNumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    // =========================
    // ACTIONS
    // =========================
    @When("I enter number {string}")
    public void iEnterNumber(String value) {
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        input.sendKeys(value);
    }

    @And("I click submit number")
    public void iClickSubmitNumber() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    // =========================
    // VALIDATIONS
    // =========================
    @Then("I should see error message {string}")
    public void iShouldSeeErrorMessage(String expectedMessage) {
        String actual = driver.findElement(By.id("ch1_error")).getText().trim();
        assertEquals(expectedMessage, actual);
    }

    @Then("I should see alert with text {string}")
    public void iShouldSeeAlertWithText(String expectedText) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String actualText = alert.getText();
        assertEquals(expectedText, actualText);

        alert.accept(); // MUST close alert
    }

    @And("I see no error message")
    public void iSeeNoErrorMessage() {
        String errorText = driver.findElement(By.id("ch1_error")).getText().trim();
        assertTrue(errorText.isEmpty(), "Expected no error message, but got: " + errorText);
    }
}