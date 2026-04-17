package cucumber.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
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

    // Steps by Ilmars

    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() {
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @Then("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() {
        List<WebElement> buttons = driver.findElements(By.tagName("input"));
        assertTrue(buttons.getFirst().isDisplayed());
        assertTrue(buttons.getFirst().isEnabled());
        assertTrue(buttons.get(1).isDisplayed());
        assertTrue(buttons.get(1).isEnabled());
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorText) {
        WebElement errorTextElement = driver.findElement(By.id("error"));
        assertTrue(errorTextElement.isDisplayed());
        assertEquals(errorText, driver.findElement(By.id("error")).getText());
    }

    @Then("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() {
        String urlStart = "https://janisdzalbe.github.io/example-site/examples/age_2.html";
        assertFalse(driver.getCurrentUrl().contains(urlStart));
    }

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I click send feedback$")
    public void iClickSubmitFeedback() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see feedback name: \"([^\"]*)\"$")
    public void iSeeName(String name) {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @Then("^I see feedback age: (\\d*)$")
    public void iSeeAgeAge(Integer age) {
        assertEquals(age.toString(), driver.findElement(By.id("age")).getText());
    }

    @When("^I enter feedback name \"([^\"]*)\"$")
    public void iEnterNameInFeedback(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @When("^I enter feedback age (\\d+)$")
    public void iEnterAgeInFeedback(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @Given("^I am on \"Enter a number\" page$")
    public void iAmOnPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter text: \"([^\"]*)\"$")
    public void iEnterTextText(String text) {
        WebElement textInputField = driver.findElement(By.id("numb"));
        textInputField.clear();
        textInputField.sendKeys(text);
    }

    @When("^I click submit button$")
    public void iClickSubmitButton() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String errorMessage) {
        assertEquals(errorMessage, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see the correct alert message$")
    public void iSeeTheAlertMessage() {
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 100 is 10.00", alert.getText());
        alert.dismiss();
    }

    @Then("^I see no error message$")
    public void iSeeNoErrorMessage() {
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String language : languages) {
            driver.findElement(By.xpath("//input[@value='" + language + "']")).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String languages) {
        assertEquals(languages, driver.findElement(By.id("language")).getText());
    }

    @When("^I set info in feedback: \"([^\"]*)\", (\\d+) and \"([^\"]*)\"$")
    public void iSetInfoInFeedback(String name, int age, String genre) {
        WebElement fb_name = driver.findElement(By.id("fb_name"));
        fb_name.clear();
        fb_name.sendKeys(name);
        WebElement fb_age = driver.findElement(By.id("fb_age"));
        fb_age.clear();
        fb_age.sendKeys(String.valueOf(age));
        WebElement fb_gender = driver.findElement(By.xpath("//input[@name='gender' and @value='" + genre + "']"));
        fb_gender.click();
    }

    @And("^I see feedback genre: \"([^\"]*)\"$")
    public void iSeeFeedbackGenre(String genre) {
        assertEquals(genre, driver.findElement(By.id("gender")).getText());
    }

    @When("^I set feedback details$")
    public void iSetFeedbackDetails(Map<String, String> map) {
        Runnable actionForName = () -> {
            WebElement element = driver.findElement(By.id("fb_name"));
            element.clear();
            element.sendKeys(map.get("name"));
        };
        Runnable actionForAge = () -> {
            WebElement element = driver.findElement(By.id("fb_age"));
            element.clear();
            element.sendKeys(map.get("age"));
        };
        Runnable actionForGenre = () -> {
            WebElement element = driver.findElement(By.xpath("//input[@name='gender' and @value='" + map.get("genre") + "']"));
            element.click();
        };
        Map<String, Runnable> actions = Map.of(
                "name", actionForName,
                "age", actionForAge,
                "genre", actionForGenre
        );
        for (String key : map.keySet()) {
            actions.get(key).run();
        }
    }

    @When("^I see feedback details$")
    public void iSeeFeedbackDetails(Map<String, String> map) {
        Map<String, String> ids = Map.of(
                "name", "name",
                "age", "age",
                "genre", "gender"
        );
        for (String key : map.keySet()) {
            assertEquals(map.get(key), driver.findElement(By.id(ids.get(key))).getText());
        }
    }

    @Given("^I am on page \"People with jobs\"$")
    public void iAmOnPagePeopleWithJobs() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @When("^I click edit button of person \"([^\"]*)\"$")
    public void iClickEditButtonOfPerson(String name) {
        WebElement editButton = driver.findElement(By.xpath("//li[contains(@id,'person') and span[text()='" + name + "']]//i"));
        editButton.click();
    }

    @When("^I change person's name to \"([^\"]*)\"$")
    public void iChangePersonSNameTo(String new_name) {
        WebElement nameElement = driver.findElement(By.id("name"));
        nameElement.clear();
        nameElement.sendKeys(new_name);
    }

    @When("^I change person's job to \"([^\"]*)\"$")
    public void iChangePersonSJobTo(String new_job) {
        WebElement jobElement = driver.findElement(By.id("job"));
        jobElement.clear();
        jobElement.sendKeys(new_job);
    }

    @When("^I confirm the edit$")
    public void iClickOnEditButton() {
        driver.findElement(By.xpath("//button[@id='modal_button' and text()='Edit']")).click();
    }

    @Then("^I see that \"([^\"]*)\" is a \"([^\"]*)\"$")
    public void iSeeJobOfPerson(String name, String new_job) {
        WebElement personElement = driver.findElement(By.xpath("//li[contains(@id,'person') and span[text()='" + name + "']]"));
        assertEquals(new_job, personElement.findElement(By.className("job")).getText());;
    }

    @When("^I click button \"Add new person\"$")
    public void iClickButtonAddNewPerson() {
        driver.findElement(By.xpath("//button[@id='addPersonBtn' and text()='Add person']")).click();
    }

    @When("^I enter new person's name \"([^\"]*)\"$")
    public void iEnterNewPersonsName(String name) {
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.clear();
        nameField.sendKeys(name);
    }

    @When("^I enter new person's job \"([^\"]*)\"$")
    public void iEnterNewPersonsJob(String job) {
        WebElement nameField = driver.findElement(By.id("job"));
        nameField.clear();
        nameField.sendKeys(job);
    }

    @When("^I click button to confirm new person's information$")
    public void iClickButtonToConfirmNewPersonsInformation() {
        driver.findElement(By.xpath("//button[@id='modal_button' and text()='Add']")).click();
    }

    @When("^I click remove button of person \"([^\"]*)\"$")
    public void iClickRemoveButtonOfPerson(String name) {
        WebElement removeButton = driver.findElement(By.xpath("//li[contains(@id,'person') and span[text()='" + name + "']]/span[text()='×']"));
        removeButton.click();
    }

    @Then("^I see no person with name \"([^\"]*)\"$")
    public void iSeeNoPersonWithName(String name) {
        List<WebElement> elements = driver.findElements(By.className("name"));
        long numberOfElements = elements.stream().filter(e -> e.getText().equals(name)).count();
        assertEquals(0, numberOfElements);
        // for some reason the following takes some 15 seconds
        // assertThrows(Exception.class, () -> driver.findElement(By.xpath("//span[@class='name' and text()='" + name + "']")));
    }

    @When("^I click on Reset button$")
    public void iClickOnResetButton() {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @Then("^I see the initial list of persons$")
    public void iSeeTheInitialListOfPersons() {
        Map<String, String> peopleAndJobs = Map.of(
                "Mike", "Web Designer",
                "Jill", "Support",
                "Jane", "Accountant",
                "John", "Software Engineer",
                "Sarah", "Product Manager",
                "Carlos", "Data Analyst",
                "Emily", "UX Designer",
                "David", "Project Manager",
                "Maria", "QA Engineer",
                "Alex", "DevOps Engineer"
        );
        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        assertEquals(10, elements.size());

        Map<String, String> peopleAndJobsOnWebsite = new HashMap<>();
        for (WebElement element: elements) {
            String name = element.findElement(By.className("name")).getText();
            String job = element.findElement(By.className("job")).getText();
            peopleAndJobsOnWebsite.put(name, job);
        }
        System.out.println(peopleAndJobs);
        System.out.println(peopleAndJobsOnWebsite);
        assertEquals(peopleAndJobs, peopleAndJobsOnWebsite);
    }
}