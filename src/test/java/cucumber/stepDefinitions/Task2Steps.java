package cucumber.stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.pages_sample.PeopleWithJobsPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import cucumber.pages_sample.PeopleWithJobsPage;

public class Task2Steps {
    private WebDriver driver;
    private PeopleWithJobsPage peoplePage;
    public Task2Steps() {
        this.driver = Hooks.driver;}

        @Given("^I am on the home pageser$$$")
        public void iAmOnTheHomePagerrr() throws Throwable {
            driver.get("https://acctabootcamp.github.io/site");
        }

    @Given("I am on people with jobs page")
    public void iAmOnPeopleWithJobsPage() {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("I click on a button {string}")
    public void iClickOnAButton(String arg0) {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @And("I enter new person name: {string}")
    public void iEnterNewPersonName(String arg0) {
        driver.findElement(By.id("name")).sendKeys("Jozeph");
    }

    @And("I enter new person job: Driver")
    public void iEnterNewPersonJobDriver() {
        driver.findElement(By.id("job")).sendKeys("Driver");

    }

    @And("Click button Add")
    public void clickButtonAdd() {
        driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
    }

    @Then("New person is added")
    public void newPersonIsAdded() {
      String job = driver.findElement(By.id("person10")).findElement(By.className("job")).getText();
      assertEquals("Driver",job);
      String Name = driver.findElement(By.id("person10")).findElement(By.className("name")).getText();
        assertEquals("Jozeph",Name);



    }

    @When("I click near fifth person \\(Sarah,Product Manager, index{int}) pencil button")
    public void iClickNearFifthPersonSarahProductManagerIndexPencilButton(int arg0) {
        driver.findElement(By.xpath("//span[contains(@onclick,'openModalForEditPersonWithJob(4)')]")).click();
    }

    @And("I change job to Pilot")
    public void iChangeJobToPilot() {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Pilot");
    }

    @And("I click button edit")
    public void iClickButtonEdit() {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();

    }

    @Then("Person job is Edited to pilot")
    public void personJobIsEditedToPilot() {
        WebElement updatedjob = driver.findElement(By.id("person4"));
        assertEquals("Pilot", updatedjob.findElement(By.className("job")).getText());
    }

    @When("I click on a cross button near sixth person \\(Carlos, Data Analyst,index{int})")
    public void iClickOnACrossButtonNearSixthPersonCarlosDataAnalystIndex(int arg0) {
        driver.findElement(By.xpath("//span[@onclick='deletePerson(5)']")).click();
    }

    @Then("I see that Carlos is removed from the list")
    public void iSeeThatCarlosIsRemovedFromTheList() {
            List<WebElement> persons = driver.findElements(By.cssSelector("li[id^='person']"));
            assertEquals(9, persons.size());
            List<String> actualPeople = new ArrayList<>();
            for (WebElement person : persons) {
                String name = person.findElement(By.className("name")).getText().trim();
                String job = person.findElement(By.className("job")).getText().trim();

                actualPeople.add(name + ", " + job);
            }
            assertFalse(actualPeople.contains("Carlos, Data Analyst"));
        }


}



