package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {
    private WebDriver driver;


    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    //----------------
    // Task 1
    // ----------------

    @Given("^I am on enter a number page$")
    public void iAmOnTheNumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter value: \"([^\"]*)\"$")
    public void iEnterValue(String value) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    @And("^I click submit$")
    public void iClickSubmit() throws Throwable {
        driver.findElement(By.cssSelector("button[onclick='numberValidation()']")).click();
    }

    @Then("^I see error \"([^\"]*)\" on page$")
    public void iShouldSeeError(String error) throws Throwable {
        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see square root alert: \"([^\"]*)\"$")
    public void iShouldSeeSquareRootAlert(String message) throws Throwable {
        Alert alert = driver.switchTo().alert();
        assertEquals(message, alert.getText());
        alert.accept();
    }

    //----------------
    // Task 2
    // ----------------
    @Given("^I am on people with jobs page$")
    public void iAmOnThePeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I reset the people list$")
    public void iResetThePeopleList() {
        driver.findElement(By.xpath("//button[normalize-space()='Reset List']")).click();
    }

    @When("^I add person \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iAddPersonWithJob(String name, String job) {
        driver.findElement(By.xpath("//button[normalize-space()='Add person']")).click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        nameField.clear();
        nameField.sendKeys(name);

        jobField.clear();
        jobField.sendKeys(job);

        driver.findElement(By.id("modal_button")).click();
    }

    @When("^I edit person \"([^\"]*)\" job from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iEditPersonJob(String name, String oldJob, String newJob) {
        WebElement person = findPerson(name);

        WebElement editButton = person.findElement(By.className("editbtn"));
        editButton.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        assertEquals(name, nameField.getAttribute("value"));
        assertEquals(oldJob, jobField.getAttribute("value"));

        jobField.clear();
        jobField.sendKeys(newJob);

        driver.findElement(By.id("modal_button")).click();
    }

    @When("^I remove person \"([^\"]*)\"$")
    public void iRemovePerson(String name) {
        WebElement person = findPerson(name);
        person.findElement(By.className("closebtn")).click();
    }

    @Then("^I see person \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iSeePersonWithJob(String name, String job) {
        WebElement person = findPerson(name);
        assertEquals(name, person.findElement(By.className("name")).getText());
        assertEquals(job, person.findElement(By.className("job")).getText());
    }

    @Then("^I do not see person \"([^\"]*)\"$")
    public void iDoNotSeePerson(String name) {
        List<WebElement> people = driver.findElements(By.xpath("//li[.//span[normalize-space()='" + name + "']]"));
        assertTrue(people.isEmpty());
    }

    @And("^I see (\\d+) people in the list$")
    public void iSeePeopleInTheList(int expectedCount) {
        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));
        assertEquals(expectedCount, people.size());
    }

    @Then("^original people list is displayed$")
    public void originalPeopleListIsDisplayed() {

        String[] expectedNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] expectedJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager", "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};

        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));
        assertEquals(10, people.size());

        for (int i = 0; i < people.size(); i++) {
            WebElement person = people.get(i);
            String actualName = person.findElement(By.className("name")).getText();
            String actualJob = person.findElement(By.className("job")).getText();

            assertEquals(expectedNames[i], actualName);
            assertEquals(expectedJobs[i], actualJob);
        }
    }
    private WebElement findPerson(String name) {
        return driver.findElement(By.xpath(
                "//li[.//span[normalize-space()='" + name + "']]"
        ));
    }
}
