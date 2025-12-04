package cucumber.stepDefinitions;

import cucumber.customDataTableType.PersonData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSteps {
    private WebDriver driver;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the numbers page$")
    public void iAmOnTheNumbersPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter a number: \"([^\"]*)\"$")
    public void iEnterANumber(String number) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @And("^I click submit button$")
    public void iClickSubmitButton() throws Throwable {
        driver.findElement(By.className("w3-orange")).click();
    }

    @Then("^I see error message: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see message \"([^\"]*)\"$")
    public  void iSeeMessage(String answer) throws Throwable {
        Alert answerAlert =  driver.switchTo().alert();
        assertEquals(answer, answerAlert.getText());
        answerAlert.accept();
    }

    @And("^I do not see any error message$")
    public void iDoNotSeeAnyErrorMessage() throws Throwable {
        assertFalse(driver.findElement(By.id("ch1_error")).isDisplayed());
    }


    // Task 2
    @Given("^I am on the list of people with jobs page$")
    public void iAmOnTheListOfPeopleWithJobsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click add person$")
    public void iClickAddPerson() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
    }

    @When("^I fill fields$")
    public void iFillFields(Map<String, String> valuesToEnter) throws Throwable {
        for (Map.Entry<String, String> entry : valuesToEnter.entrySet()) {
            driver.findElement(By.id(entry.getKey())).clear();
            driver.findElement(By.id(entry.getKey())).sendKeys(entry.getValue());
        }
    }

    @When("^I click Add button$")
    public void iClickAddButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @Then("^I see person added to the list$")
    public void iSeePersonAddedToTheList(Map<String, String> valuesToCheck) throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(valuesToCheck.get("name"))) {
                assertEquals(valuesToCheck.get("job"), person.findElement(By.className("job")).getText());
            }
        }
    }

    @When("^I click Edit button for person: \"([^\"]*)\"$")
    public void iClickEditButtonForPerson(String personName) throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(personName)) {
                person.findElement(By.className("fa-pencil")).click();
                return;
            }
        }
    }

    @When("^I see person values$")
    public void iSeePersonValues(Map<String, String> valuesToCheck) throws Throwable {
        assertEquals(valuesToCheck.get("name"), driver.findElement(By.id("name")).getAttribute("value"));
        assertEquals(valuesToCheck.get("job"), driver.findElement(By.id("job")).getAttribute("value"));
    }

    @When("^I change Job: \"([^\"]*)\"$")
    public void iChangeJob(String job) throws Throwable {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @When("^I click Edit button$")
    public void iClickEditButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @Then("^I see person updated$")
    public  void iSeePersonUpdated(Map<String, String> valuesToCheck) throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(valuesToCheck.get("name"))) {
                assertEquals(valuesToCheck.get("job"), person.findElement(By.className("job")).getText());
                return;
            }
        }
    }

    @When("^I click Delete button for person: \"([^\"]*)\"$")
    public void iClickDeleteButtonForPerson(String personName) throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(personName)) {
                person.findElement(By.className("closebtn")).click();
                return;
            }
        }
    }

    @Then("^I see person \"([^\"]*)\" is removed from the list$")
    public void iSeePersonIsRemovedFromTheList(String personName) throws Throwable {
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(personName)) {
                fail();
                return;
            }
        }
    }

    @When("^click Reset List$")
    public void clickResetList() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @Then("^I see list is back to initial state$")
    public void iSeeListIsBackToInitialState() throws Throwable {
        String[][] peopleMap = {{"Mike", "Web Designer"}, {"Jill", "Support"}, {"Jane", "Accountant"}, {"John", "Software Engineer"},
                {"Sarah", "Product Manager"}, {"Carlos", "Data Analyst"}, {"Emily", "UX Designer"}, {"David", "Project Manager"},
                {"Maria", "QA Engineer"}, {"Alex", "DevOps Engineer"}};
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        assertEquals(10, people.size());
        for (int i=0; i<peopleMap[2].length; i++) {
            assertEquals(peopleMap[i][0], people.get(i).findElement(By.className("name")).getText());
            assertEquals(peopleMap[i][1], people.get(i).findElement(By.className("job")).getText());
            i++;
        }
    }
}
