package cucumber.stepDefinitions;

import com.google.common.base.Equivalence;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class Task2Steps {
    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on People with jobs page$")
    public void iAmOnEnterANumberPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I press Add person button$")
    public void iPressAddPersonButton() {
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
    }

    @And("^I enter the values in form:$")
    public void iEnterTheValuesInForm(Map<String, String> table) {
        // Enter the name
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(table.get("name"));
        // Enter the job
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(table.get("job"));
    }

    @And("^I press the Add button$")
    public void iPressTheAddButton() {
        driver.findElement(By.xpath("//button[text()='Add']")).click();
    }

    @Then("^I check if the person is (?:added to|updated in) the list:$")
    public void iCheckIfThePersonIsAddedToTheList(Map<String, String> table) {
        WebElement newEntry = driver.findElement(By.xpath("//li[*/text()='"+ table.get("name") +"']"));
        assertEquals(table.get("name"), newEntry.findElement(By.xpath(".//*[contains(@class, 'name')]")).getText());
        assertEquals(table.get("job"), newEntry.findElement(By.xpath(".//*[@class='job']")).getText());
    }

    @When("^I press the Edit button for \"([^\"]*)\"$")
    public void iPressTheEditButtonForName(String name) {
        WebElement entryToEdit = driver.findElement(By.xpath("//li[*/text()='"+ name +"']"));
        entryToEdit.findElement(By.xpath(".//i[@class='fa fa-pencil']")).click();
    }

    @And("^I check the person values are prepopulated$")
    public void iCheckThePersonValuesArePrepopulated(Map<String, String> table) {
        assertEquals(table.get("name"), driver.findElement(By.id("name")).getDomProperty("value"));
        assertEquals(table.get("previousJob"), driver.findElement(By.id("job")).getDomProperty("value"));
    }

    @And("^I edit the current job to \"([^\"]*)\"$")
    public void iEditTheCurrentJobTo(String job) {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }


    @And("^I press the Edit button$")
    public void iPressTheEditButton() {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }

    @When("^I press the Delete button for \"([^\"]*)\"$")
    public void iPressTheDeleteButtonFor(String name) {
        WebElement entryToDelete = driver.findElement(By.xpath("//li[*/text()='"+ name +"']"));
        entryToDelete.findElement(By.xpath(".//*[contains(text(), '×')]")).click();
    }

    @Then("^I check if the person \"([^\"]*)\" is removed from the list$")
    public void iCheckIfThePersonIsRemovedFromTheList(String name) throws InterruptedException {
        // Can't just find if the element is NOT present, for some reason can't handle the error
        // test step times out, but still passes though
//        try {
//            assertTrue(driver.findElements(By.xpath("//li[*/text()='" + name + "']")).isEmpty());
//        } catch (NoSuchElementException e) {
//            assertTrue(true);
//        }

        List<WebElement> allElements = driver.findElements(By.id("listOfPeople"));
        for (WebElement element : allElements) {
            if (element.findElement(By.className("name")).getText().equals(name)) {
                fail();
                return;
            }
        }
    }

    @Then("^I press Reset List button$")
    public void iPressResetListButton() {
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
    }

    @And("^I check if the list is in initial state$")
    public void iCheckIfTheListIsInInitialState() {
        Map<String, String> initialList = getInitialList();
        List<WebElement> allPeople = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        assertEquals(initialList.size(), allPeople.size());

        for(WebElement person : allPeople){
            String name = person.findElement(By.xpath(".//*[contains(@class, 'name')]")).getText();
            String job = person.findElement(By.xpath(".//*[@class='job']")).getText();

            assertTrue(initialList.containsKey(name));
            assertEquals(initialList.get(name), job);
        }
    }

    private static Map<String, String> getInitialList() {
        // should convert to List and implement duplicate checks accordingly
        // as this will not work if the name repeats - key must be unique
        Map<String, String> initialList = new HashMap<>();
        initialList.put("Mike", "Web Designer");
        initialList.put("Jill", "Support");
        initialList.put("Jane", "Accountant");
        initialList.put("John", "Software Engineer");
        initialList.put("Sarah", "Product Manager");
        initialList.put("Carlos", "Data Analyst");
        initialList.put("Emily", "UX Designer");
        initialList.put("David", "Project Manager");
        initialList.put("Maria", "QA Engineer");
        initialList.put("Alex", "DevOps Engineer");
        return initialList;
    }
}
