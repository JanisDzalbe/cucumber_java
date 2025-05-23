package cucumber.stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.junit.Assert.*;

public class taskTwoSteps {

    private WebDriver driver = Hooks.driver;
    private final int INITIAL_COUNT = 3;

    @Given("I open the people list page")
    public void iOpenPeopleListPage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs");
    }

    @And("The default list is visible")
    public void verifyDefaultList() {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        assertEquals(INITIAL_COUNT, people.size());
        assertEquals("Mike", people.get(0).findElement(By.className("name")).getText());
        assertEquals("Web Designer", people.get(0).findElement(By.className("job")).getText());
        assertEquals("Jill", people.get(1).findElement(By.className("name")).getText());
        assertEquals("Support", people.get(1).findElement(By.className("job")).getText());
        assertEquals("Jane", people.get(2).findElement(By.className("name")).getText());
        assertEquals("Accountant", people.get(2).findElement(By.className("job")).getText());
    }

    @When("I open add person dialog")
    public void openAddPersonDialog() {
        driver.findElement(By.cssSelector("[onclick='openModalForAddPersonWithJob()']")).click();
    }

    @And("I input {string} and {string}")
    public void inputPersonDetails(String name, String job) {
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        nameField.clear();
        nameField.sendKeys(name);
        jobField.clear();
        jobField.sendKeys(job);
    }

    @And("I confirm adding the person")
    public void confirmAddPerson() {
        driver.findElement(By.cssSelector("[onclick='addPersonWithJobToList()']")).click();
    }

    @When("I edit the {int}{word} person")
    public void selectPersonToEdit(int index, String ordinal) {
        driver.findElements(By.className("w3-padding-16")).get(index - 1).findElement(By.className("editbtn")).click();
    }

    @Then("The list includes a person named {string} with job {string}")
    public void verifyPersonAdded(String name, String job) {
        List<WebElement> names = driver.findElements(By.className("name"));
        List<WebElement> jobs = driver.findElements(By.className("job"));
        assertEquals(name, names.get(names.size() - 1).getText());
        assertEquals(job, jobs.get(jobs.size() - 1).getText());
    }

    @And("I update the name field to {string}")
    public void updateName(String name) {
        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    @And("I update the job field to {string}")
    public void updateJob(String job) {
        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    @And("I update the name to {string} and job to {string}")
    public void updateNameAndJob(String name, String job) {
        updateName(name);
        updateJob(job);
    }

    @Then("The {int}{word} person’s name is {string} and job is {string}")
    public void verifyNameAndJobUpdated(int index, String ordinal, String name, String job) {
        WebElement person = driver.findElements(By.className("w3-padding-16")).get(index - 1);
        assertEquals(name, person.findElement(By.className("name")).getText());
        assertEquals(job, person.findElement(By.className("job")).getText());
    }

    @And("Press edit Button")
    public void saveChanges() {
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Edit']")).click();
    }

    @And("The total number of people decreased")
    public void verifyListIsShorter() {
        assertNotEquals(INITIAL_COUNT, driver.findElements(By.className("w3-padding-16")).size());
    }

    @When("I delete the person name {string}")
    public void deletePersonByName(String name) {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(name)) {
                person.findElement(By.className("closebtn")).click();
                break;
            }
        }
    }

    @Then("The list does not contain {string}")
    public void verifyPersonDeleted(String name) {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            assertNotEquals(name, person.findElement(By.className("name")).getText());
        }
    }

    @Then("The default list is shown")
    public void verifyDefaultListIsShown() {
        verifyDefaultList();
    }

    @When("I reset the people list")
    public void resetPeopleList() {
        driver.findElement(By.cssSelector("[onclick='resetListOfPeople()']")).click();
    }

    @Then("All input fields are empty")
    public void verifyInputsAreEmpty() {
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        assertEquals("", nameField.getAttribute("value"));
        assertEquals("", jobField.getAttribute("value"));
    }

    @And("I clear all inputs")
    public void clearInputFields() {
        driver.findElement(By.id("addPersonBtn")).click();
    }
}
