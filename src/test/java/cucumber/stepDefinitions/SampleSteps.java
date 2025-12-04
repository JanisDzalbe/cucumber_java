package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.swing.*;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SampleSteps {
    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site");
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

    // Sample1 Task
    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton1']")).isEnabled());
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton2']")).isEnabled());
    }

    // Sample2 Task
    @Then("^I see error: \"([^\"]*)\"$")
    public void errorMsg(String message) throws Throwable {
        assertTrue(driver.findElement(By.id("error")).isDisplayed());
        assertEquals(message, driver.findElement(By.id("error")).getText());
    }
    @And("^I am not navigated to age message page$")
    public void msgPage() throws Throwable {
        assertFalse(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/examples/age_2"));
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());
    }

    // Sample3 Task
    @Given("^I am on Feedback page$")
    public void feedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name in Feedback page: \"([^\"]*)\"$")
    public void newName(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^I enter age in Feedback page: (\\d+)$")
    public void addedAge(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click Send button$")
    public void clickSendButton() throws Throwable {
        driver.findElement(By.cssSelector("[type=\"submit\"]")).click();
    }

    @Then("^I see input name: \"([^\"]*)\"$")
    public void inputName(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @And("^I see input age: (\\d+)$")
    public void inputAge(int age) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
    }

    //Task1


    @When("^I enter invalid number or text: (.*)$")
    public void iEnterInvalidNumberOrText(String input) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(String.valueOf(input));
    }

    @And("^I press Submit button$")
    public void clickSubmitButton() throws Throwable {
        driver.findElement(By.className("w3-btn")).click();
    }

    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());
    }

    @When("^I enter number: (\\d+)$")
    public void iEnterNumber(int number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(String.valueOf(number));
    }

    @Then("^I see alert message: \"([^\"]*)\"$")
    public void iSeeAlertMessage(String message) throws Throwable {
        Alert alert = driver.switchTo().alert();
        assertEquals(message, alert.getText());
        alert.accept();
    }

    @And("^No error is shown$")
    public void noErrorIsShown() throws Throwable {
        assertTrue(driver.findElements(By.id("ch1_error")).isEmpty());
    }

    @Then("^I select feedback languages$")
    public void isSelectFeedbackLaguages$(List<String> languages) throws Throwable {
       for (String language : languages) {
           driver.findElement(By.xpath("//*[@type='checkbox' and @value='" + language + "']")).click();
       }
    }
    @Then("I can see languages {string} in feedback check")
    public void isAssertLanguagesInFeedbackCheck$(String languages) throws Throwable {
        assertEquals(languages, driver.findElement(By.id("language")).getText());
    }
    @Given("^I am on feedback page$")
    public void iAmOnEnteraNumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }
    @And("^I click send feedback$")
    public void iClickSendFeedback() throws Throwable {
        driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();
    }

    //Sample 5
    @When("^I input values in Feedback page$")
    public void iInputValuesInFeedbackPage(Map<String, String> inputMapp) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(inputMapp.get("Name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(inputMapp.get("Age"));
        driver.findElement(By.cssSelector("[type='radio'][value = '"+inputMapp.get("Genre").toLowerCase()+"']")).click();
    }

    @When("^I can see values in feedback check$")
    public void iAssertValuesInFeedbackCheck(Map<String, String> inputMapp) throws Throwable {
        assertEquals(inputMapp.get("Name"), driver.findElement(By.id("name")).getText());
        assertEquals(inputMapp.get("Age"), driver.findElement(By.id("age")).getText());
        assertEquals(inputMapp.get("Genre").toLowerCase(), driver.findElement(By.id("gender")).getText());
    }
}
