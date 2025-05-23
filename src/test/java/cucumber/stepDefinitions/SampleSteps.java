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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Given("^I am on the locators page$")
    public void iAmOnLocatorsPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
        String h1 = driver.findElement(By.cssSelector("#heading_1")).getText();
        String h2 = driver.findElement(By.cssSelector("#heading_2")).getText();
        assertEquals("Heading 1", h1);
        assertEquals("Heading 2 text", h2);
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsLocatorsPageClickable() throws Throwable {
        WebElement btn1 = driver.findElement(By.cssSelector("input[name='randomButton1']"));
        WebElement btn2 = driver.findElement(By.cssSelector("input[name='randomButton2']"));
        assertTrue(btn1.isEnabled());
        assertTrue(btn2.isEnabled());
    }

    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/age");
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

    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String message) throws Throwable {
        String error = driver.findElement(By.cssSelector("#error")).getText();
        assertEquals(message, error);
    }

    @And("^I am not navigated to age message page$")
    public void staySamePage() throws Throwable {
        String expectedUrl = "https://acctabootcamp.github.io/site/examples/age";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Given("^I (?:am on|open) feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
    }

    @When("^I enter name: \"([^\"]*)\" and age: (\\d+) on feedback page$")
    public void iEnterNameAndAgeFeedbackPage(String name, int age) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click send on feedback page$")
    public void iClickSendFeedbackPage() throws Throwable {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("^I should see name \"([^\"]*)\" and age (\\d+) on confirmation page$")
    public void iShouldSeeNameAndAgeOnConfirmationPage(String expectedName, int expectedAge) throws Throwable {
        String actualName = driver.findElement(By.id("name")).getText();
        String actualAge = driver.findElement(By.id("age")).getText();
        assertEquals(expectedName, actualName);
        assertEquals(String.valueOf(expectedAge), actualAge);
    }

    @Given("^I am on the number input page$")
    public void iAmOnNumberInputPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" and click submit$")
    public void iEnterValueAndClickSubmit(String input) {
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys(input);
        driver.findElement(By.cssSelector("button.w3-btn")).click();
    }

    @Then("^I should see error message \"([^\"]*)\"$")
    public void iShouldSeeErrorMessage(String expectedMessage) {
        String errorMsg = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expectedMessage, errorMsg);
    }

    @Then("I should see square root result for {string} as {string}")
    public void iShouldSeeSquareRootResult(String input, String output) {
        Alert alert = driver.switchTo().alert();
        String actualTxt = alert.getText().trim();
        String expectedTxt = "Square root of " + input + " is " + output;
        assertEquals(expectedTxt, actualTxt);
        alert.accept();
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
}
