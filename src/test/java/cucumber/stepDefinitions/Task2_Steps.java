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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2_Steps {
    private WebDriver driver;
    private int initialCount;

    public Task2_Steps() {
        this.driver = Hooks.driver;
    }

    @Given("user is on people with jobs page")
    public void openPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @And("user stores initial list size")
    public void storeInitialList() {
        initialCount = driver.findElements(By.cssSelector("#listOfPeople li")).size();
    }

    // ---------- ADD PERSON (FIXED) ----------

    @When("user adds a new person with name {string} and job {string}")
    public void addPerson(String name, String job) {

        // adds person
        driver.findElements(By.id("addPersonBtn")).getFirst().click();

        // fills form
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);

        // clicks Add button
        driver.findElements(By.id("modal_button")).getFirst().click();
    }

    @Then("list size increases")
    public void verifyPersonAdded() {
        int newSize = driver.findElements(By.cssSelector("#listOfPeople li")).size();
        assertTrue(newSize > initialCount);
    }

    @When("user deletes first person")
    public void deleteFirstPerson() {
        WebElement firstPerson = driver.findElements(By.cssSelector("#listOfPeople li")).getFirst();
        firstPerson.findElement(By.cssSelector(".closebtn")).click();
    }

    @Then("list size decreases")
    public void verifyPersonDeleted() {
        int newSize = driver.findElements(By.cssSelector("#listOfPeople li")).size();
        assertTrue(newSize < initialCount);
    }

    @When("user edits first person to name {string} and job {string}")
    public void editPerson(String newName, String newJob) {

        WebElement firstPerson = driver.findElements(By.cssSelector("#listOfPeople li")).getFirst();

        // clicks edit
        firstPerson.findElement(By.cssSelector(".editbtn")).click();

        // methods of clear and fill
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        nameInput.clear();
        nameInput.sendKeys(newName);

        jobInput.clear();
        jobInput.sendKeys(newJob);

        driver.findElements(By.id("modal_button")).getFirst().click(); //submits
    }

    @Then("edited person is updated")
    public void verifyEdited() {

        WebElement firstPerson = driver.findElements(By.cssSelector("#listOfPeople li")).getFirst();

        String name = firstPerson.findElement(By.cssSelector(".name")).getText();
        String job = firstPerson.findElement(By.cssSelector(".job")).getText();

        assertTrue(name.contains("EditedUser"));
        assertTrue(job.contains("EditedJob"));
    }

    @When("user resets list")
    public void resetList() {
        driver.findElement(By.xpath("//button[contains(text(),'Reset List')]")).click();
    }

    @Then("list returns to original size")
    public void verifyReset() {
        int currentSize = driver.findElements(By.cssSelector("#listOfPeople li")).size();
        assertEquals(initialCount, currentSize);
    }
}
