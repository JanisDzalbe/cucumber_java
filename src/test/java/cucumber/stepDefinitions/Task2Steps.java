package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I go to the (person|job) page$")
    public void iGoToSomePage(String page) throws Throwable {
        if (page.equals("person")) {
            driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people.html");
        } else if (page.equals("job")) {
            driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
        }
    }

    @And("^I click on the Add person button$")
    public void iClickOnTheAddPersonButton() {
        driver.findElement(By.id("addPersonBtn")).click();
    }


    @And("^I enter name \"([^\"]*)\"$")
    public void iEnterName(String text) throws Throwable {
        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(text);
    }

    @And("^I enter surname \"([^\"]*)\"$")
    public void iEnterSurname(String text) throws Throwable {
        WebElement surNameInput = driver.findElement(By.id("surname"));
        surNameInput.clear();
        surNameInput.sendKeys(text);
    }

    @And("^I enter job \"([^\"]*)\"$")
    public void iEnterJob(String text) throws Throwable {
        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        jobInput.sendKeys(text);
    }

    @And("^I enter Date of Birth \"([^\"]*)\"$")
    public void iEnterDateOfBirth(String text) throws Throwable {
        WebElement dateOfBirthInput = driver.findElement(By.id("dob"));
        dateOfBirthInput.clear();
        dateOfBirthInput.sendKeys(text);
    }

    @And("^I choose language \"([^\"]*)\"$")
    public void iChooseLanguage(String text) throws Throwable {
        List<WebElement> langs = driver.findElements(By.cssSelector("input[name='language']"));
        for (WebElement element : langs) {
            if (element.getAttribute("id").equals(text)) {
                element.click();
            }
        }
    }
    @And("^I choose gender \"([^\"]*)\"$")
    public void iChooseGender(String text) throws Throwable {
        List<WebElement> gen = driver.findElements(By.cssSelector("input[name='gender']"));
        for (WebElement element : gen) {
            if (element.getAttribute("id").equals(text)) {
                element.click();
            }
        }
    }
    @And("^I choose employee status \"([^\"]*)\"$")
    public void iChooseStatus(String text) throws Throwable {
        List<WebElement> options = driver.findElement(By.id("status")).findElements(By.tagName("option"));
        for (WebElement element : options) {
            if (element.getAttribute("value").equals(text)) {
                element.click();
            }
        }
    }

    @And("^I click on the Add button$")
    public void iClickOnTheButton() {
        driver.findElement(By.id("modal_button")).click();
    }

    @And("^I click Edit button$")
    public void iClickOnEditButton() {
        driver.findElement(By.className("fa-pencil")).click();
    }


    @And("^I click on the Edit button$")
    public void iClickOnTheEditButton() {
        driver.findElement(By.xpath("//button[contains(text(),'Edit')]")).click();
    }

    @And("^I Click Delete button$")
    public void iClickOnDeleteButton() {
        driver.findElement(By.className("closebtn")).click();
    }


    @And("^I Click Reset list button$")
    public void iClickOnResetButton() throws Throwable {
        WebElement resetButton = driver.findElement(By.xpath("//button[contains(text(), 'Reset')]"));
        resetButton.click();
    }

    @And("^I click on Clear all fields button$")
    public void iClickOnClearAllFieldsButton() {
        driver.findElement(By.className("w3-btn")).click();
    }

    @Then("^I should see empty fields$")
    public void iSeeEmptyFields() throws Throwable {
        assertEquals("", driver.findElement(By.id("name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("surname")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("job")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("dob")).getAttribute("value"));

        List<WebElement> lang = driver.findElements(By.cssSelector("input[name='language']"));
        for (WebElement element : lang) {
            if (element.getAttribute("id").equals("english")) {
                assertTrue(element.isSelected());
            } else {
                assertFalse(element.isSelected());
            }
        }

        List<WebElement> gen = driver.findElements(By.cssSelector("input[name='gender']"));
        for (WebElement element : gen) {
            assertFalse(element.isSelected());
        }
        WebElement status = driver.findElement(By.id("status"));
        assertEquals("employee", status.getAttribute("value"));

    }
    @And("^I should see person with name \"([^\"]*)\" and surname \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iShouldSeePerson(String name, String surname, String job) throws Throwable {
        List<WebElement> rows = driver.findElements(By.id("listOfPeople"));
        boolean found = false;
        for (WebElement row : rows) {
            String rowText = row.getText();
            if (rowText.contains(name) && rowText.contains(surname) && rowText.contains(job)) {
                found = true;
                break;
            }
        }
        assertTrue("Person not found in the list", found);
    }
    @And("^I should not see person with name \\\"([^\\\"]*)\\\" and surname \\\"([^\\\"]*)\\\"$")
    public void iShouldNotSeePerson(String name, String surname) throws Throwable {
        List<WebElement> rows = driver.findElements(By.cssSelector("listOfPeople"));
        boolean found = false;
        for (WebElement row : rows) {
            String rowText = row.getText();
            if (rowText.contains(name) && rowText.contains(surname)) {
                found = true;
                break;
            }
        }
        assertFalse("Person was not deleted from the list", found);
    }

}
