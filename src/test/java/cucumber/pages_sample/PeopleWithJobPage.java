package cucumber.pages_sample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PeopleWithJobPage {

    @FindBy(how = How.CLASS_NAME, using = "name")
    private List<WebElement> name;
    @FindBy(how = How.CLASS_NAME, using = "job")
    private List<WebElement> job;
    @FindBy(how = How.XPATH, using = "(//button[text()='Add person'])[1]")
    private WebElement addPersonButton;
    @FindBy(how = How.XPATH, using = "(//button[text()='Reset List'])[1]")
    private WebElement resetListButton;
    @FindBy(how = How.CLASS_NAME, using = "fa-pencil")
    private List<WebElement> editPersonButton;
    @FindBy(how = How.CSS, using = ".w3-closebtn.closebtn")
    private List<WebElement> deletePersonButton;

    public String getPageUrl() {
        return "https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs";
    }

    public void clickAddPersonButton() {
        addPersonButton.click();
    }

    public void clickRestListButton() {
        resetListButton.click();
    }

    public void clickEditPersonButton(String targetName) {
        for (int i = 0; i < name.size(); i++) {
            if (name.get(i).getText().equals(targetName)) {
                editPersonButton.get(i).click();
                return;
            }
        }
        throw new RuntimeException("Name '" + targetName + "' not found in the list.");
    }

    public void clickDeletePersonButton(List<String> peopleleToDelete) {
        for (int i = 0; i < name.size(); i++) {
            for (String person: peopleleToDelete) {
                if (name.get(i).getText().equals(person)) {
                    deletePersonButton.get(i).click();
                }
            }
        }
    }

    public void checkListValues(Map<String, String> table) {
        int i = 0;
        for (Map.Entry<String, String> entry : table.entrySet()) {
            assertEquals(entry.getKey(), name.get(i).getText());
            assertEquals(entry.getValue(), job.get(i).getText());
            i++;
        }
    }

    public void checkNameAndJobFromList (String targetName, String targetJob) {
        for (int i = 0; i < name.size(); i++) {
            if (name.get(i).getText().equals(targetName)) {
                assertEquals(targetName, name.get(i).getText());
                assertEquals(targetJob, job.get(i).getText());
                return;
            }
        }
        throw new RuntimeException("Name '" + targetName + "' not found in the list.");
    }


}
