package cucumber.stepDefinitions;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Locale;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleSteps {
    private WebDriver driver;
    private static List<String> namesList;
    private static List<String> jobsList;

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

    // Sample1 Task
    @When("^I am on the locators page$")
    public void iAmOnLocatorsPage(){
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders(){
        WebElement heading1 = driver.findElement(By.id("heading_1"));
        WebElement heading2 = driver.findElement(By.id("heading_2"));

        assertTrue(heading1.isDisplayed());
        assertTrue(heading2.isDisplayed());

        assertEquals(heading1.getText(),"Heading 1");
        assertEquals(heading2.getText(),"Heading 2 text");
    }

    @And("^Buttons in Locators page are clickable$")
     public void buttonsInLocatorsPageAreClickable(){
        WebElement buttonThis = driver.findElement(By.name("randomButton1"));
        WebElement buttonThisAlso = driver.findElement(By.id("buttonId"));

        assertTrue(buttonThis.isDisplayed());
        assertTrue(buttonThis.isEnabled());

        assertTrue(buttonThisAlso.isDisplayed());
        assertTrue(buttonThisAlso.isEnabled());
     }

     // Sample 2 task
     @Then("^I see error: \"([^\"]*)\"$")
     public void iSeeError(String errorText){
        assertTrue(driver.findElement(By.id("error")).isDisplayed());
         assertEquals(errorText,driver.findElement(By.id("error")).getText());
     }

     @And("^I am not navigated to age message page")
    public void iAmNotNavigated(){
        assertFalse(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/examples/age_2.html?name=1&age=2"));
        assertTrue(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/examples/age.html"));
     }

    @Given("^I am on Feedback page$")
    public void iAmOnFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter name into feedback: \"([^\"]*)\"$")
    public void iEnterNameIntoFeedback(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);


    }

    @And("^I enter age into feedback: (\\d+)$")
    public void iEnterAgeIntoFeedback(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click submit feedback$")
    public void iClickSubmitFeedback() throws Throwable {
      // driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
        driver.findElement(By.cssSelector("[type=\"submit\"]")).click();
    }


    @Then("^check form data Name: \"([^\"]*)\"$")
    public void iSeeNameFeedbackForm(String name) throws Throwable {
//        String url = "https://janisdzalbe.github.io/example-site/tasks/check_feedback.html?name=John&age=30&comment=";
//        assertEquals(url, driver.getCurrentUrl());
        assertEquals(String.valueOf(name), driver.findElement(By.id("name")).getText());
    }

    @Then("^check form data Age: (\\d+)$")
    public void iSeeAgeFeedbackForm(int age) throws Throwable {
//        String url = "https://janisdzalbe.github.io/example-site/tasks/check_feedback.html?name=John&age=30&comment=";
//        assertEquals(url, driver.getCurrentUrl());
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
    }

    @Then("^check form data \"([^\"]*)\" and (\\d+)$")
    public void iSeeNameAndAgeFeedbackForm(int age, String name) throws Throwable {
        assertEquals(String.valueOf(age), driver.findElement(By.id("age")).getText());
        assertEquals(String.valueOf(name), driver.findElement(By.id("name")).getText());
    }

    //Task1 start
    @When("^I am on the Square root page$")
    public void iAmOnTheSquareRootPage(){
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");

    }

    @And("^I fill the field with wrong (.+) value$")
    public void iShouldInputWrongValuesField(String wrongValue){
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.isDisplayed();
        inputField.isEnabled();

        inputField.sendKeys(wrongValue);
    }

    @And("^I fill the field with correct (\\d+) value$")
    public void iShouldSeeInputCorrectNumbers(String correctValue){
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.isDisplayed();
        inputField.isEnabled();

        inputField.sendKeys(correctValue);
    }

    @And("^I press submit button$")
    public void iShouldSeeSubmitButtons(){
        WebElement buttonSubmit = driver.findElement(By.xpath("//*[text()='Submit' and contains(@class, 'w3-btn')]"));
        assertTrue(buttonSubmit.isDisplayed());
        assertTrue(buttonSubmit.isEnabled());

        buttonSubmit.click();
    }

    @Then("^I receive an error message ([^\"]*)$")
    public void iCheckResult(String errorText){
        WebElement errorField = driver.findElement(By.className("error"));
        assertEquals(errorText, errorField.getText());

    }

   // @Then("I send (\\d+) receive an (\\d+)$")
    @Then("I compare suitableValue to an ([^\"]*)$")
    public void iReceiveCompareResult( String textResult){
        Alert alert1 = driver.switchTo().alert();
        assertEquals(alert1.getText(), textResult);
        alert1.accept();
    }

    @Then("I send (.+) to calculate an (.+)$")
    public void iReceiveCalcResult(String suitableValue, String result){
        double sqrtResult = Math.sqrt(Double.parseDouble(suitableValue));
        sqrtResult = Math.round(sqrtResult * 100 ) / 100.0;

        String checkResultFormatted =
                "Square root of "  + suitableValue + " is "  +  String.format(Locale.US,"%.2f", sqrtResult);

        // debug visual formatting
        //    System.out.println("------------" + checkResultFormatted);

        Alert alert1 = driver.switchTo().alert();
        assertTrue(checkResultFormatted.equals(alert1.getText()));

        //Double check maybe overkill
        String sqrtResultOverkill =  String.format(Locale.US,"%.2f", sqrtResult);
        assertEquals(result,sqrtResultOverkill);
        //Double check maybe overkill

        alert1.accept();
    }

    @And("^I check that no error message$")
    public void iCheckTheNoErrorMessage(){
        WebElement errorField = driver.findElement(By.className("error"));
        assertFalse(errorField.isDisplayed());
    }
    //Task1 end

    //Sample 4 Start
    @When("^I select Feedback languages$")
    public void iSelectFeedbackLang(List<String> langList){
         for(String lang : langList){
             driver.findElement(By.xpath("//*[@type='checkbox' and @value='" + lang + "']")).click();
         }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String languages){
       assertEquals(languages, driver.findElement(By.id("language")).getText());
    }

    // Sample 5
    @When("^I input Name, Age and Genre in Feedback Page$")
    public void iInputNameAgeAndGenre(Map<String, String> inputMap) throws  Throwable{
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(inputMap.get("Name"));

     //   iEnterNameInFeedback(inputMap.get("Name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(inputMap.get("Age"));
        driver.findElement(By.cssSelector("[type='radio'][value='" + inputMap.get("Genre").toLowerCase() + "']")).click();
    }

    @When("^I can see values in feedback page$")
    public void iAssertValueInFeedbackCheck(Map<String, String> inputMap) throws  Throwable{
        assertEquals((inputMap.get("Name")),driver.findElement(By.id("name")).getText());
        assertEquals((inputMap.get("Age")),driver.findElement(By.id("age")).getText());
        assertEquals((inputMap.get("Genre")),driver.findElement(By.id("gender")).getText());
    }

    //Task 2 Start
    @Given ("^I am on the People with jobs page$")
    public void iAmOnThePeopleWithJobsPage()
    {
         driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

     //   WebElement buttonAdd = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Add person')]"));
        WebElement buttonAdd = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(text(),'Add person')]"));
        assertTrue(buttonAdd.isDisplayed());
        assertTrue(buttonAdd.isEnabled());

        WebElement buttonResetList = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(text(),'Reset List')]"));
        assertTrue(buttonResetList.isDisplayed());
        assertTrue(buttonResetList.isEnabled());
    }

    

    @And ("^Add precondition list with Names and Jobs$")
    public void addPreconditionListNameAndJob() throws Throwable
    {
        namesList = new ArrayList<>();
        namesList.add("Mike");
        namesList.add("Jill");
        namesList.add("Jane");
        namesList.add("John");
        namesList.add("Sarah");
        namesList.add("Carlos");
        namesList.add("Emily");
        namesList.add("David");
        namesList.add("Maria");
        namesList.add("Alex");

        jobsList = new ArrayList<>();
        jobsList.add("Web Designer");
        jobsList.add("Support");
        jobsList.add("Accountant");
        jobsList.add("Software Engineer");
        jobsList.add("Product Manager");
        jobsList.add("Data Analyst");
        jobsList.add("UX Designer");
        jobsList.add("Project Manager");
        jobsList.add("QA Engineer");
        jobsList.add("DevOps Engineer");
    }
    @Then ("^I compare and prove list$")
    public void iCompareAndProveList(){

        assertEquals(driver.getCurrentUrl(),"https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

        List<WebElement> allPeople = driver.findElements(By.className("w3-padding-16"));

        String xName = "";
        String xJob = "";
        for (int i = 0; i < allPeople.size(); i++) {
            WebElement person = allPeople.get(i);
            xName = person.findElement(By.className("w3-xlarge")).getText();
            xJob = person.findElement(By.className("job")).getText();

            assertEquals(xName, namesList.get(i));
            assertEquals(xJob, jobsList.get(i));
        }
        
    }


    @When ("^I add new person with Name and Job$")
    public void iAddNewPerson(Map<String ,String> table) throws Throwable
    {
        WebElement buttonAddPerson = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(text(),'Add person')]"));

        buttonAddPerson.click();

        assertEquals(driver.getCurrentUrl(), "https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html");
        WebElement fieldName =  driver.findElement(By.xpath("//*[@id='name']"));
        WebElement fieldJob = driver.findElement(By.xpath("//*[@id='job']"));

        assertEquals("", fieldName.getText());
        assertEquals("", fieldJob.getText());

        fieldName.sendKeys(table.get("Name"));
        fieldJob.sendKeys(table.get("Job"));

        namesList.add(table.get("Name"));
        jobsList.add(table.get("Job"));

        driver.findElement(By.xpath("//*[@id='modal_button' and contains(text(),'Add')]")).click();

        assertEquals(driver.getCurrentUrl(), "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

     //   Thread.sleep(10000);


    }

    @And  ("^I edit a person to$")
    public void iEditAPerson(Map<String ,String> table) throws Throwable
    {
        assertEquals(driver.getCurrentUrl(), "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

        //for example 4rd person John Software Engineer
        WebElement person3 = driver.findElement(By.cssSelector("#person3 .fa.fa-pencil"));
        person3.click();
        int personToEdit = 3;

        assertEquals(driver.getCurrentUrl(), "https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html?id=3");

        WebElement fieldName =  driver.findElement(By.xpath("//*[@id='name']"));
        WebElement fieldJob = driver.findElement(By.xpath("//*[@id='job']"));

        assertEquals("John", fieldName.getAttribute("value"));
        assertEquals("Software Engineer",  fieldJob.getAttribute("value"));

        fieldName.clear();
        fieldJob.clear();
        assertEquals("", fieldName.getAttribute("value"));
        assertEquals("",  fieldJob.getAttribute("value"));

        namesList.set(personToEdit,table.get("Name"));
        jobsList.set(personToEdit,table.get("Job"));

        fieldName.sendKeys(table.get("Name"));
        fieldJob.sendKeys(table.get("Job"));

        assertEquals(table.get("Name"), fieldName.getAttribute("value"));
        assertEquals(table.get("Job"),  fieldJob.getAttribute("value"));

        WebElement buttonEdit = driver.findElement(By.xpath("//*[@id='modal_button' and contains(text(),'Edit')]"));
        assertTrue(buttonEdit.isDisplayed());
        assertTrue(buttonEdit.isEnabled());

        buttonEdit.click();

        assertEquals(driver.getCurrentUrl(), "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");


        WebElement updatedPerson3 = driver.findElement(By.id("person3"));
        String xName = updatedPerson3.findElement(By.cssSelector(".w3-xlarge.name")).getText();
        String xJob = updatedPerson3.findElement(By.className("job")).getText();

        assertEquals(table.get("Name"), xName);
        assertEquals(table.get("Job"), xJob);

    }

    @And  ("^I remove a person 3$")
    public void iRemoveAPerson() throws Throwable
    {
        assertEquals(driver.getCurrentUrl(), "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

        WebElement person2 = driver.findElement(By.xpath("//*[@onclick='deletePerson(2)']"));
        assertTrue(person2.isEnabled());
        assertTrue(person2.isDisplayed());

        person2.click();

        assertEquals(driver.getCurrentUrl(), "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

        namesList.remove(2);
        jobsList.remove(2);

        //  Thread.sleep(10000);
    }

    @Then ("^I reset list to original$")
    public void iResetToOriginalListAndProve() throws Throwable
    {
        WebElement buttonResetList = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(text(),'Reset List')]"));
        assertTrue(buttonResetList.isDisplayed());
        assertTrue(buttonResetList.isEnabled());


        addPreconditionListNameAndJob();
        buttonResetList.click();
    }


    //Task 2 Finish

}