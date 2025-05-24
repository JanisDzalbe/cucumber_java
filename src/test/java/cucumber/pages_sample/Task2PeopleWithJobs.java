package cucumber.pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static org.junit.Assert.*;

public class Task2PeopleWithJobs {
    @FindBy(how = How.CSS, using = "div.w3-btn-group > button")
    private List<WebElement> buttons;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Clear all fields']")
    private WebElement clearFieldsButton;

    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "job")
    private WebElement jobInput;

    public static final String ADD_PAGE_BASE = "https://acctabootcamp.github.io/site/tasks/enter_a_new_person_with_a_job.html";

    public Task2PeopleWithJobs() {
    }

    public String getPageUrl() {
        return "https://acctabootcamp.github.io/site/tasks/enter_a_new_person_with_a_job.html";
    }

    public void clickAddButton() {
        buttons.get(0).click();
    }

    public void clickCancelButton() {
        buttons.get(1).click();
    }

    public void clickEditButton() {
        buttons.get(0).click();
    }

    public void clickClearFields() {
        clearFieldsButton.click();
    }

    public void assertFieldsAreEmpty() {
        assertEquals("", nameInput.getAttribute("value"));
        assertEquals("", jobInput.getAttribute("value"));
    }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterJob(String job) {
        jobInput.clear();
        jobInput.sendKeys(job);
    }
}
