package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SampleStepsHomework {
    private WebDriver driver;
    private int initialRecordCount;

    public SampleStepsHomework() {

        this.driver = Hooks.driver;
    }

    public int getInitialRecordCount() throws Throwable {
        List<WebElement> recordNum = driver.findElements(By.className("w3-xlarge"));
        return recordNum.size();
    }

    @Given("^I am on People with job page$")
    public void iAmOnPeopleWithJobPage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I count the number of records on the page$")
    public void iCountTheNumberOfTheRecords() throws Throwable {
        initialRecordCount = getInitialRecordCount();
    }

    @When("^I click on the \"Add person\" button$")
    public void iClickOnAddPerson() throws Throwable {
        driver.findElement(By.xpath("//button[normalize-space(text())='Add person']")).click();
    }

    @Then("I am redirected to the Add new person page$")
    public void redirectionToAddANewPersonPage() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/tasks/enter_a_new_person_with_a_job.html", driver.getCurrentUrl());
    }

    @When("^I enter a new name \"([^\"]*)\" and a new job \"([^\"]*)\"$")
    public void iEnterNameAndJob(String name, String job) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click on the \"Add\" button$")
    public void iClickOnAddButtonAtAddNewPersonPage() throws Throwable {
        driver.findElement(By.xpath("(//button[normalize-space(text())='Add'])[1]")).click();
    }

    @Then("I am redirected to the main page$")
    public void redirectionToTheMainPage() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html", driver.getCurrentUrl());
    }

    @And("^I can see that the number of records has increased$")
    public void seeRecordNumberHasIncreaseOnMainPage() throws Throwable {
        initialRecordCount = initialRecordCount + 1;
        List<WebElement> recordNum = driver.findElements(By.className("w3-xlarge"));
        Assertions.assertEquals(initialRecordCount, recordNum.size());
    }

    @When("^I choose the second record and click on the pencil icon to edit it$")
    public void iClickOnPencilIcon1() throws Throwable {
        driver.findElement(By.cssSelector("#person1 i.fa-pencil")).click();
    }

    @And("I can see that the name and job have been updated to name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void checkThatNameAndJobHasBeenChanged(String name, String job) throws Throwable {
        assertEquals(name, driver.findElement(By.cssSelector("#person1 > span.w3-xlarge")).getText());
        assertEquals(job, driver.findElement(By.cssSelector("#person1 > span.job")).getText());
        }

    @Then("I am redirected to the Editing page for second record$")
    public void redirectionToTheEditingPage1() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/tasks/enter_a_new_person_with_a_job.html?id=1", driver.getCurrentUrl());
    }

    @And("^I click on the \"Edit\" button$")
    public void iClickOnEditButtonAtEditPersonPage() throws Throwable {
        driver.findElement(By.xpath("(//button[normalize-space(text())='Edit'])[1]")).click();
    }

    @When("^I click on the \"x\" icon next to the third record to delete it$")
    public void iClickOnDeleteButtonAtPersonPage2() throws Throwable {
        driver.findElement(By.cssSelector("#person2 > span.closebtn")).click();
    }

    @Then("I remain on the main page$")
    public void remaneOnTheMainPage() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html", driver.getCurrentUrl());
    }

    @And("^I can see that the number of records has decreased$")
    public void seeRecordNumberHasDecreaseOnMainPage() throws Throwable {
        initialRecordCount = initialRecordCount - 1;
        List<WebElement> recordNum = driver.findElements(By.className("w3-xlarge"));
        Assertions.assertEquals(initialRecordCount, recordNum.size());
    }
    @When("^I click on the \"Reset List\" button$")
    public void iClickOnResetListOnAddNewPersonPage() throws Throwable {
        driver.findElement(By.xpath("//button[normalize-space(text())='Reset List']")).click();
    }
    @When("^I choose the third record and click on the pencil icon to edit it$")
    public void iClickOnPencilIcon2() throws Throwable {
        driver.findElement(By.cssSelector("#person2 i.fa-pencil")).click();
    }
    @Then("I am redirected to the Editing page for third record$")
    public void redirectionToTheEditingPage2() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/tasks/enter_a_new_person_with_a_job.html?id=2", driver.getCurrentUrl());
    }
    @Then("I can see changes for name \"([^\"]*)\" and job \"([^\"]*)\" are not saved$")
    public void checkThatNameAndJobHasNotBeenChanged2(String name, String job) throws Throwable {
        assertNotEquals(name, driver.findElement(By.cssSelector("#person2 > span.w3-xlarge")).getText());
        assertNotEquals(job, driver.findElement(By.cssSelector("#person2 > span.job")).getText());
    }
    @When("^I click on the \"x\" icon next to the first record to delete it$")
    public void iClickOnDeleteButtonAtPersonPage0() throws Throwable {
        driver.findElement(By.cssSelector("#person0 > span.closebtn")).click();
    }
    @When("^I clear name and job fields$")
    public void iEnterNameAndJob() throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("job")).clear();
    }
    @When("^I click on the \"Clear all fields\" button$")
    public void iClickOnClearAllFields() throws Throwable {
        driver.findElement(By.cssSelector("#addPersonBtn")).click();
    }
    @Then("^I see empty Name and Job fields$")
    public void iSeeEmptyNameAndJobFieldsEnterNameAndJob() throws Throwable {
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("job")).getText());
    }
    @And("I can see that the name and job fields of fourth record are empty$")
    public void checkThatNameAndJobFieldsAreEmpty3() throws Throwable {
        assertEquals("", driver.findElement(By.cssSelector("#person3 > span.w3-xlarge")).getText());
        assertEquals("", driver.findElement(By.cssSelector("#person3 > span.job")).getText());
    }
}

