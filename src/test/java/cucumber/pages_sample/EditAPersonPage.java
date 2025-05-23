package cucumber.pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class EditAPersonPage {

    @FindBy(how = How.ID, using = "name")
    private WebElement name;
    @FindBy(how = How.ID, using = "job")
    private WebElement job;
    @FindBy(how = How.XPATH, using = "(//button[text()='Edit'])")
    private WebElement editButton;
    @FindBy(how = How.ID, using = "(//button[text()='Cancel'])")
    private WebElement cancelButton;
    @FindBy(how = How.ID, using = "(//button[text()='Clear all fields'])")
    private WebElement clearButton;

    public String getPageUrl (int pageId) {
        return "https://acctabootcamp.github.io/site/tasks/enter_a_new_person_with_a_job.html?id=0";
    }

    public void clickEditButton () {
        editButton.click();
    }

    public void clickCancelButton () {
        cancelButton.click();
    }

    public void clickClearButton () {
        clearButton.click();
    }

    public void editName (String name) {
        this.name.clear();
        this.name.sendKeys(name);
    }

    public void editJob (String job) {
        this.job.clear();
        this.job.sendKeys(job);
    }
}
