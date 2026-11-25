package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }
    // ---------------------
// Task 1 for incorrect value
    // ---------------------

    @Given("^I am on the page$")
    public void iAmOnThePage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");

    }

    @When("^I enter value: \"([^\"]*)\"$")
    public void iEnterValue(String value) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);

    }

    @And("^I click submit button$")
    public void iClickSubmitButton() {
        driver.findElement(By.xpath("//button[@onclick='numberValidation()']")).click();
    }

    @Then("^I see error: \"([^\"]*)\" on enter a number page$")
    public void iSeeError(String error) {
        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
    }

    // ---------------------
// Task 1 for correct number
    // ---------------------

    @Then("^I see alert with text: \"([^\"]*)\"$")
    public void iSeeAlertWithText(String alertText) {
        Alert alert = driver.switchTo().alert();
        assertEquals(alertText, alert.getText());
        alert.accept();

    }
}

