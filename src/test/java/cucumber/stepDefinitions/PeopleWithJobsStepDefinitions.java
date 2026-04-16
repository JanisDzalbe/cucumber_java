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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PeopleWithJobsStepDefinitions {

    private static final String PAGE_URL =
            "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";
    private WebDriver driver;
    private WebDriverWait wait;
    private List<String> originalListState;

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

    // --- Given ---

    @Given("I am on list of people with jobs page")
    public void iAmOnListOfPeopleWithJobsPage() {
        driver.get(PAGE_URL);
        originalListState = captureListState();
    }

    // --- When: Add ---

    @When("I click Add person button")
    public void iClickAddPersonButton() {
        WebElement addButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Add person')]")
                )
        );
        addButton.click();
    }

    @When("I fill in person details:")
    public void iFillInPersonDetails(Map<String, String> details) {
        for (Map.Entry<String, String> entry : details.entrySet()) {
            WebElement field = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id(entry.getKey())
                    )
            );
            field.clear();
            field.sendKeys(entry.getValue());
        }
    }

    @When("I click Save person button")
    public void iClickSavePersonButton() {
        WebElement saveButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Save')]")
                )
        );
        saveButton.click();
    }

    // --- When: Edit ---

    @When("I click Edit button for person {string}")
    public void iClickEditButtonForPerson(String name) {
        WebElement editButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//tr[contains(.,'" + name
                                + "')]//button[contains(text(),'Edit')]"
                                + " | //li[contains(.,'" + name
                                + "')]//button[contains(text(),'Edit')]")
                )
        );
        editButton.click();
    }

    @When("I clear and fill in person details:")
    public void iClearAndFillInPersonDetails(Map<String, String> details) {
        for (Map.Entry<String, String> entry : details.entrySet()) {
            WebElement field = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id(entry.getKey())
                    )
            );
            field.clear();
            field.sendKeys(entry.getValue());
        }
    }

    // --- When: Remove ---

    @When("I click Remove button for person {string}")
    public void iClickRemoveButtonForPerson(String name) {
        WebElement removeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//tr[contains(.,'" + name
                                + "')]//button[contains(text(),'Remove')]"
                                + " | //li[contains(.,'" + name
                                + "')]//button[contains(text(),'Remove')]"
                                + " | //tr[contains(.,'" + name
                                + "')]//button[contains(text(),'Delete')]"
                                + " | //li[contains(.,'" + name
                                + "')]//button[contains(text(),'Delete')]")
                )
        );
        removeButton.click();
    }

    // --- When: Reset ---

    @When("I click Reset List button")
    public void iClickResetListButton() {
        WebElement resetButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Reset')]")
                )
        );
        resetButton.click();
    }

    // --- When: Composite step for Scenario Outline ---

    @When("I add person {string}")
    public void iAddPerson(String name) {
        iClickAddPersonButton();
        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("name"))
        );
        nameField.clear();
        nameField.sendKeys(name);

        WebElement jobField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("job"))
        );
        jobField.clear();
        jobField.sendKeys("Automation Tester");

        iClickSavePersonButton();
    }

    @When("I edit person {string}")
    public void iEditPerson(String name) {
        iClickEditButtonForPerson(name);

        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("name"))
        );
        nameField.clear();
        nameField.sendKeys(name + " Edited");

        iClickSavePersonButton();
    }

    @When("I remove person {string}")
    public void iRemovePerson(String name) {
        iClickRemoveButtonForPerson(name);
    }

    // --- Then ---

    @Then("I should see person {string} with job {string} in the list")
    public void iShouldSeePersonWithJobInTheList(String name, String job) {
        WebElement personRow = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'" + name + "') and contains(text(),'" + job + "')]"
                                + " | //tr[contains(.,'" + name + "') and contains(.,'" + job + "')]")
                )
        );
        Assert.assertTrue(
                "Person '" + name + "' with job '" + job + "' is not visible",
                personRow.isDisplayed()
        );
    }

    @Then("I should not see person {string} in the list")
    public void iShouldNotSeePersonInTheList(String name) {
        List<WebElement> matches = driver.findElements(
                By.xpath("//tr[contains(.,'" + name + "')] | //li[contains(.,'" + name + "')]")
        );
        Assert.assertTrue(
                "Person '" + name + "' should not be in the list but was found",
                matches.isEmpty()
        );
    }

    @Then("the list should be restored to original state")
    public void theListShouldBeRestoredToOriginalState() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//tr | //li"), 0
        ));
        List<String> currentState = captureListState();
        Assert.assertEquals(
                "List was not restored to original state",
                originalListState,
                currentState
        );
    }

    // --- Helper ---

    private List<String> captureListState() {
        List<WebElement> rows = driver.findElements(
                By.xpath("//table//tr[td] | //ul//li")
        );
        return rows.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}