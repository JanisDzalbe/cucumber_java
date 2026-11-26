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

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TaskSteps {
    private WebDriver driver;
    private List<String> originalList;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }
//-------------------------------------------------------------------------
// Task 1
//--------------------------------------------------------------------------

    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number: \"([^\"]*)\"$")
    public void iEnterNumber(String number) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click Submit on number page$")
    public void iClickSubmitOnNumberPage() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("^I see number error: \"([^\"]*)\"$")
    public void iSeeNumberError(String errorMessage) {
        assertEquals(errorMessage, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see alert: \"([^\"]*)\"$")
    public void iSeeAlert(String alert) {
        assertEquals(alert, driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }

    //-------------------------------------------------------------------------
    // Task 2
    //--------------------------------------------------------------------------
    @Given("^I am on people with jobs page$")
    public void iAmOnPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I add a person with details$")
    public void iAddPersonWithDetails(Map<String, String> details) {
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
        enterPersonDetails(details);
        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @Then("^I see the person in the list$")
    public void iSeeThisPersonInTheList(Map<String, String> details) {
        assertTrue(findPerson(details));
    }

    @And("^I do not see the person in the list$")
    public void iDoNotSeePersonInTheList(Map<String, String> details) {
        assertFalse(findPerson(details));
    }


    @When("^I edit person \"([^\"]*)\" with details$")
    public void iEditPersonWithDetails(String name, Map<String, String> details) {
        WebElement person = findPersonByName(name);
        assertNotNull(person);

        person.findElement(By.className("editbtn")).click();
        enterPersonDetails(details);
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @When("^I remove person \"([^\"]*)\"$")
    public void iRemovePerson(String name) {
        WebElement person = findPersonByName(name);
        assertNotNull(person);

        person.findElement(By.className("closebtn")).click();
    }

    @Given("^I save the original list$")
    public void iSaveTheOriginalList() {
        originalList = getPeopleList();
    }

    @When("^I click Reset List$")
    public void iClickResetList() {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @Then("^I see the original list$")
    public void iSeeTheOriginalList() {
        assertEquals(originalList, getPeopleList());
    }

    public void enterPersonDetails(Map<String, String> details) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(details.get("name"));
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(details.get("job"));
    }

    public boolean findPerson(Map<String, String> details) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(details.get("name")) && person.findElement(By.className("job")).getText().equals(details.get("job"))) {
                return true;
            }
        }
        return false;
    }

    public WebElement findPersonByName(String name) {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(name)) {
                return person;
            }
        }
        return null;

    }

    public List<String> getPeopleList() {
        List<String> peopleList = new ArrayList<>();
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            String job = person.findElement(By.className("job")).getText();

            peopleList.add(name + " - " + job);
        }

        return peopleList;
    }

}