import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;
import java.util.List;

public class FeedbackLanguagesStepDefinitions {

    private static final String BASE_URL = "https://janisdzalbe.github.io/example-site";
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // --- Background ---

    @Given("I am on age page")
    public void iAmOnAgePage() {
        driver.get(BASE_URL + "/examples/age");
    }

    // --- Existing action page steps ---

    @Given("I am on action page")
    public void iAmOnActionPage() {
        driver.get(BASE_URL + "/examples/actions");
    }

    @When("I clicked on checkboxes:")
    public void iClickedOnCheckboxes(List<String> checkboxLabels) {
        for (String label : checkboxLabels) {
            WebElement checkbox = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//label[contains(text(),'" + label
                                    + "')]/input[@type='checkbox'] | //input[@type='checkbox' and @value='"
                                    + label + "']")
                    )
            );
            checkbox.click();
        }
    }

    @And("I click the result checkbox button")
    public void iClickTheResultCheckboxButton() {
        WebElement resultButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Result')] | //input[@type='button' and contains(@value,'Result')]")
                )
        );
        resultButton.click();
    }

    @Then("message for checkboxes {string} is seen")
    public void messageForCheckboxesIsSeen(String expectedMessage) {
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("result"))
        );
        Assert.assertEquals(expectedMessage, message.getText());
    }

    // --- New feedback page steps ---

    @Given("I am on feedback page")
    public void iAmOnFeedbackPage() {
        driver.get(BASE_URL + "/tasks/provide_feedback");
    }

    @When("I select feedback languages")
    public void iSelectFeedbackLanguages(List<String> languages) {
        for (String language : languages) {
            WebElement langCheckbox = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//label[contains(text(),'" + language
                                    + "')]/input[@type='checkbox'] | //input[@type='checkbox' and @value='"
                                    + language + "']")
                    )
            );
            langCheckbox.click();
        }
    }

    @And("I click send feedback")
    public void iClickSendFeedback() {
        WebElement sendButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Send'] | //input[@value='Send']")
                )
        );
        sendButton.click();
    }

    @Then("I can see languages {string} in feedback check")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) {
        WebElement languageResult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-language"))
        );
        Assert.assertEquals(expectedLanguages, languageResult.getText());
    }
}