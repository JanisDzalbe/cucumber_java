package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    //Sample 1 task
    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeLocatorsPageHeaders() throws Throwable {
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
        assertEquals("Heading 1",
                driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text",
                driver.findElement(By.id("heading_2")).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("input[name='randomButton1']")).isEnabled());
        assertTrue(driver.findElement(By.id("buttonId")).isEnabled());
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

    //Sample 2 Task
    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeAgePageError(String error) throws Throwable {
        assertTrue(driver.findElement(By.id("error")).isDisplayed());
        assertEquals(error, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgePage() throws Throwable {
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age",
                driver.getCurrentUrl());
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
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }

    //Sample 3 task

    @Given("^I (?:am on|open) feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I set name: \"([^\"]*)\"$")
    public void iSetName(String name) throws Throwable {
        driver.findElement(By.cssSelector("#fb_name")).clear();
        driver.findElement(By.cssSelector("#fb_name")).sendKeys(name);
    }

    @And("^I set age: (\\d+)$")
    public void iSetAge(int age) throws Throwable {
        driver.findElement(By.cssSelector("#fb_age")).clear();
        driver.findElement(By.cssSelector("#fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click Send button$")
    public void iClickSendAge() throws Throwable {
        driver.findElement(By.cssSelector("button[type='submit'].w3-blue")).isEnabled();
        driver.findElement(By.cssSelector("button[type='submit'].w3-blue")).click();
    }

    @Then("^I see \"([^\"]*)\" in check feedback page$")
    public void iSeeNameInFeedbackCheck(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.cssSelector("#name")).getText());
    }

    @And("^I see (\\d+) in check feedback page$")
    public void iSeeAgeInFeedbackCheck(int age) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.cssSelector("#age")).getText());
    }



    //Task 1

    @Given("^I (?:am on|open) Number page$")
    public void iAmOnNumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in the number field$")
    public void iEnterInput(String input) throws Throwable {
        driver.findElement(By.cssSelector("#numb")).clear();
        driver.findElement(By.cssSelector("#numb")).sendKeys(input);
    }

    @When("^I press the submit button$")
    public void iPressSubmit() throws Throwable {
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='button'].w3-orange"));
        assertTrue(submitButton.isDisplayed());
        assertTrue(submitButton.isEnabled());
        submitButton.click();
    }

    @Then("^I see the error message \"([^\"]*)\"$")
    public void iSeeErrorMessage(String error) throws Throwable {
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errorMessage.isDisplayed());

        String actualError = errorMessage.getText();

        assertEquals(error, actualError);
    }



    @When("^I enter number: (\\d+)$")
    public void iEnterValidNumber(int number) throws Throwable {
        driver.findElement(By.cssSelector("#numb")).clear();
        driver.findElement(By.cssSelector("#numb")).sendKeys(String.valueOf(number));
    }

    @Then("^I see the popup window for number: (\\d+)$")
    public void iSeePopupWindow(int number) throws Throwable {
        Alert alert = driver.switchTo().alert();

        String squareRoot = String.format("%.2f", Math.sqrt(number));
        String expectedAlertText =
                "Square root of " + number + " is " + squareRoot;

        assertEquals(expectedAlertText, alert.getText());

        alert.accept();

        WebElement error = driver.findElement(By.id("ch1_error"));
        assertFalse(error.isDisplayed());
    }
}
