package cucumber.pages_sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class PeopleWithJobsPage {

    private final WebDriver driver;

    private static final String PAGE_URL =
            "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs";

    public PeopleWithJobsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageUrl() {
        return PAGE_URL;
    }

    private List<String> getNames() {
        List<WebElement> nameEls = driver.findElements(By.cssSelector("span.name"));
        List<String> names = new ArrayList<>();
        for (WebElement el : nameEls) {
            names.add(el.getText().trim());
        }
        return names;
    }

    private List<String> getJobs() {
        List<WebElement> jobEls = driver.findElements(By.cssSelector("span.job"));
        List<String> jobs = new ArrayList<>();
        for (WebElement el : jobEls) {
            jobs.add(el.getText().trim());
        }
        return jobs;
    }

    public void checkOriginalListPresent() {
        WebElement addPersonButton = driver.findElement(By.xpath("//button[normalize-space()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[normalize-space()='Reset List']"));

        assertTrue("Add person button must be displayed", addPersonButton.isDisplayed());
        assertTrue("Add person button must be enabled", addPersonButton.isEnabled());
        assertTrue("Reset List button must be displayed", resetListButton.isDisplayed());
        assertTrue("Reset List button must be enabled", resetListButton.isEnabled());

        List<String> actualNames = getNames();
        List<String> actualJobs = getJobs();

        assertEquals("Must be 10 names", 10, actualNames.size());
        assertEquals("Must be 10 occupations", 10, actualJobs.size());

        String[] expectedNames = {
                "Mike",
                "Jill",
                "Jane",
                "John",
                "Sarah",
                "Carlos",
                "Emily",
                "David",
                "Maria",
                "Alex"
        };

        String[] expectedJobs = {
                "Web Designer",
                "Support",
                "Accountant",
                "Software Engineer",
                "Product Manager",
                "Data Analyst",
                "UX Designer",
                "Project Manager",
                "QA Engineer",
                "DevOps Engineer"
        };

        for (int i = 0; i < expectedNames.length; i++) {
            assertEquals("Name in row " + i, expectedNames[i], actualNames.get(i));
            assertEquals("Occupation in row " + i, expectedJobs[i], actualJobs.get(i));
        }
    }

    public void clickAddPerson() {
        WebElement addPersonButton = driver.findElement(By.xpath("//button[normalize-space()='Add person']"));
        addPersonButton.click();
    }

    private List<WebElement> getInputFields() {
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        assertTrue("At least 2 input fields required", inputs.size() >= 2);
        return inputs;
    }

    public void fillPersonName(String name) {
        List<WebElement> inputs = getInputFields();
        WebElement nameInput = inputs.get(0);
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void fillPersonJob(String job) {
        List<WebElement> inputs = getInputFields();
        WebElement jobInput = inputs.get(1);
        jobInput.clear();
        jobInput.sendKeys(job);
    }
    public void savePerson() {
        List<WebElement> addButtons = driver.findElements(By.xpath("//button[normalize-space()='Add']"));
        for (WebElement btn : addButtons) {
            if (btn.isDisplayed() && btn.isEnabled()) {
                btn.click();
                return;
            }
        }


        List<WebElement> editButtons = driver.findElements(By.xpath("//button[normalize-space()='Edit']"));
        for (WebElement btn : editButtons) {
            if (btn.isDisplayed() && btn.isEnabled()) {
                btn.click();
                return;
            }
        }

        throw new IllegalStateException("Neither 'Add' nor 'Edit' button is available to save person");
    }

    public void clickEditForPerson(String name) {
        List<String> names = getNames();
        int index = names.indexOf(name);
        assertTrue("Person with name '" + name + "' must exist", index >= 0);

        List<WebElement> editButtons = driver.findElements(By.cssSelector("span.editbtn"));
        assertTrue("There must be edit buttons", editButtons.size() > index);

        WebElement editBtn = editButtons.get(index);
        editBtn.click();
    }

    public void clickRemoveForPerson(String name) {
        List<String> names = getNames();
        int index = names.indexOf(name);
        assertTrue("Person with name '" + name + "' must exist", index >= 0);

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("span.closebtn"));
        assertTrue("There must be delete buttons", deleteButtons.size() > index);

        WebElement deleteBtn = deleteButtons.get(index);
        deleteBtn.click();
    }

    public void clickResetList() {
        WebElement resetListButton = driver.findElement(By.xpath("//button[normalize-space()='Reset List']"));
        resetListButton.click();
    }

    public boolean isPersonPresent(String name) {
        return getNames().contains(name);
    }

    public boolean isPersonPresent(String name, String job) {
        List<String> names = getNames();
        List<String> jobs = getJobs();

        if (names.size() != jobs.size()) {
            return false;
        }

        for (int i = 0; i < names.size(); i++) {
            if (name.equals(names.get(i)) && job.equals(jobs.get(i))) {
                return true;
            }
        }
        return false;
    }
}