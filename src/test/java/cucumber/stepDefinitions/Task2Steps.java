package cucumber.stepDefinitions;

import cucumber.task2_pages.ListOfPeopleWithJobsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Task2Steps {
    private WebDriver driver;
    static ListOfPeopleWithJobsPage page;

    public Task2Steps() {
        this.driver = Hooks.driver;
        page = PageFactory.initElements(Hooks.driver, ListOfPeopleWithJobsPage.class);
    }
    @Given("^I (?:am on|open) page list of people with jobs$")
    public void iAmOnPageListOfPeopleWithJobs() {
        driver.get(page.getPageUrl());
        page.captureDefaultListState();
    }

    @Then ("^I am navigated to New Person page$")
    public void iAmNavigatedToNewPersonPage() {
        assertTrue(driver.getCurrentUrl().contains("enter_a_new_person_with_a_job.html"));
    }

    @When("^I click the Add Person button$")
    public void iClickAddPersonButton() throws Throwable {
        page.storePeopleCount();
        page.clickAddPerson();
    }

    @Then("^I see that the name and job field is empty$")
    public void iSeeEmptyNameJobField() throws Throwable {
        page.assertNameFieldEmpty();
        page.assertJobFieldEmpty();
    }

    @Then("^I see the name and job field contains \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iSeeNameJobFieldFilled(String name, String job) throws Throwable {
        page.assertNameFieldFilled(name);
        page.assertJobFieldFilled(job);
    }

    @When("^I enter \"([^\"]*)\" into the name field$")
    public void iEnterName(String name) throws Throwable {
        page.enterName(name);
    }

    @When("^I enter \"([^\"]*)\" into the job field$")
    public void iEnterJob(String job) throws Throwable {
        page.enterJob(job);
    }

    @When("^I add a new person with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iEnterNameAndJob(String name, String job) {
        page.enterName(name);
        page.enterJob(job);
    }

    @When("^I click the (?:Add|Edit) button$")
    public void iClickAddButton() throws Throwable {
        page.clickAddButton();
    }

    @When("^I click the Reset List button$")
    public void iClickResetListButton() throws Throwable {
        page.clickResetList();
    }

    @Then("I see \"([^\"]*)\" with job \"([^\"]*)\" in the list$")
    public void iSeePersonInList(String name, String job) throws Throwable{
        page.assertPersonInList(name, job, true);
    }

    @Then("^I see \"([^\"]*)\" with job \"([^\"]*)\" is not in the list$")
    public void iSeePersonNotInList(String name, String job) throws Throwable {
        page.assertPersonInList(name, job, false);
    }

    @Then("^I see list increased by (\\d+)$")
    public void iSeeListIncreased(int number) throws Throwable {
        page.assertListIncreasedBy(number);
    }

    @Then("^I see list decreased by (\\d+)$")
    public void iSeeListDecreased(int number) throws Throwable {
        page.assertListDecreasedBy(number);
    }

    @When("^I click the edit button for \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iClickEditButtonForPerson(String name, String job) throws Throwable {
        List<WebElement> people = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        for (WebElement person : people) {
            String personName = person.findElement(By.xpath("./span[contains(@class,'name')]")).getText();
            if (personName.equals(name)) {
                String personJob = person.findElement(By.xpath(".//span[@class='job']")).getText();
                if (personJob.equals(job)) {
                    person.findElement(By.xpath(".//span[contains(@class,'editbtn')]")).click();
                    return;
                }
            }
        }
    }

    @When("^I click remove button for \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iRemovePerson(String name, String job) throws Throwable {
        page.storePeopleCount();
        List<WebElement> people = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        for (WebElement person : people) {
            String personName = person.findElement(By.xpath("./span[contains(@class,'name')]")).getText();
            if (personName.equals(name)) {
                String personJob = person.findElement(By.xpath(".//span[@class='job']")).getText();
                if (personJob.equals(job)) {
                    person.findElement(By.xpath(".//span[contains(@class,'closebtn')and text()='×']")).click();
                    return;
                }
            }
        }
    }

    @Then("^I see the list matches the original default entries$")
    public void iSeeListMatchesDefault() {
        List<WebElement> people = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        List<String> currentSnapshot = new java.util.ArrayList<>();
        for (WebElement person : people) {
            String name = person.findElement(By.xpath(".//span[contains(@class,'name')]")).getText();
            String job = person.findElement(By.xpath(".//span[contains(@class,'job')]")).getText();
            currentSnapshot.add(name + "," + job);
        }
        assertEquals(page.getOriginalSnapshot(), currentSnapshot);
    }

    @When("I add a new person:")
    public void iAddNewPerson(DataTable table) {

        Map<String, String> data = table.asMap();

        page.enterName(data.get("name"));
        page.enterJob(data.get("job"));
    }
}
