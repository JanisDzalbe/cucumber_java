package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see home page header$")
    public void iShouldSeeHomePageHeader() throws Throwable {
        assertEquals("This is a home page",
                driver.findElement(By.cssSelector("h1")).getText());
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeLocatorsPageHeaders() throws Throwable {
        assertEquals("Heading 1",
                driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text",
                driver.findElement(By.id("heading_2")).getText());
    }

    @Then("^Buttons in Locators page are clickable$")
    public void locatorPageButtonsAreClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton1']")).isEnabled());
        assertTrue(driver.findElement(By.cssSelector("[name='randomButton2']")).isEnabled());
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

    @And("^I enter age (\\d+)$")
    public void iEnterAge(int age) throws Throwable {
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
    }


    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/age");
    }

    @Given("^I (?:am on|open) feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
    }

    @When("^I enter name \"([^\"]*)\" and age (\\d+) on feedback page$")
    public void iEnterNameAndAgeOnFeedback(String name, int age) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @When("^I click Send on feedback page$")
    public void iClickSendOnFeedback() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguage(List<String> languages) throws Throwable {
        for (String language : languages) {
            driver.findElement(By.xpath("//*[ @value='" + language + "']")).click();
        }
    }

    @When("^I click send feedback$")
    public void iClickSendFeedbackButton() throws Throwable {
        driver.findElement(By.className("w3-btn-block")).click();
    }

    @Then("^I can see languages \"(.*)\" in feedback check$")
    public void languagesForFeedbackIsSeen(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("lang_check")).getText());
    }

    @Then("^I see name \"([^\"]*)\" at check feedback page$")
    public void iSeeNameInFeedbackCheck(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @Then("^I see age (\\d+) in check feedback page$")
    public void iSeeAgeInFeedbackCheck(int age) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
    }

    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: '([^']*)'$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("error")).getText());
    }

    @Then("^I am not navigated to age message page$")
    public void assertAgePageUrl() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/examples/age", driver.getCurrentUrl());
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

    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in number field$")
    public void iEnterValueInNumberField(String value) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    @And("^I click number submit button$")
    public void iClickNumberSubmitButton() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeInlineError(String expected) {
        String actual = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expected, actual);
    }

    @Then("^I see result message: \"([^\"]*)\"$")
    public void iSeeAlertMessage(String expected) {
        String actual = driver.switchTo().alert().getText();
        assertEquals(expected, actual);
        driver.switchTo().alert().accept();
    }


    @When("^I enter feedback values:$")
    public void iEnterFeedbackValues(Map<String, String> valuesToEnter) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(valuesToEnter.get("name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(valuesToEnter.get("age"));
        driver.findElement(By.cssSelector("[value='" + valuesToEnter.get("gender") + "']")).click();
    }

    @Then("^I can see input in feedback check$")
    public void inputForFeedbackIsSeen(Map<String, String> valuesToEnter) throws Throwable {
        assertEquals(valuesToEnter.get("name"), driver.findElement(By.id("name")).getText());
        assertEquals(valuesToEnter.get("age"), driver.findElement(By.id("age")).getText());
        assertEquals(valuesToEnter.get("gender"), driver.findElement(By.id("gender")).getText());
    }







    @Given("^I am on the People with jobs page$")
    public void iAmOnPeopleWithJobsPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click the Add person button$")
    public void iClickAddPersonButton() {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @When("^I enter name ([^\"]*) and job ([^\"]*)$")
    public void iEnterNameAndJob(String name, String job) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @Then("^I should see ([^\"]*) with job ([^\"]*) in the list$")
    public void iShouldSeePersonInList(String name, String job) {
        String personBlock = driver.findElement(By.id("listOfPeople")).getText();
        assertTrue(personBlock.contains(name));
        assertTrue(personBlock.contains(job));
    }

    @When("^I click the edit icon for ([^\"]*)$")
    public void iClickEditIconFor(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        for (WebElement person : people) {
            if (person.getText().contains(name)) {
                person.findElement(By.cssSelector(".fa-pencil")).click();
                break;
            }
        }
    }

    @And("^I update name to ([^\"]*) and job to ([^\"]*)$")
    public void iUpdateNameAndJob(String newName, String newJob) {
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        nameField.clear();
        nameField.sendKeys(newName);
        jobField.clear();
        jobField.sendKeys(newJob);
    }
    @And("^I should not see ([^\"]*) in the list$")
    public void iShouldNotSeePersonInList(String oldName) {
        String personBlock = driver.findElement(By.id("listOfPeople")).getText();
        assertTrue(!personBlock.contains(oldName));
    }
    @When("^I click the save button$")
    public void iClickSaveButton() {
        // matches either Add or Edit (same modal)
        driver.findElement(By.xpath("//button[text()='Add' or text()='Edit']")).click();
    }
    String deletedPersonName; // store globally in the class

    @When("^I delete a random person$")
    public void iDeleteRandomPerson() {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        if (people.isEmpty()) {
            throw new RuntimeException("No people found to delete");
        }

        int randomIndex = new Random().nextInt(people.size());

        WebElement selected = people.get(randomIndex);
        deletedPersonName = selected.findElement(By.cssSelector(".w3-xlarge")).getText();

        String js = String.format("deletePerson(%d)", randomIndex);
        ((JavascriptExecutor) driver).executeScript(js);
    }
    @When("^I click the delete icon for ([^\"]*)$")
    public void iClickDeleteIconFor(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        for (WebElement person : people) {
            if (person.getText().contains(name)) {
                person.findElement(By.cssSelector("span[onclick^='deletePerson']")).click();
                break;
            }
        }
    }

    @Then("^([^\"]*) should not be in the list$")
    public void personShouldNotBeInList(String name) {
        String listText = driver.findElement(By.id("listOfPeople")).getText();
        assertTrue("Person still found: " + name, !listText.contains(name));
    }

    @When("^I add a new person ([^\"]*) with job ([^\"]*)$")
    public void iAddNewPerson(String name, String job) {
        driver.findElement(By.id("addPersonBtn")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @And("^I click Reset List$")
    public void iClickResetList() {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @Then("^I should see the original people:$")
    public void iShouldSeeOriginalPeople(List<String> expectedNames) {
        String list = driver.findElement(By.id("listOfPeople")).getText();
        for (String name : expectedNames) {
            assertTrue("Missing: " + name, list.contains(name));
        }
    }
    @And("^I should not see ([^\"]*) in the list person$")
    public void iShouldNotSeeName(String name) {
        String list = driver.findElement(By.id("listOfPeople")).getText();
        assertFalse("Still found: " + name, list.contains(name));
    }


    @When("^I edit ([^\"]*) to name ([^\"]*) and job ([^\"]*)$")
    public void iEditNameAndJob(String originalName, String newName, String newJob) {
        // Click edit icon for the original name
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getText().contains(originalName)) {
                String js = String.format("openModalForEditPersonWithJob(%d)", i);
                ((JavascriptExecutor) driver).executeScript(js);
                break;
            }
        }

        // Fill in the new name and job
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.clear();
        nameField.sendKeys(newName);

        WebElement jobField = driver.findElement(By.id("job"));
        jobField.clear();
        jobField.sendKeys(newJob);
    }

    @And("^I click (Add|Edit) button$")
    public void iClickAddOrEditButton(String buttonText) {
        // This works because the visible text in the button is either "Add" or "Edit"
        driver.findElement(By.xpath("//button[text()='" + buttonText + "']")).click();
    }

    @When("^I delete ([^\"]*)$")
    public void iDeletePersonByName(String nameToDelete) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        for (int i = 0; i < people.size(); i++) {
            String personText = people.get(i).getText();
            if (personText.contains(nameToDelete)) {
                String js = String.format("deletePerson(%d)", i);
                ((JavascriptExecutor) driver).executeScript(js);
                break;
            }
        }
    }
}



