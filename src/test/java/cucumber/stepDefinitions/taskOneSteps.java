package cucumber.stepDefinitions;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class taskOneSteps {
    private WebDriver driver;

    public taskOneSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the number entry page$")
    public void iAmOnTheNumberEntryPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" into the number field$")
    public void iEnterIntoNumberFields(String input) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys("input");
    }

    @When("^I enter number (\\d+) into the number field$")
    public void iEnterNumberIntoTheNumberFieeld(int input) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(String.valueOf(input));
    }


    @And("^I click the check button$")
    public void iClickTheCheckButton() throws Throwable {
        driver.findElement(By.cssSelector("button[onclick='numberValidation()']")).click();
    }

    @Then("^I should see error message: \"([^\"]*)\"$")
    public void iShouldSeeErrorMessage(String expMessage) throws Throwable {
        String actMessage = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expMessage, actMessage);
    }

    @Then("^I should see a success alert with the correct square root of (\\d+)")
    public void iShouldSeeASuccessAllertWithTheCorrectSquareRoot(int number) {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        double expectedRoot = Math.round(Math.sqrt(number) * 100.0) / 100.0;
        String expectedText = "Square root of " + number + " is " + expectedRoot;

        String actualAlertText = alert.getText();
        assertEquals(expectedText, actualAlertText);

        alert.accept();
    }





}
