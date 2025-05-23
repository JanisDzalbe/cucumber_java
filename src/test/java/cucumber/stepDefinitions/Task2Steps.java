package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
        driver.findElement(By.id("name")).sendKeys(text);
    }

    @And("^I enter surname \"([^\"]*)\"$")
    public void iEnterSurname(String text) throws Throwable {
        driver.findElement(By.id("surname")).sendKeys(text);
    }

    @And("^I enter job \"([^\"]*)\"$")
    public void iEnterJob(String text) throws Throwable {
        driver.findElement(By.id("job")).sendKeys(text);
    }

    @And("^I enter Date of Birth \"([^\"]*)\"$")
    public void iEnterDateOfBirth(String text) throws Throwable {
        driver.findElement(By.id("dob")).sendKeys(text);
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

    @And("^I Click Delete button$")
    public void iClickOnDeleteButton() {
        driver.findElement(By.className("closebtn")).click();
    }


    @And("^I Click Reset list button$")
    public void iClickOnResetButton() throws Throwable {
        WebElement resetButton = driver.findElement(By.xpath("//button[contains(text(), 'Reset')]"));
        resetButton.click();

    }

}
