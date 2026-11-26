package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskSteps {
    private WebDriver driver;

    List<String> originalList = new ArrayList<>();

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    // -------------------------------
    // Task 1
    // -------------------------------

    @Given("^I am on Enter a number page$")
    public void enterNumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number \"([^\"]*)\" on the page$")
    public void enterNumber(String number) {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click Submit$")
    public void submitBtn() {
        driver.findElement(By.cssSelector("[type='button']")).click();
    }

    @Then("^I see error \"([^\"]*)\"$")
    public void iSeeErrorOnEnterNumberPage(String error) {
        assertEquals(error, driver.findElement(By.className("error")).getText());
    }

    @Then("^I see alert: \"([^\"]*)\"$")
    public void iSeeAlert(String alert) {
        assertEquals(alert, driver.switchTo().alert().getText());
    }

    @When("^I accept the alert$")
    public void iAcceptTheAlert() {
        driver.switchTo().alert().accept();
    }

    @Then("^I see no error message$")
    public void iSeeNoErrorMessage() {
        assertEquals("", driver.findElement(By.className("error")).getText());
    }


    // -------------------------------
    // Task 2
    // -------------------------------

    @Given("^I am on People with jobs page$")
    public void peopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @When("^I click Add person$")
    public void clickAddPersonBtn() {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @Then("^I am redirected to People with jobs page form$")
    public void redirectedToPeopleWithJobsPageForm() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html");
    }

    @When("^I enter name \"([^\"]*)\"")
    public void enterName(String name) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter job \"([^\"]*)\"$")
    public void enterJob(String job) {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click Add$")
    public void clickAddBtn() {
        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @Then("^I am redirected to People with jobs page$")
    public void redirectedToPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @And("^I see a person with name \"([^\"]*)\" and job \"([^\"]*)\" in the list$")
    public void iSeeAPersonWithNameAndJobInTheList(String name, String job) {
        List<WebElement> people = driver.findElements(By.className("w3-container"));

        boolean personFound = false;

        for (WebElement person : people) {
            if (person.getText().contains(name) && person.getText().contains(job)) {
                personFound = true;
                break;
            }
        }

        assertTrue(personFound);
    }

    @When("^I click pencil icon for person \"([^\"]*)\"")
    public void iClickPencilIcon(String name) {
        WebElement person = driver.findElement(By.xpath("//li[.//*[contains(@class, 'name') and text()='" + name + "']]"));
        person.findElement(By.cssSelector(".fa.fa-pencil")).click();
    }

    @Then("^I am redirected to People with jobs edit page")
    public void redirectedToPeopleWithJobsEditPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html?id=0");
    }

    @When("^I enter new name \"([^\"]*)\"")
    public void enterNewName(String name) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter new job \"([^\"]*)\"")
    public void enterNewJob(String job) {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click Edit$")
    public void clickEditBtn() {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @When("^I click cross icon for person \"([^\"]*)\"")
    public void iClickCrossIcon(String name) {
        WebElement person = driver.findElement(By.xpath("//li[.//*[contains(@class, 'name') and text()='" + name + "']]"));
        person.findElement(By.xpath("//*[@id='person0']//*[contains(@class, 'closebtn')]")).click();
    }

    @Then("^I do not see a person with name \"([^\"]*)\" in the list$")
    public void iDoNotSeeAPersonInTheList(String name) {
        List<WebElement> personAfterDelete = driver.findElements(By.xpath("//li[.//*[contains(@class, 'name') and text()='" + name + "']]"));
        assertEquals(0, personAfterDelete.size());
    }

    @And("^I remember the original list of people$")
    public void iRememberTheOriginalListOfPeople() {
        originalList.clear();

        List<WebElement> people = driver.findElements(By.tagName("//li[.//*[contains(@class, 'name')]]"));

        for (WebElement person : people) {
            originalList.add(person.getText());
        }
    }

    @When("^I click Reset List$")
    public void clickResetBtn() {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @Then("^I see the original list of people$")
    public void iSeeTheOriginalListOfPeople() {
        List<String> currentList = new ArrayList<>();

        List<WebElement> people = driver.findElements(By.tagName("//li[.//*[contains(@class, 'name')]]"));

        for (WebElement person : people) {
            currentList.add(person.getText());
        }

        assertEquals(originalList, currentList);
    }

}



