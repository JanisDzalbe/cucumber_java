package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    //----------------------------------
    // Task 1
    //----------------------------------

    @Given("^I am on Enter a number page$")
    public void iAmOnTheEnterNumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter (.*) on Enter a number page")
    public void iEnterTestData(String testData) throws Throwable {

        if (testData.equals("CorrectData")) {
            testData = "75";
        }

        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(testData);
    }

    @When("^I click Submit button$")
    public void iClickSubmitButton() throws Throwable {
        driver.findElement(By.cssSelector("[type='button']")).click();
    }

    @Then("^I see \"([^\"]*)\"$")
    public void iSeeErrorMessage(String errorMessage) throws Throwable {
        assertEquals(errorMessage, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see an alert with correct calculation")
    public void iSeeCalculationResult() throws Throwable {

        int CorrectData = 75;
        double result = Math.round(Math.sqrt(CorrectData) * 100) / 100.0;

        Alert alert = driver.switchTo().alert();
        String expectedMessage = "Square root of " + CorrectData + " is " + result;

        assertEquals(expectedMessage, alert.getText());

        alert.accept();
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }

}
