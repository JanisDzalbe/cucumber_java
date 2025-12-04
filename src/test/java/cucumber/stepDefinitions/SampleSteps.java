package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    // Sample1 task
    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() {driver.get("https://janisdzalbe.github.io/example-site/examples/locators");}

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() {
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
    }

    @Then("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() {
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton1']")).isEnabled());
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton1']")).isEnabled());
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void errorHaventEnteredAnythingInAgeField(String errorText) {
        assertTrue(driver.findElement(By.id("error")).isDisplayed());
        assertEquals(errorText, driver.findElement(By.id("error")).getText());
    }

    @Then("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage(){
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age",
                driver.getCurrentUrl());
        assertFalse(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/examples/age_2"));
    }

    //sample3 task
    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage(){
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name FB: \"([^\"]*)\"$")
    public void iEnterNameFB(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^I enter age FB: (\\d+)$")
    public void iEnterAgeFB(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback() throws Throwable {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("^I see name \"([^\"]*)\" in feedback$")
    public void iSeeNameInFB(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @And("^I see age (\\d+) in feedback$")
    public void iSeeAgeInFB(int age) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
    }

    //Task1
    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage(){
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number: \"([^\"]*)\"$")
    public void iEnterNumber(String number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click submit$")
    public void iClickSubmit() throws Throwable {
        driver.findElement(By.className("w3-btn")).click();
    }

    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String message) {
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());
    }

    // working scenario
    @And("^I enter number: (\\d+)$")
    public void iEnterNumber(int number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(String.valueOf(number));
    }

    @Then("^I see popup message: \"([^\"]*)\"$")
    public void iSeePopupMessage(String message) {
        Alert alert = driver.switchTo().alert();
        assertEquals(message, alert.getText());
        alert.accept();
    }

    @And("^I don't see error message$")
    public void iDontSeeErrorMessage() {
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    //Sample4 task
    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> values) throws Throwable {
        for (String value : values) {
            driver.findElement(By.cssSelector("[value='" + value + "']")).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iSeeLanguagesInFeedbackCheck(String languages) {
        assertEquals(languages, driver.findElement(By.id("language")).getText());
    }

    //Sample5 task
    @When("^I enter feedback values:$")
    public void iEnterFeedbackValues(Map<String, String> inputMap) throws Throwable{
        iEnterNameFB(inputMap.get("name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(inputMap.get("age"));
        driver.findElement(By.cssSelector("[type='radio'][value='"+inputMap.get("gender").toLowerCase()+"']")).click();
    }

    @Then("^I can see values in feedback check$")
    public void iCanSeeValuesInFeedbackCheck(Map<String, String> inputMap) throws Throwable{
        assertEquals(inputMap.get("name"), driver.findElement(By.id("name")).getText());
        assertEquals(inputMap.get("age"), driver.findElement(By.id("age")).getText());
        assertEquals(inputMap.get("gender").toLowerCase(), driver.findElement(By.id("gender")).getText());
    }
}

