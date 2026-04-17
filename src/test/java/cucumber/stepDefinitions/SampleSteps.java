package cucumber.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
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

    // Steps by Ilmars

    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() {
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @Then("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() {
        List<WebElement> buttons = driver.findElements(By.tagName("input"));
        assertTrue(buttons.getFirst().isDisplayed());
        assertTrue(buttons.getFirst().isEnabled());
        assertTrue(buttons.get(1).isDisplayed());
        assertTrue(buttons.get(1).isEnabled());
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorText) {
        WebElement errorTextElement = driver.findElement(By.id("error"));
        assertTrue(errorTextElement.isDisplayed());
        assertEquals(errorText, driver.findElement(By.id("error")).getText());
    }

    @Then("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() {
        String urlStart = "https://janisdzalbe.github.io/example-site/examples/age_2.html";
        assertFalse(driver.getCurrentUrl().contains(urlStart));
    }

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I click send feedback$")
    public void iClickSubmitFeedback() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see feedback name: \"([^\"]*)\"$")
    public void iSeeName(String name) {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @Then("^I see feedback age: (\\d*)$")
    public void iSeeAgeAge(Integer age) {
        assertEquals(age.toString(), driver.findElement(By.id("age")).getText());
    }

    @When("^I enter feedback name \"([^\"]*)\"$")
    public void iEnterNameInFeedback(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @When("^I enter feedback age (\\d+)$")
    public void iEnterAgeInFeedback(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @Given("^I am on \"Enter a number\" page$")
    public void iAmOnPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter text: \"([^\"]*)\"$")
    public void iEnterTextText(String text) {
        WebElement textInputField = driver.findElement(By.id("numb"));
        textInputField.clear();
        textInputField.sendKeys(text);
    }

    @When("^I click submit button$")
    public void iClickSubmitButton() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String errorMessage) {
        assertEquals(errorMessage, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see the correct alert message$")
    public void iSeeTheAlertMessage() {
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 100 is 10.00", alert.getText());
        alert.dismiss();
    }

    @Then("^I see no error message$")
    public void iSeeNoErrorMessage() {
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String language : languages) {
            driver.findElement(By.xpath("//input[@value='" + language + "']")).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String languages) {
        assertEquals(languages, driver.findElement(By.id("language")).getText());
    }

    @When("^I set info in feedback: \"([^\"]*)\", (\\d+) and \"([^\"]*)\"$")
    public void iSetInfoInFeedback(String name, int age, String genre) {
        WebElement fb_name = driver.findElement(By.id("fb_name"));
        fb_name.clear();
        fb_name.sendKeys(name);
        WebElement fb_age = driver.findElement(By.id("fb_age"));
        fb_age.clear();
        fb_age.sendKeys(String.valueOf(age));
        WebElement fb_gender = driver.findElement(By.xpath("//input[@name='gender' and @value='" + genre + "']"));
        fb_gender.click();
    }

    @And("^I see feedback genre: \"([^\"]*)\"$")
    public void iSeeFeedbackGenre(String genre) {
        assertEquals(genre, driver.findElement(By.id("gender")).getText());
    }

    @When("^I set feedback details$")
    public void iSetFeedbackDetails(Map<String, String> map) {
        Runnable actionForName = () -> {
            WebElement element = driver.findElement(By.id("fb_name"));
            element.clear();
            element.sendKeys(map.get("name"));
        };
        Runnable actionForAge = () -> {
            WebElement element = driver.findElement(By.id("fb_age"));
            element.clear();
            element.sendKeys(map.get("age"));
        };
        Runnable actionForGenre = () -> {
            WebElement element = driver.findElement(By.xpath("//input[@name='gender' and @value='" + map.get("genre") + "']"));
            element.click();
        };
        Map<String, Runnable> actions = Map.of(
                "name", actionForName,
                "age", actionForAge,
                "genre", actionForGenre
        );
        for (String key : map.keySet()) {
            actions.get(key).run();
        }
    }

    @When("^I see feedback details$")
    public void iSeeFeedbackDetails(Map<String, String> map) {
        Map<String, String> ids = Map.of(
                "name", "name",
                "age", "age",
                "genre", "gender"
        );
        for (String key : map.keySet()) {
            assertEquals(map.get(key), driver.findElement(By.id(ids.get(key))).getText());
        }
    }

}