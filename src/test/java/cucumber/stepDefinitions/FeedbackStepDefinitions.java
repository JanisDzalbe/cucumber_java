package cucumber.stepDefinitions;
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

public class FeedbackStepDefinitions {

    private static final String BASE_URL = "https://janisdzalbe.github.io/example-site";
    private static final String FEEDBACK_URL = BASE_URL + "/tasks/provide_feedback";
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

    // --- Background / Given ---

    @Given("I am on provide feedback page")
    public void iAmOnProvideFeedbackPage() {
        driver.get(FEEDBACK_URL);
    }

    // --- When steps ---

    @When("I select language: {string}")
    public void iSelectLanguage(String language) {
        WebElement languageOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@type='radio' and @name='language' and @value='"
                                + language + "']")
                )
        );
        languageOption.click();
    }

    @When("I select genre: {string}")
    public void iSelectGenre(String genre) {
        WebElement genreOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@type='radio' and @name='genre' and @value='"
                                + genre + "']")
                )
        );
        genreOption.click();
    }

    @When("I select rating: {string}")
    public void iSelectRating(String rating) {
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("rating"))
        );
        Select select = new Select(dropdown);
        select.selectByVisibleText(rating);
    }

    @When("I enter comment: {string}")
    public void iEnterComment(String comment) {
        WebElement commentField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("comment"))
        );
        commentField.clear();
        commentField.sendKeys(comment);
    }

    @When("I click Send button")
    public void iClickSendButton() {
        WebElement sendButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Send'] | //input[@value='Send']")
                )
        );
        sendButton.click();
    }

    // --- Then steps: verify submitted values are displayed ---

    @Then("I see submitted language: {string}")
    public void iSeeSubmittedLanguage(String expectedLanguage) {
        WebElement languageResult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-language"))
        );
        Assert.assertEquals(expectedLanguage, languageResult.getText());
    }

    @Then("I see submitted genre: {string}")
    public void iSeeSubmittedGenre(String expectedGenre) {
        WebElement genreResult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-genre"))
        );
        Assert.assertEquals(expectedGenre, genreResult.getText());
    }

    @Then("I see submitted rating: {string}")
    public void iSeeSubmittedRating(String expectedRating) {
        WebElement ratingResult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-rating"))
        );
        Assert.assertEquals(expectedRating, ratingResult.getText());
    }

    @Then("I see submitted comment: {string}")
    public void iSeeSubmittedComment(String expectedComment) {
        WebElement commentResult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("submitted-comment"))
        );
        Assert.assertEquals(expectedComment, commentResult.getText());
    }
}