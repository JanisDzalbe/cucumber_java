package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.*;

public class SampleSteps {
    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site");
    }

    @Given("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter name \"([^\"]*)\" and age (\\d+) on feedback page$")
    public void iEnterNameAndAgeOnFeedbackPage(String name, int age) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
        assertEquals("Heading 1",
                driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text",
                driver.findElement(By.id("heading_2")).getText());
    }

    @Then("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("body > input[type=button]:nth-child(9)")).isEnabled());
        assertTrue(driver.findElement(By.id("buttonId")).isEnabled());
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
        driver.get("https://acctabootcamp.github.io/site/examples/age");
    }

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
    }

    @Then("^I click submit$")
    public void iClickSubmit() throws Throwable {
        driver.findElement(By.className("w3-orange")).click();
    }

    @When("^I enter correct number: (\\d+)$")
    public void iEnterCorrectNumber(int number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(String.valueOf(number));
    }

    @Then("^I see the pop-up with answer for number: (\\d+)$")
    public void iSeeThePopUpWithAnswer(String number) throws Throwable {
        double expectedSqrt = Math.sqrt(Double.parseDouble(number));
        String expSqrtForm = String.format(Locale.US, "%.2f", expectedSqrt);
        Alert alert = driver.switchTo().alert();
        String expectedAlert = "Square root of " + number + " is " + expSqrtForm;
        Assertions.assertEquals(expectedAlert, alert.getText());
        alert.accept();
    }

    @And("^No error is visible after pop-up is closed$")
    public void noErrorIsVisibleAfterPopUpIsClosed() {
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    @And("^I click send on feedback page$")
    public void iClickSendOnFeedbackPage() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see name \"([^\"]*)\" in check feedback page$")
    public void iSeeNameInCheckFeedbackPage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("name")).getText());
    }

    @Then("^I see age (\\d+) in check feedback page$")
    public void iSeeAgeInCheckFeedbackPage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("age")).getText());
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
    public void iSeeError(String message) throws Throwable {
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

    @When("^I enter number: (.*)$")
    public void iEnterNumberInt(String number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("I see text: \"([^\"]*)\"$")
    public void iSeeText(String error) throws Throwable {
        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
    }

    @When("I select feedback languages:")
    public void iSelectFeedbackLanguages(List<String> languages) throws Throwable {
        for (String language : languages) {
            driver.findElement(By.xpath("//*[@value='" + language + "']")).click();
        }
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback() throws Throwable {
        driver.findElement(By.className("w3-blue")).click();
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeChoosedLanguagesInFeedbackCheck(String language) throws Throwable {
        assertEquals(language, driver.findElement(By.id("language")).getText());
    }

    @When("^I enter feedback values:$")
    public void iEnterFeedbackValues(Map<String, String> valuesToEnter) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(valuesToEnter.get("name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(valuesToEnter.get("age"));
        driver.findElement(By.cssSelector("[value='" + valuesToEnter.get("gender") + "']")).click();
    }

    @Then("^I can see input in feedback check$")
    public void inputForFeedbackIsSeen(Map<String, String> valuesToEnter) throws Throwable {
        assertEquals(valuesToEnter.get("name"), driver.findElement(By.id("name")).getText());
        assertEquals(valuesToEnter.get("age"), driver.findElement(By.id("age")).getText());
        assertEquals(valuesToEnter.get("gender"), driver.findElement(By.id("gender")).getText());
    }

}

