package cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the Enter a number page$")
            public void IamOnEnterNumPage(){
    driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");}

    @When("^I enter number \"([^\"]*)\" into the number field$")
    public void enterWrongNumberInput(String number) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @When("^I click the submit button$")
    public void clickOnSubmitButton(){
        driver.findElement(By.className("w3-btn")).click();
    }

    @Then("^I see the error message \"([^\"]*)\"$")
    public void errorMessageDisplayed(String message){
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then ("^I see the alert \"([^\"]*)\" message$")
    public void iSeeTheAlertMessage(String message){
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        assertEquals(message, alertText);
        //no error is shown
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }

}