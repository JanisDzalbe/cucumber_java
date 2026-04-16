package cucumber.stepDefinitions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the enter a number page$")
    public void onNumberPage(){
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number \"([^\"]*)\"$")
    public void enterNumber(String number){
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click submit button$")
    public void clickSubmitButton(){
        driver.findElement(By.className("w3-btn")).click();
    }

    @Then("^I should see message : \"([^\"]*)\"$")
    public void seeMessage(String message){
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I should see alert with message \"([^\"]*)\"$")
    public void seeAlert(String message) {
        Alert alert = driver.switchTo().alert();
        assertEquals(message, alert.getText());
        alert.accept();
    }
}
