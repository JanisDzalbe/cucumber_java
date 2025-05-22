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

public class SampleSteps {
    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site");
    }

    // Sample 1
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
                driver.findElement(By.cssSelector("heading_1")).getText());
        assertEquals("Heading 2",
                driver.findElement(By.cssSelector("heading_2")).getText());
    }
    @Then("^Buttons in Locators page are clickable")
    public void locatorPageButtonsAreClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("[name=\"randomButton1\"]")).isEnabled());
        assertTrue(driver.findElement(By.cssSelector("[name=\"randomButton2\"]")).isEnabled());
    }

    @And("^I should see home page description$")
    public void iShouldSeeHomePageDescription() throws Throwable {
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                driver.findElement(By.cssSelector("p")).getText());
    }

    // Sample 2
    // When I enter name: "John"
    @When("^I  enter name: \"([^\"]*)\"$")
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

    // And I click submit age
    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    // Then I see error: "You haven't entered anything in age field"
    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("error")).getText());
    }

    // And I am not navigated to age message page
    @Then("^I am not navigated to age message page$")
    public void iAmNotOnAgePage() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/examples/age", driver.getCurrentUrl());
    }

    // Sample 3
    @Given("^I navigate to feedback page \"([^\"]*)\"$")
    public void iNavigateToFeedbackPage(String url) throws Throwable {
        driver.get(url);
    }

    @When("^I enter feedback name: \"([^\"]*)\"$")
    public void iEnterFeedbackName(String name) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter feedback age: (\\d+)$")
    public void iEnterFeedbackAge(int age) throws Throwable {
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
    }

    @And("^I click Send button$")
    public void iClickSendButton() throws Throwable {
        driver.findElement(By.id("send")).click();
    }

    @Then("^I verify name field displays: \"([^\"]*)\"$")
    public void iVerifyNameFieldDisplays(String expectedName) throws Throwable {
        String actualName = driver.findElement(By.id("name")).getAttribute("value");
        assertEquals(expectedName, actualName);
    }

    @And("^I verify age field displays: (\\d+)$")
    public void iVerifyAgeFieldDisplays(int expectedAge) throws Throwable {
        String actualAge = driver.findElement(By.id("age")).getAttribute("value");
        assertEquals(String.valueOf(expectedAge), actualAge);
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




    // Task 1

    // Number entry step definitions
    @Given("^I navigate to number entry page \"([^\"]*)\"$")
    public void iNavigateToNumberEntryPage(String url) throws Throwable {
        driver.get(url);
    }

    @When("^I enter number: \"([^\"]*)\"$")
    public void iEnterNumber(String number) throws Throwable {
        driver.findElement(By.id("number")).clear();
        driver.findElement(By.id("number")).sendKeys(number);
    }

    @And("^I click submit number$")
    public void iClickSubmitNumber() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see number error message: \"([^\"]*)\"$")
    public void iSeeNumberErrorMessage(String expectedErrorMessage) throws Throwable {
        assertEquals(expectedErrorMessage, driver.findElement(By.id("error")).getText());
    }

    @Then("^I see number success message: \"([^\"]*)\"$")
    public void iSeeNumberSuccessMessage(String expectedSuccessMessage) throws Throwable {
        assertEquals(expectedSuccessMessage, driver.findElement(By.id("message")).getText());
    }
}
