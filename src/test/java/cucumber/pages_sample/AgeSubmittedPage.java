package cucumber.pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgeSubmittedPage {

    @FindBy(how = How.ID, using = "message")
    private WebElement message;

    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement backButton;

    public void clickBackButton() {
        backButton.click();
    }

    public void checkMessageText(String expectedMessage) {
        assertEquals(expectedMessage, message.getText());
    }
}