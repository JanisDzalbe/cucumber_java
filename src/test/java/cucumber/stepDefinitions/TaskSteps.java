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

    // -------------------------------
    // Task 1
    // -------------------------------

    @Given("^I am on Enter a number page$")
    public void enterNumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number \"([^\"]*)\" on the page$")
    public void enterNumber(String number) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click Submit$")
    public void submitBtn() {
        driver.findElement(By.cssSelector("[type='button']")).click();
    }

    @Then("^I see error \"([^\"]*)\"$")
    public void iSeeErrorOnEnterNumberPage(String error) {
        assertEquals(error, driver.findElement(By.className("error")).getText());
    }

    @Then("^I see alert: \"([^\"]*)\"$")
    public void iSeeAlert(String alert) {
        assertEquals(alert, driver.switchTo().alert().getText());
    }

    @When("^I accept the alert$")
    public void iAcceptTheAlert() {
        driver.switchTo().alert().accept();
    }

    @Then("^I see no error message$")
    public void iSeeNoErrorMessage() {
        assertEquals("", driver.findElement(By.className("error")).getText());
    }

}



