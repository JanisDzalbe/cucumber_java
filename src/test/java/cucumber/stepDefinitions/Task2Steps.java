package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.Alert;

public class Task2Steps {

    private static final String pageURL = "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";
    private static final By peopleRows = By.cssSelector("#listOfPeople li");
    private static final By nameInput = By.id("name");
    private static final By jobInput = By.id("job");

    private WebDriver driver;
    private WebDriverWait wait;
    private List<Person> originalPeople;

    @Given("I open the list of people with jobs page")
    public void openPeoplePage() {
    driver = Hooks.driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    driver.get(pageURL);
    wait.until(ExpectedConditions.visibilityOfElementLocated(peopleRows));
    originalPeople = readPeopleList();
    }

    @When("I add {string} with job {string}")
    public void addPerson(String name, String job) {
    driver.findElement(By.xpath("//button[normalize-space(.)='Add person']")).click();
    fillPersonForm(name, job);
    submitPersonForm("Add");
    }

    @When("I edit {string} to {string} with job {string}")
    public void editPerson(String currentName, String newName, String newJob) {
    findPersonRow(currentName).findElement(By.className("editbtn")).click();
    fillPersonForm(newName, newJob);
    submitPersonForm("Edit");
    }

    @When("I remove {string} from the people list")
    public void removePerson(String name) {
    findPersonRow(name).findElement(By.className("closebtn")).click();
    }

    @When("I click Reset List")
    public void resetList() {
    driver.findElement(By.xpath("//button[normalize-space(.)='Reset List']")).click();
    }

    @Then("{string} is listed with job {string}")
    public void checkPersonJob(String name, String expectedJob) {
    WebElement row = findPersonRow(name);
    String actualJob = row.findElement(By.className("job")).getText();

    assertEquals(expectedJob, actualJob, "Unexpected job for " + name);
    }

    @Then("{string} is absent from the people list")
    public void checkPersonIsAbsent(String name) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(personRow(name)));
    }

    @Then("the original people list is restored")
    public void checkOriginalListIsRestored() {
    assertEquals(originalPeople, readPeopleList());
    }

    private void fillPersonForm(String name, String job) {
    WebElement nameField = wait.until(
    ExpectedConditions.visibilityOfElementLocated(nameInput));
    WebElement jobField = driver.findElement(jobInput);

    nameField.clear();
    nameField.sendKeys(name);
    jobField.clear();
    jobField.sendKeys(job);
    }

    private void submitPersonForm(String buttonText) {
    By submitButton = By.xpath("//*[@id='addEditPerson']//button[normalize-space(.)='" + buttonText + "']");
    wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    private WebElement findPersonRow(String name) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(personRow(name)));
    }

    private By personRow(String name) {
    return By.xpath("//ul[@id='listOfPeople']//li[span[normalize-space(.)='" + name + "']]");
    }

    private List<Person> readPeopleList() {
    return driver.findElements(peopleRows).stream()
    .map(row -> new Person(row.findElement(By.className("name")).getText(), row.findElement(By.className("job")).getText())).toList();
    }

    private record Person(String name, String job) {
    }
    
}
