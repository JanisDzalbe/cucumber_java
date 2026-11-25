package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TaskSteps1 {

    private WebDriver driver;

    public TaskSteps1() {
        this.driver = Hooks.driver;
    }

    @Given("^I navigate to enter a number page$")
    public void iNavigateToEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in number field$")
    public void iEnterInNumberField(String input) {
        WebElement numberField = driver.findElement(By.id("numb"));
        numberField.clear();
        numberField.sendKeys(input);
    }


    @When("^I enter valid number (\\d*) in number field$")
    public void iEnterValidNumberInNumberField(String number) {
        WebElement numberField = driver.findElement(By.id("numb"));
        numberField.clear();
        numberField.sendKeys(number);
    }

    @And("^I click submit number button$")
    public void iClickSubmitNumberButton() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

    }

    @Then("^I should see error message \"([^\"]*)\"$")
    public void iShouldSeeErrorMessage(String expectedError) {
        String actualError = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expectedError, actualError);
    }


    @Then("^I should see square root alert for number \"([^\"]*)\"$")
    public void iShouldSeeSquareRootAlertForNumber(String expectedAlertText) {
        Alert alert = driver.switchTo().alert();
        String actualAlertText = alert.getText();
        alert.accept();

        assertEquals(expectedAlertText, actualAlertText);
    }
}
