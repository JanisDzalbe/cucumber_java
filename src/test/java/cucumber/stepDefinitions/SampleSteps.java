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
import static org.junit.jupiter.api.Assertions.assertFalse;
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


    //sample 3 Task

    @Given("^I am on Feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name in Feedback page: \"([^\"]*)\"$")
    public void iEnterNameInFeedbackPage(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^I enter age in Feedback page: (\\d+)$")
    public void iEnterAgeInFeedbackPage(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click send button$")
    public void iClickSendButton() throws Throwable {
        driver.findElement(By.cssSelector("[type=\"submit\"]")).click();
    }

    @And("^I see name \"([^\"]*)\" in check Feedback page$")
    public void iSeeNameInFeedbackPage(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @And("^I see age (\\d+) in check Feedback page$")
    public void iSeeAgeInCheckFeedbackPage(int age) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
    }


    //Task 1

    @Given("^I am on Number page$")
    public void iAmOnNumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter a \"([^\"]*)\" in the number field$")
    public void iEnterAValue(String number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click submit button$")
    public void iClickSubmitButton() throws Throwable {
        driver.findElement(By.className("w3-orange")).click();
    }

    @Then("^I see the error message \"([^\"]*)\"$")
    public void iSeeErrorMessage(String expectedError) throws Throwable {
        assertEquals(expectedError,
                driver.findElement(By.id("ch1_error")).getText());
    }

    @When("^I enter a number: (\\d+)$")
    public void iEnterANumber(int number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(String.valueOf(number));
    }

    @Then("^I see message \"([^\"]*)\" in the popup window$")
    public void iSeeMessageInPopup(String expectedMessage) throws Throwable {
        Alert alert = driver.switchTo().alert();
        assertEquals(expectedMessage, alert.getText());
        alert.accept();
    }

    @And("^I do not see error message$")
    public void iDoNotSeeErrorMessage() throws Throwable {
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }


    //Sample 4 task

    //    @Given("^I am on Feedback page$")
//    public void iAmOnFeedbackPage() {
//        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
//    }
    @When("I select Feedback languages")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String lang : languages) {
            driver.findElement(By.cssSelector("input[value='" + lang + "']")).click();
        }
    }

    @And("I click send feedback")
    public void iClickSendFeedback() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("I can see languages {string} in feedback check")
    public void iCanSeeLanguagesInFeedbackCheck(String expected) {
        String actual = driver.findElement(By.cssSelector("#language")).getText();
        assertEquals(expected, actual);
    }

    //Sample 5
    @When("I fill feedback form with:")
    public void iFillFeedbackFormWith(Map<String, String> data) {

        WebElement nameInput = driver.findElement(By.cssSelector("input[placeholder='Name']"));
        nameInput.clear();
        nameInput.sendKeys(data.get("name"));

        WebElement ageInput = driver.findElement(By.cssSelector("input[name='age']"));
        ageInput.clear();
        ageInput.sendKeys(data.get("age"));

        String gender = data.get("gender");
        String genderValue = gender.toLowerCase();

        driver.findElement(By.cssSelector("input[name='gender'][value='" + genderValue + "']")).click();
    }

//    @And("I click send feedback")
//    public void iClickSendFeedback() {
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//    }

    @Then("I see feedback result:")
    public void iSeeFeedbackResult(Map<String, String> expected) {
        String actualName = driver.findElement(By.id("name")).getText();
        String actualAge = driver.findElement(By.id("age")).getText();
        String actualGender = driver.findElement(By.id("gender")).getText();

        assertEquals(expected.get("name"), actualName);
        assertEquals(expected.get("age"), actualAge);
        assertEquals(expected.get("gender"), actualGender);
    }
}



