package cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
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

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    @When("^I enter values:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        for (Map.Entry<String, String> e : valuesToEnter.entrySet()) {
            driver.findElement(By.id(e.getKey())).clear();
            driver.findElement(By.id(e.getKey())).sendKeys(e.getValue());
            System.out.println("key is " + e.getKey());
            System.out.println("value is " + e.getValue());
        }
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

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }
    @When("^I am on the locators page$")
    public void iAmOnLocatorsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }
    @Then("^I should see both locators page headers$")
    public void assertTwoHeaders() {
        WebElement header1 = driver.findElement(By.id("heading_1"));
        WebElement header2 = driver.findElement(By.id("heading_2"));
        assertTrue(header1.isDisplayed());
        assertTrue(header2.isDisplayed());
        assertEquals("Heading 1", header1.getText());
        assertEquals("Heading 2 text", header2.getText());
    }
    @And("^Buttons in Locators page are clickable$")
    public void assertTwoButtons() {
        WebElement btn1 = driver.findElement(By.cssSelector("[name='randomButton1']"));
        WebElement btn2 = driver.findElement(By.id("buttonId"));
        assertTrue(btn1.isEnabled());
        assertTrue(btn2.isEnabled());
    }

    @And("^I see error: \"([^\"]*)\"$")
    public void validateErrorMsg(String errorMsg) {
        WebElement errorMsgObj = driver.findElement(By.id("error"));
        assertTrue(errorMsgObj.isDisplayed());
        assertEquals(errorMsg, errorMsgObj.getText());
    }

    @And("^I am not navigated to age message page$")
    public void validateNavigation()
        {
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age",driver.getCurrentUrl());
        assertFalse(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/examples/age_2.html?name=fgg&age=99") );
    }

    @And("^Navigate to page$")
    public void navigation()
    {
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age",driver.getCurrentUrl());
        assertFalse(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/examples/age_2.html?name=fgg&age=99") );
    }


    @Given("^I am on feedback page$")
    public void navigateToPage()
    {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }
    @And("I set name \"([^\"]*)\"$")
    public void enterNameInFeedbackPage(String name) {
       WebElement nameField =driver.findElement(By.id("fb_name"));
        nameField.sendKeys(name);
    }

    @And("I set age (\\d+)$")
    public void enterAgeInFeedbackPage(int age) {
        WebElement ageField =driver.findElement(By.id("fb_age"));
        ageField.sendKeys(String.valueOf(age));
    }
    @And("^I click send$")
    public void clickSend () {
        WebElement sendBtn =driver.findElement(By.cssSelector("*[type='submit']"));
        sendBtn.click();
    }
    @Then ("^I should see name as \"([^\"]*)\" and age as (\\d+)$")
    public void validateFields(String name,int age)
    {
        WebElement nameField =driver.findElement(By.id("name"));
        WebElement ageField =driver.findElement(By.id("age"));
        assertEquals(name,nameField.getText() );
        assertEquals(String.valueOf(age),ageField.getText());
    }
    @Given ("^I am on enter a number page$")
    public void iGoToEnterNumberPage()
    {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter a not valid number ([A-Za-z0-9]+)$")
    public void enterInvalidNumber(String input) {
        WebElement numberField =driver.findElement(By.id("numb"));
        numberField.sendKeys(input);
    }

    @And ("^I click submit$")
    public void iClickSubmit()
    {
        WebElement submitBtn =driver.findElement(By.xpath("//*[text()='Submit']"));
        submitBtn.click();
    }

    @When("I see error message \"([^\"]*)\"$")
    public void iValidateErrorMsg(String msg) {
        WebElement errorMsg =driver.findElement(By.id("ch1_error"));
        assertEquals(msg, errorMsg.getText());
    }
    @And("^I enter a number 60")
    public void entervalidNumber() {
        WebElement numberField =driver.findElement(By.id("numb"));
        numberField.sendKeys("60");
    }
    @And("I see alert with correct SquareRoot")
    public void validateSquareRoot() {
        Alert alert = driver.switchTo().alert();
        String alertMsg= alert.getText();
        double result = Math.round(Math.sqrt(60) * 100.0) / 100.0;
        assertEquals("Square root of "+60+" is "+ result, alertMsg);
        alert.accept();
    }
    @Then("I should not see error msg")
    public void iSeeNoError() {
        WebElement errorMsg = driver.findElement(By.id("ch1_error"));
        assertEquals("", errorMsg.getText());
    }


    @When("^I select feedback languages:$")
    public void iSelectFeedbackLanguages(List<String> languages) throws Throwable {
        for (String language : languages) {
            driver.findElement(By.xpath("//*[@type='checkbox' and @value='" + language + "']")).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iAssertLanguagesForFeedbackPage(String languages) {
        WebElement languageText = driver.findElement(By.id("language"));
        assertEquals(languages, languageText.getText());
    }

    @When("^I set values for feedback page:$")
    public void iSetValuesForFeedbackPage(Map<String, String> valuesToEnter) throws Throwable {
            enterNameInFeedbackPage (valuesToEnter.get("name"));
            int age = Integer.parseInt(valuesToEnter.get("age"));
            enterAgeInFeedbackPage(age);
            driver.findElement(By.cssSelector("[type='radio'][value='" + valuesToEnter.get("genre").toLowerCase() + "']")).click();
    }
    @When("^I should see values in feedback check:$")
    public void iAssertValuesOnFeedbackPage(Map<String, String> valuesToEnter) throws Throwable {
        int age = Integer.parseInt(valuesToEnter.get("age"));
        validateFields(valuesToEnter.get("name"), age);
        assertEquals(valuesToEnter.get("genre").toLowerCase(),  driver.findElement(By.id("gender")).getText());
    }

    @Given("^I am on people with jobs page$")
    public void iGoToPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }
    @When("^I click add person button$")
    public void iClickAddPersonBtn(){
        driver.findElement(By.xpath("//h2[text()='People with jobs']/following-sibling::Button[@id='addPersonBtn']")).click();
    }

    @And("^I fill name as \"([^\"]*)\" and job as \"([^\"]*)\" fields$")
    public void iFillFields(String name, String job){
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        nameField.sendKeys(name);
        jobField.sendKeys(job);
    }

    @And("^I click Add$")
    public void iClickAdd(){
        driver.findElement(By.xpath("//button[@id='modal_button' and text()='Add']")).click();
    }
    @And("^I should see new person is added to the list with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iAssertOnFieldsForPeopleWithJobsPage(String name, String job){
        List<WebElement> listOfPeoples = driver.findElements(By.className("w3-padding-16"));
        assertEquals(listOfPeoples.size(), 11);
        WebElement lastElement = listOfPeoples.get(listOfPeoples.size()-1);
        assertEquals(name, lastElement.findElement(By.className("name")).getText());
        assertEquals(job, lastElement.findElement(By.className("job")).getText());
    }
    @And("^I click pencil icon for existing person$")
    public void iClickPencilIcon(){
        driver.findElement(By.xpath("//i[@class='fa fa-pencil'][1]")).click();
    }
    @And("^I check values in Name and Job fields$")
    public void iCheckExistingValues(){
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        assertEquals("Mike", nameField.getAttribute("value"));
        assertEquals("Web Designer", jobField.getAttribute("value"));

    }
    @And("^I change job field to \"([^\"]*)\"$")
    public void iChangeJobField(String job){
        WebElement jobField = driver.findElement(By.id("job"));
        jobField.clear();
        jobField.sendKeys(job);
    }
    @And("^I click edit$")
    public void iClickEdit(){
       driver.findElement(By.xpath("//button[@id='modal_button' and text()='Edit']")).click();
    }
    @And("^I change job field$")
    public void iChangeJobField(){
        WebElement jobField = driver.findElement(By.id("job"));
        jobField.clear();
        jobField.sendKeys("Teacher");
    }
    @Then("^I check that the person is updated in the list with new job \"([^\"]*)\"$")
    public void iAssertOnChangedPerson(String job, DataTable table){
        List<Map<String, String>> expectedList = table.asMaps(String.class, String.class);
        WebElement firstElementAfterEditing = driver.findElements(By.className("w3-padding-16")).get(0);
        assertEquals(expectedList.get(0).get("name"), firstElementAfterEditing.findElement(By.className("name")).getText());
        assertEquals(job, firstElementAfterEditing.findElement(By.className("job")).getText());
    }
    @When("^I click cross x icon for an existing person$")
    public void iClickCrossIcon(){
        driver.findElement(By.xpath("//*[@id='person0']/span[1]")).click();
    }
    @Then("^I check that the person is removed from the list$")
    public void iAssertOnRemovedPerson(DataTable table) {
        List<Map<String, String>> expectedList = table.asMaps(String.class, String.class);
        List<WebElement> listOfPeoplesAfterRemoving = driver.findElements(By.className("w3-padding-16"));
        assertEquals(expectedList.size()-1, listOfPeoplesAfterRemoving.size());
        for (WebElement person : listOfPeoplesAfterRemoving) {
            assertFalse(person.findElement(By.className("name")).getText().equals(expectedList.get(0).get("name")));
            assertFalse(person.findElement(By.className("job")).getText().equals(expectedList.get(0).get("job")));
        }
    }
        @And("^I click Reset List$")
        public void iClickResetList() throws InterruptedException {
            driver.findElement(By.xpath("//h2[text()='People with jobs']/following-sibling::Button[@id='addPersonBtn' and text()='Reset List']")).click();

    }

    @Then("^I check that the list is back to initial state with 10 original entries$")
    public void iAssertOnOriginalList(DataTable table) {
        List<Map<String, String>> expectedList = table.asMaps(String.class, String.class);
        List<WebElement> listOfPeoples = driver.findElements(By.className("w3-padding-16"));
        assertEquals(expectedList.size(), listOfPeoples.size());
        for (int i = 0; i < expectedList.size(); i++) {

            Map<String, String> expected = expectedList.get(i);
            WebElement person = listOfPeoples.get(i);

            String actualName = person.findElement(By.className("name")).getText().trim();
            String actualJob  = person.findElement(By.className("job")).getText().trim();

            assertEquals( expected.get("name"), actualName);
            assertEquals( expected.get("job"), actualJob);
        }
    }
}





















