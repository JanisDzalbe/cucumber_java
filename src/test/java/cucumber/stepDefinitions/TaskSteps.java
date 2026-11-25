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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }
//-------------------------------------------------------------------------
// Task 1
//--------------------------------------------------------------------------

@Given("^I am on enter a number page$")
public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
}

@When("^I enter number: \"([^\"]*)\"$")
public void iEnterNumber(String number) {
        driver.findElement(By.id("numb")).clear();
    driver.findElement(By.id("numb")).sendKeys(number);
}

@And("^I click Submit on number page$")
public void iClickSubmitOnNumberPage() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
}

@Then("^I see number error: \"([^\"]*)\"$")
public void iSeeNumberError(String errorMessage) {
        assertEquals(errorMessage, driver.findElement(By.id("ch1_error")).getText());
}

@Then("^I see alert: \"([^\"]*)\"$")
public void iSeeAlert(String alert) {
        assertEquals(alert, driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }
}
