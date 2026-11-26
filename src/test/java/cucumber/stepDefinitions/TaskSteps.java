package cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    //----------------------------------
    // Task 1
    //----------------------------------

    @Given("^I am on Enter a number page$")
    public void iAmOnTheEnterNumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter (.*) on Enter a number page")
    public void iEnterTestData(String testData) throws Throwable {

        if (testData.equals("CorrectData")) {
            testData = "75";
        }

        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(testData);
    }

    @When("^I click Submit button$")
    public void iClickSubmitButton() throws Throwable {
        driver.findElement(By.cssSelector("[type='button']")).click();
    }

    @Then("^I see \"([^\"]*)\"$")
    public void iSeeErrorMessage(String errorMessage) throws Throwable {
        assertEquals(errorMessage, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see an alert with correct calculation")
    public void iSeeCalculationResult() throws Throwable {

        int CorrectData = 75;
        double result = Math.round(Math.sqrt(CorrectData) * 100) / 100.0;

        Alert alert = driver.switchTo().alert();
        String expectedMessage = "Square root of " + CorrectData + " is " + result;

        assertEquals(expectedMessage, alert.getText());

        alert.accept();
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }

    //----------------------------------
    // Task 2
    //----------------------------------

    @Given("^I am on People with jobs page$")
    public void iAmOnPeopleWithJobsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @And("^the employee list contains (9|10|11) people$")
    public void listContainsPeopleAmount(int number) throws Throwable {

        assertEquals(number, driver.findElements(By.xpath("//li[contains(@id, 'person')]")).size());
    }

    @When("^I click (Add person|Reset List) button$")
    public void iClickAddPersonResetListButton(String type) throws Throwable {
        switch (type) {
            case "Add person":
                driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Add person']")).get(0).click();
                break;
            case "Reset List":
                driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Reset List']")).get(0).click();
                break;
        }
    }

    @When("^I enter name \"([^\"]*)\" and job \"([^\"]*)\"")
    public void iEnterNameJobData(String name, String job) throws Throwable {

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);

    }

    @When("^I click Add button$")
    public void iClickAddButton() throws Throwable {

        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Add']")).click();

    }

    @Then("^I see employee \"([^\"]*)\" with job \"([^\"]*)\" in the list$")
    public void iSeeEmployee(String name, String job) throws Throwable {

        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='" + name + "'] and .//*[text()='" + job + "']]")).isDisplayed());

    }

    @When("^I click Pencil button for \"([^\"]*)\" with job \"([^\"]*)\"$")
    public void iClickPencilButton(String name, String job) throws Throwable {

        driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='"+ name +"'] and .//*[text()='"+ job +"']]//*[contains(@class,'editbtn')]")).click();
    }

    @When("^I enter new data")
    public void iEnterNewNameJobData(Map<String, String> newDataToEnter) throws Throwable {

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(newDataToEnter.get("name"));
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(newDataToEnter.get("job"));

    }

    @When("^I click Edit Button$")
    public void iClickEditButton() throws Throwable {

        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Edit']")).click();

    }

    @When("^I click the cross button for \"([^\"]*)\" with job \"([^\"]*)\"")
    public void iClickCrossButton(String name, String job) throws Throwable {

        driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='"+ name +"'] and .//*[text()='"+ job +"']]//*[contains(@class,'w3-closebtn closebtn')]")).click();
    }

    @Then("^I see that the employee \"([^\"]*)\" with job \"([^\"]*)\" is not in the list$")
    public void iNotSeeEmployee(String name, String job) throws Throwable {

        assertTrue(driver.findElements(By.xpath("//*[contains(@id,'person') and .//*[text()='" + name + "'] and .//*[text()='" + job + "']]")).isEmpty());

    }

    @And("^I see the original employee list$")
    public void listContainsOriginalPeopleAmount(DataTable employeeTable) throws Throwable {

        List<Map<String, String>> employees = employeeTable.asMaps(String.class, String.class);

        assertEquals(employees.size(), driver.findElements(By.xpath("//li[contains(@id, 'person')]")).size());

        for (Map<String, String> employee : employees){
            String name = employee.get("name");
            String job = employee.get("job");

            iSeeEmployee(name,job);

        }

    }

}
