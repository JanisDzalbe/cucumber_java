package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

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

    //    ----------------------------------------------------------------------
    //    #  TODO - Add implementation for missing steps
    //    #  @Test
    //    #  Sample 1 Task
    //    --------*******************************************************-------

    @Given("^I am on the locators page$")
    public void iamonLocatorsPage() {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @And("Buttons in Locators page are clickable")
    public void buttonsinLocatorsPageareClickable() {
        assertTrue(driver.findElement(By.xpath("//input[@value =\"This is a button\"]")).isEnabled());
        assertTrue(driver.findElement(By.xpath("//input[@value=\"This is also a button\"]")).isEnabled());
    }

    //    #  TODO - Add implementation for missing steps
    //    #  @Test  -   Sample 2 Task
    //    --------------*************-------------------

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String errorMessageString) {
        assertEquals(errorMessageString, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void notNavigatedtoAgePage() {
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());
    }

//    #   TODO - create Scenario Outline for 'Give us your feedback!' page
//    #   URL: https://janisdzalbe.github.io/example-site/tasks/provide_feedback
//    #   Navigate to page
//    #   Set name and age based on test Examples
//    #   Click "Send" button and verify that previous input is displayed in correct fields

    @Given("^I am on Provide Feedback page$")
    public void iamonProvideFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
        driver.manage().window().maximize();
    }

    @When("^I enter any user name: \"([^\"]*)\"$")
    public void iEnterAnyUserName(String userName) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(userName);
    }

    @And(("^I enter user's age: (\\d+)$"))
    public void iEnterUsersAge(String age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(age);
    }

    @And("^I click send button$")
    public void iClickSubmitButton() throws Throwable {
        driver.findElement(By.xpath("//*[text()=\"Send\"]")).click();
    }

    @Then("^I see feedback confirmation message: \"([^\"]*)\"$")
    public void seeFeedbackConfirmationMessage(String message) {
        assertEquals(message, driver.findElement(By.xpath("//*[text()=\"Is this the feedback you want to give us?\"]")).getText());
    }

    @Then("^I see username: \"([^\"]*)\" on feedback review page$")
    public void iSeeUsernameOnFeedbackReviewPage(String userName) throws Throwable {
        assertEquals(userName, driver.findElement(By.id("name")).getText());
    }

    @And("^I see user's age: (\\d+)$")
    public void iSeeUsersAge(String age) throws Throwable {
        assertEquals(age, driver.findElement(By.id("age")).getText());
    }

    @And("^I click yes button$")
    public void iClickYesButton() throws Throwable {
        driver.findElement(By.xpath("//*[text()='Yes']"));
    }

    //    #  TODO - Add implementation for missing steps
//    #  @Test
//    ******************************************************************************************************************
    @Given("^I am on feedback page$")
    public void iamonFeedbackPage() {
        iamonProvideFeedbackPage();
    }

    @When("^I select feedback languages$")
    public void iselectfeedbacklanguages(List<String> languages) throws Throwable {
        for (String language : languages) {
            driver.findElement(By.cssSelector("[value='" + language + "']")).click();
        }
    }

    @And("^I click send feedback$")
    public void iclicksendfeedback() throws Throwable {
        iClickSubmitButton();
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void icanseeLanguages(String languages) throws Throwable {
        assertEquals(languages, driver.findElement(By.xpath("//span[text()=\"English,Spanish\"]")).getText());
    }
}
