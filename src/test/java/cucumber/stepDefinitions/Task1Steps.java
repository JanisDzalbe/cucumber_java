package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on Enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter a value: \"([^\"]*)\"$")
    public void iEnterAValue(String text) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(text);
    }

    @And("^I click submit$")
    public void iClickSubmit() throws Throwable {
        driver.findElement(By.className("w3-orange")).click();
    }

    @Then("^I see no error message$")
    public void iSeeNoError() throws Throwable {
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        assertEquals("",errorText.getText());
    }

    @Then("^I see a window with result \"([^\"]*)\"$")
    public void iSeeAlertWithMessage(String expectedMessage) {
        Alert alert = driver.switchTo().alert();

        double input = 88;
        double sqrt = Math.sqrt(input);
        String expected = String.format("Square root of %.0f is %.2f", input, sqrt);

        assertEquals(expected, alert.getText());
        alert.accept();
    }

    @Then("^I get an error message: \"([^\"]*)\"$")
    public void iGetAnErrorMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());
    }
    }