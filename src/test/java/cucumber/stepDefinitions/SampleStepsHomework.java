package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @When("^I click on the \"Add person\" button$")
    public void iClickOnAddPerson() throws Throwable {
        driver.findElement(By.id("addPersonBtn")).click();
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
        initialRecordCount = getInitialRecordCount();
        List<WebElement> recordNum = driver.findElements(By.className("w3-xlarge"));
        Assertions.assertEquals(initialRecordCount, recordNum.size());
        }
    @When("^I choose the second record and click on the pencil icon to edit it$")
    public void iClickOnPencilIcon() throws Throwable {
        driver.findElement(By.cssSelector("#person1 i.fa-pencil")).click();
    }
    @And("I can see that the name and job have been updated to \"Simon\" and \"Florist\"$")
    public void checkThatNameAndJobHasBeenChanged() throws Throwable {
        assertEquals("Simon", driver.findElement(By.cssSelector("#person1 > span.w3-xlarge")).getText());
        System.out.println(driver.findElement(By.cssSelector("#person0 > span.w3-xlarge")).getText());
    }

    @Then("I am redirected to the Editing page$")
    public void redirectionToTheEditingPage() throws Throwable {
        assertEquals("https://acctabootcamp.github.io/site/tasks/enter_a_new_person_with_a_job.html?id=1", driver.getCurrentUrl());
}
    @And("^I click on the \"Edit\" button$")
    public void iClickOnEditButtonAtEditPersonPage() throws Throwable {
        driver.findElement(By.xpath("(//button[normalize-space(text())='Edit'])[1]")).click();
    }

    @When("^I click on the \"x\" icon next to the record to delete it$"){
    public void iClickOnDeleteButtonAtPersonPage() throws Throwable {
        driver.findElement(By.cssSelector("#person2 > span.closebtn")).click();
    }
}








//
//
//
//
//
//
//
//
//
//
//
//    @Given("^I am on the home page$")
//    public void iAmOnTheHomePage() throws Throwable {
//        driver.get("https://acctabootcamp.github.io/site");
//    }
//
//    @Given("^I am on the locators page$")
//    public void iAmOnLocatorPage() throws Throwable {
//        driver.get("https://acctabootcamp.github.io/site/examples/locators");
//    }
//
//    @Then("^I should see home page header$")
//    public void iShouldSeeHomePageHeader() throws Throwable {
//        assertEquals("This is a home page",
//                driver.findElement(By.cssSelector("h1")).getText());
//    }
//    @Then("^I should see both locators page headers$")
//    public void iShouldSeeLocatorPageHeaders() throws Throwable {
//        assertEquals("Heading 1",
//                driver.findElement(By.id("heading_1")).getText());
//        assertEquals("Heading 2 text",
//                driver.findElement(By.id("heading_2")).getText());
//    }
//
//    @Then("^Buttons in Locators page are clickable$")
//    public void locatorPageButtonsAreClickable() throws Throwable {
//        assertTrue(driver.findElement(By.cssSelector("*[name=\"randomButton1\"]")).isEnabled());
//        assertTrue(driver.findElement(By.cssSelector("*[name=\"randomButton2\"]")).isEnabled());
//    }
//    @And("^I should see home page description$")
//    public void iShouldSeeHomePageDescription() throws Throwable {
//        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
//                driver.findElement(By.cssSelector("p")).getText());
//    }
//
//    @When("^I enter name: \"([^\"]*)\"$")
//    public void iEnterName(String name) throws Throwable {
//        driver.findElement(By.id("name")).clear();
//        driver.findElement(By.id("name")).sendKeys(name);
//    }
//
//    @And("^I enter age: (\\d+)$")
//    public void iEnterAge(int age) throws Throwable {
//        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
//    }
//
//    @Given("^I (?:am on|open) age page$")
//    public void iAmOnAgePage() throws Throwable {
//        driver.get("https://acctabootcamp.github.io/site/examples/age");
//    }
//
//    @And("^I click submit age$")
//    public void iClickSubmitAge() throws Throwable {
//        driver.findElement(By.id("submit")).click();
//    }
//
//    @Then("^I see message: \"([^\"]*)\"$")
//    public void iSeeMessage(String message) throws Throwable {
//        assertEquals(message, driver.findElement(By.id("message")).getText());
//    }
//
//    @Then("^I see error: \"([^\"]*)\"$")
//    public void errorMessageAppears(String message) throws Throwable {
//        assertEquals(message, driver.findElement(By.id("error")).getText());
//    }
//
//    @Then("I am not navigated to age message page$")
//    public void iAmNotOnAgePage() throws Throwable {
//        assertEquals("https://acctabootcamp.github.io/site/examples/age", driver.getCurrentUrl());
//    }
//    @Given("^I (?:am on|open) feedback page$")
//    public void iAmOnFeedbackPage() throws Throwable {
//        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
//    }
//    @When("^I enter name \"([^\"]*)\" and age (\\d*) on feedback page$")
//    public void iEnterNameAndAgeOnFeedbackPage(String name, int age) throws Throwable {
//        driver.findElement(By.id("fb_name")).clear();
//        driver.findElement(By.id("fb_name")).sendKeys(name);
//        driver.findElement(By.id("fb_age")).clear();
//        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
//    }
//    @And("^I click send button$")
//    public void iClickOnSendButtonFeedbackPage() throws Throwable {
//        driver.findElement(By.tagName("button")).click();
//    }
//    @Then("^I see correct name \"([^\"]*)\" input on feedback page$")
//    public void iSeeCorrectInputName(String name) throws Throwable {
//        assertEquals(name, driver.findElement(By.id("name")).getText());
//    }
//    @Then("^I see correct age (\\d*) input on feedback page$")
//    public void iSeeCorrectInputName(int age) throws Throwable {
//        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
//    }
//
//    @Given("^I (?:am on|open) Enter a number page$")
//    public void iAmOnEnterANumberPage() throws Throwable {
//        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
//    }
//
//    @When("^I enter a number: \"([^\"]*)\"$")
//    public void iEnterANumberOnEnterANumberPage(String name) throws Throwable {
//        driver.findElement(By.id("numb")).clear();
//        driver.findElement(By.id("numb")).sendKeys(name);
//    }
//    @And("^I click on submit button$")
//    public void iClickOnSubmitButtonEnterANumberPage() throws Throwable {
//        driver.findElement(By.className("w3-orange")).click();
//    }
//    @Then("^I see an error \"([^\"]*)\" message$")
//    public void iSeeAnErrorMessage(String error) throws Throwable {
//        assertEquals(error, driver.findElement(By.id("ch1_error")).getText());
//    }
//    @Then("^I see a pop up message with an answer for a number: \"([^\"]*)\"$")
//    public void iSeeAnAnswer(String number) throws Throwable {
//        int intNumber = Integer.parseInt(number);
//        double numberSq = Math.sqrt(intNumber);
//        String formatted = String.format("%.2f", numberSq);
//        String allertExp = "Square root of " + intNumber + " is " + formatted;
//
//        Alert alert = driver.switchTo().alert();
//        String allertText = alert.getText();
//        Assertions.assertEquals(allertExp, allertText);
//        alert.accept();
//        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
//    }
//
//    @When("^I enter values:$")
//    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
//        for (Map.Entry<String, String> e : valuesToEnter.entrySet()) {
//            driver.findElement(By.id(e.getKey())).clear();
//            driver.findElement(By.id(e.getKey())).sendKeys(e.getValue());
//            System.out.println("key is " + e.getKey());
//            System.out.println("value is " + e.getValue());
//        }
//    }
//
//    @And("^I should see menu$")
//    public void iShouldSeeMenu() throws Throwable {
//        assertTrue(driver.findElement(By.className("w3-navbar")).isDisplayed());
//    }
//
//    @And("^I click the result checkbox button$")
//    public void iClickTheResultCheckboxButton() throws Throwable {
//        driver.findElement(By.id("result_button_checkbox")).click();
//    }
//
//    @When("^I clicked on checkboxes:$")
//    public void iClickedOnCheckboxes(List<String> values) throws Throwable {
//        for (String value : values) {
//            driver.findElement(By.cssSelector("[value='" + value + "']")).click();
//        }
//    }
//
//
//    @Given("^I am on action page$")
//    public void iAmOnActionPage() {
//        driver.get("https://acctabootcamp.github.io/site/examples/actions");
//    }
//    @When("^I select feedback languages$")
//    public void iSelectLanguagesFeedbackPage(List<String> languages) throws Throwable {
//        for (String language : languages) {
//            driver.findElement(By.xpath("//*[@value='" + language + "']")).click();
//        }
//    }
//    @Given("^I click send feedback$")
//    public void iClickSubmitButtonSendFeedback() {
//        driver.findElement(By.className("w3-btn-block")).click();
//    }
//    @Then("I can see languages \"([^\"]*)\" in feedback check$")
//    public void checkLanguagesForFeedbackPage(String languages) throws Throwable {
//        assertEquals(languages, driver.findElement(By.id("language")).getText());
//    }
//    @When("^I enter feedback values:$")
//    public void iEnterFeedbackValues(Map<String, String> valuesToEnter) throws Throwable {
//            driver.findElement(By.id("fb_name")).clear();
//            driver.findElement(By.id("fb_name")).sendKeys(valuesToEnter.get("name"));
//            driver.findElement(By.id("fb_age")).clear();
//            driver.findElement(By.id("fb_age")).sendKeys(valuesToEnter.get("age"));
//            driver.findElement(By.cssSelector("[value='" + valuesToEnter.get("gender") + "']")).click();
//    }
//    @Then("^I can see input in feedback check$")
//    public void seeInputForFeedbackPage(Map<String, String> valuesToEnter) throws Throwable {
//        assertEquals(valuesToEnter.get("name"), driver.findElement(By.id("name")).getText());
//        assertEquals(valuesToEnter.get("age"), driver.findElement(By.id("age")).getText());
//        assertEquals(valuesToEnter.get("gender"), driver.findElement(By.id("gender")).getText());
//    }
//
//
//}
