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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleSteps {

    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    // ---------- HOME PAGE ----------

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() {
        driver.get("https://acctabootcamp.github.io/site");
    }

    @When("I am on the locators page")
    public void iAmOnLocatorPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see home page header$")
    public void iShouldSeeHomePageHeader() {
        assertEquals("This is a home page",
                driver.findElement(By.cssSelector("h1")).getText());
    }

    @And("^I should see home page description$")
    public void iShouldSeeHomePageDescription() {
        assertEquals(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                driver.findElement(By.cssSelector("p")).getText());
    }

    @And("^I should see menu$")
    public void iShouldSeeMenu() {
        assertTrue(driver.findElement(By.className("w3-navbar")).isDisplayed());
    }

    // ---------- LOCATORS PAGE ----------

    @Then("I should see both locators page headers")
    public void iShouldSeeLocatorsPageHeaders() {

        WebElement header1 = driver.findElement(By.id("heading_1"));
        WebElement header2 = driver.findElement(By.id("heading_2"));

        assertTrue(header1.isDisplayed());
        assertEquals("Heading 1", header1.getText());

        assertTrue(header2.isDisplayed());
        assertEquals("Heading 2 text", header2.getText());
    }

    @And("Buttons in Locators page are clickable")
    public void buttonsInLocatorsPageAreClickable() {

        WebElement button1 = driver.findElement(By.cssSelector("[name='randomButton1']"));
        WebElement button2 = driver.findElement(By.cssSelector("[name='randomButton2']"));

        assertTrue(button1.isDisplayed());
        assertTrue(button1.isEnabled());

        assertTrue(button2.isDisplayed());
        assertTrue(button2.isEnabled());
    }

    // ---------- AGE PAGE ----------

    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/age");
    }

    @When("^I enter name: \"([^\"]*)\"$")
    public void iEnterName(String name) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter age: (\\d+)$")
    public void iEnterAge(int age) {
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
    }

    @And("^I click submit age$")
    public void iClickSubmitAge() {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    // ---------- ERROR HANDLING ----------

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorMessage) {
        String actualError = driver.findElement(By.id("error")).getText();
        assertEquals(errorMessage, actualError);
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() {
        String currentUrl = driver.getCurrentUrl();

        assertTrue(currentUrl.contains("age"));

        boolean messageExists = driver.findElements(By.id("message")).size() > 0;

        if (messageExists) {
            assertTrue(driver.findElement(By.id("message")).getText().isEmpty());
        }
    }

    // ---------- TABLE INPUT ----------

    @When("^I enter values:$")
    public void iEnterValues(Map<String, String> valuesToEnter) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(valuesToEnter.get("name"));

        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(valuesToEnter.get("age"));
    }

    // ---------- CHECKBOX TASK ----------

    @And("^I click the result checkbox button$")
    public void iClickTheResultCheckboxButton() {
        driver.findElement(By.id("result_button_checkbox")).click();
    }

    @When("^I clicked on checkboxes:$")
    public void iClickedOnCheckboxes(List<String> values) {
        for (String value : values) {
            driver.findElement(By.cssSelector("[value='" + value + "']")).click();
        }
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String message) {
        assertEquals(message, driver.findElement(By.id("result_checkbox")).getText());
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }

    // ---------- ✅ FEEDBACK TASK (FIXED PROPERLY) ----------

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) {
        List<WebElement> checkboxes = driver.findElements(By.name("language"));

        for (String language : languages) {
            for (WebElement checkbox : checkboxes) {
                if (checkbox.getAttribute("value").equals(language)) {
                    if (!checkbox.isSelected()) {
                        checkbox.click();
                    }
                }
            }
        }
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback() {
        driver.findElement(By.cssSelector("button")).click();
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) {
        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(pageText.contains(expectedLanguages));
    }

    // ---------- ENTER A NUMBER PAGE ----------

    @Given("I am on the enter a number page")
    public void iAmOnTheEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("I enter the number {string}")
    public void iEnterTheNumber(String number) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @When("I click the number submit button")
    public void iClickTheNumberSubmitButton() {
        driver.findElement(By.cssSelector("button[onclick='numberValidation()']")).click();
    }

    @Then("I should see the number error message {string}")
    public void iShouldSeeTheNumberErrorMessage(String expectedMessage) {
        assertEquals(expectedMessage, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("I should see an alert with message {string}")
    public void iShouldSeeAnAlertWithMessage(String expectedMessage) {
        Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText();
        alert.accept();
        assertEquals(expectedMessage, actualMessage);
    }
}