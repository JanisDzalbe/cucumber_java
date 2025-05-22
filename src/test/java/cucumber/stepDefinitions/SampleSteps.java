package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see home page header$")
    public void iShouldSeeHomePageHeader() throws Throwable {
        assertEquals("This is a home page",
                driver.findElement(By.cssSelector("h1")).getText());
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeLocatorsPageHeaders() throws Throwable {
        assertEquals("Heading 1",
                driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text",
                driver.findElement(By.id("heading_2")).getText());
    }

    @Then("^Buttons in Locators page are clickable$")
    public void locatorPageButtonsAreClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton1']")).isEnabled());
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton2']")).isEnabled());
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

    @And("^I enter age (\\d+)$")
    public void iEnterAge(int age) throws Throwable {
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
    }


    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/age");
    }

    @Given("^I (?:am on|open) feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
    }

    @When("^I enter name \"([^\"]*)\" and age (\\d+) on feedback page$")
    public void iEnterNameAndAgeOnFeedback(String name, int age) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @When("^I click Send on feedback page$")
    public void iClickSendOnFeedback() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see name \"([^\"]*)\" at check feedback page$")
    public void iSeeNameInFeedbackCheck(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @Then("^I see age (\\d+) in check feedback page$")
    public void iSeeAgeInFeedbackCheck(int age) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
    }

    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: '([^']*)'$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("error")).getText());
    }

    @Then("^I am not navigated to age message page$")
    public void assertAgePageUrl() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/examples/age", driver.getCurrentUrl());
    }


    @When("^I enter values:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        for (Map.Entry<String, String> e : valuesToEnter.entrySet()) {
            driver.findElement(By.id(e.getKey())).clear();
            driver.findElement(By.id(e.getKey())).sendKeys(e.getValue());
            System.out.println("key is " + e.getKey());
            System.out.println("value is " + e.getValue());
        }
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
        driver.get("https://acctabootcamp.github.io/site/examples/actions");
    }

    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in number field$")
    public void iEnterValueInNumberField(String value) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    @And("^I click number submit button$")
    public void iClickNumberSubmitButton() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeInlineError(String expected) {
        String actual = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expected, actual);
    }

    @Then("^I see result message: \"([^\"]*)\"$")
    public void iSeeAlertMessage(String expected) {
        String actual = driver.switchTo().alert().getText();
        assertEquals(expected, actual);
        driver.switchTo().alert().accept();
    }

}



