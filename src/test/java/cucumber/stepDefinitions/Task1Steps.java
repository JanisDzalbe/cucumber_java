package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("I am on enter a number page")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
        // menu is loaded separately and moves the form down, so wait until it appears
        driver.findElement(By.cssSelector("#header > *"));
    }

    @When("I submit value {string}")
    public void iSubmitValue(String value) {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.clear();
        numberInput.sendKeys(value);
        driver.findElement(By.className("w3-orange")).click();
    }

    @Then("I see number error {string}")
    public void iSeeNumberError(String error) {
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        assertTrue(errorText.isDisplayed());
        assertEquals(error, errorText.getText());
    }

    @Then("I see alert {string} and accept it")
    public void iSeeAlertAndAcceptIt(String text) {
        Alert alert = driver.switchTo().alert();
        assertEquals(text, alert.getText());
        alert.accept();
    }

    @And("I do not see number error")
    public void iDoNotSeeNumberError() {
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }
}
