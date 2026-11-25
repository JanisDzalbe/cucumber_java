package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Step definitions mapping Gherkin steps in feature files to Selenium WebDriver actions.
 */
public class SampleSteps {
    private WebDriver driver;

    /**
     * Reuses the shared WebDriver instance initialized in Hooks.
     */
    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site");
    }

    @Then("^I should see home page header$")
    public void iShouldSeeHomePageHeader() throws Throwable {
        assertEquals("This is a home page",
                driver.findElement(By.cssSelector("h1")).getText());
    }

    @And("^I should see home page description$")
    public void iShouldSeeHomePageDescription() throws Throwable {
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                driver.findElement(By.cssSelector("p")).getText());
    }

    @When("^I enter name: \"([^\"]*)\"$")
    public void iEnterName(String name) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter age: (\\d+)$")
    public void iEnterAge(int age) throws Throwable {
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
    }

    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/age");
    }

    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    @When("^I enter values:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(valuesToEnter.get("name"));
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(valuesToEnter.get("age"));
    }

    @And("^I should see menu$")
    public void iShouldSeeMenu() throws Throwable {
        assertTrue(driver.findElement(By.className("w3-navbar")).isDisplayed());
    }

    @And("^I click the result checkbox button$")
    public void iClickTheResultCheckboxButton() throws Throwable {
        driver.findElement(By.id("result_button_checkbox")).click();
    }

    @When("^I clicked on checkboxes:$")
    public void iClickedOnCheckboxes(List<String> values) throws Throwable {
        for (String value : values) {
            driver.findElement(By.cssSelector("[value='" + value + "']")).click();
        }
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("result_checkbox")).getText());
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }

    /**
     * Navigates to the Locators example page.
     */
    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    /**
     * Verifies that both headings on the Locators page are visible with the expected text.
     */
    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() {
        WebElement heading1 = driver.findElement(By.id("heading_1"));
        WebElement heading2 = driver.findElement(By.id("heading_2"));
        assertTrue(heading1.isDisplayed());
        assertEquals("Heading 1", heading1.getText());
        assertTrue(heading2.isDisplayed());
        assertEquals("Heading 2 text", heading2.getText());
    }

    /**
     * Verifies that both buttons on the Locators page are visible and enabled (clickable).
     */
    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() {
        WebElement button1 = driver.findElement(By.name("randomButton1"));
        WebElement button2 = driver.findElement(By.id("buttonId"));
        assertTrue(button1.isDisplayed());
        assertTrue(button1.isEnabled());
        assertTrue(button2.isDisplayed());
        assertTrue(button2.isEnabled());
    }

    /**
     * Verifies that the expected validation error message is displayed on the age page.
     *
     * @param errorMessage the expected error text captured from the Gherkin step
     */
    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorMessage) {
        WebElement errorElement = driver.findElement(By.id("error"));
        assertTrue(errorElement.isDisplayed());
        assertEquals(errorMessage, errorElement.getText());
    }

    /**
     * Verifies that the browser remained on the age input page and did not navigate to the age message page.
     */
    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() {
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());
        assertTrue(driver.findElements(By.id("message")).isEmpty());
    }

    /**
     * Navigates to the "Give us your feedback!" task page.
     */
    @Given("^I am on the provide feedback page$")
    public void iAmOnTheProvideFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    /**
     * Fills the Name and Age fields on the feedback form.
     *
     * @param name the name to enter into {@code #fb_name}
     * @param age  the age to enter into {@code #fb_age}
     */
    @When("^I enter feedback name \"([^\"]*)\" and age (\\d+)$")
    public void iEnterFeedbackNameAndAge(String name, int age) {
        WebElement nameInput = driver.findElement(By.id("fb_name"));
        WebElement ageInput = driver.findElement(By.id("fb_age"));
        nameInput.clear();
        nameInput.sendKeys(name);
        ageInput.clear();
        ageInput.sendKeys(String.valueOf(age));
    }

    /**
     * Submits the feedback form by clicking the "Send" button.
     */
    @And("^I click the Send feedback button$")
    public void iClickTheSendFeedbackButton() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    /**
     * Verifies that the submitted name and age appear in the corresponding summary fields.
     *
     * @param expectedName the expected submitted name
     * @param expectedAge  the expected submitted age
     */
    @Then("^I see submitted feedback name \"([^\"]*)\" and age (\\d+)$")
    public void iSeeSubmittedFeedbackNameAndAge(String expectedName, int expectedAge) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("check_feedback.html"));
        wait.until(d -> !d.findElement(By.id("name")).getText().isEmpty());

        assertEquals(expectedName, driver.findElement(By.id("name")).getText());
        assertEquals(String.valueOf(expectedAge), driver.findElement(By.id("age")).getText());
    }

    /**
     * Navigates to the "Enter a number" task page.
     */
    @Given("^I am on the enter a number page$")
    public void iAmOnTheEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    /**
     * Clears the number input field and enters the given value.
     *
     * @param value the text or numeric string to enter into {@code #numb}
     */
    @When("^I enter value \"([^\"]*)\" into the number field$")
    public void iEnterValueIntoTheNumberField(String value) {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.clear();
        numberInput.sendKeys(value);
    }

    /**
     * Clicks the orange Submit button on the "Enter a number" page.
     */
    @And("^I click the Submit number button$")
    public void iClickTheSubmitNumberButton() {
        driver.findElement(By.className("w3-orange")).click();
    }

    /**
     * Verifies that the expected validation error message is displayed in {@code #ch1_error}.
     *
     * @param expectedError the expected validation error message
     */
    @Then("^I see number validation error \"([^\"]*)\"$")
    public void iSeeNumberValidationError(String expectedError) {
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        assertTrue(errorElement.isDisplayed());
        assertEquals(expectedError, errorElement.getText());
    }

    /**
     * Switches to the browser alert, verifies the calculated square root message, and accepts the alert.
     *
     * @param expectedAlertText the expected text inside the browser alert box
     */
    @Then("^I see square root alert with text \"([^\"]*)\"$")
    public void iSeeSquareRootAlertWithText(String expectedAlertText) {
        Alert alert = driver.switchTo().alert();
        assertEquals(expectedAlertText, alert.getText());
        alert.accept();
    }

    /**
     * Verifies that no validation error message is displayed on the page after a valid submission.
     */
    @And("^no number validation error is shown$")
    public void noNumberValidationErrorIsShown() {
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        assertFalse(errorElement.isDisplayed());
        assertEquals("", errorElement.getText());
    }
}