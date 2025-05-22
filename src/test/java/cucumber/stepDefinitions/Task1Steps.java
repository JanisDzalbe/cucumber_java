package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on enter a number page$")
    public void iAmOnTheEnterANumberPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter a number: \"([^\"]*)\"$")
    public void iEnterANumber(String number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click submit number button$")
    public void iClickSubmitNumberButton() throws Throwable {
        driver.findElement(By.className("w3-btn")).click();
    }

    @Then("^I get error message: \"([^\"]*)\"$")
    public void iSeeNumberErrorMessage(String error) throws Throwable {
        assertEquals(error, driver.findElement(By.className("error")).getText());
    }

    @Then("^I see correct message and square root for number: (\\d+)$")
    public void iSeeCorrectMessageAndSquareRootForNumber(int number) throws Throwable {
        Double squareRoot = Math.sqrt(number);
        String expected = String.format("Square root of %d is %.2f", number, squareRoot);

        Alert alert = driver.switchTo().alert();
        assertEquals(expected, alert.getText());
        alert.accept();
    }

    @And("^I see no error messages$")
    public void iSeeNoErrorMessages() throws Throwable {
        assertEquals("", driver.findElement(By.className("error")).getText());
    }
}
