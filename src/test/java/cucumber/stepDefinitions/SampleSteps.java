package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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


    //-------------------------------
    //Sample 1
    //-------------------------------
    @When("^I am on the locators page$")
    public void iAmOnLocatorsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void assertHeadersInLocatorsPage() {
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @Then("^Buttons in Locators page are clickable$")
    public void assertButtonsInLocatorsPage() {
        assertTrue(driver.findElement(By.cssSelector("[name=\"randomButton1\"]")).isEnabled());
        assertTrue(driver.findElement(By.cssSelector("[name=\"randomButton2\"]")).isEnabled());

    }

    //-------------------------------
    //Sample 2
    //-------------------------------
    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeErrorInAgePage(String errorMessage) {
        assertEquals(errorMessage, driver.findElement(By.id("error")).getText());
    }

    @Then("^I am not navigated to age message page$")
    public void urlDoesNotContainAgeMessagePageUrl() {
        assertFalse(driver.getCurrentUrl().contains("/age_2.html"));


    }

    //-------------------------------
    //Sample 3
    //-------------------------------

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name \"([^ ]*)\" in feedback page$")
    public void iEnterNameInFeedbackPage(String name) {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @When("^I enter age (\\d*) in feedback page$")
    public void iEnterAgeInFeedbackPage(String age) {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(age);
    }

    @When("^Send feedback$")
    public void iSendFeedback() {
        driver.findElement(By.cssSelector("[type=\"submit\"]")).click();

    }

    @Then("^I see name \"([^ ]*)\" in feedback review page$")
    public void assertNameInFeedbackReviewPage(String name) {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @Then("^I see age (\\d*) in feedback review page$")
    public void assertAgeInFeedbackReviewPage(String age) {
        assertEquals(age, driver.findElement(By.id("age")).getText());
    }


    //-------------------------------
    //Task 1
    //-------------------------------

    //Given I am on enter a number page
    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    //When I enter value "<value>" in enter a number page
    //When I enter value "81" in enter a number page
    @When("^I enter value \"([^\"]*)\" in enter a number page$")
    public void iEnterValueInEnterANumberPage(String value) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    //And I click submit in enter a number page
    @When("^I click submit in enter a number page$")
    public void iClickSubmitInEnterANumberPage() {
        driver.findElement(By.className("w3-orange")).click();
    }

    //Then I see error "<error>" in enter a number page
    @Then("^I see error \"([^\"]*)\" in enter a number page$")
    public void assertErrorInEnterANumberPage(String error) {
        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
    }

    //I see alert "Square root of 81 is 9.00" in enter a number page
    @Then("^I see alert \"([^\"]*)\" in enter a number page$")
    public void assertAlertInEnterANumberPage(String alertText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals(alertText, alert.getText());
    }

    //I accept alert in enter a number page
    @Then("^I accept alert in enter a number page$")
    public void iAcceptAlertInEnterANumberPage() {
        driver.switchTo().alert().accept();
    }

    //And I see no error in enter a number page
    @Then("^I see no error in enter a number page$")
    public void assertNoErrorInEnterANumberPage() {
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }

}