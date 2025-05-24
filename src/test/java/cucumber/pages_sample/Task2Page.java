package cucumber.pages_sample;

import cucumber.stepDefinitions.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static org.junit.Assert.*;

public class Task2Page {

    @FindBy(how = How.CSS, using = "ul#listOfPeople li")
    private List<WebElement> peopleItems;

    @FindBy(how = How.ID, using = "addPersonBtn")
    private WebElement addPersonButton;

    @FindBy(how = How.CSS, using = "ul#listOfPeople span.editbtn")
    private List<WebElement> editButtons;

    @FindBy(how = How.CSS, using = "button[onclick='resetListOfPeople()']")
    private WebElement resetListButton;

    public static final String ADD_EDIT_PAGE_BASE = "https://acctabootcamp.github.io/site/tasks/enter_a_new_person_with_a_job.html";

    public Task2Page() {
    }

    public String getPageUrl() {
        return "https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs";
    }

    public void clickAddPersonButton() {
        addPersonButton.click();
    }

    public void clickResetListButton() {
        resetListButton.click();
    }

    public void assertPersonExists(String expectedName, String expectedJob) {
        for (WebElement item : peopleItems) {
            String nameText = item.findElement(By.cssSelector("span.name")).getText().trim();
            String jobText  = item.findElement(By.cssSelector("span.job")).getText().trim();
            if (expectedName.equals(nameText) && expectedJob.equals(jobText)) {
                break;
            }
        }
    }
    public void clickEditPerson(String expectedName, String expectedJob) {
        for (WebElement item : peopleItems) {
            String nameText = item.findElement(By.cssSelector("span.name")).getText().trim();
            String jobText  = item.findElement(By.cssSelector("span.job")).getText().trim();
            if (expectedName.equals(nameText) && expectedJob.equals(jobText)) {
                item.findElement(By.cssSelector("span.editbtn")).click();
                break;
            }
        }
    }
    public void clickRemovePerson(String expectedName, String expectedJob) {
        for (WebElement item : peopleItems) {
            String nameText = item.findElement(By.cssSelector("span.name")).getText().trim();
            String jobText  = item.findElement(By.cssSelector("span.job")).getText().trim();
            if (expectedName.equals(nameText) && expectedJob.equals(jobText)) {
                item.findElement(By.cssSelector("span.closebtn")).click();
                break;
            }
        }
    }
    public void assertPersonNotExists(String expectedName, String expectedJob) {
        for (WebElement item : peopleItems) {
            String nameText = item.findElement(By.cssSelector("span.name")).getText().trim();
            String jobText  = item.findElement(By.cssSelector("span.job")).getText().trim();
            if (expectedName.equals(nameText) && expectedJob.equals(jobText)) {
                break;
            }
        }
    }

    public void assertDefaultList() {
        assertEquals(3, peopleItems.size());

        WebElement first = peopleItems.get(0);
        assertEquals("Mike", first.findElement(By.cssSelector("span.name")).getText().trim());
        assertEquals("Web Designer", first.findElement(By.cssSelector("span.job")).getText().trim());

        WebElement second = peopleItems.get(1);
        assertEquals("Jill", second.findElement(By.cssSelector("span.name")).getText().trim());
        assertEquals("Support", second.findElement(By.cssSelector("span.job")).getText().trim());

        WebElement third = peopleItems.get(2);
        assertEquals("Jane", third.findElement(By.cssSelector("span.name")).getText().trim());
        assertEquals("Accountant", third.findElement(By.cssSelector("span.job")).getText().trim());
    }

}
