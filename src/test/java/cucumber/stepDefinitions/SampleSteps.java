package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SampleSteps {
    private WebDriver driver;

    By feedbackNameInput = By.id("feedback-name");
    By feedbackAgeInput = By.id("feedback-age");
    By sendButton = By.id("submit-feedback");
    By resultName = By.id("result-name");
    By resultAge = By.id("result-age");

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


    //Sample 1 task

    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothHeaders() {
        WebElement h1 = driver.findElement(By.id("heading_1"));
        WebElement h2 = driver.findElement(By.id("heading_2"));

        assertTrue("Heading 1 is not visible!", h1.isDisplayed());
        assertTrue("Heading 2 is not visible!", h2.isDisplayed());

    }

    @Then("^Buttons in Locators page are clickable$")
    public void buttonsAreClickable() {

        WebElement btn1 = driver.findElement(By.xpath("//input[@type='button' and @value='This is a button']"));
        WebElement btn2 = driver.findElement(By.id("buttonId"));

        assertTrue("Button 1 is not clickable", btn1.isDisplayed() && btn1.isEnabled());
        assertTrue("Button 2 is not clickable", btn2.isDisplayed() && btn2.isEnabled());

        btn1.click();
        btn2.click();
    }

    //Sample 2 task

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String expectedError) {
        WebElement error = driver.findElement(By.id("error"));
        assertEquals(expectedError, error.getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() {

        List<WebElement> message = driver.findElements(By.id("message"));

        assertTrue("User was incorrectly navigated to age message page!", message.isEmpty());

        assertTrue(driver.findElement(By.tagName("h2")).getText().contains("Name"));
    }

    //Sample 3 task

    @Given("I am on the feedback page")
    public void iAmOnTheFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("I enter feedback name {string}")
    public void iEnterFeedbackName(String name) {
        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys(name);
    }

    @And("^I enter feedback age (.*)$")
    public void iEnterFeedbackAge(String age) {
        WebElement ageInput = driver.findElement(By.id("fb_age"));
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    @And("I click send feedback")
    public void i_click_send_feedback() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("^I should see feedback name \"([^\"]*)\"$")
    public void iShouldSeeFeedbackName(String expectedName) {
        WebElement nameSpan = driver.findElement(By.id("name"));
        assertEquals(expectedName, nameSpan.getText());
    }

    @And("^I should see feedback age \"([^\"]*)\"$")
    public void iShouldSeeFeedbackAge(String expectedAge) {
        WebElement ageSpan = driver.findElement(By.id("age"));
        assertEquals(expectedAge, ageSpan.getText());
    }

    //Sample 4 task

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) {

        for (String lang : languages) {
            // Checkbox pattern: <input class="w3-check" type="checkbox" name="language" value="English">
            WebElement checkbox = driver.findElement(By.xpath("//input[@name='language' and @value='" + lang + "']"));

            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguages(String expected) {

        // Result page shows languages inside:
        // <span id="language">English,Spanish</span>
        WebElement langSpan = driver.findElement(By.id("language"));
        String actual = langSpan.getText().replace(" ", "");

        assertEquals(expected, actual);
    }





    // Task 1

    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number \"([^\"]*)\"$")
    public void iEnterNumber(String input) {
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys(input);
    }

    @And("^I click submit number button$")
    public void iClickSubmitNumberButton() {
        // Button uses onclick(), so we use CSS: button[type='button']
        driver.findElement(By.cssSelector("button[type='button']")).click();
    }

    @Then("I should see number error {string}")
    public void iShouldSeeNumberError(String expected) {
        String actual = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expected, actual);
    }


    @Then("I should see result {string}")
    public void iShouldSeeResult(String expected) {

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        assertTrue(text.contains(expected));

        alert.accept();
    }


    // Task 2


}
