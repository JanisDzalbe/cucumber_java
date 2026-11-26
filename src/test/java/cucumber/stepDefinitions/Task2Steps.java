package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

///
public class Task2Steps {
    private WebDriver driver;
    private List<String> initialPeople;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on People with jobs page$")
    public void iAmOnThePeopleWithJobsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("I add person {string} with job {string}")
    public void iAddPerson(String name, String job) {
        driver.findElement(By.id("addPersonBtn")).click();
        driver.findElement(By.id("name")).sendKeys("Krishna Prabhu");
        driver.findElement(By.id("job")).sendKeys("Test Specialist");
        driver.findElement(By.id("modal_button")).click();
    }

    @Then("person {string} with job {string} is added to the list")
    public void iSeePersonWithJobadded(String name, String job) {
        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(pageText.contains("Krishna Prabhu"));
        assertTrue(pageText.contains("Test Specialist"));
    }
    @And("I see the people list should contain {int} entries")
    public void thePeopleListShouldContain11Entries(int expectedCount) {

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        assertEquals(expectedCount, people.size());
    }

    @When("I click the Edit icon for {string}")
    public void iClickEditButton(String name) {
        driver.findElement(By.xpath("//*[@id=\"person1\"]/span[2]")).click();
    }

    @Then("I edit the Job field to {string} and see the updated job {string} in the list")
    public void iEditAPersonDetail(String name, String job) {
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        assertEquals("Jill", nameInput.getDomProperty("value"));
        assertEquals("Support", jobInput.getDomProperty("value"));

        String updatedJob = "Test Specialist";
        jobInput.clear();
        jobInput.sendKeys(updatedJob);

        driver.findElement(By.id("modal_button")).click();
        WebElement editedPerson = driver.findElement(By.id("person1"));
        assertEquals("Jill", editedPerson.findElement(By.className("name")).getText());
        assertEquals(updatedJob, editedPerson.findElement(By.className("job")).getText());
    }

//    @Then("I see the people list should contain {int} entries")
//    public void thePeopleListShouldContainEntries(int expectedCount) {
//
//        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
//
//        assertEquals(expectedCount, people.size());
//    }

    @When("I remove person {string}")
    public void iRemovedAPerson(String name) {
        WebElement alex = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='Sarah']]"));
        alex.findElement(By.className("closebtn")).click();
    }

    @Then("I see person {string} with job {string} is removed from the initial list")
    public void iSeeThePersonIsRemovedFromTheList(String name, String job) {
        assertTrue(driver.findElements(By.xpath("//li[.//span[contains(@class,'name') and text()='Sarah']]")).isEmpty());
        assertEquals(9, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());
    }

    @When("^I reset the list$")
    public void iResetTheList() {
        driver.findElements(By.xpath("//button[text()='Reset List']")).get(0).click();
    }

    @Then("^I see the initial list$")
    public void iSeeTheInitialList() {
        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(10, personList.size());
        List<WebElement> listofPeople = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(10, listofPeople.size());
    }

}
