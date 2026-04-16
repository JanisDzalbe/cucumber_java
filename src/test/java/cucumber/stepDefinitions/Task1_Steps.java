package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1_Steps {
    private WebDriver driver;

    public Task1_Steps() {
        this.driver = Hooks.driver;
    }

    @Given("I am on the enter a number page")
    public void iAmOnTheEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("I enter {string} into the number field")
    public void iEnterIntoTheNumberField(String input) {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.clear();
        numberInput.sendKeys(input);
    }

    @When("I click the Submit button")
    public void iClickTheSubmitButton() {
        driver.findElement(By.cssSelector("button.w3-btn")).click();
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedError) {
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        assertEquals(expectedError, errorElement.getText());
    }

    @Then("I should see the result {string}")
    public void iShouldSeeTheResult(String expectedResult) {
        org.openqa.selenium.Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertEquals(expectedResult, alertText);
        alert.accept();
    }
}
