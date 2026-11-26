package cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Steps {
    WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    // Open the page
    @Given("I open the People with Jobs page")
    public void iOpenThePeopleWithJobsPage() {

        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    // Reset before each scenario
    @Given("I reset the people list")
    public void iResetThePeopleList() {

        driver.findElement(
                By.xpath("//button[text()='Reset List']")).click();
    }

    // ADD PERSON
    @When("I click Add person")
    public void iClickAddPerson() {

        driver.findElement(
                By.xpath("//button[text()='Add person']")).click();
    }

    @When("I enter name {string} and job {string}")
    public void iEnterNameAndJob(String name, String job) {

        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @When("I click Add")
    public void iClickAdd() {

        driver.findElement(
                By.xpath("//button[text()='Add']")).click();
    }

    @Then("I should see {string} with job {string} in the list")
    public void iShouldSeePersonWithJob(String name, String job) {

        WebElement person = driver.findElement(
                By.xpath("//li[.//span[text()='" + name + "']]"));

        assertEquals(name,
                person.findElement(By.className("name")).getText());

        assertEquals(job,
                person.findElement(By.className("job")).getText());
    }

    @Then("the people list should contain {int} entries")
    public void thePeopleListShouldContainEntries(int expectedCount) {

        List<WebElement> people = driver.findElements(
                By.cssSelector("#listOfPeople li"));

        assertEquals(expectedCount, people.size());
    }

    // EDIT PERSON
    @When("I click the pencil icon for {string}")
    public void iClickThePencilIconFor(String name) {

        WebElement person = driver.findElement(
                By.xpath("//li[.//span[text()='" + name + "']]"));

        person.findElement(By.className("editbtn")).click();
    }

    @Then("the Name field should contain {string} and Job field should contain {string}")
    public void iVerifyNameAndJobFields(String name, String job) {

        assertEquals(name,
                driver.findElement(By.id("name")).getAttribute("value"));

        assertEquals(job,
                driver.findElement(By.id("job")).getAttribute("value"));
    }

    @When("I change the Job field to {string}")
    public void iChangeTheJobField(String newJob) {

        WebElement job = driver.findElement(By.id("job"));

        job.clear();
        job.sendKeys(newJob);
    }

    @When("I click Edit")
    public void iClickEdit() {

        driver.findElement(
                By.xpath("//button[text()='Edit']")).click();
    }

    // REMOVE PERSON
    @When("I click the cross icon for {string}")
    public void iClickTheCrossIconFor(String name) {

        WebElement person = driver.findElement(
                By.xpath("//li[.//span[text()='" + name + "']]"));

        person.findElement(By.className("closebtn")).click();
    }

    @Then("{string} should not be in the people list")
    public void personShouldNotBeInTheList(String name) {

        assertEquals(0,
                driver.findElements(
                        By.xpath("//li[.//span[text()='" + name + "']]")
                ).size());
    }

    // RESET LIST - USING EXAMPLES DATA
    @When("I modify the people list by {string} for {string} with job {string}")
    public void iModifyThePeopleListBy(String action, String name, String job) {

        if (action.equals("add")) {

            iClickAddPerson();
            iEnterNameAndJob(name, job);
            iClickAdd();

        } else if (action.equals("edit")) {

            iClickThePencilIconFor(name);
            iChangeTheJobField(job);
            iClickEdit();

        } else if (action.equals("remove")) {

            iClickTheCrossIconFor(name);
        }
    }

    @Then("the {string} modification should be visible for {string} with job {string}")
    public void theModificationShouldBeVisible(String action, String name, String job) {

        if (action.equals("add")) {

            thePeopleListShouldContainEntries(11);
            iShouldSeePersonWithJob(name, job);

        } else if (action.equals("edit")) {

            thePeopleListShouldContainEntries(10);
            iShouldSeePersonWithJob(name, job);

        } else if (action.equals("remove")) {

            thePeopleListShouldContainEntries(9);
            personShouldNotBeInTheList(name);
        }
    }

    @When("I click Reset List")
    public void iClickResetList() {

        driver.findElement(
                By.xpath("//button[text()='Reset List']")).click();
    }

    // VERIFY ORIGINAL PEOPLE USING DATA TABLE
    @Then("the original people and jobs should be restored:")
    public void theOriginalPeopleAndJobsShouldBeRestored(DataTable dataTable) {

        // Get all expected names and jobs from the feature file
        List<Map<String, String>> originalPeople =
                dataTable.asMaps(String.class, String.class);

        // Check total number of people
        thePeopleListShouldContainEntries(originalPeople.size());

        // Verify every original name and job
        for (Map<String, String> person : originalPeople) {

            String name = person.get("name");
            String job = person.get("job");

            iShouldSeePersonWithJob(name, job);
        }
    }

}
