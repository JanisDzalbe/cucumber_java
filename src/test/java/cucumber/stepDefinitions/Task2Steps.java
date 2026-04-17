package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;


    }

    @Given("^I am on list of people with jobs page$")
    public void iAmOnListOfPeopleWhitJobsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click \"([^\"]*)\" button$")
    public void iClickAddButton(String ButtonText) {
        driver.findElement(By.xpath("//button[contains(text(), '" + ButtonText + "')]")).click();
    }

    @And("^I enter name: \"([^\"]*)\" ")
    public void iEnterName(String name) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);

    }
    @And("^I enter job: \"([^\"]*)\"$")
    public void iEnterJob(String job) {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }


    @And("^I check if the new person \"([^\"]*)\" the \"([^\"]*)\" is added$")
    public void iCheckIfTheNewPersonIsAdded(String name, String job) {
        WebElement newpersoncheck = driver.findElement(By.xpath("//li[span[text()='" + name + "']]"));
        assertTrue(newpersoncheck.getText().contains(job));
    }
    @When("^I click Edit button for \"([^\"]*)\"$")
    public void iClickEditButtonFor(String name) {
        WebElement editButton = driver.findElement(By.xpath("//li[contains(@id,'person') and span[text()='" + name + "']]//i"));
        editButton.click();
    }
    @And("^I change name to \"([^\"]*)\"$")
    public void iChangeNameTo(String newName) {
        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(newName);
    }
    @And("^I change job to \"([^\"]*)\"$")
    public void iChangeJobTo(String newJob) {
        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        jobInput.sendKeys(newJob);
    }
    @And("^I check if the person is changed to \"([^\"]*)\" the \"([^\"]*)\"$")
    public void iCheckIfThePersonIsChanged(String newName, String newJob) {
        WebElement changedPersonCheck = driver.findElement(By.xpath("//li[span[text()='" + newName + "']]"));
        assertTrue(changedPersonCheck.getText().contains(newJob));
    }
    @When("^I click Delete button for \"([^\"]*)\"$")
    public void iClickDeleteButtonFor(String name) {
        WebElement Delete = driver.findElement(By.xpath("//li[contains(@id,'person') and span[text()='" + name + "']]/span[text()='×']"));
        Delete.click();
    }
    @Then("^I check if the person \"([^\"]*)\" is removed from the list$")
    public void iCeckIfThePersonIsRemovedFromTheList(String name) {
        assertEquals(0,driver.findElements(By.xpath("//li[span[text()='" + name + "']]")).size());

    }
    @Then("^I check if the original list is restored$")
    public void iCheckIfTheOriginalListIsRestored() {
        List<WebElement> persons = driver.findElements(By.cssSelector("#listOfPeople li"));
        HashMap<String, String> people = new HashMap<>();
        people.put("Mike", "Web Designer");
        people.put("Jill", "Support");
        people.put("Jane", "Accountant");
        people.put("John", "Software Engineer");
        people.put("Sarah", "Product Manager");
        people.put("Carlos", "Data Analyst");
        people.put("Emily", "UX Designer");
        people.put("David", "Project Manager");
        people.put("Maria", "QA Engineer");
        people.put("Alex", "DevOps Engineer");
        assertEquals(10, persons.size());

        for (WebElement l : persons) {
            String name = l.findElement(By.className("name")).getText();
            String job = l.findElement(By.className("job")).getText();
            assertTrue(people.containsKey(name));
            assertEquals(people.get(name), job);
        }
    }
}