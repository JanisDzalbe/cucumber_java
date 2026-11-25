package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static cucumber.stepDefinitions.Hooks.driver;
import static org.junit.jupiter.api.Assertions.*;

///
public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on task page$")
    public void iAmOnTheTaskPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter a number \"([^\"]*)\"$")
    public void iEnterNumber(String Input) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(Input);
    }

    @And("^I click submit button$")
    public void iClickSendButton() throws Throwable {
        driver.findElement(By.cssSelector("[type=\"button\"]")).click();
    }

    @Then("^I see an error message: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String error) throws Throwable {
        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
    }

    @When("^I enter a correct number$")
    public void iEnterNumber() throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys("77");
    }

    @Then("^I see the Valid message$")
    public void iSeeValidMessage() throws Throwable {
        //assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
        Alert alert = driver.switchTo().alert();
        String actualResult = alert.getText();
        assertEquals("Square root of 77 is 8.77",actualResult);
        alert.accept();
    }

}
