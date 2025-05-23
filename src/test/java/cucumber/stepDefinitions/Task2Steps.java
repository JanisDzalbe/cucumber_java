package cucumber.stepDefinitions;

import io.cucumber.java.an.E;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.*;

public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the list of people page$")
    public void listOfPeoplePage(){
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs");
    }

    private final int defaultListSize = 3;
    @And("^Default list is displayed$")
    public void defaultList(){
        List<WebElement> persons = driver.findElements(By.className("w3-padding-16"));
        assertEquals(defaultListSize, persons.size());
        assertEquals("Mike", persons.get(0).findElement(By.className("name")).getText());
        assertEquals("Web Designer", persons.get(0).findElement(By.className("job")).getText());
        assertEquals("Jill", persons.get(1).findElement(By.className("name")).getText());
        assertEquals("Support", persons.get(1).findElement(By.className("job")).getText());
        assertEquals("Jane", persons.get(2).findElement(By.className("name")).getText());
        assertEquals("Accountant", persons.get(2).findElement(By.className("job")).getText());
    }

    @When("^I click add person button$")
    public void addPersonButtonPress(){
        driver.findElement(By.cssSelector("[onclick='openModalForAddPersonWithJob()']")).click();
    }

    @And("^I enter (\"(.*)\") and (\"(.*)\")$")
    public void personDataEntry(String name, String job){
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click add button$")
    public void addButtonPress(){
        driver.findElement(By.cssSelector("[onclick='addPersonWithJobToList()']")).click();
    }

    @Then("^Person with (\"(.*)\") and (\"(.*)\") is added to displayed list")
    public void checkPersonIsAdded(String name, String job){
        List<WebElement> nameList = driver.findElements(By.className("name"));
        List<WebElement> jobList = driver.findElements(By.className("job"));
        assertEquals(name, nameList.get(nameList.size()-1).getText());
        assertEquals(job, jobList.get(jobList.size()-1).getText());
    }

    @And("^I click cancel button$")
    public void addMenuCancelButtonPress(){
        driver.findElement(By.cssSelector("[onclick='window.history.back()']")).click();
    }

    @Then("^Person with name (\"(.*)\") and job (\"(.*)\") is not added to list")
    public void checkCancelOfAddingPerson(String name, String job){
        List<WebElement> nameList = driver.findElements(By.className("name"));
        List<WebElement> jobList = driver.findElements(By.className("job"));
        assertNotEquals(name, nameList.get(nameList.size()-1).getText());
        assertNotEquals(job, jobList.get(jobList.size()-1).getText());
    }

    @When("^I click on edit button in (\\d+) item of list$")
    public void editButtonPress(int index){
        driver.findElements(By.className("w3-padding-16")).get(index-1).findElement(By.className("editbtn")).click();
    }

    @And("^I enter \"(.*)\" in the job input$")
    public void changeJobInEditMenu(String job){
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click the edit button$")
    public void editMenuButtonPress(){
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Edit']")).click();
    }

    @Then("^(\\d+) item in list has \"(.*)\" as a job$")
    public void checkIfJobEditSucceed(int index, String job){
        assertEquals(job, driver.findElements(By.className("w3-padding-16")).get(index-1).findElement(By.className("job")).getText());
    }

    @And("^I enter \"(.*)\" in the name input$")
    public void changeNameEditMenu(String name){
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @Then("^(\\d+) item in list has \"(.*)\" as a name$")
    public void checkIfNameEditSucceed(int index, String name){
        assertEquals(name, driver.findElements(By.className("w3-padding-16")).get(index-1).findElement(By.className("name")).getText());
    }

    @Then("^(\\d+) item in list has name (\"(.*)\") and job (\"(.*)\")")
    public void checkIfNameAndJobEditSucceed(int index, String name, String job){
        assertEquals(name, driver.findElements(By.className("w3-padding-16")).get(index-1).findElement(By.className("name")).getText());
        assertEquals(job, driver.findElements(By.className("w3-padding-16")).get(index-1).findElement(By.className("job")).getText());
    }

    @When("^I click on remove button in \"(.*)\" item of list$")
    public void removePersonFromList(String name){
        List<WebElement> persons = driver.findElements(By.className("w3-padding-16"));
        for(WebElement person : persons){
            if(person.findElement(By.className("name")).getText().equals(name))
            {
                person.findElement(By.className("closebtn")).click();
                break;
            }
        }
    }

    @Then("^\"(.*)\" is not part of the list$")
    public void checkThatItemNoLongerInList(String name){
        List<WebElement> persons = driver.findElements(By.className("w3-padding-16"));
        for(WebElement person : persons){
            assertNotEquals(name, person.findElement(By.className("name")).getText());
        }
    }

    @And("^List is shorter$")
    public void checkIfListIsShorterThanDefault(){
        assertNotEquals(defaultListSize, driver.findElements(By.className("w3-padding-16")).size());
    }

    @When("^I click reset list button$")
    public void resetListButtonPress(){
        driver.findElement(By.cssSelector("[onclick='resetListOfPeople()']")).click();
    }

    @And("^I click clear all fields button$")
    public void clearAllFieldsButtonPress(){
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @Then("^Input fields are empty$")
    public void checkInputFieldsEmpty(){
        assertTrue(driver.findElement(By.id("name")).getAttribute("value").isEmpty());
        assertTrue(driver.findElement(By.id("job")).getAttribute("value").isEmpty());
    }
}
