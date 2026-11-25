package cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Steps {
    WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("I open the Enter a Number page")
    public void iOpenTheEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
        driver.manage().window().maximize();
    }

    @When("I enter {string} in the number field")
    public void iEnterValueInTheNumberField(String inputNumber) {


        WebElement numberField = driver.findElement(By.id("numb"));

        numberField.clear();
        numberField.sendKeys(inputNumber);
    }

    @When("I click the Submit button")
    public void iClickTheSubmitButton() {

        driver.findElement(By.tagName("button")).click();
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedError) {

        String actualError = driver.findElement(
                By.id("ch1_error")).getText();

        assertEquals(expectedError, actualError);
    }

    @Then("I should see the square root alert {string}")
    public void iShouldSeeTheSquareRootAlert(String expectedResult) {

        Alert alert = driver.switchTo().alert();

        assertEquals(expectedResult, alert.getText());
    }

    @Then("I accept the square root alert")
    public void iAcceptTheSquareRootAlert() {

        driver.switchTo().alert().accept();
    }

    @Then("I should see no error message")
    public void iShouldSeeNoErrorMessage() {

        assertEquals("",
                driver.findElement(By.id("ch1_error")).getText());
    }

}
