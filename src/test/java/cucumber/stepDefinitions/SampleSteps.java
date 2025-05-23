package cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        driver.get("https://acctabootcamp.github.io/site/examples/age");
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
        driver.get("https://acctabootcamp.github.io/site/examples/actions");
    }

    @Given("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
        assertEquals("Heading 1", driver.findElements(By.cssSelector("h2")).get(0).getText());
        assertEquals("Heading 2 text", driver.findElements(By.cssSelector("h2")).get(1).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws Throwable {
        List<WebElement> buttons = driver.findElements(By.cssSelector("input[type=button]"));
        for(WebElement button : buttons){
            assertTrue(button.isEnabled()); // I think this is the right property? couldn't find anything online
            button.click(); // not sure if I should click but, but they don't do anything, so I do here :)
            System.out.println("button!!'");
        }
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeErrorYouHavenTEnteredAnythingInAgeField(String errorText) throws Throwable {
        assertEquals(errorText, driver.findElement(By.id("error")).getText());
    }
    // TODO Note to self - when creating cases with strings, intellij suggests {string} instead of the regex pattern. Ask about that
    // Found other files here using the pattern, what's the difference?

    @And("I am not navigated to age message page")
    public void iAmNotNavigatedToAgeMessagePage() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/examples/age", driver.getCurrentUrl());
    }


    @Given("^I am on the provide feedback page$")
    public void iAmOnTheProvideFeedbackPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
    }

    @When("I enter my name: {string}")
    public void iEnterMyName(String name) {
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }


    @And("I enter my age: {string}")
    public void iEnterMyAge(String age) {
        driver.findElement(By.id("fb_age")).sendKeys(age);
    }

    @And("I click send (feedback)")
    public void iClickSend() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("I see my name is: {string}")
    public void iSeeMyNameIs(String name) {
        assertEquals(name, driver.findElement(By.cssSelector("span#name")).getText());
    }

    @And("I see my age is: {string}")
    public void iSeeMyAgeIs(String age) {
        assertEquals(age, driver.findElement(By.cssSelector("span#age")).getText());
    }

    // I did this for task 3, which I now think I misinterpreted. oops
    @And("I select my language(s): {string}")
    public void iSelectMyLanguages(String language) {
        // I want to accept several languages as input, i.e. English, French. Only way of doing it I could think of lol
        //https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
        String [] languages = language.split(" ");
        for (String lang : languages) {
            driver.findElement(By.cssSelector("input[value='" + lang + "']")).click();
        }
    }

    // I can't seem to figure out how to get (?:is|are) to work with {string}. it just refuses to work for some reason.
    // Spent literally an hour just to decide to try it with the regex. Why is it like that. Why does it refuse to work otherwise.
    @And("^I see my language(?:s)? (?:is|are): \"([^\"]*)\"$")
    public void iSeeMyLanguagesAre(String language) {
        String [] languages = language.split(" ");
        for (String lang : languages) {
            // contains here because I can't guarantee the order of input + don't wanna bother with commas
            // May not be best practice if the output somehwo shows languages that were not selected
            assertTrue(driver.findElement(By.cssSelector("span#language")).getText().contains(lang));
        }
    }

    @When("^I select feedback languages:$")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String lang : languages) {
            driver.findElement(By.cssSelector("input[value='" + lang + "']")).click();
        }
    }

    @Then("I can see languages {string} in feedback check")
    public void iCanSeeLanguagesInFeedbackCheck(String languages) {
        assertEquals(languages, driver.findElement(By.cssSelector("span#language")).getText());
        // would it be better practice here to split it into strings and assert that they are in it? in case the order changes? not sure
    }

    @When("I enter my information:")
    public void iEnterMyInformation(Map<String, String> data) {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(data.get("name"));

        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(data.get("age"));

        driver.findElement(By.cssSelector("input[type=radio][value=" + data.get("gender") + "]")).click();
    }

    @Then("I see my information:")
    public void iSeeMyInformation(Map<String, String> data) {
        assertEquals(data.get("name"), driver.findElement(By.cssSelector("span#name")).getText());
        assertEquals(data.get("age"), driver.findElement(By.cssSelector("span#age")).getText());
        assertEquals(data.get("gender"), driver.findElement(By.cssSelector("span#gender")).getText());
    }
}
