package cucumber.stepDefinitions;

import com.google.common.base.Equivalence;
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

import static org.junit.Assert.*;


public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on Enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in number field$")
    public void iEnterInNumberField(String input) {
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys(input);
    }

    @When("^I press Submit button$")
    public void iPressSubmitButton() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see the error: \"([^\"]*)\"$")
    public void iSeeTheError(String message) {
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I confirm the alert shows the square root for \"([^\"]*)\"$")
    public void iConfirmTheAlertShowsTheSquareRootForInput(String input) {
        Alert alert = driver.switchTo().alert();
        double sqrt = Math.sqrt(Double.parseDouble(input));
        System.out.printf("Calculated: %s, Rounded: %.2f", sqrt, sqrt);

        String alertAssertion = String.format("Square root of %s is %.2f", input, sqrt);
        assertEquals(alertAssertion, alert.getText());
    }

    @Then("^I check no error message is shown after closing the alert$")
    public void iCheckNoErrorMessageIsShownAfterClosingTheAlert() {
        driver.switchTo().alert().accept();
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }
}
