package cucumber.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cucumber.pages_sample.*;

import java.util.Map;

import static org.junit.Assert.*;

public class SamplePOSteps {
    private WebDriver driver;
    static AgePage agePage;
    static AgeSubmittedPage ageSubmittedPage;

    public SamplePOSteps() {
        this.driver = Hooks.driver;
        agePage = PageFactory.initElements(Hooks.driver, AgePage.class);
        ageSubmittedPage = PageFactory.initElements(Hooks.driver, AgeSubmittedPage.class);
    }

    @When("^I enter name: \"([^\"]*)\" using PO$")
    public void iEnterName(String name) throws Throwable {
        agePage.enterName(name);
    }

    @And("^I enter age: (\\d+) using PO$")
    public void iEnterAge(int age) throws Throwable {
        agePage.enterAge(age);
    }

    @Given("^I (?:am on|open) age page using PO$")
    public void iAmOnAgePage() throws Throwable {
        driver.get(agePage.getPageUrl());
    }

    @And("^I click submit age using PO$")
    public void iClickSubmitAge() throws Throwable {
        agePage.clickSubmit();
    }

    @Then("^I see message: \"(.*)\" using PO$")
    public void iSeeMessage(String message) throws Throwable {
        ageSubmittedPage.checkMessageText(message);
    }

    @When("^I enter values using PO:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        agePage.enterName(valuesToEnter.get("name"));
        agePage.enterAge(valuesToEnter.get("age"));
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorTxt) throws Throwable {
        assertTrue(errorTxt, driver.findElement(By.id("error")).isDisplayed());
        assertEquals(errorTxt, driver.findElement(By.id("error")).getText());

    }

    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() throws Throwable{
        assertFalse(driver.getCurrentUrl().contains("https://janisdzalbe.github.io/example-site/examples/age_2"));
        assertEquals("https://janisdzalbe.github.io/example-site/examples/age", driver.getCurrentUrl());
    }
}
