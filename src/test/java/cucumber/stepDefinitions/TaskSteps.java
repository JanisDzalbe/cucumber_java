package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on enter a number page$")
    public void i_am_on_enter_a_number_page() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }
    @When("^I enter \"([^\"]*)\" in field$")
    public void i_enter_in_field(String field) throws Throwable {
        driver.findElement(By.id("numb")).sendKeys(field);
    }

    @When("^I press submit button$")
    public void i_press_submit_button() throws Throwable {
        driver.findElement(By.xpath("//*[@onclick = 'numberValidation()']")).click();
    }



    @Then("^I see an error$")
    public void i_see_an_error() throws Throwable {
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
    }
    @Then("^I see correct error \"([^\"]*)\"$")
    public void i_see_correct_error(String error) throws Throwable {
        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see alert$")
    public void i_see_alert() throws Throwable {
        assertTrue(driver.switchTo().alert().getText().equals("Square root of 64 is 8.00"));
    }


}

