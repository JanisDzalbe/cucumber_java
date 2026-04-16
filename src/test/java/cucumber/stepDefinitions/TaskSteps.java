package cucumber.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
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
}
