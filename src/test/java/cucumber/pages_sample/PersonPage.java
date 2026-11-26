package cucumber.pages_sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PersonPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//button[text()='Add person']")
    private WebElement add;

    @FindBy(how = How.XPATH, using = "//button[text()='Reset List']")
    private WebElement reset;

    @FindBy(how = How.ID, using = "name")
    private WebElement name;

    @FindBy(how = How.ID, using = "job")
    private WebElement job;

    @FindBy(how = How.ID, using = "modal_button")
    private WebElement save;

    public PersonPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageUrl()
    {
        return "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";
    }

    public void clickAddPerson()
    {
        add.click();
    }

    public void clickResetList()
    {
        reset.click();
    }

    public void enterNameAndJob(String name, String job)
    {
        this.name.clear();
        this.name.sendKeys(name);
        this.job.clear();
        this.job.sendKeys(job);
    }

    public void clickModalSaveButton() {
        save.click();
    }

    public void clickEditForPerson(String personName)
    {
        WebElement edit = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + personName + "']]//span[contains(@class,'editbtn')]")
        );
        edit.click();
    }

    public void clickRemoveForPerson(String personName)
    {
        WebElement remove = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" +
                        personName + "']]//span[contains(@class,'closebtn')]")
        );
        remove.click();
    }

    public void checkPersonWithJobExists(String personName, String job) {
        List<WebElement> matches = driver.findElements(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + personName + "'] and .//span[contains(@class,'job') and text()='" + job + "']]")
        );
        assertTrue("Expected person " + personName + " with job " + job + " in the list", matches.size() > 0);
    }

    public void checkPersonExists(String personName) {
        List<WebElement> matches = driver.findElements(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + personName + "']]")
        );
        assertTrue("Expected person " + personName + " in the list", matches.size() > 0);
    }

    public void checkPersonNotExists(String personName) {
        List<WebElement> matches = driver.findElements(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + personName + "']]")
        );
        assertFalse("Expected person " + personName + " NOT to be in the list", matches.size() > 0);
    }
}

