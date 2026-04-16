package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1 {
    private WebDriver driver;
    Alert alert;

    public Task1() {
        this.driver = Hooks.driver;
    }

    @Given("^I (?:am on|open) the enter number page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number: \"([^\"]*)\"$")
    public void iEnterNumber(String number) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(String.valueOf(number));
    }

    @And("^I press Submit button$")
    public void iPressSubmitButton() {
        driver.findElement(By.xpath("//button[@type='button' and text()='Submit']")).click();
    }

    @Then("^I see an Error: \"([^\"]*)\"$")
    public void iSeeAnErrorMessage(String errorMessage) {
        assertEquals(errorMessage, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see an alert and check result$")
    public void iSeeAnAlertAndCheckResult() {
        alert = driver.switchTo().alert();
        String expectedResult = "Square root of 64 is 8.00";

        assertTrue(alert.getText().contains(expectedResult));
    }

    @And("^I accept alert$")
    public void iAcceptAlert() {
        alert.accept();
    }

    @And("I see that's no errors displayed$")
    public void iSeeNoErrors() {
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }
}
