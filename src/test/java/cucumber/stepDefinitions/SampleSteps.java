package cucumber.stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SampleSteps {
    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    // ---------------- HOME PAGE ----------------

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() {
        driver.get("https://acctabootcamp.github.io/site");
    }

    @Then("^I should see home page header$")
    public void iShouldSeeHomePageHeader() {
        assertEquals("This is a home page",
                driver.findElement(By.cssSelector("h1")).getText());
    }

    @And("^I should see home page description$")
    public void iShouldSeeHomePageDescription() {
        assertEquals(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                driver.findElement(By.cssSelector("p")).getText());
    }

    @And("^I should see menu$")
    public void iShouldSeeMenu() {
        assertTrue(driver.findElement(By.className("w3-navbar")).isDisplayed());
    }

    // ---------------- AGE PAGE ----------------

    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/age");
    }

    @When("^I enter name: \"([^\"]*)\"$")
    public void iEnterName(String name) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter age: (\\d+)$")
    public void iEnterAge(int age) {
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
    }

    @And("^I click submit age$")
    public void iClickSubmitAge() {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    // ---------------- ERROR SCENARIO ----------------

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorMessage) {
        assertEquals(errorMessage, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() {
        assertTrue(driver.getCurrentUrl().contains("/examples/age"));
    }

    // ---------------- OTHER EXISTING STEPS ----------------

    @When("^I enter values:$")
    public void iEnterValues(Map<String, String> valuesToEnter) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(valuesToEnter.get("name"));

        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(valuesToEnter.get("age"));
    }

    @And("^I click the result checkbox button$")
    public void iClickTheResultCheckboxButton() {
        driver.findElement(By.id("result_button_checkbox")).click();
    }

    @When("^I clicked on checkboxes:$")
    public void iClickedOnCheckboxes(List<String> values) {
        for (String value : values) {
            driver.findElement(By.cssSelector("[value='" + value + "']")).click();
        }
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String message) {
        assertEquals(message, driver.findElement(By.id("result_checkbox")).getText());
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }

    // ---------------- FEEDBACK PAGE ----------------

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

    @And("^I choose feedback option: \"([^\"]*)\"$")
    public void iChooseFeedbackOption(String option) {
        Select select = new Select(driver.findElement(By.id("like_us")));
        select.selectByVisibleText(option);
    }

    @And("^I enter feedback comment: \"([^\"]*)\"$")
    public void iEnterFeedbackComment(String comment) {
        driver.findElement(By.cssSelector("textarea[name='comment']")).clear();
        driver.findElement(By.cssSelector("textarea[name='comment']")).sendKeys(comment);
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback() {
        driver.findElement(By.xpath("//button[text()='Send']")).click();
    }

    @Then("^I should see feedback name: \"([^\"]*)\"$")
    public void iShouldSeeFeedbackName(String name) {
        assertTrue(driver.getCurrentUrl().contains("name=" + name));
    }

    @And("^I should see feedback age: \"([^\"]*)\"$")
    public void iShouldSeeFeedbackAge(String age) {
        assertTrue(driver.getCurrentUrl().contains("age=" + age));
    }

    @And("^I should see feedback option: \"([^\"]*)\"$")
    public void iShouldSeeFeedbackOption(String option) {
        String expectedOption = option.replace(" ", "+").replace("?", "%3F");
        assertTrue(driver.getCurrentUrl().contains("option=" + expectedOption));
    }

    @And("^I should see feedback comment: \"([^\"]*)\"$")
    public void iShouldSeeFeedbackComment(String comment) {
        String expectedComment = comment.replace(" ", "+").replace("?", "%3F");
        assertTrue(driver.getCurrentUrl().contains("comment=" + expectedComment));
    }

    // ---------------- ENTER A NUMBER PAGE ----------------

    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number value: \"([^\"]*)\"$")
    public void iEnterNumberValue(String value) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    @And("^I click submit number$")
    public void iClickSubmitNumber() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("^I should see number error: \"([^\"]*)\"$")
    public void iShouldSeeNumberError(String error) {
        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I should see square root message: \"([^\"]*)\"$")
    public void iShouldSeeSquareRootMessage(String expectedMessage) {
        String actualAlertText = driver.switchTo().alert().getText();
        assertEquals(expectedMessage, actualAlertText);
        driver.switchTo().alert().accept();
    }
}