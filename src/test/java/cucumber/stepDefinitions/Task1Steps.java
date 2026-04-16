package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }
    @Given("^I am on the home pageser$")
    public void iAmOnTheHomePagerrr() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site");
    }

    @Given("I am on enter a number page")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("I enter incorrect {string}")
    public void iEnterIncorrect(String value) {
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    @And("I click submit button")
    public void iClickSubmitButton() {
        driver.findElement(By.className("w3-btn")).click();
    }

    @Then("I see error {string}")
    public void iSeeError(String message) {
        assertEquals(message,driver.findElement(By.id("ch1_error")).getText());
    }

    @When("I enter correct value {int}")
    public void iEnterCorrectValue(int arg0) {
        driver.findElement(By.id("numb")).sendKeys("64");

    }

    @Then("I see message: Square root of {int} is {double}")
    public void iSeeMessageSquareRootOfIs(int arg0, double arg2) {
        Alert alert = driver.switchTo().alert();
        double squareText = Math.sqrt(64);
        DecimalFormat df = new DecimalFormat("#.00");
        String expectedAlertText = "Square root of " + "64" + " is " + df.format(squareText);
        assertEquals(expectedAlertText, alert.getText());

    }
}
