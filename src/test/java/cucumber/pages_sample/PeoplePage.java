package cucumber.pages_sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PeoplePage {
    private final WebDriver driver;

    // PageFactory finds this WebDriver constructor and injects the driver
    public PeoplePage(WebDriver driver) {
        this.driver = driver;
    }

    // ------------ Buttons ------------------
    @FindBy(id = "addPersonBtn")
    private WebElement addPersonButton;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "job")
    private WebElement jobInput;

    // Confirm-add button (no id)
    @FindBy(xpath = "//*[@onclick='addPersonWithJobToList()']")
    private WebElement confirmAddButton;

    // Confirm-edit button
    @FindBy(id = "modal_button")
    private WebElement confirmEditButton;

    // Reset the list
    @FindBy(xpath = "//*[@onclick='resetListOfPeople()']")
    private WebElement resetButton;

    // ------------ Actions ----------------

    // Our website
    public String getPageUrl() {
        return "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";
    }

    // Clear fields first, because in edit mode they are prefilled with old values
    private void fillForm(String name, String job) {
        nameInput.clear();
        nameInput.sendKeys(name);
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    // Click "Add person", fill the fields and confirm
    public void addPerson(String name, String job) {
        addPersonButton.click();
        fillForm(name, job);
        confirmAddButton.click();
    }

    // Rename a person keeping the job & XPath finds the edit icon in that person's row
    public void renamePerson(String oldName, String newName) {
        driver.findElement(By.xpath(
                "//li[span[contains(@class,'name') and text()='" + oldName +
                "']]//span[contains(@class,'editbtn')]")).click();
        nameInput.clear();
        nameInput.sendKeys(newName);
        confirmEditButton.click();
    }

    // Remove a person & XPath finds the close icon in that person's row
    public void removePerson(String name) {
        driver.findElement(By.xpath(
                "//li[span[contains(@class,'name') and text()='" + name +
                "']]//span[contains(@class,'closebtn')]"
        )).click();
    }

    // Reset List
    public void resetList() {
        resetButton.click();
    }

    // Whole list as a Map "name -> job" (the current arrangements remain in place)
    // used for assertions and snapshots.
    public Map<String, String> getPeople() {
        Map<String, String> people = new LinkedHashMap<>();
        List<WebElement> rows = driver.findElements(By.cssSelector("#listOfPeople li"));
        for (WebElement row : rows) {
            String name = row.findElement(By.className("name")).getText();
            String job = row.findElement(By.className("job")).getText();
            people.put(name, job);
        }
        return people;
    }
}