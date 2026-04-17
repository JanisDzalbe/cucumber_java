package cucumber.stepDefinitions;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SampleSteps {
    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://acctabootcamp.github.io/site");
    }

    @Then("^I should see home page header$")
    public void iShouldSeeHomePageHeader() throws Throwable {
        assertEquals("This is a home page",
                driver.findElement(By.cssSelector("h1")).getText());
    }

    @And("^I should see home page description$")
    public void iShouldSeeHomePageDescription() throws Throwable {
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                driver.findElement(By.cssSelector("p")).getText());
    }

    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable{
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable{
        assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
        assertEquals("Heading 1", driver.findElement(By.id("heading_1")).getText());
        assertTrue(driver.findElement(By.id("heading_2")).isDisplayed());
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws  Throwable{
        assertTrue(driver.findElement(By.xpath("//input[@name='randomButton1']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//input[@name='randomButton1']")).isEnabled());
        assertTrue(driver.findElement(By.xpath("//input[@name='randomButton2']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//input[@name='randomButton2']")).isEnabled());
    }

    @When("^I enter name: \"([^\"]*)\"$")
    public void iEnterName(String name) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter age: (\\d+)$")
    public void iEnterAge(int age) throws Throwable {
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
    }

    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/age");
    }

    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @And("^I click the send button$")
    public void iClickTheSendButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Send']")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    @Given("^I am on feedback page$")
    public void iAmOnTheFeedbackPage()throws Throwable{
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) throws Throwable {
        for (String language : languages) {
            driver.findElement(By.cssSelector("[value='" + language + "']")).click();
        }
    }

    @And("^I click send feedback$")
    public void iClickSendFeedback() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Send']")).click();
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iSeeLanguagesInFeedbackCheck(String languages) throws Throwable {
        assertEquals(languages, driver.findElement(By.id("language")).getText());
    }


    @When("^I enter the feedback name: \"([^\"]*)\"$")
    public void iEnterTheFeedbackName(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^I enter the feedback age: (\\d+)$")
    public void iEnterTheFeedbackAge(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @Then("^I see the feedback name: \"([^\"]*)\" in feedback$")
    public void iSeeTheFeedbackName(String name) throws Throwable {
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @And("^I see the feedback age: \"([^\"]*)\" in feedback$")
    public void iSeeTheFeedbackAge(String age) throws Throwable {
        assertEquals(age, driver.findElement(By.id("age")).getText());
    }

    @When("^I enter values:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(valuesToEnter.get("name"));
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(valuesToEnter.get("age"));
    }

    @And("^I should see menu$")
    public void iShouldSeeMenu() throws Throwable {
        assertTrue(driver.findElement(By.className("w3-navbar")).isDisplayed());
    }

    @And("^I click the result checkbox button$")
    public void iClickTheResultCheckboxButton() throws Throwable {
        driver.findElement(By.id("result_button_checkbox")).click();
    }

    @When("^I clicked on checkboxes:$")
    public void iClickedOnCheckboxes(List<String> values) throws Throwable {
        for (String value : values) {
            driver.findElement(By.cssSelector("[value='" + value + "']")).click();
        }
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("result_checkbox")).getText());
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String error) throws Throwable{
        assertEquals(error, driver.findElement(By.id("error")).getText());
    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() throws Throwable{
        assertFalse(driver.getCurrentUrl().contains("age_2.html"));
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }

    @Given("^I am on the enter number page$")
    public void iAmOnTheEnterNumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I write \"([^\"]*)\" in field$")
    public void iWriteNumberInField(String input)throws Throwable{
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(input);
    }

    @And("^I click on submit$")
    public void iClickOnSubmit() throws Throwable {
        driver.findElement(By.className("w3-orange")).click();
    }

    @Then("^I see errormessage: \"([^\"]*)\"$")
    public void iSeeErrorMessage(String errormessage) throws Throwable {
        assertEquals(errormessage, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I see squareroot message: \"([^\"]*)\"$")
    public void iSeeSquarerootMessage(String expectedMessage) throws Throwable {

        Alert alert = driver.switchTo().alert();
        assertEquals(expectedMessage, alert.getText());
        alert.accept();
    }

    @When("^I add feedback details:$")
    public void iAddFeedbackDetails(Map<String, String> feedbackDetails) throws Throwable{
        iEnterTheFeedbackName(feedbackDetails.get("name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(feedbackDetails.get("age"));
        driver.findElement(By.cssSelector("[value='" + feedbackDetails.get("gender")+ "'")).click();
    }

    @Then("^I can see name \"([^\"]*)\" in feedback page$")
    public void iCanSeeNameInFeedbackPage(String name)throws Throwable{
        assertEquals(name, driver.findElement(By.id("name")).getText());
    }

    @And("^I can see age \"([^\"]*)\" in feedback page$")
    public void iCanSeeAgeInFeedbackPage(String age)throws Throwable{
        assertEquals(age, driver.findElement(By.id("age")).getText());
    }


    @Then("^I can see gender \"([^\"]*)\" in feedback page$")
    public void iCanSeeGenderInFeedbackPage(String gender)throws Throwable{
    assertEquals(gender, driver.findElement(By.id("gender")).getText());
    }


    @Given("^I am on list of people with jobs page$")
    public void iAmOnListOfPeoplePage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @And("^I enter name: \"([^\"]*)\" and job: \"([^\"]*)\"$")
    public void iEnterNameAndJob(String name,String job)throws Throwable{
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I see name: \"([^\"]*)\" with job: \"([^\"]*)\" in the list$")
    public void iSeeNewPersonOnList(String name,String job) throws Throwable {
        WebElement newPerson= driver.findElement(By.xpath("//span[text()='" + name + "']"));
        WebElement newJob= driver.findElement(By.xpath("//span[text()='" + job + "']"));
        assertNotNull(newPerson);
        assertNotNull(newJob);
    }

    @Then("^I should see \"([^\"]*)\" in the list$")
    public void iShouldSeeInTheList(String name) {
        assertTrue(driver.findElement(By.xpath("//span[text()='" + name + "']")).isDisplayed());
    }

    @When("^I click Add Person button$")
    public void iClickAddPersonButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
    }

    @When("^I click on the Reset list button$")
    public void iClickOnTheRestButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @Then("^I click on Add button$")
    public void iClickOnAddButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @Then("^I should see the original list$")
    public void iShouldSeeOriginalList() throws Throwable {
        assertTrue(driver.findElement(By.xpath("//span[text()='John']")).isDisplayed());
    }

    @Then("^I remove person \"([^\"]*)\"$")
    public void iRemovePerson(String name) throws Throwable {
//        driver.findElement(By.xpath("//li[@id='person2']//span[contains(@class,'large name')]")).getText();
        driver.findElement(By.xpath("//span[contains(@class, 'large name') and text()='" + name + "']//parent::li//child::span[contains(@onclick,'deletePerson')]")).click();
    }

    @Then("^I should not see \"([^\"]*)\" in list$")
    public void iShouldNotSeeRemovedPersonOnList(String name) throws Throwable {
        assertThrows(NoSuchElementException.class, ()->driver.findElement(By.xpath("//span[text()='" +name+ "']")).isDisplayed());
    }

    @When("^I edit name: \"([^\"]*)\" with new name: \"([^\"]*)\" and new job: \"([^\"]*)\"$")
    public void iEditNameAndJob(String name,String newName, String newJob)throws Throwable{
        //driver.findElement(By.xpath("//li[@id='person5']//span[contains(@class,'large name')]")).getText();
        driver.findElement(By.xpath("//span[text()='" +name+ "']//preceding::span[1]")).click();
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("job")).sendKeys(newJob);
        driver.findElement(By.id("name")).sendKeys(newName);
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }
}

