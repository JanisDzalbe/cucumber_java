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

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    // Task 1

    @Given("^I am on enter a number page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in number field$")
    // Locate the input by ID, clear existing text, and type new input
    public void iEnterInNumberField(String input) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(input);
    }

    @And("^I click submit number$")
    public void iClickSubmitNumber() {
        // Use text-based XPath to find the button regardless of its ID or class
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("^I see number error: \"([^\"]*)\"$")
    public void iSeeNumberError(String expectedError) {
        // Captures error message from the paragraph element below the input
        assertEquals(expectedError, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see square root message: \"([^\"]*)\"$")
    public void iSeeSquareRootMessage(String expectedMessage) {
        // Switches driver focus from the main HTML to the JavaScript Alert popup
        org.openqa.selenium.Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText();
        assertEquals(expectedMessage, actualMessage);
        alert.accept();
    }

    // Task 2

    @Given("^I am on people with jobs page$")
    public void iAmOnPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click \"([^\"]*)\" button$")
    // Dynamic locator that clicks any button containing specific text (e.g., "Add person" or "Reset List")
    public void iClickButton(String btnText) {
        driver.findElement(By.xpath("//button[contains(text(), '" + btnText + "')]")).click();
    }

    @And("^I fill in name: \"([^\"]*)\" and job: \"([^\"]*)\"$")
    public void iFillInNameAndJob(String name, String job) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @Then("^I see \"([^\"]*)\" with job \"([^\"]*)\" in the list$")
    public void iSeePersonInList(String name, String job) {
        // Find the list item that contains the specific name
        WebElement personRow = driver.findElement(By.xpath("//li[span[text()='" + name + "']]"));
        assertTrue(personRow.getText().contains(job));
    }

    @When("^I remove person \"([^\"]*)\" from the list$")
    public void iRemovePerson(String name) {
        // 'normalize-space' strips leading/trailing whitespace
        String xpath = "//li[span[normalize-space()='" + name + "']]//span[contains(@class, 'closebtn')]";
        driver.findElement(By.xpath(xpath)).click();
    }

    @When("^I edit person \"([^\"]*)\" to have name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iEditPerson(String oldName, String newName, String newJob) {
        // Same logic for the edit (pencil) button
        String xpath = "//li[span[normalize-space()='" + oldName + "']]//span[contains(@class, 'editbtn')]";
        driver.findElement(By.xpath(xpath)).click();

        iFillInNameAndJob(newName, newJob);
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @Then("^I should not see \"([^\"]*)\" in the list$")
    public void iShouldNotSeeInTheList(String name) {
        List<WebElement> people = driver.findElements(By.xpath("//span[text()='" + name + "']"));
        assertTrue(people.isEmpty(), "Person " + name + " was found but should have been deleted.");
    }

    @Then("^I should see the original list of people$")
    public void iShouldSeeOriginalList() {
        // Check for a few default names that should always be there after a reset
        assertTrue(driver.findElement(By.xpath("//span[text()='Mike']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//span[text()='Emily']")).isDisplayed());
    }

    @Then("^I should see \"([^\"]*)\" in the list$")
    public void iShouldSeeInTheList(String name) {
        assertTrue(driver.findElement(By.xpath("//span[text()='" + name + "']")).isDisplayed());
    }



}

