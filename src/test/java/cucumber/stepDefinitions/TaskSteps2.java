
package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static cucumber.stepDefinitions.Hooks.driver;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSteps2 {
    // ========================
    // ADD A NEW PERSON
    // ========================

    @Given("^I open the People with Jobs page$")
    public void iOpenThePeopleWithJobsPage() {

        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click Add person$")
    public void iClickAddPerson() {

        driver.findElement(
                By.cssSelector("button[onclick='openModalForAddPersonWithJob()']")
        ).click();
    }

    @When("^I enter name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iEnterNameAndJob(String name, String job) {

        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click the Add button$")
    public void iClickTheAddButton() {

        driver.findElement(By.xpath("//button[text()='Add']")).click();
        // System.out.println(driver.findElement(By.id("listOfPeople")).getText());
    }

    @Then("^I should see \"([^\"]*)\" with job \"([^\"]*)\" in the list$")
    public void iShouldSeePersonWithJobInTheList(String name, String job) {

        String actualName = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]//span[contains(@class,'name')]")
        ).getText();

        String actualJob = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]//span[contains(@class,'job')]")
        ).getText();

        assertEquals(name, actualName);
        assertEquals(job, actualJob);
    }

    @When("^I reset the original list$")
    public void iResetTheOriginalList() {

        driver.findElement(
                By.cssSelector("button[onclick='resetListOfPeople()']")
        ).click();
    }

    @Then("^I should see the original list$")
    public void iShouldSeeTheOriginalList() {

        assertEquals(10, driver.findElements(
                By.cssSelector("#listOfPeople li")
        ).size());
    }


// =======================
// EDIT AN EXISTING PERSON
// ========================

    @When("^I click the Edit button for \"([^\"]*)\"$")
    public void iClickTheEditButtonForPerson(String name) {

        driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]//span[contains(@class,'editbtn')]")
        ).click();
    }

    @And("^I change his name to \"([^\"]*)\"$")
    public void iChangeHisNameTo(String newName) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(newName);

    }

    @And("^I click the Edit button$")
    public void iClickTheEditButton() {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }
    // ===========================
    // REMOVE AN EXISTING PERSON
    // ===========================

    @When("^I remove \"([^\"]*)\" from the list$")
    public void iRemovePersonFromTheList(String name) {

        driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]//span[contains(@class,'closebtn')]")
        ).click();

    }

    @Then("^I should not see \"([^\"]*)\" in the list$")
    public void iShouldNotSeePersonInTheList(String name) {

        assertEquals(0, driver.findElements(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]")
        ).size());

    }
}

