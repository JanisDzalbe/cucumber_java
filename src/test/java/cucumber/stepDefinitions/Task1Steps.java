package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {this.driver = Hooks.driver;}

    @Given("^I am on the enter number page$")
    public void iAmOnTheFeedbackPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter a number: \"([^\"]*)\"$")
    public void iEnterNumber(String number) {
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click submit$")
    public void iClickSubmit() {
        driver.findElement(By.xpath("//*[@type='button']")).click();
    }
    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) {
        assertEquals(message, driver.findElement(By.className("error")).getText());
    }

    @Then("^I check (\\d+) square root alert message$")
    public void iCheckSquareRootAlertMessage(int number) {
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Square root of " + number + " is " + String.format(Locale.US, "%.2f", Math.sqrt(number)),
                alert.getText());
        alert.accept();
    }

    @And("^I do not see error message$")
    public void iDoNotSeeErrorMessage() {
        assertFalse(driver.findElement(By.className("error")).isDisplayed());
    }
}

