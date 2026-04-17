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

    @When("^I am on the locators page$")
    public void iAmOnLocatorsPage(){driver.get("https://janisdzalbe.github.io/example-site/examples/locators");}

    @Then("^I should see both locators page headers$")
        public void seeBothLocatorsPage(){
            assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
            assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsAreClickable(){
        assertTrue(driver.findElement(By.name("randomButton1")).isDisplayed());
        assertTrue(driver.findElement(By.name("randomButton2")).isDisplayed());
    }

    @Then("^I see error: \"([^\"]*)\"$")
        public void seeError(String message){
        assertTrue(driver.findElement(By.id("error")).isDisplayed());
        assertEquals(message, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void notNavigated(){
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());
    }

    @Given("^I navigate to page$")
    public void navigateToPage(){
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter feedback name: \"([^\"]*)\"$")
    public void enterFeedbackName(String name) {
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @When("^I enter feedback age: \"([^\"]*)\"$")
    public void enterFeedbackAge(String age) {
        driver.findElement(By.id("fb_age")).sendKeys(age);
    }

    @And("^I click send button$")
    public void clickSendButton(){
        driver.findElement(By.className("w3-btn-block")).click();
    }

    @Then("^I should see name \"([^\"]*)\" and age \"([^\"]*)\"")
    public void seeNameAge(String name, String age){
        assertEquals(name, driver.findElement(By.id("name")).getText());
        assertEquals(age, driver.findElement(By.id("age")).getText());
    }

    @When("^I select feedback languages$")
    public void selectLanguages(List<String> languages){
        for (String l : languages){
            driver.findElement(By.cssSelector("[value='" + l + "']")).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check")
    public void seeLanguages(String languages){
        assertEquals(languages, driver.findElement(By.id("language")).getText());
    }
}
