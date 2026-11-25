package cucumber.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on Enter a number Page$")
    public void iAmOnEnterNumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in input field$")
    public void iEnterInInputField(String value) throws  Throwable {
        driver.findElement(By.className("w3-input")).clear();
        driver.findElement(By.className("w3-input")).sendKeys(value);
    }

    @And("^I click submit button$")
    public void iClickSubmitButton() throws Throwable {
        assertTrue(driver.findElement(By.className("w3-btn")).isEnabled());
        driver.findElement(By.className("w3-btn")).click();
    }

    @Then("^I see error \"([^\"]*)\"$")
    public void iSeeError(String expectedError) throws Throwable {
        assertEquals(expectedError,driver.findElement(By.id("ch1_error")).getText());
    }


    @When("^I enter value: (\\d+)$")
    public void iEnterValue(int number) throws  Throwable{
        driver.findElement(By.className("w3-input")).clear();
        driver.findElement(By.className("w3-input")).sendKeys(String.valueOf(number));
    }


    @Then("^I see popUp message: \"([^\"]*)\"$")
    public void iSeePopUpMessage(String expectedMessage) throws Throwable {
       Alert alert = driver.switchTo().alert();
       String actualMessage = alert.getText();
       alert.accept();
       assertEquals(expectedMessage,actualMessage,"actual result is not equal as expected ");
    }
}