package cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static java.awt.Color.blue;
import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.By.*;

///
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
                driver.findElement(cssSelector("h1")).getText());
    }

    @And("^I should see home page description$")
    public void iShouldSeeHomePageDescription() throws Throwable {
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                driver.findElement(cssSelector("p")).getText());
    }

    @When("^I enter name: \"([^\"]*)\"$")
    public void iEnterName(String name) throws Throwable {
        driver.findElement(id("name")).clear();
        driver.findElement(id("name")).sendKeys(name);
    }

    @And("^I enter age: (\\d+)$")
    public void iEnterAge(int age) throws Throwable {
        driver.findElement(id("age")).sendKeys(String.valueOf(age));
    }

    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/age");
    }

    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(id("submit")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(id("message")).getText());
    }

    @When("^I enter values:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        driver.findElement(id("name")).clear();
        driver.findElement(id("name")).sendKeys(valuesToEnter.get("name"));
        driver.findElement(id("age")).clear();
        driver.findElement(id("age")).sendKeys(valuesToEnter.get("age"));
    }

    @And("^I should see menu$")
    public void iShouldSeeMenu() throws Throwable {
        assertTrue(driver.findElement(className("w3-navbar")).isDisplayed());
    }

    @And("^I click the result checkbox button$")
    public void iClickTheResultCheckboxButton() throws Throwable {
        driver.findElement(id("result_button_checkbox")).click();
    }

    @When("^I clicked on checkboxes:$")
    public void iClickedOnCheckboxes(List<String> values) throws Throwable {
        for (String value : values) {
            driver.findElement(cssSelector("[value='" + value + "']")).click();
        }
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String message) throws Throwable {
        assertEquals(message, driver.findElement(id("result_checkbox")).getText());
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }

    //=========================================
    //Sample1Steps
    //=========================================

    @Given("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeebothLocatorsPageHeader() throws Throwable {
        assertEquals("Heading 1",
                driver.findElement(id("heading_1")).getText());
        assertEquals("Heading 2 text",
                driver.findElement(id("heading_2")).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws Throwable {
            assertTrue(driver.findElement(name("randomButton1")).isEnabled());
            assertTrue(driver.findElement(name("randomButton2")).isEnabled());
        }

    //=========================================
    //Sample2Steps
    //=========================================

    @Then("^I see error: \\\"([^\\\"]*)\\\"$$")
    public void iSeeErrorMessage(String name) throws Throwable {
        assertEquals("You haven't entered anything in age field",
                driver.findElement(id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void agePageIsNotNavigated() throws Throwable {
        String currentUrl = driver.getCurrentUrl();
        assertNotEquals("https://janisdzalbe.github.io/example-site/examples/age_2.html", currentUrl,"I am not navigated to age message page"
        );
    }

    //=========================================
    //Sample3Steps
    //=========================================

    @Given("^I am on feedback page$")
    public void iAmOnTheFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name on feedback page: \"([^\"]*)\"$")
    public void iEnterNameOnFeedback(String name) throws Throwable {
        driver.findElement(id("fb_name")).clear();
        driver.findElement(id("fb_name")).sendKeys(name);
    }

    @And("^I enter age on feedback page: (\\d+)$")
    public void iEnterAgeOnFeedback(String age) throws Throwable {
        driver.findElement(id("fb_age")).clear();
        driver.findElement(id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click send button$")
    public void iClickSendButton() throws Throwable {
        driver.findElement(cssSelector("[type=\"submit\"]")).click();
    }

    @Then("^I see the correct name: \"([^\"]*)\"$")
    public void iSeeMessageTheCorrectName(String message) throws Throwable {
        assertEquals(message, driver.findElement(id("name")).getText());
    }

    @Then("^I see the correct age: \"([^\"]*)\"$")
    public void iSeeMessageTheCorrectAge(String message) throws Throwable {
        assertEquals(message, driver.findElement(id("age")).getText());
    }

    //=========================================
    //Sample4Steps
    //=========================================

    @When("^I select feedback languages$")
    public void iSelectLanguageOnFeedback(List<String> languages) throws Throwable {
        for (String language : languages) {
            driver.findElement(cssSelector("[type=\"checkbox\"][value='" + language + "']")).click();
        }
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback()  {
                driver.findElement(cssSelector("[type=\"submit\"]")).click();
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iSeeLanguages(String languages) throws Throwable {
        assertEquals(languages, driver.findElement(id("language")).getText());
    }

    //=========================================
    //Sample5Steps
    //=========================================

    @Given("^I am on the provide feedback page$")
    public void iAmOnProvideFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter Name, Age and Genre in feedback page:$")
    public void iEnterNameAgeGenreInFeedback(Map<String, String> inputMap)throws Throwable {
        iEnterNameOnFeedback(inputMap.get("name"));
        iEnterAgeOnFeedback(inputMap.get("age"));
        driver.findElement(By.cssSelector("[name='gender'][value='"+inputMap.get("genre")+"']")).click();
    }

    @And("^I see genre \"([^\"]*)\" in feedback review page$")
    public void assertGenreInFeedbackReviewPage(String gender){
        assertEquals(gender, driver.findElement(By.id("gender")).getText());
    }

    @And("^I see Name, Age and Genre in feedback review page$")
    public void iSeeNameAgeGenreInFeedbackReviewPage(Map<String, String> expectedValues)throws Throwable {
        assertEquals(expectedValues.get("name"), driver.findElement(By.cssSelector("#fb_thx #name")).getText());
        assertEquals(expectedValues.get("age"), driver.findElement(By.cssSelector("#fb_thx #age")).getText());
        assertEquals(expectedValues.get("genre"), driver.findElement(By.cssSelector("#fb_thx #gender")).getText());
    }

    }


