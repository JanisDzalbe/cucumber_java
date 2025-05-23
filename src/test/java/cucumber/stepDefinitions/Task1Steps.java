package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Steps {

    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("I am on the enter a number page")
    public void iAmOnTheEnterANumberPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("I enter number: {string}")
    public void iEnterNumber(String number) {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.click();
        numberInput.clear();
        numberInput.sendKeys(number);
    }

    @And("I click submit")
    public void iClickSubmit() {
        driver.findElement(By.cssSelector("button.w3-orange")).click();
    }

    @Then("I receive error: {string}")
    public void iReceiveError(String error) {
        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("I get the correct square root of number: {string}")
    public void myAnswerIsCorrect(String number) {
        int numb = Integer.valueOf(number);
        double correctSquareRoot = Math.sqrt(numb);

        assertTrue(driver.switchTo().alert().getText().contains(String.format(Locale.US,"%.2f", correctSquareRoot)));
    }

}
