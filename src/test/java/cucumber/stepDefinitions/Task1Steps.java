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

import static org.junit.jupiter.api.Assertions.*;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on enter number page$")
    public void Iamonenternumberpage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in number field$")
    public void iEnterInNumberField(String number) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }


    @And("^I click Submit button$")
    public void IclickSubmitbutton() throws Throwable{
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("^I see error message\": \"([^\"]*)\"$")
    public void Iseeerrormessage(String exeptError) throws Throwable{
        assertEquals(exeptError, driver.findElement(By.id("ch1_error")).getText());
    }


    @Then("^I see square massage: \"([^\"]*)\"$")
    public void iSeeSquareRootMessage(String expectedMessage) {
//      Check the alert message and accept it
        Alert alert = driver.switchTo().alert();
        assertEquals(expectedMessage, alert.getText());
        alert.accept();

    }
}
