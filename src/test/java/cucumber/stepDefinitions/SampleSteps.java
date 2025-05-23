package cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

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

    @Given("^I am on the locators page$")
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

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("input[name='randomButton1']")).isEnabled());
        assertTrue(driver.findElement(By.cssSelector("input[name='randomButton2']")).isEnabled());
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

    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String error) throws Throwable {
        assertEquals(error, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() throws Throwable {
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

    @Given("^I am on provide feedback page$")
    public void iAmOnProvideFeedbackPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
    }

    @When("^I set name: \"([^\"]*)\"$")
    public void iSetName(String name) {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^I set age: (\\d+)$")
    public void iSetAge(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click send button")
    public void iClickSendButton() throws Throwable {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("^I see displayed \"([^\"]*)\" in name field$")
    public void iSeeDisplayedName(String name) throws Throwable {
        assertTrue(driver.getCurrentUrl().contains("https://acctabootcamp.github.io/site/tasks/check_feedback.html"));
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @And("^I see displayed (\\d+) in age field$")
    public void iSeeDisplayedName(int age) throws Throwable {
        assertTrue(driver.getCurrentUrl().contains("https://acctabootcamp.github.io/site/tasks/check_feedback.html"));
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
    }

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
    }

    @When("^I select feedback languages$")
    public void iSelectLanguages(List<String> languages) throws Throwable {
        for (String language : languages) {
            driver.findElement(By.cssSelector("input[type='checkbox'][value='" + language + "']")).click();
        }
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback() throws Throwable {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String languages) throws Throwable {
        assertEquals(languages, driver.findElement(By.id("language")).getText());
    }

    @When("^I set the following:$")
    public void iSetValues(Map<String, String> table) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(table.get("name"));

        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(table.get("age"));

        driver.findElement(By.cssSelector("input[type='radio'][value='"+ table.get("gender") + "']")).click();
    }

    @When("^I see my input in feedback check$")
    public void iSeeInputInFeedbackCheck(Map<String, String> table) throws Throwable {
        assertEquals(table.get("name"), driver.findElement(By.id("name")).getText());
        assertEquals(table.get("age"), driver.findElement(By.id("age")).getText());
        assertEquals(table.get("gender"), driver.findElement(By.id("gender")).getText());
    }
}
