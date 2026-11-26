package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TaskSteps2 {
    private WebDriver driver;

    public TaskSteps2() {
        this.driver = Hooks.driver;
    }
    // ---------------------
// Task 2 Scenario of adding a new person
    // ---------------------

    @Given("^I am on the List of people page$")
    public void iAmOnTheListPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

    }

    @When("^I click Add person button$")
    public void iClickAddPersonButton() {
        driver.findElement(By.cssSelector("[onclick='openModalForAddPersonWithJob()']")).click();

    }

    @And("^I enter job: \"([^\"]*)\"$")
    public void iEnterJob(String job) throws Throwable {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click Add button$")
    public void iClickAddButton() {
        driver.findElement(By.cssSelector("[onclick='addPersonWithJobToList()']")).click();
    }

    @Then("^New Person appears in the list$")
    public void newPersomAppearsInTheList() {
        assertTrue(driver.findElement(By.id("listOfPeople")).getText().contains("Filips"));
        assertTrue(driver.findElement(By.id("listOfPeople")).getText().contains("Agent"));
    }

    // ---------------------
// Task 2 for Editing
    // ---------------------

    @When("^I click Edit icon$")
    public void iClickEditIcon() {
        driver.findElement(By.cssSelector(".editbtn")).click();
    }

    @And("^I click Edit button$")
    public void iClickEditButton() {
        driver.findElement(By.id("modal_button")).click();

    }

    @Then("^Updated details appears in the list$")
    public void updatedDetailsAppearsInTheList() {
        assertTrue(driver.findElement(By.id("listOfPeople")).getText().contains("Abass"));
        assertTrue(driver.findElement(By.id("listOfPeople")).getText().contains("CEO"));
    }
    // ---------------------
// Task 3 for Removing
    // ---------------------

    @When("^I click on \"x\" icon$")
    public void iClickOnXIcon() {
        driver.findElement(By.cssSelector(".closebtn")).click();
    }

    @Then("^Field deletes in the list$")
    public void fieldDeletesInTheList() {
        assertFalse(driver.findElement(By.id("listOfPeople")).getText().contains("Mike"));
    }
    // ---------------------
// Task 4 Reset list after adding
    // ---------------------
    @And("^I click on ResetList button$")
    public void iClickOnResetListButton() {
        driver.findElement(By.cssSelector("[onclick='resetListOfPeople()']")).click();
    }

    @Then("^List updates to initial$")
    public void listUpdatesToInitial() {
        assertFalse(driver.findElement(By.id("listOfPeople")).getText().contains("Filips"));
        assertFalse(driver.findElement(By.id("listOfPeople")).getText().contains("Abass"));
        assertTrue(driver.findElement(By.id("listOfPeople")).getText().contains("Mike"));
    }
}
