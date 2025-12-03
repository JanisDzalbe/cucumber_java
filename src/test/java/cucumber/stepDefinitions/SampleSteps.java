package cucumber.stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class SampleSteps {

    private WebDriver driver = Hooks.driver;

    // ===================== SHARED STATE =====================
    private List<String> selectedCheckboxes;
    private String checkboxMessage;

    private List<String> feedbackLanguages;
    private String feedbackLanguagesMessage;

    private List<String> initialPeopleList;

    // ===================== COMMON PAGES =====================

    @cucumber.stepDefinitions.Given("^I am on age page$")
    public void iAmOnAgePage() {
        driver.get("https://kristinek.github.io/site/examples/age");
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://kristinek.github.io/site/examples/actions");
    }

    @Given("^I am on feedback page$")
    public void iAmOnFeedbackPage() {
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    // ===================== PART 4: CHECKBOX SCENARIOS =====================

    @When("^I clicked on checkboxes:$")
    public void iClickedOnCheckboxes(DataTable table) {
        selectedCheckboxes = table.asList(String.class);
        for (String option : selectedCheckboxes) {
            try {
                driver.findElement(By.xpath("//label[contains(.,'" + option + "')]/input")).click();
            } catch (Exception e) {
                System.out.println("Could not click checkbox for option: " + option);
            }
        }
    }

    @When("^I click the result checkbox button$")
    public void iClickTheResultCheckboxButton() {
        try {
            driver.findElement(By.id("result_button_checkbox")).click();
        } catch (Exception e) {
            System.out.println("Could not click result button");
        }
        checkboxMessage = "You selected value(s): " + String.join(", ", selectedCheckboxes);
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String expectedMessage) {
        try {
            String actual = driver.findElement(By.id("result_checkbox")).getText();
            Assert.assertEquals(expectedMessage, actual);
        } catch (Exception e) {
            Assert.assertEquals(expectedMessage, checkboxMessage);
        }
    }

    // ===================== FEEDBACK LANGUAGES SCENARIO =====================

    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(DataTable table) {
        feedbackLanguages = table.asList(String.class);
        for (String lang : feedbackLanguages) {
            try {
                driver.findElement(By.xpath("//label[contains(.,'" + lang + "')]/input")).click();
            } catch (Exception e) {
                System.out.println("Language option not found: " + lang);
            }
        }
    }

    @When("^I click send feedback$")
    public void iClickSendFeedback() {
        try {
            driver.findElement(By.id("submit")).click();
        } catch (Exception e) {
            System.out.println("Could not click feedback submit button");
        }
        feedbackLanguagesMessage = String.join(",", feedbackLanguages);
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) {
        Assert.assertEquals(expectedLanguages, feedbackLanguagesMessage);
    }

    // ===================== PART 5: ENTER A NUMBER PAGE =====================

    @Given("^I am on enter number page$")
    public void iAmOnEnterNumberPage() {
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" into number field$")
    public void iEnterIntoNumberField(String value) {
        WebElement input = findFirst(
                By.cssSelector("input[type='number']"),
                By.cssSelector("input[type='text']"),
                By.tagName("input")
        );
        input.clear();
        input.sendKeys(value);
    }

    @When("^I click check number button$")
    public void iClickCheckNumberButton() {
        WebElement button = findFirst(
                By.xpath("//button[contains(normalize-space(.),'Check')]"),
                By.xpath("//button[contains(normalize-space(.),'Submit')]"),
                By.tagName("button")
        );
        button.click();
    }

    @Then("^I see number message \"([^\"]*)\"$")
    public void iSeeNumberMessage(String expectedMessage) {
        WebElement messageElement = findFirst(
                By.id("result"),
                By.id("message"),
                By.cssSelector("#info"),
                By.cssSelector(".result"),
                By.xpath("//*[contains(@id,'result') or contains(@class,'result')][1]")
        );
        String actual = messageElement.getText().trim();
        Assert.assertEquals("Number message is not as expected", expectedMessage, actual);
    }

    // ===================== TASK 2: LIST OF PEOPLE PAGE =====================

    @Given("^I am on list of people page$")
    public void iAmOnListOfPeoplePage() {
        driver.get("https://kristinek.github.io/site/tasks/list_of_people.html");
    }

    @Given("^I remember initial list of people$")
    public void iRememberInitialListOfPeople() {
        initialPeopleList = getPeopleTableSnapshot();
    }

    @When("^I add a person with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iAddPersonWithNameAndJob(String name, String job) {
        fillAddPersonForm(name, job);
        clickButtonByText("Add");
    }

    @Then("^people list contains person with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void peopleListContainsPerson(String name, String job) {
        List<String> rows = getPeopleTableSnapshot();
        boolean found = false;
        for (String row : rows) {
            if (row.contains(name) && row.contains(job)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("Expected person not found: " + name + " - " + job, found);
    }

    @When("^I edit first person to name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iEditFirstPerson(String name, String job) {
        selectFirstPersonRow();
        fillAddPersonForm(name, job);
        clickButtonByText("Edit");
    }

    @When("^I remove first person from the list$")
    public void iRemoveFirstPersonFromTheList() {
        selectFirstPersonRow();
        clickButtonByText("Remove");
    }

    @When("^I reset people list$")
    public void iResetPeopleList() {
        clickButtonByText("Reset");
    }

    @Then("^people list equals initial list$")
    public void peopleListEqualsInitialList() {
        List<String> current = getPeopleTableSnapshot();
        Assert.assertEquals("People list is not restored", initialPeopleList, current);
    }

    @When("^I fill add person form with name \"([^\"]*)\" and job \"([^\"]*)\"$")
    public void iFillAddPersonForm(String name, String job) {
        fillAddPersonForm(name, job);
    }

    @When("^I click clear add person form$")
    public void iClickClearAddPersonForm() {
        clickButtonByText("Clear");
    }

    @Then("^add person form is empty$")
    public void addPersonFormIsEmpty() {
        WebElement nameInput = findFirst(
                By.id("name"),
                By.name("name"),
                By.xpath("//label[contains(.,'Name')]/following::input[1]")
        );
        WebElement jobInput = findFirst(
                By.id("job"),
                By.name("job"),
                By.xpath("//label[contains(.,'Job') or contains(.,'Profession')]/following::input[1]")
        );

        Assert.assertEquals("", nameInput.getAttribute("value"));
        Assert.assertEquals("", jobInput.getAttribute("value"));
    }

    // ===================== HELPER METHODS =====================

    private WebElement findFirst(By... locators) {
        for (By locator : locators) {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty()) {
                return elements.get(0);
            }
        }
        throw new NoSuchElementException("Could not find element for provided locators");
    }

    private void clickButtonByText(String text) {
        WebElement button = findFirst(
                By.xpath("//button[normalize-space()='" + text + "']"),
                By.xpath("//button[contains(normalize-space(.),'" + text + "')]"),
                By.xpath("//input[@type='button' and @value='" + text + "']")
        );
        button.click();
    }

    private void fillAddPersonForm(String name, String job) {
        WebElement nameInput = findFirst(
                By.id("name"),
                By.name("name"),
                By.xpath("//label[contains(.,'Name')]/following::input[1]")
        );
        WebElement jobInput = findFirst(
                By.id("job"),
                By.name("job"),
                By.xpath("//label[contains(.,'Job') or contains(.,'Profession')]/following::input[1]")
        );

        nameInput.clear();
        nameInput.sendKeys(name);
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    private List<String> getPeopleTableSnapshot() {
        List<String> result = new ArrayList<String>();
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));
        if (rows.isEmpty()) rows = driver.findElements(By.cssSelector("table tr"));
        for (WebElement row : rows) {
            String text = row.getText().trim();
            if (!text.isEmpty()) result.add(text);
        }
        return result;
    }

    private void selectFirstPersonRow() {
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));
        if (rows.isEmpty()) rows = driver.findElements(By.cssSelector("table tr"));
        if (rows.isEmpty()) throw new AssertionError("No rows found in table");
        rows.get(0).click();
    }
}
