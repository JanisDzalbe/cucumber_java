import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import java.util.List;

public class LocatorsStepDefinitions {

    private static final String BASE_URL = "https://janisdzalbe.github.io/example-site";
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // --- Steps for Scenario 1: Simple scenario ---

    @When("I am on the home page")
    public void iAmOnTheHomePage() {
        driver.get(BASE_URL);
    }

    @Then("I should see home page header")
    public void iShouldSeeHomePageHeader() {
        WebElement header = driver.findElement(By.tagName("h1"));
        Assert.assertTrue("Home page header is not displayed", header.isDisplayed());
    }

    @And("I should see home page description")
    public void iShouldSeeHomePageDescription() {
        WebElement description = driver.findElement(By.tagName("p"));
        Assert.assertTrue("Home page description is not displayed", description.isDisplayed());
    }

    @And("I should see menu")
    public void iShouldSeeMenu() {
        WebElement menu = driver.findElement(By.tagName("nav"));
        Assert.assertTrue("Menu is not displayed", menu.isDisplayed());
    }

    // --- Steps for Scenario 2: New Simple scenario ---

    @When("I am on the locators page")
    public void iAmOnTheLocatorsPage() {
        driver.get(BASE_URL + "/examples/locators");
    }

    @Then("I should see both locators page headers")
    public void iShouldSeeBothLocatorsPageHeaders() {
        WebElement heading1 = driver.findElement(By.xpath("//h1[contains(text(),'Heading 1')]"));
        WebElement heading2 = driver.findElement(By.xpath("//h2[contains(text(),'Heading 2')]"));
        Assert.assertTrue("Heading 1 is not displayed", heading1.isDisplayed());
        Assert.assertTrue("Heading 2 is not displayed", heading2.isDisplayed());
    }

    @And("Buttons in Locators page are clickable")
    public void buttonsInLocatorsPageAreClickable() {
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        Assert.assertFalse("No buttons found on the locators page", buttons.isEmpty());
        for (WebElement button : buttons) {
            Assert.assertTrue("Button '" + button.getText() + "' is not enabled", button.isEnabled());
            Assert.assertTrue("Button '" + button.getText() + "' is not displayed", button.isDisplayed());
        }
    }
}