package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

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

    // Sample Task 1

    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void assertLocatorPageHeaders() throws Throwable {
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void isButtonsClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton1']")).isEnabled());
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton2']")).isEnabled());
    }

    // Sample Task 2

    @Then("^I see error: \"([^\"]*)\"$")
    public void notEnteredAge(String errorText) throws Throwable {
        assertTrue(driver.findElement(By.id("error")).isDisplayed());
        assertEquals(errorText, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void notNavigatedToAgeMessagePage() throws Throwable {
        assertFalse(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/examples/age_2.html"));
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());
    }

    // Sample Task 3

    @Given("I am on Feedback page")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name in Feedback page: \"([^\"]*)\"$")
    public void iEnterNameInFeedbackPage(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^I enter age in Feedback page: (\\d+)$")
    public void iEnterAgeInFeedbackPage(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I press Send feedback button$")
    public void iClickSubmitButtonInFeedbackPage() throws Throwable {
        driver.findElement(By.cssSelector("[type=\"submit\"]")).click();
    }

    @Then("^I see name \"([^\"]*)\" in check feedback page$")
    public void iSeeNameInCheckFeedbackPage(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @And("^I see age (\\d+) in check feedback page$")
    public void iSeeAgeInCheckFeedbackPage(int age) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
    }

    // Sample Task 4

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPageTask() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) {
        // Each language corresponds to checkbox with value="English", "Spanish", etc.
        for (String lang : languages) {
            driver.findElement(By.cssSelector("input[value='" + lang + "']")).click();
        }
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) {
        String actualLanguages = driver.findElement(By.id("language")).getText();
        assertEquals(expectedLanguages, actualLanguages);
    }

    // Sample 5

    @When("^I fill feedback form with data$")
    public void iFillFeedbackFormWithData(Map<String, String> data) {

        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(data.get("name"));

        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(data.get("age"));

        String langInput = data.get("language");
        if (langInput != null && !langInput.isEmpty()) {
            List<WebElement> languages = driver.findElements(By.name("language"));
            for (WebElement lang : languages) {
                if (langInput.contains(lang.getAttribute("value"))) {
                    lang.click();
                }
            }
        }

        String genderInput = data.get("gender");
        if (genderInput != null && !genderInput.isEmpty()) {
            List<WebElement> genders = driver.findElements(By.name("gender"));
            for (WebElement g : genders) {
                if (g.getAttribute("value").equalsIgnoreCase(genderInput)) {
                    g.click();
                    break;
                }
            }
        }

        if (data.get("option") != null && !data.get("option").isEmpty()) {
            Select likeUsDropdown = new Select(driver.findElement(By.id("like_us")));
            likeUsDropdown.selectByVisibleText(data.get("option"));
        }

        if (data.get("comment") != null) {
            driver.findElement(By.name("comment")).clear();
            driver.findElement(By.name("comment")).sendKeys(data.get("comment"));
        }
    }

    @Then("^I should see submitted name \"([^\"]*)\"$")
    public void iShouldSeeSubmittedName(String expectedName) {
        String actual = driver.findElement(By.id("name")).getText();
        assertEquals(expectedName, actual);
    }

    @Then("^I should see submitted age \"([^\"]*)\"$")
    public void iShouldSeeSubmittedAge(String expectedAge) {
        String actual = driver.findElement(By.id("age")).getText();
        assertEquals(expectedAge, actual);
    }

    @Then("^I should see submitted gender \"([^\"]*)\"$")
    public void iShouldSeeSubmittedGender(String expectedGender) {
        String actual = driver.findElement(By.id("gender")).getText();
        assertEquals(expectedGender, actual);
    }

    @Then("^I should see submitted option \"([^\"]*)\"$")
    public void iShouldSeeSubmittedOption(String expectedOption) {
        String actual = driver.findElement(By.id("option")).getText();
        assertEquals(expectedOption, actual);
    }

    @Then("^I should see submitted comment \"([^\"]*)\"$")
    public void iShouldSeeSubmittedComment(String expectedComment) {
        String actual = driver.findElement(By.id("comment")).getText();
        assertEquals(expectedComment, actual);
    }


    // Individual Task 1

    @Given("^the user is on the Enter a number page$")
    public void theUserIsOnEnterNumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^the user enters \"([^\"]*)\" into the number field$")
    public void theUserEntersIntoNumberField(String inputValue) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(inputValue);
    }

    @And("^the user clicks the Submit button$")
    public void theUserClicksSubmitButton() {
        driver.findElement(By.className("w3-orange")).click();
    }

    @Then("^the error message \"([^\"]*)\" should be displayed$")
    public void theErrorMessageShouldBeDisplayed(String expectedError) {
        String actualError = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expectedError, actualError);
    }

    @Then("^the result \"([^\"]*)\" should be displayed$")
    public void theResultShouldBeDisplayed(String expectedResult) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        // Extract last number
        String[] numbers = text.replaceAll("[^0-9. ]", "").trim().split(" ");
        String actualResult = numbers[numbers.length - 1];

        assertEquals(expectedResult, actualResult);

        alert.accept();
    }

    // Individual Task 2

    @Given("^the user is on the People with jobs list page$")
    public void openPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^the user clicks the Add person button$")
    public void clickAddPersonButton() {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @When("^the user fills the Add Person form with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void fillAddPersonForm(String name, String job) {
        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(name);

        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    @When("^the user submits the Add Person form$")
    public void submitAddPersonForm() {
        driver.findElement(By.id("modal_button")).click();
    }

    @Then("^the list should contain a person with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void listShouldContainPerson(String name, String job) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        boolean found = false;
        for (WebElement person : people) {
            String personName = person.findElement(By.className("name")).getText();
            String personJob = person.findElement(By.className("job")).getText();
            if (personName.equals(name) && personJob.equals(job)) {
                found = true;
                break;
            }
        }
        assertTrue("Person " + name + " with job " + job + " not found!", found);
    }

    @When("^the user clicks the Edit button for person \"([^\"]*)\"$")
    public void clickEditButton(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        for (WebElement person : people) {
            String personName = person.findElement(By.className("name")).getText();
            if (personName.equals(name)) {
                person.findElement(By.className("editbtn")).click();
                break;
            }
        }
    }

    @When("^the user changes the name to \"([^\"]*)\" and job to \"([^\"]*)\"$")
    public void editPersonForm(String newName, String newJob) {
        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(newName);

        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        jobInput.sendKeys(newJob);

        driver.findElement(By.id("modal_button")).click();
    }

    @And("^the user submits the form$")
    public void userSubmitsTheForm() {
        driver.findElement(By.id("modal_button")).click();
    }

    @And("^the user submits the form to save$")
    public void userSubmitsTheFormToSave() {
        driver.findElement(By.xpath("//button[text()='Add']"));
    }

    @And("^the user fills the form with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void userFillsFormWithNameAndJob(String name, String job) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);

        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @Then("^the list should not contain (?:a person with name |the person )\"([^\"]*)\"$")
    public void listShouldNotContainPerson(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        for (WebElement person : people) {
            String personName = person.findElement(By.className("name")).getText();
            assertNotEquals("Person " + name + " should not be present!", name, personName);
        }
    }

    @When("^the user clicks the Delete button for person \"([^\"]*)\"$")
    public void clickDeleteButton(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        for (WebElement person : people) {
            String personName = person.findElement(By.className("name")).getText();
            if (personName.equals(name)) {
                person.findElement(By.className("closebtn")).click();
                break;
            }
        }
    }

    @When("^the user clicks the Reset List button$")
    public void clickResetListButton() {
        List<WebElement> buttons = driver.findElements(By.id("addPersonBtn"));
        buttons.get(1).click();
    }

    @Then("^the list should contain the original person \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void listShouldContainOriginalPerson(String name, String job) {
        listShouldContainPerson(name, job);
    }

    @Then("^the list should not contain the person \"([^\"]*)\"$")
    public void listShouldNotContainThePerson(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        for (WebElement person : people) {
            String personName = person.findElement(By.className("name")).getText();
            assertNotEquals("Person " + name + " should not be present!", name, personName);
        }
    }




}
