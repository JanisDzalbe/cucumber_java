package cucumber.pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class AgeSubmittedPage {
    @FindBy(how = How.CSS, using = "#message")
    private WebElement message;
    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement backButton;

    public String getPageBaseUrl() {
        return "https://janisdzalbe.github.io/example-site/examples/age_2";
    }

    public void clickBackButton() {
        backButton.click();
    }

    public void checkMessageText(String messageText) {
        assertEquals(message.getText(), messageText);
    }
}