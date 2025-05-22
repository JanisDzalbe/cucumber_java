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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {

        this.driver = Hooks.driver;
    }

    @Given("^I am on number page$")
    public void iAmOnNumberPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }
    @And("^I should see number page header$")
    public void iShouldSeeNumberPageHeader() throws Throwable {
        assertEquals("Enter a number",
                driver.findElement(By.cssSelector("h2")).getText());
    }

    @And("^I enter \"([^\"]*)\" in the field$")
    public void iEnterTextInTheField(String text) throws Throwable {
        driver.findElement(By.id("numb")).sendKeys(text);
    }

    @And("^I click the result$")
    public void iClickTheResult() {

    }

    @And("^I click submit button$")
    public void iClickSubmitButton() {
        driver.findElement(By.className("w3-btn")).click();
    }

    @Then("^Error message is: \"([^\"]*)\"$")
    public void errorMessageIs(String message) throws Throwable {
        Thread.sleep(2000);
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());


    }
    @Then("^Message is: \"([^\"]*)\"$")
    public void messageIs(String message) throws Throwable {
        Alert alert = driver.switchTo().alert();
        String result = alert.getText();
        assertEquals(message, result);
    }


}
