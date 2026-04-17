package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    private final WebDriver driver;
    private List<WebElement> addPersonButton;
    private List<WebElement> resetListButton;
    private final Map<String, String> employeeAndJob;

    public Task2() {
        this.driver = Hooks.driver;
        employeeAndJob = new LinkedHashMap<>();
        initEmployeeAndJobMap();
    }

    private void initEmployeeAndJobMap() {
        employeeAndJob.put("Mike", "Web Designer");
        employeeAndJob.put("Jill", "Support");
        employeeAndJob.put("Jane", "Accountant");
        employeeAndJob.put("John", "Software Engineer");
        employeeAndJob.put("Sarah", "Product Manager");
        employeeAndJob.put("Carlos", "Data Analyst");
        employeeAndJob.put("Emily", "UX Designer");
        employeeAndJob.put("David", "Project Manager");
        employeeAndJob.put("Maria", "QA Engineer");
        employeeAndJob.put("Alex", "DevOps Engineer");
    }

    @Given("^I (?:am on|open) the \"People with Job\" page$")
    public void iAmONPeopleWithJobPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }


    @When("^I can see both buttons are enabled and displayed$")
    public void iCanSeeBothButtonsAreEnabledAndDisplayed() {
        addPersonButton = driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Add person']"));
        resetListButton = driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Reset List']"));

        assertTrue(addPersonButton.getFirst().isDisplayed());
        assertTrue(addPersonButton.getFirst().isEnabled());

        assertTrue(resetListButton.getFirst().isDisplayed());
        assertTrue(resetListButton.getFirst().isEnabled());
    }

    @And("^I click on Reset to reset the list$")
    public void iClickOnResetToResetTheList() {
        resetListButton = driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Reset List']"));
        resetListButton.getFirst().click();
    }

    @And("^I click on Add to add a new person$")
    public void iClickOnAddToAddANewPerson() {
        addPersonButton = driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Add person']"));
        addPersonButton.getFirst().click();
    }

    @And("^I am on Add page$")
    public void iWasRedirectedToAddPage() {
        String enterNewPersonWithJobUrl = "https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html";
        assertEquals(enterNewPersonWithJobUrl, driver.getCurrentUrl());
    }

    @And("^I enter a name and job:$")
    public void iEnterANameAndJob(Map<String, String> data) {
        WebElement name = driver.findElement(By.id("name"));
        WebElement job = driver.findElement(By.id("job"));

        name.clear();
        job.clear();

        name.sendKeys(data.get("name"));
        job.sendKeys(data.get("job"));
    }

    @And("^I click on Add$")
    public void iClickOnAdd() {
        WebElement addButton = driver.findElement(By.xpath("//*[@id='modal_button' and text()='Add']"));
        addButton.click();
    }

    @Then("^I validated that new person is added:$")
    public void iValidatedThatNewPersonIsAdded(Map<String, String> data) {
        String expectedName = data.get("name");
        String expectedJob = data.get("job");
        WebElement personElement = driver.findElement(By.xpath("//*[contains(@id, 'person')]//*[contains(@class, 'name') and text()='" + expectedName + "']"));
        WebElement jobElement = personElement.findElement(By.xpath("../*[contains(@class, 'job')]"));
        assertEquals(expectedJob, jobElement.getText());
    }


    @And("^I click on the remove icon for the first person: \"([^\"]*)\"$")
    public void iClickOnRemoveIconToRemoveFirstPerson(String name) {
        List<WebElement> persons = driver.findElements(By.xpath("//*[contains(@id, 'person')]//span[contains(@class, 'name') and contains(text(), '" + name + "')]"));
        persons.getFirst().findElement(By.xpath("//*[contains(@class,'closebtn') and text()='×']")).click();
    }

    @Then("^I validate that first person is no longer in the list: \"([^\"]*)\"$")
    public void iValidateThatFirstPersonIsNoLongerInTheList(String name) {
        List<WebElement> newUpdatedPersons = driver.findElements(By.cssSelector("[id*='person']"));
        for (WebElement element : newUpdatedPersons) {
            assertNotEquals(name, element.findElement(By.className("name")).getText());
        }
    }

    @And("^I validate the initial state of the list$")
    public void iValidateTheInitialStateOfTheList() {
        List<WebElement> nameList = driver.findElements(By.cssSelector("[id*='person']"));
        assertEquals(employeeAndJob.size(), nameList.size());
        for (WebElement element : nameList) {
            String elementName = element.findElement(By.className("name")).getText();
            String elementJob = element.findElement(By.className("job")).getText();
            assertTrue(employeeAndJob.containsKey(elementName));
            assertEquals(employeeAndJob.get(elementName), elementJob);
        }
    }

    @And("^I click on Edit icon to edit Mike$")
    public void iClickOnEditIconToEditPerson() {
        List<WebElement> newPerson = driver.findElements(By.cssSelector("[id*='person']"));
        newPerson.getFirst().findElement(By.xpath("//*[@class='fa fa-pencil']")).click();
    }

    @And("^I am on Edit page$")
    public void iAmOnEditPage() {
        String expectedURl = "https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html?id=0";
        assertEquals(expectedURl, driver.getCurrentUrl());
    }

    @And("^I see filled data: \"([^\"]*)\" and \"([^\"]*)\"")
    public void iSeeFilledDataAnd(String expectedName, String expectedJob) {
        WebElement name = driver.findElement(By.id("name"));
        WebElement job = driver.findElement(By.id("job"));

        assertTrue(name.isDisplayed());
        assertTrue(job.isDisplayed());
        assertTrue(name.isEnabled());
        assertTrue(job.isEnabled());

        assertEquals(expectedName, name.getDomProperty("value"));
        assertEquals(expectedJob, job.getDomProperty("value"));
    }

    @And("^I change data for job: \"([^\"]*)\"$")
    public void iChangeDataForJob(String expectedJobName) {
        WebElement job = driver.findElement(By.id("job"));
        job.clear();
        job.sendKeys(expectedJobName);
    }

    @Then("^I click on Edit to submit$")
    public void iClickOnEditToSubmit() {
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='modal_button' and text()='Edit']"));
        submitButton.click();
    }

    @And("^I check if \"([^\"]*)\" has modified job: \"([^\"]*)\"$")
    public void iCheckIfHasModifiedJob(String expectedName, String expectedJob) {
        WebElement personElement = driver.findElement(By.xpath("//*[contains(@id, 'person')]//*[contains(@class, 'name') and text()='" + expectedName + "']"));
        WebElement jobElement = personElement.findElement(By.xpath("../*[contains(@class, 'job')]"));
        assertEquals(expectedJob, jobElement.getText());
    }
}
