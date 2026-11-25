package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.Alert;

public class Task1Steps {
    private WebDriver driver;

    @Given("^I am on the Enter a Number page$")
    public void iAmOnTheEnterANumberPage() {
    this.driver = Hooks.driver;
    this.driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
}

    @When("^I enter \"([^\"]*)\" in the number field$")
    public void iEnterValueInTheNumberField(String input) {
    WebElement numberField = driver.findElement(By.id("numb"));
    numberField.clear();
    numberField.sendKeys(input);
}

    @And("^I click Submit on the number page$")
    public void iClickSubmitOnTheNumberPage() {
    driver.findElement(By.cssSelector("button[onclick='numberValidation()']")).click();
}

    @Then("^I see the number error \"([^\"]*)\"$")
    public void iSeeTheNumberError(String expectedError) {
    assertEquals(expectedError, driver.findElement(By.id("ch1_error")).getText());
}

    @Then("^I see an alert with the text \"([^\"]*)\"$")
    public void iSeeAnAlertWithTheText(String expectedText) {
    Alert alert = driver.switchTo().alert();
    String actualText = alert.getText();
    alert.accept();

    assertEquals(expectedText, actualText);
}
}
