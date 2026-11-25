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

//    @Then("^I see message: \"([^\"]*)\"$")
//    public void iSeeMessage(String message) throws Throwable {
//        assertEquals(message, driver.findElement(By.id("message")).getText());
//    }

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

    //----------------------------------
    // Sample 1
    //----------------------------------

    @Given("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeLocatorPageHeaders() throws Throwable {
        assertEquals("Heading 1",
                driver.findElements(By.cssSelector("h2")).get(0).getText());
        assertEquals("Heading 2 text",
                driver.findElements(By.cssSelector("h2")).get(1).getText());
    }

    @Then("^Buttons in Locators page are clickable$")
    public void iClickLocatorsPageButtons() throws Throwable {

        assertTrue(driver.findElements(By.xpath("//input[@type='button']")).get(0).isEnabled());
        assertTrue(driver.findElements(By.xpath("//input[@type='button']")).get(1).isEnabled());
    }

    //----------------------------------
    // Sample 2
    //----------------------------------

    @Then("^I see (error|message): \"([^\"]*)\"$")
    public void iSeeSomething(String type, String message) throws Throwable {

        switch (type) {
            case "error":
                assertEquals(message, driver.findElement(By.id("error")).getText());
                break;
            case "message":
                assertEquals(message, driver.findElement(By.id("message")).getText());
                break;
        }

    }

    //    @Then("^I see error: \"([^\"]*)\"$")
//    public void iSeeError(String error) throws Throwable {
//        assertEquals(error, driver.findElement(By.id("error")).getText());
//    }

    @Then("^I am not navigated to age message page$")
    public void iNotNavigatedAgePage() throws Throwable {

        assertFalse(driver.getCurrentUrl().contains("/age_2.html"));
    }

    //----------------------------------
    // Sample 3
    //----------------------------------

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name \"([^\"]*)\" in feedback page$")
    public void iEnterNameInFeedbackPage(String name) throws Throwable {
        driver.findElement(By.xpath("//*[contains(@id, 'name')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'name')]")).sendKeys(name);
    }

    @When("^I enter age (\\d*) in feedback page$")
    public void iEnterAgeInFeedbackPage(String age) throws Throwable {
        driver.findElement(By.xpath("//*[contains(@id, 'age')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'age')]")).sendKeys(age);
    }

    @When("^I click send feedback$")
    public void iClickSubmitFeedback() throws Throwable {
        driver.findElement(By.cssSelector("[type='submit']")).click();
    }

    @Then("^I see name \"([^\"]*)\" in feedback review page$")
    public void iSeeSubmitFeedbackName(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @Then("^I see age (\\d*) in feedback review page$")
    public void iSeeSubmitFeedbackAge(String age) throws Throwable {
        assertEquals(age, driver.findElement(By.id("age")).getText());
    }

}
