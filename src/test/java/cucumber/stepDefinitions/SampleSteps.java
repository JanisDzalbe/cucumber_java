package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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


    ////----------------------------------Sample1----------------------------------//
    @When("I am on the locators page")
    public void iAmOnTheLocatorsPage() {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("I should see both locators page headers")
    public void iShouldSeeBothLocatorsPageHeaders() {
        WebElement header1 = driver.findElement(By.id("heading_1"));
        WebElement header2 = driver.findElement(By.id("heading_2"));
        assertEquals("Heading 1", header1.getText());
        assertEquals("Heading 2 text", header2.getText());
    }

    @And("Buttons in Locators page are clickable")
    public void buttonsInLocatorsPageAreClickable() {

        assertTrue(driver.findElement(By.cssSelector("[name=\"randomButton1\"]")).isEnabled());
        assertTrue(driver.findElement(By.cssSelector("[name=\"randomButton2\"]")).isEnabled());
    }

    ////----------------------------------Sample2----------------------------------//

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String expectedError) {
        String actualError = driver.findElement(By.id("error")).getText();
        assertEquals(expectedError, actualError);
    }

    @And("I am not navigated to age message page")
    public void iAmNotNavigatedToAgeMessagePage() {

        assertFalse(driver.getCurrentUrl().contains("/age_2.html"));
    }


    ////----------------------------------Sample3----------------------------------//

    @Given("I navigate to provide feedback page")
    public void iNavigateToProvideFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");

    }

    @When("I enter name \"([^\\\"]*)\" and age (\\d*)$")
    public void iEnterNameAndAge(String name, String age) {
        WebElement nameField = driver.findElement(By.id("fb_name"));
        nameField.clear();
        nameField.sendKeys(name);

        WebElement ageField = driver.findElement(By.id("fb_age"));
        ageField.clear();
        ageField.sendKeys(age);
    }


    @And("I click Send button")
    public void iClickSendButton() {
        driver.findElement(By.cssSelector("[type=\"submit\"]")).click();
    }

    @Then("^I should see name \"([^\"]*)\" and age (\\d*) displayed in the feedback fields$")
    public void iShouldSeeNameAndAgeDisplayedInTheFeedbackFields(String expectedName, String expectedAge) {
        String actualName = driver.findElement(By.id("name")).getText();
        String actualAge = driver.findElement(By.id("age")).getText();

        assertEquals(expectedName, actualName);
        assertEquals(expectedAge, actualAge);
    }


    ////----------------------------------Sample4----------------------------------//


    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String language : languages) {
            driver.findElement(By.cssSelector("[type='checkbox'][value='" + language + "']")).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) {
        String actualLanguages = driver.findElement(By.id("language")).getText();

        assertEquals(expectedLanguages, actualLanguages);
    }

    ////----------------------------------Sample5----------------------------------//


    @When("^I enter feedback values:$")
    public void iEnterFeedbackValues(Map<String, String> valuesToEnter) {
       /* driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(valuesToEnter.get("name"));

        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(valuesToEnter.get("age"));*/
      ;
        iEnterNameAndAge(valuesToEnter.get("name"), valuesToEnter.get("age"));
        String genreValue = valuesToEnter.get("genre");
     //   driver.findElement(By.cssSelector("[type='radio'][value='" + genreValue + "']")).click();
        driver.findElement(By.xpath("//input[@name='gender' and @value='" + genreValue + "']")).click();

    }


    @Then("^I should see name, age and genre in feedback check$")
    public void assertNameAgeGenreInFeedbackReviewPage(Map<String, String> inputMap) {
        iShouldSeeNameAndAgeDisplayedInTheFeedbackFields(inputMap.get("name"), inputMap.get("age"));
        assertGenreInFeedbackReviewPage(inputMap.get("genre"));
    }

    public void assertGenreInFeedbackReviewPage(String expectedGenre) {
        String actualGenre = driver.findElement(By.id("gender")).getText();
        assertEquals(expectedGenre, actualGenre);
    }
}