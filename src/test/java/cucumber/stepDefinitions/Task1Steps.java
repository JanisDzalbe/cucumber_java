package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;


    }
    @Given("^I am on number input page$")
    public void iAmOnNumberInputPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }
    @When("^I enter number \"([^\"]*)\"$")
    public void iEnterNumber(String number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(String.valueOf(number));
    }
    @And("^I click submit button$")
    public void iClickSubmitButton() throws Throwable {
        driver.findElement(By.className("w3-orange")).click();
    }
    @Then("^I see error message \"([^\"]*)\"$")
    public void iSeeErrorMessage(String errorMessage) throws Throwable {
        assertEquals(errorMessage, driver.findElement(By.id("ch1_error")).getText());
    }
    @Then("I see popup with answer \"([^\"]*)\"$")
    public void iSeePopupWithAnswer(String answer) throws Throwable {
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 51 is 7.14", alert.getText());
        alert.accept();
    }


}


