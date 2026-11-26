package cucumber.stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PeopleWithJobsSteps {

    WebDriverWait wait;

    @Given("user opens the People with jobs page")
    public void userOpensThePage() {

        Hooks.driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

        wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
    }

    //add

    @When("user adds person {string} with job {string}")
    public void userAddsPerson(String name, String job) {

        Hooks.driver.findElement(By.xpath("//button[normalize-space()='Add person']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        Hooks.driver.findElement(By.id("name")).sendKeys(name);
        Hooks.driver.findElement(By.id("job")).sendKeys(job);

        Hooks.driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();

        wait.until(driver -> {
            List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

            for (WebElement person : people) {
                String personName = person.findElement(By.className("name")).getText().trim();

                String personJob = person.findElement(By.className("job")).getText().trim();

                if (personName.equals(name) && personJob.equals(job)) {
                    return true;
                }
            }

            return false;
        });
    }

    @Then("person {string} with job {string} should be displayed")
    public void personShouldBeDisplayed(String name, String job) {
        WebElement person = findPerson(name);

        assertEquals(job, person.findElement(By.className("job")).getText());
    }

    //edit

    @When("user changes the job of {string} to {string}")
    public void userChangesTheJob(String name, String newJob) {

        WebElement person = findPerson(name);

        person.findElement(By.className("editbtn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        WebElement nameInput = Hooks.driver.findElement(By.id("name"));
        WebElement jobInput = Hooks.driver.findElement(By.id("job"));

        assertEquals(name, nameInput.getAttribute("value"));

        jobInput.clear();
        jobInput.sendKeys(newJob);

        Hooks.driver.findElement(By.xpath("//button[normalize-space()='Edit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Add person']")));
    }

    @Then("person {string} should have job {string}")
    public void personShouldHaveJob(String name, String job) {

        WebElement person = findPerson(name);

        assertEquals(job, person.findElement(By.className("job")).getText());
    }

    //remove

    @When("user removes person {string}")
    public void userRemovesPerson(String name) {

        WebElement person = findPerson(name);

        person.findElement(By.className("closebtn")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[.//span[@class='name' and normalize-space()='" + name + "']]")));
    }

    @Then("person {string} should not be displayed")
    public void personShouldNotBeDisplayed(String name) {

        assertTrue(Hooks.driver.findElements(By.xpath("//li[.//span[@class='name' and normalize-space()='" + name + "']]")).isEmpty());
    }

    //reset

    @When("user clicks the Reset List button")
    public void userClicksResetList() {

        Hooks.driver.findElement(By.xpath("(//button[normalize-space()='Reset List'])[1]")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//ul[@id='listOfPeople']//li"), 10));
    }

    @Then("the original list should be displayed:")
    public void originalListShouldBeDisplayed(DataTable table) {

        List<Map<String, String>> people = table.asMaps(String.class, String.class);

        assertEquals(10, Hooks.driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());

        for (Map<String, String> person : people) {

            WebElement actualPerson = findPerson(person.get("Name"));

            assertEquals(person.get("Job"), actualPerson.findElement(By.className("job")).getText());
        }
    }

    public WebElement findPerson(String name) {
        List<WebElement> people = Hooks.driver.findElements(By.cssSelector("#listOfPeople li"));

        for (WebElement person : people) {
            String personName = person.findElement(By.className("name")).getText().trim();

            if (personName.equalsIgnoreCase(name)) {
                return person;
            }
        }

        throw new NoSuchElementException("Person not found: " + name);
    }
}