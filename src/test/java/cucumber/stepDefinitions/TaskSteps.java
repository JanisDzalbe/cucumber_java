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

import static cucumber.stepDefinitions.Hooks.driver;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* TASK

# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

 */


public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    // Open enter a number page

    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() {

        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }
    // Enter text or number
    @When("^I enter \"([^\"]*)\"$")
    public void iEnterInput(String input) {

         /*
        In principle, the "clear" button can be left in, it doesn't affect speed or the underlying process,
        but it can clear any internal text that might appear in the search bar—essentially
        serving as a preemptive safeguard in the future. I think so...
         */
       // driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(input);
    }


    // Click Submit
    @And("^I submit the number$")
    public void iSubmitTheNumber() {

        driver.findElement(
                By.cssSelector("button[onclick='numberValidation()']")
        ).click();
    }

    // Verify error message
    @Then("^I see number validation error: \"([^\"]*)\"$")
    public void iSeeError(String expectedError) {

        String actualError = driver.findElement(
                By.id("ch1_error")
        ).getText();

        assertEquals(expectedError, actualError);
    }

    // Enter correct number
    @When("^I enter a correct number$")
    public void iEnterCorrectNumber() {
        /*
        In principle, the "clear" button can be left in, it doesn't affect speed or the underlying process,
        but it can clear any internal text that might appear in the search bar—essentially
        serving as a preemptive safeguard in the future. I think so...
         */
     // driver.findElement(By.id("numb")).clear();

        driver.findElement(By.id("numb")).sendKeys("75");
    }

    // Verify correct result

    @Then("^I see the correct result$")
    public void iSeeCorrectResult() {
        Alert alert = driver.switchTo().alert();
    }
}