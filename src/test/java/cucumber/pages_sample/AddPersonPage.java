package cucumber.pages_sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertTrue;

public class AddPersonPage {
    WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "job")
    private WebElement jobInput;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//button[text()='Edit']")
    private WebElement editButton;

    @FindBy(xpath = "//button[text()='Clear all fields']")
    private WebElement clearButton;


    public void enterNameAndJob(String name, String job) {
        nameInput.clear();
        nameInput.sendKeys(name);
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public void clickAdd() {
        addButton.click();
    }

    public void enterJob(String job){
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public void clickEditButton(){
        editButton.click();
    }

    public void clickClearButton(){
        clearButton.click();
    }

    public void checkNameAndJobAreEmpty() {
        assertTrue("Name input is not empty!", nameInput.getAttribute("value").isEmpty());
        assertTrue("Job input is not empty!", jobInput.getAttribute("value").isEmpty());
    }
}
