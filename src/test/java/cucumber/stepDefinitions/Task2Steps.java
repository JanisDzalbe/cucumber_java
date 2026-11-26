package cucumber.stepDefinitions;


import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task2Steps {
    private WebDriver driver;
    private List<String> originalList;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on page with people list$")
    public void i_am_on_page_with_people_list() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I press the Add person button$")
    public void i_press_the_Add_person_button() throws Throwable {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @When("I enter person name: {string}")
    public void iEnterPersonName(String name) {

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }


    @When("^I press the Add button$")
    public void i_press_the_Add_button() throws Throwable {
        driver.findElement(By.xpath("//*[@onclick = 'addPersonWithJobToList()']")).click();
    }

    @When("I enter person job: {string}")
    public void iEnterPersonJob(String job) {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);

    }

    @Then("I see {string} with job {string} in the list")
    public void iSeeWithJobInTheList(String name, String job) {
        WebElement person = driver.findElement(By.xpath("//li[span[contains(@class,'name') and text()='" + name + "']]"));

        String actualJob = person.findElement(By.className("job")).getText();

        assertEquals(job, actualJob);
    }

    @And("I see {int} people in the list")
    public void iSeePeopleInTheList(int expectedCount) {
        int actualCount = driver.findElements(
                By.cssSelector("#listOfPeople li")).size();

        assertEquals(expectedCount, actualCount);
    }

    @When("I press the edit button for {string}")
    public void iPressTheEditButtonFor(String name) {
        driver.findElement(By.xpath("//li[span[contains(@class,'name') and text()='" + name + "']]//span[contains(@class,'editbtn')]")).click();
    }
    @When("I press the Edit button")
    public void iPressTheEditButton() {

        driver.findElement(By.id("modal_button")).click();
    }
    @Then("I do not see {string} in the list")
    public void iDoNotSeePersonInTheList(String name) {

        List<WebElement> people = driver.findElements(
                By.cssSelector("#listOfPeople .name"));

        for (WebElement person : people) {
            assertFalse(person.getText().equals(name));
        }
    }
    @When("I press the remove button for {string}")
    public void iPressTheRemoveButtonFor(String name) {


        driver.findElement(By.xpath("//li[span[contains(@class,'name') and text()='" + name + "']]//span[contains(@class,'closebtn')]")).click();    }
    @When("I press the Reset List button")
    public void iPressTheResetListButton() {

        driver.findElement(
                By.xpath("//*[@onclick='resetListOfPeople()']")).click();
    }
    @Given("I remember the original list")
    public void iRememberTheOriginalList() {

        originalList = getPeopleList();
    }
    private List<String> getPeopleList() {

        List<String> result = new ArrayList<>();

        List<WebElement> people = driver.findElements(
                By.cssSelector("#listOfPeople li")
        );

        for (WebElement person : people) {

            String name = person.findElement(
                    By.className("name")).getText();

            String job = person.findElement(
                    By.className("job")).getText();

            result.add(name + " | " + job);
        }

        return result;
    }
    @Then("I see the original list")
    public void iSeeTheOriginalList() {

        List<String> actualList = getPeopleList();

        assertEquals(originalList, actualList);
    }
}

