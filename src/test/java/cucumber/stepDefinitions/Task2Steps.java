package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on enter jobs page$")
    public void Iamonenterjobspage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click \"([^\"]*)\" button")
    public void iClickButton(String btnText) {
        driver.findElement(By.xpath("//button[contains(text(), '" + btnText + "')]")).click();
    }

    @Then("^I write a name field: \"([^\"]*)\"$")
    public void iwritenamefield(String name) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @Then("^I write a job field: \"([^\"]*)\"$")
    public void iwriteanamefield(String job) throws Throwable {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @When("^I want edit person named: \"([^\"]*)\"$")
    public void Iwanteditpersonnamed(String name) throws Throwable{
        String xpath = "//li[span[normalize-space()='" + name + "']]//span[contains(@class, 'editbtn')]";
        driver.findElement(By.xpath(xpath)).click();
    }

    @Then("^I edit the person job: \"([^\"]*)\"$")
    public void Ieditpersonjob(String job) throws Throwable{
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @Then("^I check the person \"([^\"]*)\" and his new job: \"([^\"]*)\"$")
    public void iSeeEditedPersonInList(String name, String job) throws Throwable{
        // Find the list item that contains the specific name
        WebElement personcheck = driver.findElement(By.xpath("//li[span[text()='" + name + "']]"));
        assertTrue(personcheck.getText().contains(job));
    }

    @When("^I want delete person named: \"([^\"]*)\"$")
    public void Iwantdeletepersonnamed(String name) throws Throwable{
        String xpath = "//li[span[normalize-space()='" + name + "']]//span[contains(@class, 'closebtn')]";
        driver.findElement(By.xpath(xpath)).click();
    }

    @Then("^I check the person \"([^\"]*)\"$")
    public void Ichecktheperson(String name) {
        WebElement personcheck = driver.findElement(By.xpath("//li[span[text()='" + name + "']]"));
        assertTrue(personcheck.getText().contains(name));
    }

    @Then("^I check a original list$")
    public void iCheckOriginalList() {
        int peopleCount = driver.findElements(By.cssSelector("#listOfPeople li")).size();
        assertEquals(10, peopleCount);
    }
}
