package cucumber.pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnterANewPersonPage {

    @FindBy(how = How.ID, using = "name")
    private WebElement name;
    @FindBy(how = How.ID, using = "job")
    private WebElement job;
    @FindBy(how = How.XPATH, using = "(//button[text()='Add'])")
    private WebElement addButton;
    @FindBy(how = How.XPATH, using = "(//button[text()='Cancel'])")
    private WebElement cancelButton;
    @FindBy(how = How.XPATH, using = "(//button[text()='Clear all fields'])")
    private WebElement clearButton;

    public String getPageUrl () {
        return "https://acctabootcamp.github.io/site/tasks/enter_a_new_person_with_a_job.html";
    }

    public void clickAddButton () {
        addButton.click();
    }

    public void clickCancelButton () {
        cancelButton.click();
    }

    public void clickClearButton () {
        clearButton.click();
    }

    public void inputName (String name) {
        this.name.sendKeys(name);
    }

    public void inputJob (String job) {
        this.job.sendKeys(job);
    }

    public void checkNameForEmptyTextField() {
        assertTrue(name.getText().isEmpty());
    }

    public void checkJobForEmptyTextField() {
        assertTrue(job.getText().isEmpty());
    }
}
