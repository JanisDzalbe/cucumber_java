package cucumber.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SampleSteps {
    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site");
    }

    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Given("I am on feedback page")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @Then("^I should see home page header$")
    public void iShouldSeeHomePageHeader() throws Throwable {
        assertEquals("This is a home page",
                driver.findElement(By.cssSelector("h1")).getText());
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() {
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
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

    @When("^I enter name: \"([^\"]*)\" and age: \"(\\d+)\"$")
    public void iEnterNameAndAge(String name, String age) {
        WebElement nameInput = driver.findElement(By.id("fb_name"));
        WebElement ageInput = driver.findElement(By.id("fb_age"));
        nameInput.clear();
        nameInput.sendKeys(name);
        ageInput.clear();
        ageInput.sendKeys(age);
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

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() {
        driver.findElements(By.cssSelector("input[type='button']"))
                .forEach(b -> assertTrue(b.isDisplayed() && b.isEnabled()));
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

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String l : languages){
            driver.findElement(By.cssSelector("[value='"+l+"']")).click();
        }
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback() {
        WebElement sendFeedbackButton = driver.findElement(By.cssSelector("button[type='submit']"));
        sendFeedbackButton.click();
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("result_checkbox")).getText());
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorMessage) {
        assertEquals(errorMessage, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() {
        assertFalse(driver.getCurrentUrl().matches("^https://janisdzalbe\\.github\\.io/example-site/examples/age_2\\.html\\?name=.+&age=\\d+$"));
    }

    @Then("^I see name field is \"([^\"]*)\"$")
    public void iSeeNameFieldIs(String name) {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @And("^I see age field is \"(\\d+)\"$")
    public void iSeeAgeFieldIs(String age) {
        assertEquals(age, driver.findElement(By.id("age")).getText());
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) {
        WebElement languageFeedbackElement = driver.findElement(By.id("language"));
        assertEquals(expectedLanguages, languageFeedbackElement.getText());
    }

    @When("I enter name, age and gender into feedback")
    public void iEnterNameAgeAndGenderIntoFeedback(Map<String, String> inputMap) {
        WebElement nameInput = driver.findElement(By.id("fb_name"));
        WebElement ageInput = driver.findElement(By.id("fb_age"));
        WebElement genderRadioButton = driver.findElement(By.cssSelector("[value='"+inputMap.get("gender")+"']"));
        
        nameInput.clear();
        nameInput.sendKeys(inputMap.get("name"));
        ageInput.clear();
        ageInput.sendKeys(inputMap.get("age"));
        genderRadioButton.click();
    }

    @Then("I verify name, age and gender from feedback")
    public void iVerifyNameAgeAndGenderFromFeedback(Map<String, String> expectedMap) {
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement ageField = driver.findElement(By.id("age"));
        WebElement genderField = driver.findElement(By.id("gender"));

        assertEquals(expectedMap.get("name"), nameField.getText());
        assertEquals(expectedMap.get("age"), ageField.getText());
        assertEquals(expectedMap.get("gender"), genderField.getText());
    }
}
