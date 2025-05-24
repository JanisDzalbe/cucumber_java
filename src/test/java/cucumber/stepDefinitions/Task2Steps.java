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

import static org.junit.Assert.*;
public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the page of People with jobs$")
    public void iAmOnThePageOfPeopleWithJobs() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click Add person button$")
    public void iClickAddPersonButton() throws Throwable {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @When("^I enter the values:$")
    public void iEnterTheValues(Map<String, String> valuesToEnter) throws Throwable {
        for (Map.Entry<String, String> e : valuesToEnter.entrySet()) {
            driver.findElement(By.id(e.getKey())).clear();
            driver.findElement(By.id(e.getKey())).sendKeys(e.getValue());
        }
    }

    @And("^I click the Add button$")
    public void iClickTheAddButton() throws Throwable {
        driver.findElement(By.id("modal_button")).click();
    }

    @Then("^List size become bigger$")
    public void listSizeBecomeBigger() {
        List<WebElement> persons = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(4, persons.size());
    }

    @When("^I click pencil button$")
    public void iClickPencilButton() throws Throwable {
        driver.findElement(By.xpath("//*[@id='person0']/span[2]/i")).click();
    }

    @Then("^I enter name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iClearInformationInFieldsNameAndJob(String name,String job) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click edit button$")
    public void iClickEditButton() throws Throwable {
        driver.findElement(By.id("modal_button")).click();
    }

    @Then("^I see another name \"([^\"]*)\" and job \"([^\"]*)\" for person (\\d+)$")
    public void seeNewNameAndJob(String name, String job, int index){
        assertEquals(name, driver.findElement(By.xpath("//li[@id='person" + (index - 1) + "']/span[contains(@class, 'name')]")).getText());
        assertEquals(job, driver.findElement(By.xpath("//li[@id='person" + (index - 1) + "']/span[contains(@class, 'job')]")).getText());
    }

    @When("^I click remove button$")
    public void iClickRemoveButton() throws Throwable {
        driver.findElement(By.xpath("//*[@id='person0']/span[1]")).click();
    }

    @Then("^List size become smaller$")
    public void listSizeBecomeSmaller () {
        List<WebElement> persons = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(2, persons.size());
    }

    @When("^I click Clear all fields button$")
    public void iClickClearAllFieldsButton() throws Throwable {
        driver.findElement(By.xpath("//*[@id='addPersonBtn']")).click();
    }

    @And("^Fields with name and job are clear")
    public void fieldsWithNameAndJobAreClear(){
        assertEquals("",driver.findElement(By.id("name")).getAttribute("value"));
        assertEquals("",driver.findElement(By.id("job")).getAttribute("value"));
    }

    @Then("^I click Reset List button$")
    public void iClickResetListButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @And("Main list restored")
    public void mainListRestored() {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        assertEquals(3, people.size());

        assertEquals("Mike", people.get(0).findElement(By.className("name")).getText());
        assertEquals("Web Designer", people.get(0).findElement(By.className("job")).getText());
        assertEquals("Jill", people.get(1).findElement(By.className("name")).getText());
        assertEquals("Support", people.get(1).findElement(By.className("job")).getText());
        assertEquals("Jane", people.get(2).findElement(By.className("name")).getText());
        assertEquals("Accountant", people.get(2).findElement(By.className("job")).getText());
    }
}
