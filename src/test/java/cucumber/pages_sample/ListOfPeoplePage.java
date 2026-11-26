package cucumber.pages_sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;



public class ListOfPeoplePage {
    private WebDriver driver;

    @FindBy(id = "addPersonBtn")
    private WebElement addPersonButton;
    @FindBy(xpath = "//button[text()='Reset List']")
    private WebElement resetButton;
    @FindBy(css = "#addEditPerson #name")
    private WebElement nameInputField;
    @FindBy(css = "#addEditPerson #job")
    private WebElement jobInput;
    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButtonInAddPage;
    @FindBy(css = "[id*='person']")
    private List<WebElement> PeopleList;
    @FindBy(xpath = "//button[text()='Edit']")
    private WebElement editButton;
    @FindBy(css = ".remove")
    private List<WebElement> removeButton;

    public ListOfPeoplePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageUrl() {
        return "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";
    }
    //-----------add person-------------------

    public void clickAddPerson() {
        addPersonButton.click();
    }

    public void enterName(String name) {
        nameInputField.clear();
        nameInputField.sendKeys(name);
    }

    public void enterJob(String job) {
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public void clickAdd() {
        addButtonInAddPage.click();
    }

    public void addPerson(String name, String job) {
        clickAddPerson();
        enterName(name);
        enterJob(job);
        clickAdd();
    }
    public boolean isPersonDisplayed(String name, String job) {
        for (WebElement person : getPeopleList()) {
            String details = person.getText();

            if (details.contains(name) && details.contains(job)) {
                return true;
            }
        }
        return false;
    }
    // get people list

    public List<WebElement>getPeopleList() {
        return PeopleList;
    }

    // ------------------Edit person---------
    //-----------------------------------------
    public void clickEditPerson(String name) {
        List<WebElement> peopleList = driver.findElements(
                By.cssSelector("[id*='person']"));
        for (WebElement person : peopleList) {
            if (person.getText().contains(name)) {
                person.findElement(By.xpath(".//span[starts-with(@onclick, 'openModalForEditPersonWithJob')]"
                )).click();
                return;
            }
        }
    }

    public String getNameFieldValue() {
        return nameInputField.getDomProperty("value");
    }

    public String getJobFieldValue() {
        return jobInput.getDomProperty("value");
    }

    public void changeJob(String job) {
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public void clickEdit() {
        editButton.click();
    }

    //------------------Remove person-----------
    //------------------------------------------

    public void removePerson(String name) {
        List<WebElement> people = driver.findElements(
                By.cssSelector("[id*='person']")
        );

        for (WebElement person : people) {
            if (person.getText().contains(name)) {
                person.findElement(By.xpath("//*[@id='person0']/span[1]")).click();
                return;
            }
        }
    }
    public boolean isPersonRemoved(String name) {
        List<WebElement> people = driver.findElements(
                By.cssSelector("[id*='person']")
        );

        for (WebElement person : people) {
            if (person.getText().contains(name)) {
                return false;
            }
        }
        return true;
    }
    // Reset list
    public void clickResetList() {
        resetButton.click();
    }

    public int getPeopleCount() {
        return getPeopleList().size();
    }



}


