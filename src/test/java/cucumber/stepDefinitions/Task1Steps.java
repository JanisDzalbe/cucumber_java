package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class Task1Steps {

    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on enter a number page$")
    public void enterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I entered number \"([^\"]*)\"$")
    public void enterNumber(String number) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I clicked submit number button$")
    public void clickSubmitNumberButton() {
        driver.findElement(By.cssSelector("button[onclick='numberValidation()']")).click();
    }

    @Then("^I see number error message \"([^\"]*)\"$")
    public void seeNumberErrorMessage(String expectedError) {
        assertEquals(expectedError, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see valid number result \"([^\"]*)\"$")
    public void seeValidNumberResult(String expectedResult) {
        assertEquals(expectedResult, driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }

}
