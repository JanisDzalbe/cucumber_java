package cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
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

    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
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

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("[name=\"randomButton1\"]")).isDisplayed());
        assertTrue(driver.findElement(By.cssSelector("[name=\"randomButton1\"]")).isEnabled());

        assertTrue(driver.findElement(By.id("buttonId")).isDisplayed());
        assertTrue(driver.findElement(By.id("buttonId")).isEnabled());
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

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String errorMessage) throws Throwable {
        assertEquals(errorMessage, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() throws Throwable {
        assertFalse(driver.getCurrentUrl().contains("age-2.html"));
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
    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }
    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> values) throws Throwable {
        for (String value : values) {
            driver.findElement(By.xpath("//input[@name='language' and @value='" + value + "']")).click();
        }
    }

    @When("^I add feedback details:$")
    public void iAddFeedbackDetails(Map<String, String> feedbackDetails) throws Throwable {

        iEnterNameInFeedback(feedbackDetails.get("name"));

        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(feedbackDetails.get("age"));

        driver.findElement(By.cssSelector("[value='" + feedbackDetails.get("gender") + "']")).click();
    }
    @Then("I can see gender \"([^\"]*)\" in feedback check$")
    public void iSeeGenderInFeedbackCheck(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("gender")).getText());
    }

    @Then("^I should see feedback summary \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iShouldSeeFeedbackSummary(DataTable table) {

        Map<String, String> expected = table.asMap(String.class, String.class);

        String actualName = driver.findElement(By.id("name")).getText().trim();
        String actualAge = driver.findElement(By.id("age")).getText().trim();
        String actualLanguage = driver.findElement(By.id("language")).getText().trim();
        String actualGender = driver.findElement(By.id("gender")).getText().trim();

        assertEquals(expected.get("name"), actualName);
        assertEquals(expected.get("age"), actualAge);
        assertEquals(expected.get("language"), actualLanguage);
        assertEquals(expected.get("gender").toLowerCase(), actualGender.toLowerCase());
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) throws Throwable {

        String actualLanguages = driver.findElement(By.id("language")).getText().trim();

        assertEquals(expectedLanguages, actualLanguages);
    }


    @When("^I enter feedback name \"([^\"]*)\"$")
    public void iEnterNameInFeedback(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @When("^I click send feedback$")
    public void iClickSendFeedback() throws Throwable {
        driver.findElement(By.className("w3-blue")).click();
    }


    @Then("^I can see name \"([^\"]*)\" in feedback check$")
    public void iSeeNameInFeedbackCheck(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @Then("^I can see age \"([^\"]*)\" in feedback check$")
    public void iSeeAgeInFeedbackCheck(int age) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
    }

    @When("^I enter feedback age \"([^\"]*)\"$")
    public void iEnterAgeInFeedback(String age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(age);
    }

    @Given("^I am on enter number page$")
    public void iAmOnEnterNumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter value \"([^\"]*)\"$")
    public void iEnterValue(String value) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    @And("^I click submit$")
    public void iClickSubmit() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("^I should see error message \"([^\"]*)\"$")
    public void iShouldSeeErrorMessage(String expectedError) throws Throwable {
        String actualError = driver.findElement(By.id("ch1_error")).getText().trim();
        assertEquals(expectedError, actualError);
    }

    @Then("^I should see result \"([^\"]*)\"$")
    public void iShouldSeeResult(String expectedResult) {
        String alertText = driver.switchTo().alert().getText();
        assertTrue(alertText.contains(expectedResult));
        driver.switchTo().alert().accept();
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }

    @Given("^I am on people list page$")
    public void iAmOnPeopleListPage(){
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click Add person$")
    public void iClickAddPerson() throws Throwable {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @And("^I add person \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iAddPersonWithJob(String name, String job) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);

        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);

        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @Then("^I should see \"([^\"]*)\" with job \"([^\"]*)\" in the list$")
    public void iShouldSeePersonWithJobInList(String name, String job) {

        String listText = driver.findElement(By.id("listOfPeople")).getText();

        assertTrue(listText.contains(name));
        assertTrue(listText.contains(job));
    }

    @When("^I edit person \"([^\"]*)\" to have job \"([^\"]*)\"$")
    public void iEditPersonToHaveJob(String name, String newjob) throws Throwable {
        driver.findElement(By.xpath( "//li[.//span[text()='" + name + "']]//span[contains(@onclick,'openModalForEditPersonWithJob')]")).click();

        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(newjob);

        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @When("^I remove person \"([^\"]*)\"$")
    public void iRemovePerson(String name) throws Throwable {
        driver.findElement(By.xpath("//li[.//span[text()='" + name + "']]//span[contains(@onclick,'deletePerson')]")).click();
    }

    @Then("^I should not see \"([^\"]*)\" in the list$")
    public void iShouldNotSeePerson(String name) {
        String listText = driver.findElement(By.id("listOfPeople")).getText();
        assertFalse(listText.contains(name));
    }

    @When("^I perform \"([^\"]*)\" on person \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iPerformAction(String action, String name, String job) throws Throwable {

        if(action.equals("add")) {

            driver.findElement(By.id("addPersonBtn")).click();

            driver.findElement(By.id("name")).clear();
            driver.findElement(By.id("name")).sendKeys(name);

            driver.findElement(By.id("job")).clear();
            driver.findElement(By.id("job")).sendKeys(job);

            driver.findElement(By.xpath("//button[text()='Add']")).click();
        }

        else {

            // get all people
            List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

            for(WebElement person : people) {
                if(person.getText().contains(name)) {
                    if(action.equals("edit")) {

                        person.findElements(By.tagName("span")).get(1).click();

                        driver.findElement(By.id("job")).clear();
                        driver.findElement(By.id("job")).sendKeys(job);

                        driver.findElement(By.xpath("//button[text()='Edit']")).click();
                    }

                    else if(action.equals("remove")) {

                        person.findElements(By.tagName("span")).get(0).click();
                    }

                    break;
                }
            }
        }
    }

    @And("^I click Reset List$")
    public void iClickResetList() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @Then("^I should see default people in the list$")
    public void iShouldSeeDefaultPeopleInList() throws Throwable {

        String listText = driver.findElement(By.id("listOfPeople")).getText();

        assertTrue(listText.contains("Mike"));
        assertTrue(listText.contains("Jill"));
        assertTrue(listText.contains("Jane"));
        assertTrue(listText.contains("John"));
    }
}
