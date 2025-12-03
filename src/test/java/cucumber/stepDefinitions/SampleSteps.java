package cucumber.stepDefinitions;

import io.cucumber.java.PendingException;
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
import static org.junit.jupiter.api.Assertions.assertAll;

public class SampleSteps {
  private WebDriver driver;

  public SampleSteps() {
    this.driver = Hooks.driver;
  }

  @Given("^I am on the home page$")
  public void iAmOnTheHomePage() throws Throwable {
    driver.get("https://janisdzalbe.github.io/example-site/");
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

  @Given("^I (?:am on|open) action page$")
  public void iAmOnActionPage() {
    driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
  }

  @Given("I (?:am on|open) the locators page$")
  public void iAmOnTheLocatorsPage() {

    driver.get("https://janisdzalbe.github.io/example-site/examples/locators");

  }

  @Then("I should see both locators page headers")
  public void iShouldSeeBothLocatorsPageHeaders() {

    assertTrue(driver.findElement(By.id("heading_1")).isDisplayed());
  }

  @And("Buttons in Locators page are clickable")
  public void buttonsInLocatorsPageAreClickable() {
    assertAll(
            () -> assertTrue(driver.findElement(By.name("randomButton1")).isEnabled()),
            () -> assertTrue(driver.findElement(By.name("randomButton1")).isDisplayed()),
            () -> assertTrue(driver.findElement(By.name("randomButton2")).isEnabled()),
            () -> assertTrue(driver.findElement(By.name("randomButton2")).isDisplayed())
    );

  }

  @Then("^I see error: \"([^\"]*)\"$")
  public void iSeeError(String arg0) throws Throwable {
    assertTrue(driver.findElement(By.className("error")).isDisplayed());
    assertEquals(arg0, driver.findElement(By.className("error")).getText());
  }

  @And("I am not navigated to age message page")
  public void iAmNotNavigatedToAgeMessagePage() {
    assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());

  }

  @Given("I (?:am on|open) feedback page$")
  public void iAmOnFeedbackPage() {
    // Write code here that turns the phrase above into concrete actions
    driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
  }

  // sample 3
  @And("^I click send$")
  public void iClickSend() {
    // Write code here that turns the phrase above into concrete actions
    driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
  }


  @When("^I enter fb name: \"([^\"]*)\"$")
  public void iEnterFbName(String arg0) {
    driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).clear();
    driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).sendKeys(arg0);
  }

  @And("^I enter fb-age: (\\d+)$")
  public void iEnterFbAgeFbAge(int age) {
    driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).sendKeys(String.valueOf(age));
  }


  @Then("^I see feedback fields$")
  public void iSeeFeedbackFields() {
      assertNotEquals("https://janisdzalbe.github.io/example-site/tasks/provide_feedback",driver.getCurrentUrl());
  }

  @And("^I check field fb name: \"([^\"]*)\"$")
  public void iCheckFieldFbName(String name) {
    assertEquals(name, driver.findElement(By.id("name")).getText());
  }


  @And("^I check field fb-age: (\\d+)$")
  public void iCheckFieldFbAgeFbAge(int age) {
    assertEquals(age, Integer.parseInt(driver.findElement(By.id("age")).getText()));
  }

  @Given("^I (?:am on|open) EnterNumber page$")
  public void iAmOnEnterNumberPage() {
      driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
  }

  @When("^I enter number in field \"([^\"]*)\"$")
  public void iEnterNumberInFieldInput(String number) {
    driver.findElement(By.id("numb")).sendKeys(number);
  }

  @And("I click submit number")
  public void iClickSubmitNumber() {
    driver.findElement(By.className("w3-orange")).click();
  }

  @Then("^I see alert \"([^\"]*)\"$")
  public void iSeeAlertMessage(String message) {
    Alert alert=driver.switchTo().alert();
    assertEquals(message,alert.getText());
  }
  @Given("^I (?:am on|open) ListOfPeople page$")
  public void iAmOnListOfPeoplePage() {
    driver.get(" https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
  }

  @When("I click Add Person")
  public void iClickAddPerson() {
    driver.findElement(By.xpath("//button[text()=\"Add person\"]")).click();
  }

  @And("^I enter Job: \"([^\"]*)\"$")
  public void iEnterJob(String job) {
    driver.findElement(By.id("job")).clear();
    driver.findElement(By.id("job")).sendKeys(job);
  }

  @And("I click Add")
  public void iClickAdd() {
    driver.findElement(By.xpath("//button[text()=\"Add\"]")).click();
  }

  @Then("I see name: {string} and job {string}")
  public void iSeeNameAndJob(String name,String job) {
    List<WebElement> allElements = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));
    WebElement Person = null;
    for (WebElement element : allElements) {
      String inputVal = element.findElement(By.className("name")).getText();
      String Jobval = element.findElement(By.className("job")).getText();
      if (inputVal.equals(name) && Jobval.equals(job)) {
        Person = element;
        break;
      }
    }
    assertEquals(name, Person.findElement(By.className("name")).getText());
    assertEquals(job, Person.findElement(By.className("job")).getText());
  }

}
