package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Step definitions mapping Gherkin steps in feature files to Selenium WebDriver actions.
 */
public class SampleSteps {
    private static final List<String> ORIGINAL_PEOPLE_LIST = List.of(
            "Mike, Web Designer",
            "Jill, Support",
            "Jane, Accountant",
            "John, Software Engineer",
            "Sarah, Product Manager",
            "Carlos, Data Analyst",
            "Emily, UX Designer",
            "David, Project Manager",
            "Maria, QA Engineer",
            "Alex, DevOps Engineer"
    );

    private WebDriver driver;

    /**
     * Reuses the shared WebDriver instance initialized in Hooks.
     */
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

    /**
     * Navigates to the Locators example page.
     */
    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    /**
     * Verifies that both headings on the Locators page are visible with the expected text.
     */
    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() {
        WebElement heading1 = driver.findElement(By.id("heading_1"));
        WebElement heading2 = driver.findElement(By.id("heading_2"));
        assertTrue(heading1.isDisplayed());
        assertEquals("Heading 1", heading1.getText());
        assertTrue(heading2.isDisplayed());
        assertEquals("Heading 2 text", heading2.getText());
    }

    /**
     * Verifies that both buttons on the Locators page are visible and enabled (clickable).
     */
    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() {
        WebElement button1 = driver.findElement(By.name("randomButton1"));
        WebElement button2 = driver.findElement(By.id("buttonId"));
        assertTrue(button1.isDisplayed());
        assertTrue(button1.isEnabled());
        assertTrue(button2.isDisplayed());
        assertTrue(button2.isEnabled());
    }

    /**
     * Verifies that the expected validation error message is displayed on the age page.
     *
     * @param errorMessage the expected error text captured from the Gherkin step
     */
    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorMessage) {
        WebElement errorElement = driver.findElement(By.id("error"));
        assertTrue(errorElement.isDisplayed());
        assertEquals(errorMessage, errorElement.getText());
    }

    /**
     * Verifies that the browser remained on the age input page and did not navigate to the age message page.
     */
    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() {
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());
        assertTrue(driver.findElements(By.id("message")).isEmpty());
    }

    /**
     * Navigates to the "Give us your feedback!" task page.
     */
    @Given("^I am on the provide feedback page$")
    public void iAmOnTheProvideFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    /**
     * Fills the Name and Age fields on the feedback form.
     *
     * @param name the name to enter into {@code #fb_name}
     * @param age  the age to enter into {@code #fb_age}
     */
    @When("^I enter feedback name \"([^\"]*)\" and age (\\d+)$")
    public void iEnterFeedbackNameAndAge(String name, int age) {
        WebElement nameInput = driver.findElement(By.id("fb_name"));
        WebElement ageInput = driver.findElement(By.id("fb_age"));
        nameInput.clear();
        nameInput.sendKeys(name);
        ageInput.clear();
        ageInput.sendKeys(String.valueOf(age));
    }

    /**
     * Submits the feedback form by clicking the "Send" button.
     */
    @And("^I click the Send feedback button$")
    public void iClickTheSendFeedbackButton() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    /**
     * Verifies that the submitted name and age appear in the corresponding summary fields.
     *
     * @param expectedName the expected submitted name
     * @param expectedAge  the expected submitted age
     */
    @Then("^I see submitted feedback name \"([^\"]*)\" and age (\\d+)$")
    public void iSeeSubmittedFeedbackNameAndAge(String expectedName, int expectedAge) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("check_feedback.html"));
        wait.until(d -> !d.findElement(By.id("name")).getText().isEmpty());

        assertEquals(expectedName, driver.findElement(By.id("name")).getText());
        assertEquals(String.valueOf(expectedAge), driver.findElement(By.id("age")).getText());
    }

    /**
     * Navigates to the "Enter a number" task page.
     */
    @Given("^I am on the enter a number page$")
    public void iAmOnTheEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    /**
     * Clears the number input field and enters the given value.
     *
     * @param value the text or numeric string to enter into {@code #numb}
     */
    @When("^I enter value \"([^\"]*)\" into the number field$")
    public void iEnterValueIntoTheNumberField(String value) {
        WebElement numberInput = driver.findElement(By.id("numb"));
        numberInput.clear();
        numberInput.sendKeys(value);
    }

    /**
     * Clicks the orange Submit button on the "Enter a number" page.
     */
    @And("^I click the Submit number button$")
    public void iClickTheSubmitNumberButton() {
        driver.findElement(By.className("w3-orange")).click();
    }

    /**
     * Verifies that the expected validation error message is displayed in {@code #ch1_error}.
     *
     * @param expectedError the expected validation error message
     */
    @Then("^I see number validation error \"([^\"]*)\"$")
    public void iSeeNumberValidationError(String expectedError) {
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        assertTrue(errorElement.isDisplayed());
        assertEquals(expectedError, errorElement.getText());
    }

    /**
     * Switches to the browser alert, verifies the calculated square root message, and accepts the alert.
     *
     * @param expectedAlertText the expected text inside the browser alert box
     */
    @Then("^I see square root alert with text \"([^\"]*)\"$")
    public void iSeeSquareRootAlertWithText(String expectedAlertText) {
        Alert alert = driver.switchTo().alert();
        assertEquals(expectedAlertText, alert.getText());
        alert.accept();
    }

    /**
     * Verifies that no validation error message is displayed on the page after a valid submission.
     */
    @And("^no number validation error is shown$")
    public void noNumberValidationErrorIsShown() {
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        assertFalse(errorElement.isDisplayed());
        assertEquals("", errorElement.getText());
    }

    // -------------------------
    // Sample 4
    // -------------------------

    /**
     * Navigates to the feedback page by reusing the existing navigation step.
     */
    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        iAmOnTheProvideFeedbackPage();
    }

    /**
     * Checks each language checkbox listed in the 1-column Cucumber data table.
     *
     * @param languages list of language values to select
     */
    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String language : languages) {
            driver.findElement(By.cssSelector("[type='checkbox'][value='" + language + "']")).click();
        }
    }

    /**
     * Clicks the Send button on the feedback form by reusing the existing submit step.
     */
    @When("^I click send feedback$")
    public void iClickSendFeedback() {
        iClickTheSendFeedbackButton();
    }

    /**
     * Verifies that the selected languages are displayed on the feedback summary page.
     *
     * @param languages expected comma-separated language string in {@code #language}
     */
    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void assertLanguagesInFeedbackReviewPage(String languages) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("check_feedback.html"));
        wait.until(d -> !d.findElement(By.id("language")).getText().isEmpty());

        assertEquals(languages, driver.findElement(By.id("language")).getText());
    }

    // -------------------------
    // Sample 5
    // -------------------------

    /**
     * Enters Name, Age, and Genre on the feedback form in a single step using a 2-column Map table.
     *
     * @param inputMap map containing keys {@code name}, {@code age}, and {@code genre}
     */
    @When("^I input Name, Age and Genre in feedback$")
    public void iInputNameAgeGenreInFeedback(Map<String, String> inputMap) {
        WebElement nameInput = driver.findElement(By.id("fb_name"));
        WebElement ageInput = driver.findElement(By.id("fb_age"));

        nameInput.clear();
        nameInput.sendKeys(inputMap.get("name"));
        ageInput.clear();
        ageInput.sendKeys(inputMap.get("age"));
        driver.findElement(By.cssSelector("[name='gender'][value='" + inputMap.get("genre") + "']")).click();
    }

    /**
     * Verifies that the submitted Name, Age, and Genre match the expected values on the review page.
     *
     * @param expectedMap map containing expected {@code name}, {@code age}, and {@code genre} values
     */
    @Then("^I see Name, Age and Genre in feedback review page$")
    public void assertNameAgeGenreInFeedbackReviewPage(Map<String, String> expectedMap) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("check_feedback.html"));
        wait.until(d -> !d.findElement(By.id("name")).getText().isEmpty());

        assertEquals(expectedMap.get("name"), driver.findElement(By.id("name")).getText());
        assertEquals(expectedMap.get("age"), driver.findElement(By.id("age")).getText());
        assertEquals(expectedMap.get("genre"), driver.findElement(By.id("gender")).getText());
    }

    // -------------------------
    // Task 2
    // -------------------------

    /**
     * Navigates to the "List of people with jobs" task page and waits for the list to render.
     */
    @Given("^I am on the list of people with jobs page$")
    public void iAmOnTheListOfPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
        createWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#listOfPeople li")));
    }

    /**
     * Opens the Add person form, fills in Name and Job, submits, and waits to return to the list page.
     *
     * @param name the new person's name
     * @param job  the new person's job title
     */
    @When("^I add a new person with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iAddANewPersonWithNameAndJob(String name, String job) {
        driver.findElement(By.xpath("//button[text()='Add person']")).click();

        WebDriverWait wait = createWait();
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement jobInput = driver.findElement(By.id("job"));

        nameInput.clear();
        nameInput.sendKeys(name);
        jobInput.clear();
        jobInput.sendKeys(job);
        driver.findElement(By.id("modal_button")).click();

        wait.until(ExpectedConditions.urlContains("list_of_people_with_jobs"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#listOfPeople li")));
    }

    /**
     * Opens the Edit form for an existing person, verifies their name, updates their job, and saves.
     *
     * @param name   the existing person's name
     * @param newJob the new job title to save
     */
    @When("^I edit person \"([^\"]*)\" to have job \"([^\"]*)\"$")
    public void iEditPersonToHaveJob(String name, String newJob) {
        WebElement person = findPersonListItemByName(name);
        person.findElement(By.className("editbtn")).click();

        WebDriverWait wait = createWait();
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement jobInput = driver.findElement(By.id("job"));

        assertEquals(name, nameInput.getDomProperty("value"));
        jobInput.clear();
        jobInput.sendKeys(newJob);
        driver.findElement(By.id("modal_button")).click();

        wait.until(ExpectedConditions.urlContains("list_of_people_with_jobs"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#listOfPeople li")));
    }

    /**
     * Clicks the remove icon for the specified person and waits for the page to reload.
     *
     * @param name the name of the person to remove
     */
    @When("^I remove person \"([^\"]*)\" from the people list$")
    public void iRemovePersonFromThePeopleList(String name) {
        WebElement oldList = driver.findElement(By.id("listOfPeople"));
        WebElement person = findPersonListItemByName(name);
        person.findElement(By.className("closebtn")).click();

        WebDriverWait wait = createWait();
        wait.until(ExpectedConditions.stalenessOf(oldList));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#listOfPeople li")));
    }

    /**
     * Clicks the "Reset List" button and waits for the reloaded list to appear.
     */
    @When("^I click the Reset List button$")
    public void iClickTheResetListButton() {
        WebElement oldList = driver.findElement(By.id("listOfPeople"));
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        WebDriverWait wait = createWait();
        wait.until(ExpectedConditions.stalenessOf(oldList));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#listOfPeople li")));
    }

    /**
     * Asserts that the rendered people list has the exact expected number of entries.
     *
     * @param expectedSize expected number of people in the list
     */
    @Then("^the people list contains (\\d+) entries$")
    public void thePeopleListContainsEntries(int expectedSize) {
        assertEquals(expectedSize, getCurrentPeopleList().size());
    }

    /**
     * Asserts that the specified person and job combination is present in the people list.
     *
     * @param name expected person's name
     * @param job  expected person's job
     */
    @And("^person \"([^\"]*)\" with job \"([^\"]*)\" is in the people list$")
    public void personWithJobIsInThePeopleList(String name, String job) {
        assertTrue(getCurrentPeopleList().contains(name + ", " + job));
    }

    /**
     * Asserts that the specified person and job combination is not present in the people list.
     *
     * @param name person's name
     * @param job  person's job
     */
    @And("^person \"([^\"]*)\" with job \"([^\"]*)\" is not in the people list$")
    public void personWithJobIsNotInThePeopleList(String name, String job) {
        assertFalse(getCurrentPeopleList().contains(name + ", " + job));
    }

    /**
     * Asserts that the entire people list matches the original 10 entries in size, order, and content.
     */
    @Then("^the people list matches the original 10 entries$")
    public void thePeopleListMatchesTheOriginalEntries() {
        List<String> actualPeople = getCurrentPeopleList();
        assertEquals(ORIGINAL_PEOPLE_LIST.size(), actualPeople.size());
        assertEquals(ORIGINAL_PEOPLE_LIST, actualPeople);
    }

    /**
     * Creates a 10-second explicit wait configured to ignore stale element references.
     *
     * @return configured {@link WebDriverWait}
     */
    private WebDriverWait createWait() {
        return (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class);
    }

    /**
     * Finds a person's {@code <li>} element on the page by exact displayed name.
     *
     * @param name exact name text inside {@code .name}
     * @return matching {@code <li>} WebElement
     */
    private WebElement findPersonListItemByName(String name) {
        return driver.findElement(
                By.xpath("//li[.//span[contains(@class, 'name') and text()='" + name + "']]")
        );
    }

    /**
     * Reads all visible people entries into a list formatted as {@code "Name, Job"}.
     *
     * @return list of currently displayed people
     */
    private List<String> getCurrentPeopleList() {
        return driver.findElements(By.cssSelector("#listOfPeople li"))
                .stream()
                .map(person -> person.findElement(By.className("name")).getText()
                        + ", " + person.findElement(By.className("job")).getText())
                .toList();
    }
}