package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I (?:am on|open) enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number: \"([^\"]*)\"$")
    public void iEnterNumber(String number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @When("^I click submit number$")
    public void iSubmitNumber() throws Throwable {
        driver.findElement(By.cssSelector("button.w3-btn.w3-orange.w3-margin")).click();
    }

    @Then("^I see number error: \"([^\"]*)\"$")
    public void iSeeNumberError(String error) throws Throwable {
        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I get popup: \"([^\"]*)\"$")
    public void iGetPopup(String message) throws Throwable {
        Alert alert = driver.switchTo().alert();
        assertEquals(message, alert.getText());
    }

    @Then("^I accept popup$")
    public void iAcceptPopup() throws Throwable {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Then("^I see no error message$")
    public void iSeeNoErrorMessage() throws Throwable {
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }

}
