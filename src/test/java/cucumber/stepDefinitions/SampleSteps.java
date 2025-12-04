package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SampleSteps {
    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/");
    }

    @Then("^I should see home page header$")
    public void iShouldSeeHomePageHeader() throws Throwable {
        assertEquals("This is a home page",
                driver.findElement(By.cssSelector("h1")).getText());
    }

    @And("^I should see home page description$")
    public void iShouldSeeHomePageDescription() throws Throwable {
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                driver.findElement(By.cssSelector("p")).getText());
    }

    @When("^I enter name: \"([^\"]*)\"$")
    public void iEnterName(String name) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter age: (\\d+)$")
    public void iEnterAge(int age) throws Throwable {
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
    }

    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/age");
    }

    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    @When("^I enter values:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        for (Map.Entry<String, String> e : valuesToEnter.entrySet()) {
            driver.findElement(By.id(e.getKey())).clear();
            driver.findElement(By.id(e.getKey())).sendKeys(e.getValue());
            System.out.println("key is " + e.getKey());
            System.out.println("value is " + e.getValue());
        }
    }

    @And("^I should see menu$")
    public void iShouldSeeMenu() throws Throwable {
        assertTrue(driver.findElement(By.className("w3-navbar")).isDisplayed());
    }

    @And("^I click the result checkbox button$")
    public void iClickTheResultCheckboxButton() throws Throwable {
        driver.findElement(By.id("result_button_checkbox")).click();
    }

    @When("^I clicked on checkboxes:$")
    public void iClickedOnCheckboxes(List<String> values) throws Throwable {
        for (String value : values) {
            driver.findElement(By.cssSelector("[value='" + value + "']")).click();
        }
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("result_checkbox")).getText());
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");
    }



                    //Simple1 Task



    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void iShouldSeeBothLocatorsPageHeaders() throws Throwable {
        assertTrue("First header should be displayed",
                driver.findElement(By.id("heading_1")).isDisplayed());            //check h1

        assertTrue("Second header should be displayed",
                driver.findElement(By.id("heading_2")).isDisplayed());            //check h2
    }

    @And("^Buttons in Locators page are clickable$")
    public void buttonsInLocatorsPageAreClickable() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement button1 = driver.findElement(By.name("randomButton1"));      //2 buttons we have
        WebElement button2 = driver.findElement(By.name("randomButton2"));

        assertNotNull("Button 1 isn't clickable", wait.until(ExpectedConditions.elementToBeClickable(button1)));
        assertNotNull("Button 2 isn't clickable", wait.until(ExpectedConditions.elementToBeClickable(button2)));

        }


                //Sample2 Task


    @Then("^I see error: \"([^\"]*)\"$")
    public void iSeeError(String errorMessage) throws Throwable {
        assertEquals(errorMessage, driver.findElement(By.id("error")).getText());
    }


    @And("^I am not navigated to age message page$")
    public void iAmNotNavigatedToAgeMessagePage() throws Throwable {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(
                "We should be still on the age page",
                currentUrl.endsWith("/examples/age"));  //are we still on the same page? has to end with /examples/age

    }



                    //Sample3 Task


    @Given("^I am on the feedback page$")
    public void iAmOnTheFeedbackPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/provide_feedback");
    }

    @When("^I enter feedback name: \"([^\"]*)\"$")
    public void iEnterFeedbackName(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^I enter feedback age: (\\d+)$")
    public void iEnterFeedbackAge(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^I click send feedback button$")
    public void iClickSendFeedbackButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Send']")).click();        //find button by xpath, 'send'text
        //Thread.sleep(3000);               //checking if input works
    }

    @Then("^I should see feedback name displayed as: \"([^\"]*)\"$")
    public void iShouldSeeFeedbackNameDisplayedAs(String expectedName) throws Throwable {
        assertEquals(expectedName, driver.findElement(By.id("name")).getText());
    }

    @And("^I should see feedback age displayed as: \"([^\"]*)\"$")
    public void iShouldSeeFeedbackAgeDisplayedAs(String expectedAge) throws Throwable {
        assertEquals(expectedAge, driver.findElement(By.id("age")).getText());
    }


                //Task 1


    @Given("^I am on the enter a number page$")
    public void iAmOnTheEnterANumberPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @When("^I enter number value: \"([^\"]*)\"$")
    public void iEnterNumberValue(String value) throws Throwable {
        driver.findElement(By.id("numb")).clear();
        driver.findElement(By.id("numb")).sendKeys(value);
    }

    @And("^I click submit number button$")
    public void iClickSubmitNumberButton() throws Throwable {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();  //find button by text using xpath
    }

    @Then("^I should see an error: \"([^\"]*)\"$")
    public void iShouldSeeAnError(String expectedError) throws Throwable {
        assertEquals(expectedError, driver.findElement(By.id("ch1_error")).getText());
    }

    @Then("^I should see a success alert for number \"([^\"]*)\"$")
    public void iShouldSeeSuccessAlert(String inputValue) throws Throwable {
        double number = Double.parseDouble(inputValue);                         //what we input
        double sqrt = Math.sqrt(number);
        String expectedAlertText = String.format("Square root of %d is %.2f", (int)number, sqrt);   //for later checking if the alert text is right

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));      //wait for alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals(expectedAlertText, alert.getText());                   //verify if alert text is right

        alert.accept();
    }

    @And("^I should not see any error message$")
    public void iShouldNotSeeAnyErrorMessage() throws Throwable {
        WebElement error = driver.findElement(By.id("ch1_error"));
        assertFalse("Error message should not be visible", error.isDisplayed());
    }



                //Sample4 task


    @When("^I select feedback languages$")
    public void iSelectFeedbackLanguages(List<String> languages) throws Throwable {
        for (String language : languages) {
            // Find checkbox by value attribute and click it
            driver.findElement(By.cssSelector("input[value='" + language + "']")).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void iCanSeeLanguagesInFeedbackCheck(String expectedLanguages) throws Throwable {
        String displayedLanguages = driver.findElement(By.id("language")).getText();
        assertEquals(expectedLanguages, displayedLanguages);
    }



            //Sample5 task


    @When("^I enter all feedback details:$")
    public void iEnterAllFeedbackDetails(Map<String, String> feedbackData) {

        for (Map.Entry<String, String> entry : feedbackData.entrySet()) {       //loop through cucumber table fields
            String field = entry.getKey();
            String value = entry.getValue();

            if (field.equals("genre")) {                //handling of radiobuttons
                String lower = value.toLowerCase();     // to lower case to match html. Female -> female
                WebElement radio = driver.findElement(
                        By.cssSelector("input[type='radio'][name='gender'][value='" + lower + "']")
                );
                radio.click();                          //clicking the correct gender
                continue;
            }

            //read text inside found element ID, for example if the found id is  "name" then input our data into corresponding field
            WebElement element = driver.findElement(By.id(field));
            element.clear();
            element.sendKeys(value);
        }
    }

    @Then("^I should see all feedback details displayed:$")
    public void iShouldSeeAllFeedbackDetailsDisplayed(Map<String, String> expectedData) throws Throwable {
        for (Map.Entry<String, String> entry : expectedData.entrySet()) {   // Map Cucumber table keys to HTML ids
            String field = entry.getKey();
            String expectedValue = entry.getValue();

            String htmlId;
            switch (field) {
                case "name":
                    htmlId = "name";
                    break;
                case "age":
                    htmlId = "age";
                    break;
                case "genre":
                    htmlId = "gender";          // table uses genre, HTML uses gender (at least on this page)
                    break;
                default:
                    htmlId = field;
            }

            //read text inside found element ID, for example if the found id is  "name" then read what's the text within the span
            WebElement element = driver.findElement(By.id(htmlId));
            String actualValue = element.getText();
            //lower case just for the gender field. if in previous step didn't use lowercase, then wouldn't need it but the table then has to be exactly like in html
            assertEquals("Data mismatch" + field, expectedValue.toLowerCase(), actualValue.toLowerCase());
        }
    }

    /*                      //A way simpler approach
    @When("^I enter all feedback details:$")
    public void iEnterValuesInFeedback(Map<String, String> inputMap) throws Throwable {
        iEnterNameInFeedback(inputMap.get("Name"));
        driver.findElement(By.id("fb_age")).clear();
        driver.findElement(By.id("fb_age")).sendKeys(inputMap.get("Age"));
        driver.findElement(By.cssSelector("input[type='radio'][value='" + inputMap.get("Genre").toLowerCase() + "']")).click();
    }

    @When("^I should see all feedback details displayed:$")
    public void iAssertValuesInFeedbackCheck(Map<String, String> inputMap) throws Throwable {
        assertEquals(inputMap.get("Name"), driver.findElement(By.id("name")).getText());
        assertEquals(inputMap.get("Age"), driver.findElement(By.id("age")).getText());
        assertEquals(inputMap.get("Genre").toLowerCase(), driver.findElement(By.id("gender")).getText());
    }
     */




                            //Task 2


    @Given("^I am on the list of people with jobs page$")
    public void iAmOnTheListOfPeopleWithJobsPage() throws Throwable {
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click add person button$")
    public void iClickAddPersonButton() throws Throwable {
        WebElement addButton = driver.findElement(By.id("addPersonBtn"));
        addButton.click();
    }

    @And("^I enter person name: \"([^\"]*)\"$")
    public void iEnterPersonName(String name) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter person job: \"([^\"]*)\"$")
    public void iEnterPersonJob(String job) throws Throwable {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click add button$")
    public void iClickAddButton() throws Throwable {
        WebElement addButton = driver.findElement(By.id("modal_button"));
        addButton.click();
    }

    @Then("^I should see person \"([^\"]*)\" with job \"([^\"]*)\" in the list$")
    public void iShouldSeePersonInList(String name, String job) throws Throwable {
        WebElement list = driver.findElement(By.id("listOfPeople"));        //list of people

        //looking for li with name and job
        boolean found = list.findElements(By.tagName("li")).stream().anyMatch(li -> {
            String liName = li.findElement(By.className("name")).getText();
            String liJob = li.findElement(By.className("job")).getText();
            return liName.equalsIgnoreCase(name) && liJob.equalsIgnoreCase(job);
        });

        if (!found) {
            throw new AssertionError("Person " + name + " with job " + job + " not found in the list");
        }
    }

    @Given("^I see person \"([^\"]*)\" with job \"([^\"]*)\" in the list$")
    public void iSeePersonInList(String name, String job) throws Throwable {
        WebElement list = driver.findElement(By.id("listOfPeople"));

        boolean found = list.findElements(By.tagName("li")).stream().anyMatch(li -> {
            String liName = li.findElement(By.className("name")).getText();
            String liJob = li.findElement(By.className("job")).getText();
            return liName.equalsIgnoreCase(name) && liJob.equalsIgnoreCase(job);
        });

        if (!found) {
            throw new AssertionError("Person " + name + " with job " + job + " not found in the list");
        }
    }

    @When("^I click edit button for person \"([^\"]*)\"$")
    public void iClickEditButtonForPerson(String name) throws Throwable {
        WebElement list = driver.findElement(By.id("listOfPeople"));
        for (WebElement li : list.findElements(By.tagName("li"))) {
            String liName = li.findElement(By.className("name")).getText();
            if (liName.equalsIgnoreCase(name)) {
                // Click the edit span (has class "editbtn")
                li.findElement(By.className("editbtn")).click();
                break;
            }
        }
    }

    @And("^I click update edit button$")
    public void iClickUpdateButton() throws Throwable {
        driver.findElement(By.id("modal_button")).click();
    }

    @When("^I click remove button for person \"([^\"]*)\"$")
    public void iClickRemoveButtonForPerson(String name) throws Throwable {
        WebElement list = driver.findElement(By.id("listOfPeople"));
        List<WebElement> items = list.findElements(By.tagName("li"));

        for (WebElement li : items) {
            String liName = li.findElement(By.className("name")).getText();     //save name we found in html
            if (liName.equalsIgnoreCase(name)) {
                li.findElement(By.className("closebtn")).click(); // click the x button if we find in html name we input
                break;
            }
        }
    }

    @Then("^I should not see person \"([^\"]*)\" in the list$")
    public void iShouldNotSeePersonInList(String name) throws Throwable {
        WebElement list = driver.findElement(By.id("listOfPeople"));
        // .anyMatch will return true if we find matching name to our input
        boolean found = list.findElements(By.tagName("li")).stream().anyMatch(li -> {
            //get name of the li with matching class
            String liName = li.findElement(By.className("name")).getText();
            //compare found name with expected, case insensitive
            return liName.equalsIgnoreCase(name);   //we search just name, can also add job but I'd assume here that if name isn't seen then job also isn't
        });

        if (found) {                //if we found this person, fail the test
            throw new AssertionError("Person " + name + " should be deleted and not appear in the list");
        }


    }

    @When("^I click the reset list button$")
    public void iClickResetListButton() throws Throwable {
        WebElement resetButton = driver.findElement(By.xpath("//button[text()='Reset List']")); //locate button by text using xpath
        resetButton.click();
    }

    @Then("^I should see the original people in the list$")
    public void iShouldSeeOriginalPeopleInTheList() throws Throwable {
        WebElement list = driver.findElement(By.id("listOfPeople"));

        String[][] originalPeople = {       //array with original people and their jobs
                {"Mike", "Web Designer"},   //[0] is name, [1] is job
                {"Jill", "Support"},
                {"Jane", "Accountant"},
                {"John", "Software Engineer"},
                {"Sarah", "Product Manager"},
                {"Carlos", "Data Analyst"},
                {"Emily", "UX Designer"},
                {"David", "Project Manager"},
                {"Maria", "QA Engineer"},
                {"Alex", "DevOps Engineer"}
        };

        List<WebElement> items = list.findElements(By.tagName("li"));       //people in html

        if (items.size() != originalPeople.length) {    //If more than 10 people = didn't reset properly
            throw new AssertionError(
                    "Expected " + originalPeople.length + " people, but found " + items.size()
            );
        }

        for (String[] person : originalPeople) {        //check that everyone has original job, if not, it didn't reset properly
            String expectedName = person[0];
            String expectedJob = person[1];

            boolean found = items.stream().anyMatch(li -> {
                String liName = li.findElement(By.className("name")).getText();
                String liJob = li.findElement(By.className("job")).getText();
                return liName.equalsIgnoreCase(expectedName) && liJob.equalsIgnoreCase(expectedJob);    //true until something doesn't match
            });

            if (!found) {                   //we check for false,
                throw new AssertionError(
                        "Person " + expectedName + " with job " + expectedJob + "has incorrect information after reset"
                );
            }
        }
    }

}



