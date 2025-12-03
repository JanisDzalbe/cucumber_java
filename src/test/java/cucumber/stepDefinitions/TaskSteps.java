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

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the numbers page$")
    public void iAmOnTheNumbersPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter a number: \"([^\"]*)\"$")
    public void iEnterANumber(String number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click submit button$")
    public void iClickSubmitButton() throws Throwable {
        driver.findElement(By.className("w3-orange")).click();
    }

    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see message \"([^\"]*)\"$")
    public  void iSeeMessage(String answer) throws Throwable {
        Alert answerAlert =  driver.switchTo().alert();
        assertEquals(answer, answerAlert.getText());
        answerAlert.accept();
    }

    @And("^I do not see any error message$")
    public void iDoNotSeeAnyErrorMessage() throws Throwable {
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

}
