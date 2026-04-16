package cucumber.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;

public class EnterNumberStepDefinitions {

    private static final String BASE_URL = "https://janisdzalbe.github.io/example-site";
    private static final String NUMBER_PAGE_URL = BASE_URL + "/tasks/enter_a_number";
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // --- Given ---

    @Given("I am on enter a number page")
    public void iAmOnEnterANumberPage() {
        driver.get(NUMBER_PAGE_URL);
    }

    // --- When ---

    @When("I enter number: {string}")
    public void iEnterNumber(String input) {
        WebElement numberField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("number"))
        );
        numberField.clear();
        numberField.sendKeys(input);
    }

    @And("I click submit number")
    public void iClickSubmitNumber() {
        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Submit'] | //input[@value='Submit']")
                )
        );
        submitButton.click();
    }

    // --- Then ---

    @Then("I see number error: {string}")
    public void iSeeNumberError(String expectedError) {
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("error"))
        );
        Assert.assertEquals(expectedError, error.getText());
    }

    @Then("I see number result: {string}")
    public void iSeeNumberResult(String expectedResult) {
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("result"))
        );
        Assert.assertTrue(
                "Expected result to contain '" + expectedResult + "' but was: " + result.getText(),
                result.getText().contains(expectedResult)
        );
    }
}