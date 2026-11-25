package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Steps {
    private static final By PERSON = By.cssSelector("#listOfPeople li");
    private static final By ADD_PERSON = By.xpath("//button[text()='Add person']");
    private static final By RESET_LIST = By.xpath("//button[text()='Reset List']");
    private static final By ADD_BUTTON = By.xpath("//button[text()='Add']");
    private static final By EDIT_BUTTON = By.xpath("//button[text()='Edit']");
    private static final By NAME_FIELD = By.id("name");
    private static final By JOB_FIELD = By.id("job");

    private WebDriver driver;
    private WebDriverWait wait;
    private List<String> initialPeople;

    public Task2Steps() {
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am on list of people page")
    public void iAmOnListOfPeoplePage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
        waitForMenu();
        initialPeople = getPeople();
    }

    @And("there are {int} people in the list")
    public void thereArePeopleInTheList(int count) {
        assertEquals(count, getPeople().size());
    }

    @When("I add person {string} with job {string}")
    public void iAddPerson(String name, String job) {
        clickAndWaitForNewPage(driver.findElement(ADD_PERSON));
        enterNameAndJob(name, job);
        clickAndWaitForNewPage(driver.findElement(ADD_BUTTON));
    }

    @When("I change person {string} with job {string} to {string} with job {string}")
    public void iChangePerson(String name, String job, String newName, String newJob) {
        clickAndWaitForNewPage(findPerson(name).findElement(By.className("editbtn")));
        assertEquals(name, driver.findElement(NAME_FIELD).getDomProperty("value"));
        assertEquals(job, driver.findElement(JOB_FIELD).getDomProperty("value"));
        enterNameAndJob(newName, newJob);
        clickAndWaitForNewPage(driver.findElement(EDIT_BUTTON));
    }

    @When("I remove person {string}")
    public void iRemovePerson(String name) {
        clickAndWaitForNewPage(findPerson(name).findElement(By.className("closebtn")));
    }

    @When("I reset the list")
    public void iResetTheList() {
        clickAndWaitForNewPage(driver.findElement(RESET_LIST));
    }

    @Then("person {string} with job {string} is added to the initial list")
    public void personIsAdded(String name, String job) {
        List<String> expectedPeople = new ArrayList<>(initialPeople);
        expectedPeople.add(name + ", " + job);
        assertEquals(expectedPeople, getPeople());
    }

    @Then("person {string} with job {string} is replaced by {string} with job {string} in the initial list")
    public void personIsReplaced(String name, String job, String newName, String newJob) {
        List<String> expectedPeople = new ArrayList<>(initialPeople);
        int index = expectedPeople.indexOf(name + ", " + job);
        assertTrue(index >= 0, "No such person in the initial list: " + name + ", " + job);
        expectedPeople.set(index, newName + ", " + newJob);
        assertEquals(expectedPeople, getPeople());
    }

    @Then("person {string} with job {string} is removed from the initial list")
    public void personIsRemoved(String name, String job) {
        List<String> expectedPeople = new ArrayList<>(initialPeople);
        assertTrue(expectedPeople.remove(name + ", " + job), "No such person in the initial list: " + name + ", " + job);
        assertEquals(expectedPeople, getPeople());
    }

    @Then("I see the initial list")
    public void iSeeTheInitialList() {
        assertEquals(initialPeople, getPeople());
    }

    // returns all people in the list as "name, job"
    private List<String> getPeople() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(PERSON, 0));
        List<String> people = new ArrayList<>();
        for (WebElement person : driver.findElements(PERSON)) {
            people.add(person.findElement(By.className("name")).getText() + ", "
                    + person.findElement(By.className("job")).getText());
        }
        return people;
    }

    private WebElement findPerson(String name) {
        return driver.findElement(By.xpath("//li[.//*[contains(@class, 'name') and text()='" + name + "']]"));
    }

    private void enterNameAndJob(String name, String job) {
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(NAME_FIELD));
        nameField.clear();
        nameField.sendKeys(name);
        WebElement jobField = driver.findElement(JOB_FIELD);
        jobField.clear();
        jobField.sendKeys(job);
    }

    // every action on this page opens or reloads a page, so wait until the old one is gone
    private void clickAndWaitForNewPage(WebElement element) {
        WebElement oldPage = driver.findElement(By.tagName("html"));
        element.click();
        wait.until(ExpectedConditions.stalenessOf(oldPage));
        waitForMenu();
    }

    // menu is loaded separately and moves the rest of the page down, so wait for it before clicking
    private void waitForMenu() {
        driver.findElement(By.cssSelector("#header > *"));
    }
}
