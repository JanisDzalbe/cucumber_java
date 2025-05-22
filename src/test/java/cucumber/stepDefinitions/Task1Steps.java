package cucumber.stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("I open the number input page")
    public void iOpenTheNumberInputPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" into the field$")
    public void iEnterIntoTheField(String input) throws Throwable {
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys(input);
    }

    @And("^I press Submit button$")
    public void iPressSubmitButton() throws Throwable {
        driver.findElement(By.className("w3-orange")).click();
    }

    @Then("^I should see error message \"([^\"]*)\"$")
    public void iShouldSeeErrorMessage(String expectedError) throws Throwable {
        WebElement errorField = driver.findElement(By.className("error"));
        String actualText = errorField.getText();
        assertEquals(expectedError, actualText);
    }

    @Then("^I should see alert with square root result \"([^\"]*)\"$")
    public void iShouldSeeAlertWithSquareRootResult(String expectedMessage) throws Throwable {
        Alert alert = driver.switchTo().alert();
        String actual = alert.getText();
        assertEquals(expectedMessage, actual);
        alert.accept();
        assertFalse(driver.findElement(By.className("error")).isDisplayed());
    }

    @Then("^No error should be displayed after closing the alert$")
    public void noErrorsShouldBeDisplayedAfterClosingTheAlert() throws Throwable {
        assertFalse(driver.findElement(By.className("error")).isDisplayed(), "Error message should not be visible after valid input.");
    }
}