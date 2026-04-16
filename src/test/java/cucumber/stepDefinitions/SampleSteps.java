package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SampleSteps {
    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site");
    }

    // new
    @When("^I am on the locators page$")
    public void iamonthelocatorpage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void ishouldseebothlocatorspageheaders() throws Throwable {
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @Then("^Buttons in Locators page are clickable$")
    public void buttoninlocatorspageareclickable() throws Throwable {
        assertTrue(driver.findElement(By.xpath("//input[@name='randomButton1']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//input[@name='randomButton1']")).isEnabled());
        assertTrue(driver.findElement(By.xpath("//input[@name='randomButton2']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//input[@name='randomButton2']")).isEnabled());
    }



    // new

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
        driver.findElement(By.cssSelector("#submit")).click();
    }

    // new

    @Then("^I see error: \"([^\"]*)\"$")
    public void IseeerrorYouhavententeredanythinginagefield(String errorMessage) throws Throwable{
        assertEquals(errorMessage, driver.findElement(By.cssSelector("#error")).getText());
    }

    @Then("^I am not navigated to age message page$")
    public void iamnotnavigatedtoagemessagepage() throws Throwable {
//        driver.get("https://janisdzalbe.github.io/example-site/examples/age");
        assertFalse(driver.getCurrentUrl().contains("age_2"));
    }

    // new

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
        assertTrue(driver.findElement(By.className("w3-black")).isDisplayed());
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

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter feedback name: \"([^\"]*)\"$")
    public void iEnterFeedbackName(String name) {
        driver.findElement(By.cssSelector("#fb_name")).clear();
        driver.findElement(By.cssSelector("#fb_name")).sendKeys(name);
    }

    @And("^I enter feedback age: (\\d+)$")
    public void iEnterFeedbackAge(int age) {
        driver.findElement(By.cssSelector("#fb_age")).clear();
        driver.findElement(By.cssSelector("#fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback() {
        driver.findElement(By.xpath("//button[text()='Send']")).click();
    }

    @Then("^I see feedback name: \"([^\"]*)\"$")
    public void iSeeFeedbackName(String expectedName) {
        assertEquals(expectedName, driver.findElement(By.id("name")).getText());
    }

    @And("^I see feedback age: \"([^\"]*)\"$")
    public void iSeeFeedbackAge(String expectedAge) {
        assertEquals(expectedAge, driver.findElement(By.id("age")).getText());
    }

    @When("^I select feedback languages$")
    public void Iselectfeedbacklanguages(List<String> languages) {
        for (String language : languages) {
            driver.findElement(By.cssSelector("input[value='" + language + "']")).click();
        }
    }


    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) {
        assertEquals(expectedLanguages, driver.findElement(By.id("language")).getText());
    }

    @When("I fill out the form like this")
    public void Ifillouttheformlikethis(Map<String, String> data){
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(data.get("name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(data.get("age"));
        String genderValue = data.get("gender").toLowerCase();
        driver.findElement(By.cssSelector("input[name='gender'][value='" + genderValue + "']")).click();
    }

    @And("^I see feedback gender: \"([^\"]*)\"$")
    public void iSeeFeedbackGender(String expectedGender) {
        assertEquals(expectedGender, driver.findElement(By.id("gender")).getText());
    }
}
