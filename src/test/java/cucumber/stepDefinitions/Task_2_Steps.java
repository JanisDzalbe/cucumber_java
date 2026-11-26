package cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task_2_Steps {
    private WebDriver driver;

    public Task_2_Steps() {
        this.driver = Hooks.driver;
    }

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        // Wait up to 5 seconds when looking for elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


    }
    @Given("^I open the List of people page$")
    public void iOpenTheListOfPeoplePage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @Then("^I am on the List of people with jobs page$")
    public void iAmOnTheListOfPeopleWithJobsPage() {
        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("list_of_people_with_jobs.html"));
    }

    @Then("^I am on the Enter a new person with a job page$")
    public void iAmOnTheEnterANewPersonPage() {
        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("enter_a_new_person_with_a_job.html"));
    }

    // And List of people is reset to the original list
    @Given("^List of people is reset to the original list$")
    public void listOfPeopleIsReset() {
        driver.findElement(By.cssSelector("button[onclick='resetListOfPeople()']")).click();;
    }

    @When("^I click the Add Person button$")
    public void IClickTheAddPersonButton() {
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        addPersonButton.click();
    }
    @When("^I enter \"([^\"]*)\" in the (Name|Job) field$")
    public void iEnterInTheField(String value, String field) {
        WebElement input = driver.findElement(By.id(field.toLowerCase()));   // "Name" -> id="name"
        input.clear();
        input.sendKeys(value);
    }
    @When("^I click the Add button$")
    public void IClickTheAddButton(){
        driver.findElement(By.cssSelector("button[onclick='addPersonWithJobToList()']")).click();
    }

    @When("^I click the Edit button$")
    public void iClickTheEditButton() {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @When("^I replace the \"([^\"]*)\" field with \"([^\"]*)\"$")
    public void iReplaceTheFieldWith(String field, String value) {
        WebElement input = driver.findElement(By.id(field.toLowerCase()));
        input.clear();
        input.sendKeys(value);
    }

    @When("^I click the edit icon for \"([^\"]*)\"$")
    public void iClickTheEditButton(String name) {
        WebElement person = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]"));
        person.findElement(By.cssSelector(".fa-pencil")).click();
    }

    @When("^I click the remove\\s+icon for \"([^\"]*)\"$")
    public void iClickTheRemoveIconFor(String name) {
        WebElement person = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]"));
        // ".closebtn" is only on the × icon (the pencil also has "w3-closebtn")
        person.findElement(By.cssSelector(".closebtn")).click();
    }

    @Given("^I add a person with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iAddAPerson(String name, String job) {
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
        driver.findElement(By.cssSelector("button[onclick='addPersonWithJobToList()']")).click();
    }

    @Given("^I edit the person \"([^\"]*)\" to have name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iEditThePerson(String name, String newName, String newJob) {
        WebElement person = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]"));
        person.findElement(By.cssSelector(".fa-pencil")).click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        nameField.clear();
        nameField.sendKeys(newName);
        jobField.clear();
        jobField.sendKeys(newJob);
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }


    @Then("^(?:List|the list) contains (\\d+) people$")
    public void theListContainsPeople(int count) {
        List<WebElement> names = driver.findElements(By.cssSelector(".name"));
        assertEquals(count, names.size());
    }

    // And Last person in the list is "<name>" and job "<job>"
    @Then("^Last person in the list is \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void lastPersonInTheListIs(String name, String job) {
        List<WebElement> names = driver.findElements(By.cssSelector(".name"));
        List<WebElement> jobs = driver.findElements(By.cssSelector(".job"));
        int last = names.size() - 1;
        assertEquals(name, names.get(last).getText());
        assertEquals(job, jobs.get(last).getText());
    }

    @When("^I click the Reset List button$")
    public void iClickTheResetListButton(){
        driver.findElement(By.cssSelector("button[onclick='resetListOfPeople()']")).click();
    }



    // And the person at position <position> has name "<new name>" and job "<new job>"
    @Then("^the person at position (\\d+) has name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void thePersonAtPositionHas(int position, String name, String job) {
        List<WebElement> names = driver.findElements(By.cssSelector(".name"));
        List<WebElement> jobs = driver.findElements(By.cssSelector(".job"));
        assertEquals(name, names.get(position - 1).getText());   // position 1 = index 0
        assertEquals(job, jobs.get(position - 1).getText());
    }

    // And the list contains a person with name "Jane" and job "Accountant"
    @Then("^the list contains a person with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void theListContainsAPerson(String name, String job) {
        WebElement person = driver.findElement(
                By.xpath("//*[contains(@id,'person') and .//*[contains(@class,'name') and text()='" + name + "']]"));
        assertEquals(job, person.findElement(By.className("job")).getText());
    }

    // And the list does not contain a person with name "Anna"
    @Then("^the list does not contain a person with name \"([^\"]*)\"$")
    public void theListDoesNotContainAPerson(String name) {
        List<WebElement> found = driver.findElements(
                By.xpath("//*[contains(@id,'person') and .//*[contains(@class,'name') and text()='" + name + "']]"));
        assertEquals(0, found.size());
    }

    // And the list matches the original list
    @Then("^the list matches the original list$")
    public void theListMatchesTheOriginalList() {
        Map<String, String> expectedPeople = Map.of(
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

        List<WebElement> names = driver.findElements(By.cssSelector(".name"));
        List<WebElement> jobs = driver.findElements(By.cssSelector(".job"));
        assertEquals(expectedPeople.size(), names.size());

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i).getText();
            String job = jobs.get(i).getText();
            assertTrue(expectedPeople.containsKey(name), "Unexpected person: " + name);
            assertEquals(expectedPeople.get(name), job);
        }
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}


