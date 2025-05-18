package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Locale;
import static org.junit.Assert.assertEquals;

public class Task1Steps {
    private WebDriver driver;

    private String enteredNumber;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the Enter a number page$")
    public void iAmOnTheEnterNumberPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter number: \"([^\"]*)\"$")
    public void iEnterNumber(String number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
        enteredNumber = number;
    }

    @And("^I click submit button")
    public void iClickSubmitButton() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see popup alert for entered number$")
    public void iSeePopupAlertForEnteredNumber() throws Throwable {
        int number = Integer.parseInt(enteredNumber);
        double root = Math.sqrt(number);
        String expectedText = String.format(Locale.US, "Square root of %d is %.2f", number, root);

        String actualAlert = driver.switchTo().alert().getText();
        assertEquals(expectedText, actualAlert);
        driver.switchTo().alert().accept();
    }
}
