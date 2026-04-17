package cucumber.stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {

    private WebDriver driver;
    private final List<String> expectedOriginalList = List.of(
            "Mike|Web Designer",
            "Jill|Support",
            "Jane|Accountant",
            "John|Software Engineer",
            "Sarah|Product Manager",
            "Carlos|Data Analyst",
            "Emily|UX Designer",
            "David|Project Manager",
            "Maria|QA Engineer",
            "Alex|DevOps Engineer"
    );

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    @Given("I am on number page")
    public void iAmOnNumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("I enter number {string}")
    public void iEnterNumber(String value) {
        WebElement input = driver.findElement(By.id("numb"));
        input.clear();
        input.sendKeys(value);
    }

    @And("I click submit number")
    public void iClickSubmitNumber() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }


    @Then("I should see error message {string}")
    public void iShouldSeeErrorMessage(String expectedMessage) {
        String actual = driver.findElement(By.id("ch1_error")).getText().trim();
        assertEquals(expectedMessage, actual);
    }

    @Then("I should see alert with text {string}")
    public void iShouldSeeAlertWithText(String expectedText) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String actualText = alert.getText();
        assertEquals(expectedText, actualText);

        alert.accept(); // MUST close alert
    }

    @And("I see no error message")
    public void iSeeNoErrorMessage() {
        String errorText = driver.findElement(By.id("ch1_error")).getText().trim();
        assertTrue(errorText.isEmpty(), "Expected no error message, but got: " + errorText);
    }

    @Given("I am on people with jobs page")
    public void iAmOnPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }
    @When("I click {string}")
    public void iClick(String button) {

        if (button.equalsIgnoreCase("Add person")) {

            driver.findElement(By.xpath("//button[text()='Add person']")).click();

        } else if (button.equalsIgnoreCase("Reset List")) {

            driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        } else if (button.equalsIgnoreCase("Add")) {

            driver.findElement(By.id("modal_button")).click();

        } else if (button.equalsIgnoreCase("Edit")) {

            driver.findElement(By.id("modal_button")).click();
        }
    }

    @And("I enter name {string} and job {string}")
    public void iEnterNameAndJob(String name, String job) {

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        nameField.clear();
        nameField.sendKeys(name);

        jobField.clear();
        jobField.sendKeys(job);
    }

    @Then("I should see {string} with job {string} in the list")
    public void iShouldSeePersonInList(String name, String job) {

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        boolean found = false;

        for (WebElement person : people) {
            String actualName = person.findElement(By.className("name")).getText();
            String actualJob = person.findElement(By.className("job")).getText();

            if (actualName.equals(name) && actualJob.equals(job)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "Person not found in list");
    }


    @When("I click edit for {string}")
    public void iClickEditFor(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        for (WebElement person : people) {
            String actualName = person.findElement(By.className("name")).getText();

            if (actualName.equals(name)) {
                person.findElement(By.cssSelector(".fa-pencil")).click();
                return;
            }
        }

        fail("Person to edit not found: " + name);
    }

    @And("I update name to {string} and job to {string}")
    public void iUpdateNameAndJob(String newName, String newJob) {

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        nameField.clear();
        nameField.sendKeys(newName);

        jobField.clear();
        jobField.sendKeys(newJob);
    }

    @When("I remove person {string}")
    public void iRemovePerson(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        for (WebElement person : people) {
            String actualName = person.findElement(By.className("name")).getText();

            if (actualName.equals(name)) {
                person.findElement(By.cssSelector(".w3-closebtn")).click();
                return;
            }
        }

        fail("Person to delete not found: " + name);
    }

    @Then("I should not see {string} in the list")
    public void iShouldNotSeePerson(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        for (WebElement person : people) {
            String actualName = person.findElement(By.className("name")).getText();

            if (actualName.equals(name)) {
                fail("Person still present in list: " + name);
            }
        }
    }

    @Then("I should see original list of people")
    public void verifyOriginalList() {

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        List<String> currentList = new ArrayList<>();

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            String job = person.findElement(By.className("job")).getText();

            currentList.add(name + "|" + job);
        }

        assertEquals(expectedOriginalList.size(), currentList.size(),
                "List size mismatch after reset");

        assertEquals(expectedOriginalList, currentList,
                "List content mismatch after reset");
    }

}