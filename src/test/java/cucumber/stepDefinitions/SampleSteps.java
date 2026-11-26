
package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleSteps {

    private WebDriver driver;

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
        assertEquals(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
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
        assertEquals(message,
                driver.findElement(By.id("message")).getText());
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
            driver.findElement(
                    By.cssSelector("[value='" + value + "']")
            ).click();
        }
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String message) throws Throwable {
        assertEquals(message,
                driver.findElement(By.id("result_checkbox")).getText());
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }

    // ACTIVITY 1 - LOCATORS PAGE

    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() {
        assertEquals("Heading 1",
                driver.findElement(By.id("heading_1")).getText());

        assertEquals("Heading 2 text",
                driver.findElement(By.id("heading_2")).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() {
        WebElement firstButton = driver.findElement(By.name("randomButton1"));
        WebElement secondButton = driver.findElement(By.name("randomButton2"));

        assertTrue(firstButton.isDisplayed());
        assertTrue(firstButton.isEnabled());

        assertTrue(secondButton.isDisplayed());
        assertTrue(secondButton.isEnabled());

        firstButton.click();
        secondButton.click();
    }

    // ACTIVITY 2 - ERROR VALIDATION

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorMessage) {
        assertEquals(errorMessage,
                driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() {
        assertTrue(driver.getCurrentUrl().contains("/examples/age"));
    }

    // ACTIVITY 3 - FEEDBACK FORM

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter feedback name: \"([^\"]*)\"$")
    public void iEnterFeedbackName(String name) {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^I enter feedback age: (\\d+)$")
    public void iEnterFeedbackAge(int age) {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click Send$")
    public void iClickSend() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("^submitted name should be \"([^\"]*)\"$")
    public void submittedNameShouldBe(String expectedName) {
        assertEquals(expectedName,
                driver.findElement(By.id("name")).getText());
    }

    @And("^submitted age should be \"([^\"]*)\"$")
    public void submittedAgeShouldBe(String expectedAge) {
        assertEquals(expectedAge,
                driver.findElement(By.id("age")).getText());
    }

    // TASK 1 - ENTER A NUMBER

    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number \"([^\"]*)\"$")
    public void iEnterNumber(String number) {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.clear();
        numberInput.sendKeys(number);
    }

    @And("^I click number submit$")
    public void iClickNumberSubmit() {
        driver.findElement(
                By.cssSelector("button[onclick='numberValidation()']")
        ).click();
    }

    @Then("^I should see number error: \"([^\"]*)\"$")
    public void iShouldSeeNumberError(String expectedError) {

        List<WebElement> errorElements =
                driver.findElements(By.id("ch1_error"));

        boolean errorFound = false;

        for (WebElement errorElement : errorElements) {
            if (errorElement.isDisplayed()
                    && errorElement.getText().equals(expectedError)) {
                errorFound = true;
                break;
            }
        }

        assertTrue(errorFound,
                "Expected error message was not displayed: " + expectedError);
    }

    @Then("^I should see alert: \"([^\"]*)\"$")
    public void iShouldSeeAlert(String expectedMessage) {

        Alert alert = driver.switchTo().alert();

        String actualMessage = alert.getText();

        assertEquals(expectedMessage, actualMessage);

        alert.accept();
    }

    // ACTIVITY 4 - FEEDBACK WITH ONLY LANGUAGES

    @When("^I select feedback languages$")
    public void selectFeedbackLanguages(List<String> languages) {

        for (String language : languages) {
            driver.findElement(
                    By.cssSelector(
                            "input[type='checkbox'][value='" + language + "']"
                    )
            ).click();
        }
    }

    @And("^I click send feedback$")
    public void clickSendFeedback() {
        driver.findElement(
                By.cssSelector("button[type='submit']")
        ).click();
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) {

        assertEquals(
                expectedLanguages,
                driver.findElement(By.id("language")).getText()
        );
    }

    // ACTIVITY 5 - SCENARIO OUTLINE WITH FEEDBACK DETAILS

    @When("^I enter feedback details:$")
    public void iEnterFeedbackDetails(Map<String, String> feedbackDetails) {

        // Enter name
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name"))
                .sendKeys(feedbackDetails.get("name"));

        // Enter age
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age"))
                .sendKeys(feedbackDetails.get("age"));

        // Select gender
        String genre = feedbackDetails.get("genre");

        driver.findElement(
                By.cssSelector(
                        "input[name='gender'][value='" + genre + "']"
                )
        ).click();
    }

    @And("^submitted genre should be \"([^\"]*)\"$")
    public void submittedGenreShouldBe(String expectedGenre) {

        assertEquals(
                expectedGenre,
                driver.findElement(By.id("gender")).getText()
        );
    }
}
