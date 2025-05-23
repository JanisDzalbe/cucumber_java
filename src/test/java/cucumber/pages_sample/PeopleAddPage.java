package cucumber.pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;

public class PeopleAddPage {
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "job")
    private WebElement job;
    @FindBy(xpath = "//*[contains(@onclick, 'addPerson')]")
    private WebElement addButton;
    @FindBy(xpath = "//*[contains(@onclick, 'editPerson')]")
    private WebElement editButton;
    @FindBy(xpath = "//*[contains(text(), 'Clear all fields')]")
    private WebElement clearButton;

    public void checkName(String name) {
        assertEquals(this.name.getAttribute("value"), name);
    }

    public void enterName(String name) {
        this.name.clear();
        this.name.sendKeys(name);
        checkName(name);
    }

    public void checkJob(String job) {
        assertEquals(this.job.getAttribute("value"), job);
    }

    public void enterJob(String job) {
        this.job.clear();
        this.job.sendKeys(job);
        checkJob(job);
    }

    public void clickModalButton() {
        addButton.click();
    }

    public void clickEditButton() {
        editButton.click();
    }

    public void clickClearButton() {
        clearButton.click();
    }



}
