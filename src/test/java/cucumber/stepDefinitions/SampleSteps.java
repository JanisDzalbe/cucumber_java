package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SampleSteps {
    private WebDriver driver;

    private int peopleCountBefore;
    private int originalPeopleCount;

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

    /// Sample2 implementation
    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String expectedError) throws Throwable {
        assertTrue(driver.findElement(By.id("error")).isDisplayed());
        assertEquals(expectedError, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() throws Throwable {
        assertFalse(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/examples/age_2"));
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());

    }
    /// End of Sample2 implementation


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

    /// Sample1
    ///When, Then and And for Sample1.feature TODO task
    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
        assertTrue(driver.findElement(By.xpath("(//h2)[1]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("(//h2)[2]")).isDisplayed());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws Throwable {
        List<WebElement> buttons = driver.findElements(By.cssSelector("button, input[type='button']"));

        for (WebElement button : buttons) {
            assertTrue(button.isDisplayed());
            assertTrue(button.isEnabled());
        }
    }

    /// Sample3 task
    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name \"([^\"]*)\" and age (\\d+)$")
    public void iEnterNameAndAge(String name, int age) {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click send button$")
    public void iClickSendButton() {
        driver.findElement(By.cssSelector("button[type='submit'], button.w3-btn-block")).click();
    }

    @Then("^I see feedback name \"([^\"]*)\" and age (\\d+)$")
    public void iShouldSeeFeedbackNameAndAge(String expectedName, int expectedAge) {
        assertTrue(driver.findElements(By.cssSelector("div.description")).get(0).getText().contains(expectedName));
        assertTrue(driver.findElements(By.cssSelector("div.description")).get(1).getText().contains(String.valueOf(expectedAge)));
    }

    /// Task 1 implementation
    @Given("^I am on enter number page$")
    public void iAmOnEnterNumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number \"([^\"]*)\"$")
    public void iEnterNumber(String value) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    @And("^I click submit button")
    public void iClickSubmitButton() {
        driver.findElement(By.cssSelector("button[type='button'], w3-btn")).click();
    }

    @Then("^I see the corresponding error \"([^\"]*)\"$")
    public void iSeeCorrespondingError(String expectedError) {
        assertEquals(expectedError, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see the message \"([^\"]*)\"$")
    public void iSeeTheMessage(String expectedText) {
        assertEquals(expectedText, driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }

    @Then("^No errors are shown$")
    public void noErrorsAreShown() {
        assertTrue( driver.findElement(By.id("ch1_error")).getText().isEmpty());
    }

    /// Sample4 implementation(missing steps)
    @When("^I select feedback languages$")
    public void iSelectLanguages(List<String> languages) {
        for (String lang : languages) {
            driver.findElement(By.cssSelector("input[type='checkbox'][name='language'][value='" + lang + "']")).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expected) {
        assertEquals(expected, driver.findElement(By.id("language")).getText());
    }

    ///  Sample5 implementation
    @When("^I fill feedback form:$")
    public void iFillFeedbackForm(Map<String, String> data) {

        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(data.get("name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(data.get("age"));
        driver.findElement(By.cssSelector("[type='radio'][value='"+data.get("genre").toLowerCase()+"']")).click();

    }

    @Then("^I see feedback details:$")
    public void iSeeFeedbackDetails(Map<String, String> expected) throws Throwable {
        assertEquals(expected.get("name"), driver.findElement(By.id("name")).getText());
        assertEquals(expected.get("age"), driver.findElement(By.id("age")).getText());
        assertEquals(expected.get("genre"), driver.findElement(By.id("gender")).getText());
    }

    /// Task2 implementation
    @Given("^I am on \"People with jobs\" page$")
    public void iAmOnJobPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    /// 2.1 Add
    @When("^I click \"Add person\" button$")
    public void iClickAddPersonButton() {
        peopleCountBefore = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16")).size();
        driver.findElement(By.xpath("//button[normalize-space()='Add person']")).click();
    }

    @And("^I fill form with:$")
    public void iFillFormWith(Map<String, String> data) {
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput  = driver.findElement(By.id("job"));

        nameInput.clear();
        nameInput.sendKeys(data.get("name"));

        jobInput.clear();
        jobInput.sendKeys(data.get("job"));
    }

    @And("^I click add button$")
    public void iClickAdd() {
        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @Then("^I should see person in the list:$")
    public void iShouldSeePersonInList(Map<String, String> expected) {
        List<WebElement> rows = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"));

        WebElement foundRow = null;
        for (WebElement row : rows) {
            String rowName = row.findElement(By.cssSelector(".name")).getText();
            if (rowName.equals(expected.get("name"))) {
                foundRow = row;
                break;
            }
        }

        Assert.assertNotNull("Person not found in list: " + expected.get("name"), foundRow);

        String actualName = foundRow.findElement(By.cssSelector(".name")).getText();
        String actualJob  = foundRow.findElement(By.cssSelector(".job")).getText();

        assertEquals(expected.get("name"), actualName);
        assertEquals(expected.get("job"), actualJob);
    }

    @Then("^list size should increase by one$")
    public void peopleListSizeShouldIncreaseBy1() {
        int after = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16")).size();
        assertEquals("Person was not added", peopleCountBefore + 1, after);
    }

    /// Edit 2.2 Edit
    @When("^I click edit for person \"([^\"]*)\"$")
    public void iClickEditForPerson(String name) {
        WebElement targetRow = null;

        for (WebElement row : driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"))) {
            String rowName = row.findElement(By.cssSelector(".name")).getText();
            if (rowName.equals(name)) {
                targetRow = row;
                break;
            }
        }
        Assert.assertNotNull("Person not found in list: " + name, targetRow);
        targetRow.findElement(By.cssSelector("span[onclick*='openModalForEditPersonWithJob']")).click();
    }

    @Then("^I should see prefilled form with:$")
    public void iShouldSeePrefilledFormWith(Map<String, String> expected) {
        assertEquals(expected.get("name"), driver.findElement(By.id("name")).getAttribute("value"));
        assertEquals(expected.get("job"), driver.findElement(By.id("job")).getAttribute("value"));
    }

    @When("^I change job to \"([^\"]*)\"$")
    public void iChangeJobTo(String newJob) {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(newJob);
    }

    @And("^I click edit button$")
    public void iClickEditButton() {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    /// Task 2.3 Remove
    @When("^I click delete a person \"([^\"]*)\"$")
    public void iClickDeleteForPerson(String name) {
        WebElement targetRow = null;
        for (WebElement row : driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"))) {
            String rowName = row.findElement(By.cssSelector(".name")).getText();
            if (rowName.equals(name)) {
                targetRow = row;
                break;
            }
        }
        Assert.assertNotNull("Person not found in list: " + name, targetRow);

        targetRow.findElement(By.cssSelector("span[onclick*='deletePerson']")).click();
    }

    @Then("^I should not see person \"([^\"]*)\" in the list$")
    public void iShouldNotSeePersonInTheList(String name) {
        boolean personExists = false;
        for (WebElement row : driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"))) {
            String rowName = row.findElement(By.cssSelector(".name")).getText();
            if (rowName.equals(name)) {
                personExists = true;
                break;
            }
        }
        Assert.assertFalse("Person should be removed but is still present: " + name, personExists);
    }

    /// Task 2.4 Reset

    @When("^I add person \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iAddPersonWithJob(String name, String job) {
        driver.findElement(By.id("addPersonBtn")).click();

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);

        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);

        driver.findElement(By.id("modal_button")).click();

        boolean found = false;
        for (WebElement row : driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"))) {
            String rowName = row.findElement(By.cssSelector(".name")).getText().trim();
            if (rowName.equals(name)) {
                found = true;
                break;
            }
        }
        assertTrue("Person was not added to list: " + name, found);
    }

    @When("^I click reset list$")
    public void iClickResetList() {
        driver.findElement(By.cssSelector("button[onclick='resetListOfPeople()']")).click();
    }

    @Then("^I should see original list$")
    public void iShouldSeeDefaultPeopleList() {

        String[][] DEFAULT_PEOPLE = {{"Mike", "Web Designer"}, {"Jill", "Support"}, {"Jane", "Accountant"}, {"John", "Software Engineer"}, {"Sarah", "Product Manager"}, {"Carlos", "Data Analyst"}, {"Emily", "UX Designer"}, {"David", "Project Manager"}, {"Maria", "QA Engineer"}, {"Alex", "DevOps Engineer"}};

        assertEquals("List must contain 10 entries", 10, driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16")).size());

        for (int i = 0; i < 10; i++) {
            String actualName = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16")).get(i).findElement(By.cssSelector(".name")).getText();
            String actualJob  = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16")).get(i).findElement(By.cssSelector(".job")).getText();

            assertEquals("Name mismatch at row " + i, DEFAULT_PEOPLE[i][0], actualName);
            assertEquals("Job mismatch at row " + i, DEFAULT_PEOPLE[i][1], actualJob);
        }
    }
}
