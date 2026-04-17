package cucumber.stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Steps {

    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on people with jobs page$")
    public void iAmOnPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click add person button$")
    public void iClickAddPersonButton() {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @And("^I enter person name: \"([^\"]*)\"$")
    public void iEnterPersonName(String name) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter person job: \"([^\"]*)\"$")
    public void iEnterPersonJob(String job) {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I save person$")
    public void iSavePerson() {
        if (driver.findElements(By.xpath("//button[normalize-space()='Add']")).size() > 0) {
            driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
        } else if (driver.findElements(By.xpath("//button[normalize-space()='Edit']")).size() > 0) {
            driver.findElement(By.xpath("//button[normalize-space()='Edit']")).click();
        }
    }

    @When("^I click edit button for person \"([^\"]*)\"$")
    public void iClickEditButtonForPerson(String name) {
        driver.findElement(
                By.xpath("//li[.//span[normalize-space()='" + name + "']]//span[contains(@onclick,'openModalForEditPersonWithJob')]")
        ).click();
    }

    @When("^I remove person \"([^\"]*)\"$")
    public void iRemovePerson(String name) {
        driver.findElement(
                By.xpath("//li[.//span[normalize-space()='" + name + "']]//span[contains(@onclick,'deletePerson')]")
        ).click();
    }

    @And("^I click reset list button$")
    public void iClickResetListButton() {
        if (driver.findElements(By.xpath("//button[normalize-space()='Reset List']")).size() > 0) {
            driver.findElement(By.xpath("//button[normalize-space()='Reset List']")).click();
        }
    }

    @Then("^I should see person name: \"([^\"]*)\"$")
    public void iShouldSeePersonName(String name) {
        assertTrue(driver.findElement(
                By.xpath("//span[normalize-space()='" + name + "']")
        ).isDisplayed());
    }

    @And("^I should see person job: \"([^\"]*)\"$")
    public void iShouldSeePersonJob(String job) {
        assertTrue(driver.findElement(
                By.xpath("//span[@class='job' and normalize-space()='" + job + "']")
        ).isDisplayed());
    }

    @Then("^I should not see person \"([^\"]*)\"$")
    public void iShouldNotSeePerson(String name) {
        assertFalse(driver.getPageSource().contains(">" + name + "<"));
    }

    @Then("^I should see original person \"([^\"]*)\"$")
    public void iShouldSeeOriginalPerson(String name) {
        assertTrue(driver.findElement(
                By.xpath("//span[normalize-space()='" + name + "']")
        ).isDisplayed());
    }
}