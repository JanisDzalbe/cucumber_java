package cucumber.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Task1Steps {

    private WebDriver driver;

    private static final String PAGE_URL =
            "https://janisdzalbe.github.io/example-site/tasks/enter_a_number";

    @Before
    public void setUp() {
        String libWithDriversLocation =
                System.getProperty("user.dir") + File.separator + "lib" + File.separator;

        System.setProperty(
                "webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver.exe"
        );

        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the {string} page")
    public void i_am_on_the_page(String pageName) {
        driver.get(PAGE_URL);
    }

    @When("I type number {string}")
    public void i_type_number(String number) {
        WebElement input = numberInput();
        input.clear();
        input.sendKeys(number);
    }

    @And("I click Submit")
    public void i_click_submit() {
        submitButton().click();
    }

    @Then("I should see error {string}")
    public void i_should_see_error(String expectedError) {
        assertEquals(expectedError, errorMessage().getText().trim());
    }

    @Then("I should see square root {string}")
    public void i_should_see_square_root(String expectedMessage) {
        Alert alert = driver.switchTo().alert();
        assertEquals(expectedMessage, alert.getText().trim());
        alert.accept();
    }

    @Then("I should see no error message")
    public void i_should_see_no_error_message() {
        try {
            WebElement error = errorMessage();
            assertEquals("", error.getText().trim());
        } catch (NoSuchElementException e) {

        }
    }


    private WebElement numberInput() {
        return driver.findElement(
                By.cssSelector("input#number, input#number_input, input[type='number'], input[type='text']")
        );
    }

    private WebElement submitButton() {
        return driver.findElement(By.cssSelector("button, input[type='submit']"));
    }

    private WebElement errorMessage() {
        return driver.findElement(By.cssSelector(".error, #error_message, #error"));
    }
}
