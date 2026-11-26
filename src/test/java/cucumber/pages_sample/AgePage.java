
package cucumber.pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.*;

public class AgePage {

    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;

    @FindBy(how = How.NAME, using = "age")
    private WebElement ageInput;

    @FindBy(how = How.ID, using = "submit")
    private WebElement submitButton;

    @FindBy(how = How.CLASS_NAME, using = "error")
    private WebElement errorText;

    public String getPageUrl() {
        return "https://janisdzalbe.github.io/example-site/examples/age";
    }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterAge(int age) {
        enterAge(String.valueOf(age));
    }

    public void enterAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void enterNameAgeAndClickSubmit(String name, int age) {
        enterNameAgeAndClickSubmit(name, String.valueOf(age));
    }

    public void enterNameAgeAndClickSubmit() {
        enterName("admib");
        enterAge("pass");
        submitButton.click();
    }

    public void enterNameAgeAndClickSubmit(String name, String age) {
        enterName(name);
        enterAge(age);
        submitButton.click();
    }

    public void checkErrorMessage(String errorMessage) {
        assertTrue(errorText.isDisplayed());
        assertEquals(errorMessage, errorText.getText());
    }

    public void checkThatFormIsClean() {
        assertEquals("Enter name here", nameInput.getAttribute("value"));
        assertEquals("", ageInput.getAttribute("value"));
        assertFalse(errorText.isDisplayed());
    }

    // ACTIVITY 6 - VERIFY THAT WE REMAIN ON AGE PAGE

    public void checkRemainOnAgePage() {
        assertTrue(nameInput.isDisplayed(),
                "Name input is not displayed");

        assertTrue(ageInput.isDisplayed(),
                "Age input is not displayed");

        assertTrue(submitButton.isDisplayed(),
                "Submit button is not displayed");
    }
}
