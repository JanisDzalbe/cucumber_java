package cucumber.pages_sample;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PeoplePage {
    WebDriver driver;

    @FindBy(xpath = "//button[text()='Add person']")
    private WebElement addPersonButton;

    @FindBy(xpath = "//button[text()='Reset List']")
    private WebElement resetListButton;

    @FindBy(id = "listOfPeople")
    private WebElement listOfPeople;

    @FindBy(css = "#listOfPeople li:nth-of-type(1) .editbtn") // By.className("error)
    private WebElement editMike;

    @FindBy(css = "#listOfPeople li:nth-of-type(1) .closebtn") // By.className("error)
    private WebElement removeMike;

    public PeoplePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void open() {
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html");
    }

    public void clickAddPerson() {
        addPersonButton.click();
    }

    public void clickResetListButton() {
        resetListButton.click();
    }

    public void checkPersonExists(String name, String job) {
        boolean found = listOfPeople.findElements(By.tagName("li")).stream()
                .anyMatch(li -> {
                    String personName = li.findElement(By.className("name")). getText();
                    String personJob = li.findElement(By.className("job")). getText();
                    return personName.equals(name) && personJob.equals(job);
                });
        assertTrue("Person with name '" + name + "' and job '" + job + "' not found in the list!", found);
    }

    public void clickEditMike() {
        editMike.click();
    }
    public void checkMike(String expectedJob) {
        WebElement mikeJob = driver.findElement(By.xpath("//li[.//span[text()='Mike']]//span[@class='job']"));
        String actualJob = mikeJob.getText();
        assertEquals("Wrong job title", expectedJob, actualJob);
    }
    public void clickRemoveMike() {
        removeMike.click();
    }

    public boolean isPersonPresent(String name) {
        List<WebElement> names = driver.findElements(By.cssSelector("span.w3-xlarge.name"));
        return names.stream().anyMatch(el -> el.getText().equals(name));
    }

    public void checkPeopleCount(int expectedCount) {
        int actualCount = listOfPeople.findElements(By.tagName("li")).size();
        assertEquals("Unexpected number of people in the list!", expectedCount, actualCount);
    }

}
