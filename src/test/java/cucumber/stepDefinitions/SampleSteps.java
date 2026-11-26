package cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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

    //Sample 1
    //-----------------

    @When("^I am on the locators page$")
    public void iAmOnLocatorsPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws Throwable {
        assertTrue(driver.findElement(By.name("randomButton1")).isEnabled());
        assertTrue(driver.findElement(By.name("randomButton2")).isEnabled());
    }

    //Sample 2
    //--------------------

    @Then("^I see error: \"([^\"]*)\"$")
    public void SeeError(String error) throws Throwable {
        assertEquals(error, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() throws Throwable {
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());
    }

    //Sample 2
    //---------------------------
    @Given("^I am on Give us your feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }


    @When("^I enter feedback name: \"([^\"]*)\"$")
    public void iEnterFeedbackName(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^I enter feedback age: (\\d+)$")
    public void enterFeedbackAge(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click send button$")
    public void clickSendButton() throws Throwable {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("^I see feedback with name \"([^\"]*)\" and age \"([^\"]*)\"$")
    public void feedbackWithNameAndAge(String name, String age) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
        assertEquals(age, driver.findElement(By.id("age")).getText());
    }

    //Sample 3
    //-------------------

    @When("I select feedback languages")
    public void selectFeedbackLanguages(List<String> languages) {
        for (String language : languages) {
            WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox' and @name='language' and @value='" + language + "']")
            );
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    @Then("I can see languages {string} in feedback check")
    public void seeLanguagesInFeedbackCheck(String expectedLanguages) {
        WebElement languageResult = driver.findElement(By.id("language"));
        assertEquals(expectedLanguages, languageResult.getText());
    }

    //Sample 5
    //--------------
    @When("I enter feedback values:")
    public void enterFeedbackValues(Map<String, String> values) {
        WebElement nameInput = driver.findElement(By.id("fb_name"));
        nameInput.clear();
        nameInput.sendKeys(values.get("name"));


        WebElement ageInput = driver.findElement(By.id("fb_age"));
        ageInput.clear();
        ageInput.sendKeys(values.get("age"));

        String genreValue = values.get("genre").toLowerCase();
        WebElement genreRadio = driver.findElement(
                By.xpath("//input[@type='radio' and @name='gender' and @value='" + genreValue + "']")
        );
        genreRadio.click();
    }

    @Then("I see feedback values:")
    public void seeFeedbackValues(Map<String, String> expectedValues) {
        assertEquals(expectedValues.get("name"), driver.findElement(By.id("name")).getText());
        assertEquals(expectedValues.get("age"), driver.findElement(By.id("age")).getText());
        assertEquals(expectedValues.get("genre"), driver.findElement(By.id("gender")).getText());
    }

}
