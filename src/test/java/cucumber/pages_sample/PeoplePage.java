
package cucumber.pages_sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeoplePage {

    private WebDriver driver;

    private final String pageUrl =
            "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";

    public PeoplePage(WebDriver driver) {
        this.driver = driver;
    }

    // ORIGINAL PEOPLE

    private final Map<String, String> originalPeople = new HashMap<>();

    {
        originalPeople.put("Alex", "DevOps Engineer");
        originalPeople.put("Emily", "UX Designer");
        originalPeople.put("Jane", "Accountant");
        originalPeople.put("Mike", "Web Designer");
        originalPeople.put("John", "Software Engineer");
        originalPeople.put("Maria", "QA Engineer");
        originalPeople.put("Carlos", "Data Analyst");
        originalPeople.put("Jill", "Support");
        originalPeople.put("David", "Project Manager");
        originalPeople.put("Sarah", "Product Manager");
    }

    // LOCATORS

    private final By addPersonButton =
            By.cssSelector("button[onclick='openModalForAddPersonWithJob()']");

    private final By resetListButton =
            By.cssSelector("button[onclick='resetListOfPeople()']");

    private final By nameInput = By.id("name");

    private final By jobInput = By.id("job");

    private final By addSaveButton =
            By.cssSelector("button[onclick='addPersonWithJobToList()']");

    private final By editSaveButton =
            By.cssSelector("button[onclick^='editPersonWithJob(']");

    private final By peopleList =
            By.cssSelector("#listOfPeople li");

    private final By personName =
            By.cssSelector(".name");

    private final By personJob =
            By.cssSelector(".job");

    private final By editButton =
            By.cssSelector(".editbtn");

    private final By deleteButton =
            By.cssSelector(".closebtn");

    // OPEN PAGE

    public void openPage() {
        driver.get(pageUrl);
    }

    // ADD PERSON

    public void clickAddPerson() {
        driver.findElement(addPersonButton).click();
    }

    public void enterPersonDetails(String name, String job) {

        WebElement nameField = driver.findElement(nameInput);
        nameField.clear();
        nameField.sendKeys(name);

        WebElement jobField = driver.findElement(jobInput);
        jobField.clear();
        jobField.sendKeys(job);
    }

    public void saveNewPerson() {
        driver.findElement(addSaveButton).click();
    }

    public void addPerson(String name, String job) {
        clickAddPerson();
        enterPersonDetails(name, job);
        saveNewPerson();
    }

    // FIND PERSON

    private WebElement findPerson(String name) {

        List<WebElement> people = driver.findElements(peopleList);

        for (WebElement person : people) {

            String actualName =
                    person.findElement(personName).getText();

            if (actualName.equals(name)) {
                return person;
            }
        }

        return null;
    }

    // EDIT PERSON

    public void editPerson(String currentName,
                           String newName,
                           String newJob) {

        WebElement person = findPerson(currentName);

        if (person == null) {
            throw new AssertionError(
                    "Person was not found: " + currentName
            );
        }

        person.findElement(editButton).click();

        enterPersonDetails(newName, newJob);

        driver.findElement(editSaveButton).click();
    }

    // REMOVE PERSON

    public void removePerson(String name) {

        WebElement person = findPerson(name);

        if (person == null) {
            throw new AssertionError(
                    "Person was not found: " + name
            );
        }

        person.findElement(deleteButton).click();
    }

    // VERIFY PERSON

    public boolean isPersonPresent(String name, String job) {

        WebElement person = findPerson(name);

        if (person == null) {
            return false;
        }

        String actualJob =
                person.findElement(personJob).getText();

        return actualJob.equals(job);
    }

    public boolean isPersonPresent(String name) {
        return findPerson(name) != null;
    }

    // RESET LIST

    public void resetList() {
        driver.findElement(resetListButton).click();
    }

    // GET ACTUAL PEOPLE

    private Map<String, String> getCurrentPeople() {

        Map<String, String> currentPeople = new HashMap<>();

        List<WebElement> people = driver.findElements(peopleList);

        for (WebElement person : people) {

            String name =
                    person.findElement(personName).getText();

            String job =
                    person.findElement(personJob).getText();

            currentPeople.put(name, job);
        }

        return currentPeople;
    }

    // VERIFY ORIGINAL LIST

    public boolean isOriginalListRestored() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            return wait.until(webDriver -> {

                try {
                    Map<String, String> currentPeople =
                            getCurrentPeople();

                    return currentPeople.size() == originalPeople.size()
                            && currentPeople.equals(originalPeople);

                } catch (StaleElementReferenceException e) {
                    return false;
                }
            });

        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}
