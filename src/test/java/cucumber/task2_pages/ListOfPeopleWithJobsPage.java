package cucumber.task2_pages;

import cucumber.stepDefinitions.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfPeopleWithJobsPage {
    WebDriver driver;
    private String pageListOfPeopleWithJobs = "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";
    public ListOfPeopleWithJobsPage() {

        this.driver = Hooks.driver;
        PageFactory.initElements(driver, this);
    }
    private int initialSize;
    private List<String> originalPeopleSnapshot;

    @FindBy(how = How.XPATH, using = "//*[@id='addPersonBtn' and contains(text(),'Add person')]")
    private WebElement addPersonButton;
    @FindBy(how = How.XPATH, using = "//*[@id='addPersonBtn' and contains(text(),'Reset List')]")
    private WebElement resetListButton;
    @FindBy(how = How.ID, using = "modal_button")
    private WebElement addButton;
    @FindBy(how = How.CSS, using = ".w3-input[id='name']")
    private WebElement nameInput;
    @FindBy(how = How.CSS, using = ".w3-input[id='job']")
    private WebElement jobInput;

    public String getPageUrl() {
        return pageListOfPeopleWithJobs;
    }

    public void clickAddPerson() {
        addPersonButton.click();
    }

    public void clickResetList() {
        resetListButton.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterJob(String job) {
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public void assertNameFieldEmpty() {
        assertEquals("", nameInput.getAttribute("value"));
    }

    public void assertJobFieldEmpty() {
        assertEquals("", jobInput.getAttribute("value"));
    }

    public void assertNameFieldFilled(String name) {
        assertEquals(name, nameInput.getAttribute("value"));
    }

    public void assertJobFieldFilled(String job) {
        assertEquals(job, jobInput.getAttribute("value"));
    }

    public void assertPersonInList(String name, String job, boolean shouldExist) {
        List<WebElement> people = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        boolean found = false;

        for (WebElement person : people) {
            String personName = person.findElement(By.xpath("./span[contains(@class,'name')]")).getText();
            if (personName.equals(name)) {
                String personJob = person.findElement(By.xpath(".//span[@class='job']")).getText();
                if (personJob.equals(job)) {
                   found = true;
                   break;
                }
            }
        }

        if(shouldExist) {
            assertTrue(found);
        } else {
            assertFalse(found);
        }
    }

    public void storePeopleCount() {
        initialSize = driver.findElements(
                By.xpath("//li[contains(@id,'person')]")
        ).size();
    }

    public void captureDefaultListState() {
        List<WebElement> people = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        originalPeopleSnapshot = new java.util.ArrayList<>();
        for (WebElement person : people) {
            String name = person.findElement(By.xpath(".//span[contains(@class,'name')]")).getText();
            String job = person.findElement(By.xpath(".//span[contains(@class,'job')]")).getText();
            originalPeopleSnapshot.add(name + "," + job);
        }
    }

    public List<String> getOriginalSnapshot() {
        return originalPeopleSnapshot;
    }

    public void assertListIncreasedBy(int expectedIncrease) {

        int newSize = driver.findElements(
                By.xpath("//li[contains(@id,'person')]")
        ).size();

        assertEquals(initialSize + expectedIncrease, newSize);
    }

    public void assertListDecreasedBy(int expectedDecrease) {

        int newSize = driver.findElements(
                By.xpath("//li[contains(@id,'person')]")
        ).size();

        assertEquals(initialSize - expectedDecrease, newSize);
    }
}