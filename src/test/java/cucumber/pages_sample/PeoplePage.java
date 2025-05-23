package cucumber.pages_sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.cucumber.datatable.DataTable;

import java.util.List;

import static org.junit.Assert.*;

public class PeoplePage {
    @FindBy(xpath = "//*[contains(@onclick, 'AddPerson')]")
    private WebElement addPersonButton;
    @FindBy(xpath = "//*[contains(@onclick, 'reset')]")
    private WebElement resetButton;
    @FindBy(xpath = "//*[contains(@class,'name')]")
    List<WebElement> names;
    @FindBy(xpath = "//*[contains(@class,'job')]")
    List<WebElement> jobs;
    @FindBy(xpath = "//*[contains(@onclick, 'Edit')]")
    List<WebElement> editButtons;
    @FindBy(xpath = "//*[contains(@onclick, 'delete')]")
    List<WebElement> deleteButtons;


    public void clickAddPersonButton() {
        addPersonButton.click();
    }

    public void checkPersonIsOnList(String name, String job) {
        boolean found = false;

        assertEquals(jobs.size(), names.size());

        for(int i = 0; i < names.size(); i++) {
            String personName = names.get(i).getText();
            String personJob = jobs.get(i).getText();

            if(personName.equals(name) && personJob.equals(job)) {
                found = true;
                break;
            }
        }

        assertTrue("Person not found", found);
    }

    public void clickEditButton(String name, String job) {

        assertEquals(jobs.size(), names.size());

        for(int i = 0; i < names.size(); i++) {
            String personName = names.get(i).getText();
            String personJob = jobs.get(i).getText();
            if(personName.equals(name) && personJob.equals(job)) {
                editButtons.get(i).click();
                break;
            }
        }
    }

    public void checkPersonNotOnList(String name, String job) {
        boolean found = false;

        assertEquals(jobs.size(), names.size());

        for(int i = 0; i < names.size(); i++) {
            String personName = names.get(i).getText();
            String personJob = jobs.get(i).getText();

            if(personName.equals(name) && personJob.equals(job)) {
                found = true;
                break;
            }
        }

        assertFalse(found);
    }

    public void clickDeleteButton(String name, String job) {
        assertEquals(jobs.size(), names.size());

        for(int i = 0; i < names.size(); i++) {
            String personName = names.get(i).getText();
            String personJob = jobs.get(i).getText();

            if(personName.equals(name) && personJob.equals(job)) {
                deleteButtons.get(i).click();
                break;
            }
        }
    }

    public void clickResetButton() {
        resetButton.click();
    }

    public void checkThatListIsReset() {
        System.out.println("Names: " + names.size());
        assertEquals("Reset list has more than 3 names",3, names.size());
        assertEquals("Reset list has more than 3 jobs",3, jobs.size());

        assertEquals("Mike", names.get(0).getText());
        assertEquals("Web Designer", jobs.get(0).getText());
        assertEquals("Jill", names.get(1).getText());
        assertEquals("Support", jobs.get(1).getText());
        assertEquals("Jane", names.get(2).getText());
        assertEquals("Accountant", jobs.get(2).getText());
    }
}
