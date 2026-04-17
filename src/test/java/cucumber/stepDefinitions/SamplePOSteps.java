package cucumber.stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cucumber.pages_sample.*;

import java.util.Map;

public class SamplePOSteps {

    private WebDriver driver;

    static AgePage agePage;
    static AgeSubmittedPage ageSubmittedPage;

    public SamplePOSteps() {
        this.driver = Hooks.driver;
        agePage = PageFactory.initElements(driver, AgePage.class);
        ageSubmittedPage = PageFactory.initElements(driver, AgeSubmittedPage.class);
    }

    @Given("^I (?:am on|open) age page using PO$")
    public void iOpenAgePage() {
        driver.get(agePage.getPageUrl());
    }

    @When("^I enter name: \"([^\"]*)\" using PO$")
    public void iEnterName(String name) {
        agePage.enterName(name);
    }

    @And("^I enter age: (\\d+) using PO$")
    public void iEnterAge(int age) {
        agePage.enterAge(age);
    }

    @And("^I click submit age using PO$")
    public void iClickSubmit() {
        agePage.clickSubmit();
    }

    @Then("^I see message: \"(.*)\" using PO$")
    public void iSeeMessage(String message) {
        ageSubmittedPage.checkMessageText(message);
    }


    @Then("^I see error: \"(.*)\" using PO$")
    public void iSeeError(String error) {
        agePage.checkErrorMessage(error);
    }

    @And("^I remain in age page using PO$")
    public void iRemainInAgePage() {
        assert driver.getCurrentUrl().contains("age_2.html");
    }

    @When("^I enter values using PO:$")
    public void iEnterValues(Map<String, String> values) {
        agePage.enterName(values.get("name"));
        agePage.enterAge(Integer.parseInt(values.get("age")));
    }
}