package cucumber.pages_sample;

import cucumber.customDataTableType.Person;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PeopleWJobsPage {
    @FindBy(className = "name")
    List<WebElement> nameSpans;
    @FindBy(className = "job")
    List<WebElement> jobSpans;
    @FindBy(css = "[onclick='openModalForAddPersonWithJob()']")
    WebElement addPersonButton;
    @FindBy(id="name")
    WebElement nameInput;
    @FindBy(id="job")
    WebElement jobInput;
    @FindBy(css = "[onclick='addPersonWithJobToList()']")
    WebElement confirmAddPersonButton;
    @FindBy(css = "[onclick='resetListOfPeople()']")
    WebElement resetListButton;
    @FindBy(css = "[onclick='openModalForEditPersonWithJob(0)']")
    WebElement firstPersonsEditButton;
    @FindBy(css = "[onclick='editPersonWithJob(0)']")
    WebElement confirmEditFirstPersonButton;
    @FindBy(css = "[onclick='deletePerson(0)']")
    WebElement firstPersonsDeleteButton;
    public static String getPageURL() {
        return "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";
    }

    public List<Person> getInitialPersonList() {
        // order does not matter because of the way `peopleListMatchesExpected` matches lists (ignores order)
        return Arrays.asList(
            new Person("Jill", "Support"),
            new Person("Mike", "Web Designer"),
            new Person("Jane", "Accountant"),
            new Person("John", "Software Engineer"),
            new Person("Sarah", "Product Manager"),
            new Person("Carlos", "Data Analyst"),
            new Person("Emily", "UX Designer"),
            new Person("David", "Project Manager"),
            new Person("Maria", "QA Engineer"),
            new Person("Alex", "DevOps Engineer")
        );
    }
    public List<Person> getCurrentPersonList(){
        List<String> parsedNames = nameSpans.stream().map(s -> s.getText()).toList();
        List<String> parsedJobs = jobSpans.stream().map(s -> s.getText()).toList();

        assertEquals(parsedNames.size(), parsedJobs.size());
        List<Person> currentPersonList = new ArrayList<>();
        for (int i = 0; i < parsedNames.size(); i++) {
            currentPersonList.add(new Person(parsedNames.get(i), parsedJobs.get(i)));
        }
        return currentPersonList;
    }

    public boolean peopleListMatchesExpected(List<Person> expectedPeople) {
        List<Person> currentPeople = getCurrentPersonList();
//      this way of comparing ignores the order which people appear on ether list
        return expectedPeople.size() == currentPeople.size() &&
                expectedPeople.containsAll(currentPeople) &&
                currentPeople.containsAll(expectedPeople);
    }

    public void addPerson(String name, String jobTitle) {
        addPersonButton.click();
        nameInput.clear();
        nameInput.sendKeys(name);
        jobInput.clear();
        jobInput.sendKeys(jobTitle);
        confirmAddPersonButton.click();
    }

    public void resetList() {
        resetListButton.click();
    }

    public boolean peopleListContainsPerson(String name, String jobTitle) {
        return getCurrentPersonList().contains(new Person(name, jobTitle));
    }

    public void addPrefixToFirstPersonsJobTitle(String jobPrefix) {
        firstPersonsEditButton.click();
        String currentJobTitle = jobInput.getDomProperty("value");
        jobInput.clear();
        jobInput.sendKeys(String.join(" ", jobPrefix, currentJobTitle));
        confirmEditFirstPersonButton.click();
    }

    public void removeFirstPerson() {
        firstPersonsDeleteButton.click();
    }
}
