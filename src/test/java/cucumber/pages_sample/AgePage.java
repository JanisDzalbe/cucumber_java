package cucumber.pages_sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class AgePage {

    private WebDriver driver;

    public AgePage(WebDriver driver) {
        this.driver = driver;
    }

    private By nameBy = By.id("name");
    private By ageBy = By.id("age");
    private By submitBy = By.id("submit");
    private By errorBy = By.id("error");

    public String getPageUrl() {
        return "https://janisdzalbe.github.io/example-site/examples/age";
    }

    private WebElement name() {
        return driver.findElement(nameBy);
    }

    private WebElement age() {
        return driver.findElement(ageBy);
    }

    private WebElement submit() {
        return driver.findElement(submitBy);
    }

    private WebElement error() {
        return driver.findElement(errorBy);
    }

    public void enterName(String name) {
        name().clear();
        name().sendKeys(name);
    }

    public void enterAge(int age) {
        age().clear();
        age().sendKeys(String.valueOf(age));
    }

    public void clickSubmit() {
        submit().click();
    }

    public void checkErrorMessage(String expected) {
        assertTrue(error().isDisplayed());
        assertEquals(expected, error().getText());
    }

    public void checkThatFormIsClean() {
        assertEquals("Enter name here", name().getAttribute("value"));
        assertEquals("", age().getAttribute("value"));
    }
}