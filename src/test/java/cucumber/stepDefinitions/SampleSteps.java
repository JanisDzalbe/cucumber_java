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
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
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

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorMessage) throws Throwable {
        assertEquals(errorMessage, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() throws Throwable {
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());
    }



    @Given("I open feedback page")
    public void iOpenFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("I enter feedback name {string}")
    public void iEnterFeedbackName(String name) {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("I enter feedback age {int}")
    public void iEnterFeedbackAge(int age) {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("I click send feedback")
    public void iClickSendFeedback() {
        driver.findElement(By.xpath("//button[contains(text(),'Send')]")).click();
    }

    @Then("I see name {string} in input")
    public void iSeeNameInInput(String expected) {

        String pageText = driver.findElement(By.tagName("body")).getText();

        assertTrue(pageText.contains("Your name: " + expected));
    }

    @And("I see age {string} in input")
    public void iSeeAgeInInput(String expected) {

        String pageText = driver.findElement(By.tagName("body")).getText();

        assertTrue(pageText.contains("Your age: " + expected));
    }

    @Given("I am on feedback page")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("I select feedback languages")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String language : languages) {
            driver.findElement(By.xpath("//label[contains(.,'" + language + "')]/input | //input[contains(@value,'" + language + "')]")).click();
        }
    }

    @Then("I can see languages {string} in feedback check")
    public void iCanSeeLanguagesInFeedbackCheck(String expected) {
        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(pageText.contains("Your language: " + expected));
    }



    @When("I enter feedback values:")
    public void iEnterFeedbackValues(Map<String, String> data) {

        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(data.get("name"));

        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(data.get("age"));

        String genre = data.get("genre");

        driver.findElement(
                By.xpath("//input[@type='radio' and @value='" + genre.toLowerCase() + "']")
        ).click();
    }

    @Then("I see name {string} in feedback")
    public void iSeeNameInFeedback(String expected) {
        String text = driver.findElement(By.tagName("body")).getText();
        assertTrue(text.contains("Your name: " + expected));
    }

    @And("I see age {string} in feedback")
    public void iSeeAgeInFeedback(String expected) {
        String text = driver.findElement(By.tagName("body")).getText();
        assertTrue(text.contains("Your age: " + expected));
    }

    @And("I see genre {string} in feedback")
    public void iSeeGenreInFeedback(String expected) {

        String url = driver.getCurrentUrl();

        assertTrue(url.contains("gender=" + expected.toLowerCase()));
    }


    //  ----------------Task 1-----------------

    @Given("^I open enter number page$")
    public void iOpenEnterNumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter value \"([^\"]*)\"$")
    public void iEnterValue(String value) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    @And("^I click submit number$")
    public void iClickSubmitNumber() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("I see error message {string}")
    public void iSeeErrorMessage(String message) {

        String actual = driver.findElement(By.id("ch1_error")).getText();
        assertTrue(actual.contains(message));
    }

    @Then("I see result {string}")
    public void iSeeResult(String expected) {

        Alert alert = driver.switchTo().alert();

        String actual = alert.getText();

        assertTrue(actual.contains(expected));

        alert.accept();
    }


    //  ----------------Task 2-----------------

    @Given("I am on people with jobs page")
    public void iAmOnPeoplePage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @When("I add person with values:")
    public void iAddPerson(Map<String, String> data) {

        driver.findElement(By.id("addPersonBtn")).click();

        driver.findElement(By.id("name")).sendKeys(data.get("name"));
        driver.findElement(By.id("job")).sendKeys(data.get("job"));

        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @Then("I can see person {string} with job {string}")
    public void iCanSeePerson(String name, String job) {

        String text = driver.findElement(By.id("listOfPeople")).getText();

        assertTrue(text.contains(name));
        assertTrue(text.contains(job));
    }

    @When("I edit person {string} and change job to {string}")
    public void iEditPerson(String name, String newJob) {

        driver.findElement(
                By.xpath("//li[contains(.,'" + name + "')]//span[contains(@onclick,'openModal')]")
        ).click();

        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        jobInput.sendKeys(newJob);

        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @When("I remove person {string}")
    public void iRemovePerson(String name) {

        driver.findElement(
                By.xpath("//li[contains(.,'" + name + "')]//span[contains(@onclick,'deletePerson')]")
        ).click();
    }

    @Then("I cannot see person {string}")
    public void iCannotSeePerson(String name) {

        String text = driver.findElement(By.id("listOfPeople")).getText();

        assertFalse(text.contains(name));
    }

    @And("I click reset list")
    public void iClickReset() {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();    }

    @Then("I can see original list with 10 people")
    public void iSeeOriginalList() {

        List<WebElement> people = driver.findElements(By.cssSelector("[id^='person']"));

        assertEquals(10, people.size());
    }



}







