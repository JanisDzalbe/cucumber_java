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

public class AgeStepDefinitions {

    private static final String BASE_URL = "https://janisdzalbe.github.io/example-site";
    private static final String AGE_PAGE_URL = BASE_URL + "/examples/age";
    private static final String AGE_MESSAGE_PAGE_URL = AGE_PAGE_URL + "/message";
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

    // ==================== Non-PO steps (existing) ====================

    @Given("I am on age page")
    public void iAmOnAgePage() {
        driver.get(AGE_PAGE_URL);
    }

    @When("I enter name: {string}")
    public void iEnterName(String name) {
        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("name"))
        );
        nameField.clear();
        nameField.sendKeys(name);
    }

    @When("I enter age: {int}")
    public void iEnterAge(int age) {
        WebElement ageField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("age"))
        );
        ageField.clear();
        ageField.sendKeys(String.valueOf(age));
    }

    @When("I click submit age")
    public void iClickSubmitAge() {
        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("submit"))
        );
        submitButton.click();
    }

    @Then("I see message: {string}")
    public void iSeeMessage(String expectedMessage) {
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );
        Assert.assertEquals(expectedMessage, message.getText());
    }

    @Then("I see error: {string}")
    public void iSeeError(String expectedError) {
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("error"))
        );
        Assert.assertEquals(expectedError, error.getText());
    }

    @And("I am not navigated to age message page")
    public void iAmNotNavigatedToAgeMessagePage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(
                "Should not navigate to message page, but was: " + currentUrl,
                currentUrl.contains(AGE_MESSAGE_PAGE_URL)
        );
        Assert.assertTrue(
                "Should remain on age page, but was: " + currentUrl,
                currentUrl.contains(AGE_PAGE_URL)
        );
    }

    // ==================== PO steps (new) ====================

    @Given("I am on age page using PO")
    public void iAmOnAgePageUsingPO() {
        driver.get(AGE_PAGE_URL);
    }

    @Given("I open age page using PO")
    public void iOpenAgePageUsingPO() {
        driver.get(AGE_PAGE_URL);
    }

    @When("I enter name: {string} using PO")
    public void iEnterNameUsingPO(String name) {
        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("name"))
        );
        nameField.clear();
        nameField.sendKeys(name);
    }

    @When("I enter age: {int} using PO")
    public void iEnterAgeUsingPO(int age) {
        WebElement ageField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("age"))
        );
        ageField.clear();
        ageField.sendKeys(String.valueOf(age));
    }

    @When("I click submit age using PO")
    public void iClickSubmitAgeUsingPO() {
        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("submit"))
        );
        submitButton.click();
    }

    @Then("I see message: {string} using PO")
    public void iSeeMessageUsingPO(String expectedMessage) {
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );
        Assert.assertEquals(expectedMessage, message.getText());
    }

    @Then("I see error: {string} using PO")
    public void iSeeErrorUsingPO(String expectedError) {
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("error"))
        );
        Assert.assertEquals(expectedError, error.getText());
    }

    @And("I remain in age page using PO")
    public void iRemainInAgePageUsingPO() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(
                "Should not navigate to message page, but was: " + currentUrl,
                currentUrl.contains(AGE_MESSAGE_PAGE_URL)
        );
        Assert.assertTrue(
                "Should remain on age page, but was: " + currentUrl,
                currentUrl.contains(AGE_PAGE_URL)
        );
    }
}