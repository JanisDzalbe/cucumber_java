package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SampleSteps {
    private static WebDriver driver;

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

    //Sample 1 task
    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeLocatorsPageHeaders() throws Throwable {
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
        assertEquals("Heading 1",
                driver.findElement(By.id("heading_1")).getText());
        assertEquals("Heading 2 text",
                driver.findElement(By.id("heading_2")).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageClickable() throws Throwable {
        assertTrue(driver.findElement(By.cssSelector("input[name='randomButton1']")).isEnabled());
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

    //Sample 2 Task
    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeAgePageError(String error) throws Throwable {
        assertTrue(driver.findElement(By.id("error")).isDisplayed());
        assertEquals(error, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgePage() throws Throwable {
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age",
                driver.getCurrentUrl());
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

    //Sample 3 task

    @Given("^I (?:am on|open) feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I set name: \"([^\"]*)\"$")
    public void iSetName(String name) throws Throwable {
        driver.findElement(By.cssSelector("#fb_name")).clear();
        driver.findElement(By.cssSelector("#fb_name")).sendKeys(name);
    }

    @And("^I set age: (\\d+)$")
    public void iSetAge(int age) throws Throwable {
        driver.findElement(By.cssSelector("#fb_age")).clear();
        driver.findElement(By.cssSelector("#fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click Send button$")
    public void iClickSendAge() throws Throwable {
        driver.findElement(By.cssSelector("button[type='submit'].w3-blue")).isEnabled();
        driver.findElement(By.cssSelector("button[type='submit'].w3-blue")).click();
    }

    @Then("^I see \"([^\"]*)\" in check feedback page$")
    public void iSeeNameInFeedbackCheck(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.cssSelector("#name")).getText());
    }

    @And("^I see (\\d+) in check feedback page$")
    public void iSeeAgeInFeedbackCheck(int age) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.cssSelector("#age")).getText());
    }


    //Task 1

    @Given("^I (?:am on|open) Number page$")
    public void iAmOnNumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in the number field$")
    public void iEnterInput(String input) throws Throwable {
        driver.findElement(By.cssSelector("#numb")).clear();
        driver.findElement(By.cssSelector("#numb")).sendKeys(input);
    }

    @When("^I press the submit button$")
    public void iPressSubmit() throws Throwable {
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='button'].w3-orange"));
        assertTrue(submitButton.isDisplayed());
        assertTrue(submitButton.isEnabled());
        submitButton.click();
    }

    @Then("^I see the error message \"([^\"]*)\"$")
    public void iSeeErrorMessage(String error) throws Throwable {
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errorMessage.isDisplayed());

        String actualError = errorMessage.getText();

        assertEquals(error, actualError);
    }


    @When("^I enter number: (\\d+)$")
    public void iEnterValidNumber(int number) throws Throwable {
        driver.findElement(By.cssSelector("#numb")).clear();
        driver.findElement(By.cssSelector("#numb")).sendKeys(String.valueOf(number));
    }

    @Then("^I see the popup window for number: (\\d+)$")
    public void iSeePopupWindow(int number) throws Throwable {
        Alert alert = driver.switchTo().alert();

        String squareRoot = String.format("%.2f", Math.sqrt(number));
        String expectedAlertText =
                "Square root of " + number + " is " + squareRoot;

        assertEquals(expectedAlertText, alert.getText());

        alert.accept();

        WebElement error = driver.findElement(By.id("ch1_error"));
        assertFalse(error.isDisplayed());
    }

    //Sample 4 Task

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) throws Throwable {
        for (String language : languages) {
            driver.findElement(
                    By.cssSelector("input[type='checkbox'][name='language'][value='" + language + "']")).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iAssertFeedbackLanguages(String languages) throws Throwable {
        assertEquals(languages, driver.findElement(By.id("language")).getText());
    }


    //Sample 5 Task
    @When("^I input values on feedback page$")
    public void iInputValuesOnFeedbackPage(Map<String, String> inputMap) throws Throwable {
        iSetName(inputMap.get("name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(inputMap.get("age"));
        driver.findElement(By.cssSelector("[type='radio'][value='" + inputMap.get("gender") + "']")).click();
    }

    @When("^I can see values in feedback check$")
    public void iAssertValuesOnFeedbackPage(Map<String, String> inputMap) throws Throwable {
        assertEquals(inputMap.get("name"), driver.findElement(By.id("name")).getText());
        assertEquals(inputMap.get("age"), driver.findElement(By.id("age")).getText());
        assertEquals(inputMap.get("gender"), driver.findElement(By.id("gender")).getText());
    }

    //TASK 2

    @Given("^I am on List of People With Jobs page$")
    public void iAmOnPeopleJobsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    int initialCount;
    List<String> originalList = new ArrayList<>();
    WebElement removedPerson;
    WebElement firstPerson;
    String originalFirstPersonName;
    String removedPersonIdentifier;

    //Adding a person
    @When("^I click on add person button$")
    public void clickAddPersonButton() throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        initialCount = people.size();

        WebElement addPersonButton = driver.findElement(By.xpath("(//button[text()='Add person'])"));
        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        addPersonButton.click();
    }

    @Then("^I am redirected to Enter a New Person page$")
    public void assertRedirectionToAddPersonPage() throws Throwable {
        assertTrue(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html"));
    }

    @When("^I enter name and job$")
    public void enterNameAndJob(Map<String, String> inputMap) throws Throwable {
        WebElement nameInput = driver.findElement(By.cssSelector("#name"));
        WebElement jobInput = driver.findElement(By.cssSelector("#job"));

        assertTrue(nameInput.isDisplayed());
        assertTrue(jobInput.isDisplayed());

        nameInput.clear();
        nameInput.sendKeys(inputMap.get("name"));

        jobInput.clear();
        jobInput.sendKeys(inputMap.get("job"));

        Thread.sleep(2000);
    }

    @And("^I click Add button$")
    public void clickModalAddButon() throws Throwable {
        WebElement addModalButton = driver.findElement(By.xpath("//button[@id='modal_button' and text()='Add']"));
        assertTrue(addModalButton.isDisplayed());
        assertTrue(addModalButton.isEnabled());
        addModalButton.click();
    }

    @Then("^I see added person in the end of the list$")
    public void assertNewPersonIsAdded(Map<String, String> inputMap) throws Throwable {
        List<WebElement> peopleUpdated = driver.findElements(By.className("w3-padding-16"));

        WebElement addedPerson = peopleUpdated.get(peopleUpdated.size() - 1);

        assertTrue(addedPerson.isDisplayed());

        String actualName = addedPerson.findElement(By.className("name")).getText();
        String actualJob = addedPerson.findElement(By.className("job")).getText();

        assertEquals(inputMap.get("name"), actualName);
        assertEquals(inputMap.get("job"), actualJob);

        int actualListSize = peopleUpdated.size();
        assertNotEquals(initialCount, actualListSize);

        Thread.sleep(3000);
    }

    //Editing a person
    @When("^I click on edit button for person with index: (\\d+)$")
    public void clickEditPersonButton(int index) throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        initialCount = people.size();

        assertTrue(index >= 0 && index < people.size());

        WebElement editedPerson = people.get(index);
        assertTrue(editedPerson.isDisplayed());

        WebElement editedPersonEditButton = editedPerson.findElement(By.className("editbtn"));
        assertTrue(editedPersonEditButton.isDisplayed());
        assertTrue(editedPersonEditButton.isEnabled());
        editedPersonEditButton.click();
    }

    @And("^I click Edit button$")
    public void clickModalEditButon() throws Throwable {
        WebElement editModalButton = driver.findElement(By.xpath("//button[@id='modal_button' and text()='Edit']"));
        assertTrue(editModalButton.isDisplayed());
        assertTrue(editModalButton.isEnabled());
        editModalButton.click();

        Thread.sleep(2000);
    }

    @Then("^I see edited person with (\\d+) on the list$")
    public void assertPersonEdited(int index, Map<String, String> inputMap) throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        WebElement addedPerson = people.get(index);

        String actualName = addedPerson.findElement(By.className("name")).getText();
        String actualJob = addedPerson.findElement(By.className("job")).getText();

        assertEquals(inputMap.get("name"), actualName);
        assertEquals(inputMap.get("job"), actualJob);

        assertEquals(initialCount, people.size());
    }

    //Removing a person
    @When("^I click on remove button for person with index (\\d+)$")
    public void clickRemovePersonButton(int index) throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        initialCount = people.size();
        originalList.clear();

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job = person.findElement(By.className("job")).getText().trim();
            originalList.add(name + " | " + job);
        }

        WebElement personToRemove = people.get(index);
        assertTrue(personToRemove.isDisplayed());

        String name = personToRemove.findElement(By.className("name")).getText().trim();
        String job  = personToRemove.findElement(By.className("job")).getText().trim();
        removedPersonIdentifier = name + " | " + job;

        WebElement removedPersonRemoveButton =
                personToRemove.findElement(By.className("closebtn"));
        assertTrue(removedPersonRemoveButton.isDisplayed());
        assertTrue(removedPersonRemoveButton.isEnabled());
        removedPersonRemoveButton.click();
    }

    @Then("^The removed person is not on the list$")
    public void assertRemovePersonNotOnList() throws Throwable {
        List<WebElement> peopleUpdated = driver.findElements(By.className("w3-padding-16"));
        List<String> actualList = new ArrayList<>();

        for (WebElement person : peopleUpdated) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job = person.findElement(By.className("job")).getText().trim();
            actualList.add(name + " | " + job);
        }

        assertNotEquals(originalList, actualList);
        assertNotEquals(originalList.size(), actualList.size());

        assertFalse(actualList.contains(removedPersonIdentifier));

        Thread.sleep(5000);
    }

    //Resetting the list

    @When("^I click on first person's edit button$")
    public void editFirstPersonName() throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));

        originalList.clear();

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job  = person.findElement(By.className("job")).getText().trim();
            originalList.add(name + " | " + job);
        }

        firstPerson = people.get(0);
        originalFirstPersonName = firstPerson.findElement(By.className("name")).getText().trim();

        WebElement firstPersonEditButton = firstPerson.findElement(By.className("editbtn"));
        assertTrue(firstPersonEditButton.isDisplayed());
        assertTrue(firstPersonEditButton.isEnabled());
        firstPersonEditButton.click();
    }

    @When("^I enter a different name$")
    public void enterNameAndJob() throws Throwable {
        WebElement nameInput = driver.findElement(By.cssSelector("#name"));

        assertTrue(nameInput.isDisplayed());

        nameInput.clear();
        nameInput.sendKeys(("Alice"));

        Thread.sleep(2000);
    }

    @Then("^I see first person's edited name$")
    public void assertFirstNameChanged() throws Throwable {
        List<WebElement> peopleUpdated = driver.findElements(By.className("w3-padding-16"));

        WebElement updatedFirstPerson = peopleUpdated.get(0);

        String updatedName = updatedFirstPerson.findElement(By.className("name")).getText().trim();

        assertNotEquals(originalFirstPersonName, updatedName);
    }


    @When("^I click on Reset List button$")
    public void clickResetListButton() throws Throwable {
        WebElement resetButton = driver.findElement(By.xpath("//button[text()='Reset List']"));
        assertTrue(resetButton.isDisplayed());
        assertTrue(resetButton.isEnabled());
        resetButton.click();
    }

    @Then("^I see that the list is reset$")
    public void assertListReset() throws Throwable {
        List<WebElement> peopleUpdated = driver.findElements(By.className("w3-padding-16"));
        List<String> actualList = new ArrayList<>();

        for (WebElement person : peopleUpdated) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job = person.findElement(By.className("job")).getText().trim();
            actualList.add(name + " | " + job);
        }

        assertEquals(originalList, actualList);
    }
}