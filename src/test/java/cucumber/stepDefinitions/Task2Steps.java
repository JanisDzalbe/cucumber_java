package cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on list of people page$")
    public void iAmOnListOfPeoplePage() {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I navigate to new person page$")
    public void navigateToNew() {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @When("^I enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void enterNameAndJob(String name, String job) {
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @When("^I click Add$")
    public void clickAdd() {
        driver.findElement(By.id("modal_button")).click();
    }

    @Then("^New person with \"([^\"]*)\" and \"([^\"]*)\" is present in the list$")
    public void newPersonPresent(String name, String job) {
        List<WebElement> personListNames = driver.findElements(By.cssSelector("#listOfPeople li .name"));
        assertEquals(name, personListNames.get(3).getText());
        List<WebElement> personListJobs = driver.findElements(By.cssSelector("#listOfPeople li .job"));
        assertEquals(job, personListJobs.get(3).getText());
    }

    @Then("^List size is increased by 1$")
    public void listSizeIncreased() {
        List<WebElement> persons = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(4, persons.size());
    }

    @When("^I click remove \"([^\"]*)\"$")
    public void removePerson(String name) {
        List<WebElement> personNames = driver.findElements(By.className("name"));
        List<String> personNamesStrings = new ArrayList<>();

        for (WebElement personName : personNames) {
            personNamesStrings.add(personName.getText());
        }
        driver.findElement(By.xpath("//*[@onClick='deletePerson(" + personNamesStrings.indexOf(name) + ")']")).click();
    }

    @Then("^\"([^\"]*)\" record doesn't exist anymore$")
    public void doesntExist(String name) {
        List<WebElement> personNames = driver.findElements(By.className("name"));
        List<String> personNamesStrings = new ArrayList<>();

        for (WebElement personName : personNames) {
            personNamesStrings.add(personName.getText());
        }

        assertFalse(personNamesStrings.contains(name));
    }

    @Then("^List size is decreased by 1$")
    public void listSizeDecreased() {
        List<WebElement> persons = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(2, persons.size());
    }

    @When("^I choose to edit person (\\d+)$")
    public void editAPerson(int index) {
        driver.findElement(By.xpath("//*[@onClick='openModalForEditPersonWithJob(" + (index - 1) + ")']")).click();
    }

    @When("^I enter new name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void enterNewNameAndJob(String name, String job) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(name);
    }

    @When("^I click edit$")
    public void clickEdit() {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @Then("^I see new name \"([^\"]*)\" and job \"([^\"]*)\" for person (\\d+)$")
    public void seeNewNameAndJob(String name, String job, int index){
        assertEquals(name, driver.findElement(By.xpath("//li[@id='person" + (index - 1) + "']/span[contains(@class, 'name')]")).getText());
        assertEquals(name, driver.findElement(By.xpath("//li[@id='person" + (index - 1) + "']/span[contains(@class, 'job')]")).getText());
    }

    @When("^I click reset$")
    public void clickReset() {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @Then("^Old list is restored$")
    public void restoreOldList(){
        List<WebElement> persons = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(3, persons.size());

        assertEquals("Mike", driver.findElement(By.xpath("//li[@id='person0']/span[contains(@class, 'name')]")).getText());
        assertEquals("Web Designer", driver.findElement(By.xpath("//li[@id='person0']/span[contains(@class, 'job')]")).getText());
        assertEquals("Jill", driver.findElement(By.xpath("//li[@id='person1']/span[contains(@class, 'name')]")).getText());
        assertEquals("Support", driver.findElement(By.xpath("//li[@id='person1']/span[contains(@class, 'job')]")).getText());
        assertEquals("Jane", driver.findElement(By.xpath("//li[@id='person2']/span[contains(@class, 'name')]")).getText());
        assertEquals("Accountant", driver.findElement(By.xpath("//li[@id='person2']/span[contains(@class, 'job')]")).getText());
    }

    @When("^I click clear$")
    public void clickClear(){
        driver.findElement(By.xpath("//button[text()='Clear all fields']")).click();
    }

    @Then("^Fields are cleared$")
    public void fieldsCleared () {
        assertEquals("", driver.findElement(By.id("name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("job")).getAttribute("value"));
    }
}
