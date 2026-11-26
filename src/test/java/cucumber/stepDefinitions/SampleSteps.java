package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    // ------------------
    // sample task
    // ------------------

    @When("^I am on the locators page$")
    public void iAmOnLocatorsPage() {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
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

    @Then("^I see error: \"([^\"]*)\"$")
    public void ISeeErrorMessage(String errorMessage) {
        assertEquals(errorMessage, driver.findElement(By.id("error")).getText());
    }

    @Then("^I am not navigated to age message page$")
    public void IamNotNavigatedToAgeMessagePage() {
        assertTrue(driver.findElement(By.xpath("//h1[text()='Age page']")).isDisplayed());


    }

    @Given("^I am on the feedback page$")
    public void iAmOnLFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name \"([^\"]*)\" in feedback page$")
    public void iEnterNameOnFeedbackPage(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }


    @When("^I enter age (//d*) in feedback page$")
    public void iEnterAgeInFeedbackPage(String age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(age);
    }

    @When("^I click send feedback$")
    public void clickSendFeedback() {
        driver.findElement(By.cssSelector(".w3-btn-block.w3-blue")).click();
    }
    @When("^I select feedback languages$")
    public void selectFeedbackLanguages(List<String> languages) {
        for (String language : languages) {
            driver.findElement(By.xpath("//input[@type='checkbox' and @value='" + language + "']")).click();
        }
    }
    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void canSeeLanguagesInFeedbackCheck(String expectedLanguages) {
        String actualLanguages = driver.findElement(By.id("language")).getText();
        assertEquals(expectedLanguages, actualLanguages);
    }

    //----------------
    //Sample 5
    //-----------------

    @When("^I enter feedback data$")
    public void IEnterFeedbackData(Map<String, String> data) {
        driver.findElement(By.id("fb_name")).sendKeys(data.get("name"));
        driver.findElement(By.id("fb_age")).sendKeys(data.get("age"));
        driver.findElement(By.xpath("//input[@type='radio' and @value='" + data.get("genre") + "']")).click();
    }

    @When("^I click send button$")
    public void IClickSendButton() {
        driver.findElement(By.cssSelector(".w3-btn-block.w3-blue")).click();
    }
    @Then("^I see feedback data on check page$")
    public void ISeeFeedbackDataOnCheckPage(Map<String, String> expected) {
        assertEquals(expected.get("name"),  driver.findElement(By.id("name")).getText());
        assertEquals(expected.get("age"),   driver.findElement(By.id("age")).getText());
        assertEquals(expected.get("genre"), driver.findElement(By.id("gender")).getText());
    }

}

