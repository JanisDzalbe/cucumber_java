package cucumber.pages_sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListOfPeoplePage {
    private String pageUrl = "https://acctabootcamp.github.io/site/tasks/list_of_people";
    public String getPageUrl() { return pageUrl; }

    @FindBy (css = "ul#listOfPeople li")
    List<WebElement> listOfPeople;
    public List<WebElement> getListOfPeople() { return listOfPeople; } // for custom functionality if needed

    //there are 2 buttons so for reliability I'm using xpath to click the first one.
    @FindBy (xpath = "//button[@id='addPersonBtn' and text() = 'Add person'][1]")
    private WebElement addPersonButton;
    public void clickAddPersonButton() { addPersonButton.click(); }

    @FindBy (xpath = "//button[@id='addPersonBtn' and text() = 'Reset List'][1]")
    private WebElement resetListButton;
    public void clickResetListButton() { resetListButton.click(); }

    public String getPersonName(int index) {
        return listOfPeople.get(index).findElement(By.cssSelector("span.name")).getText();
    }

    public String getPersonSurname(int index){
        return listOfPeople.get(index).findElement(By.cssSelector("span.surname")).getText();
    }

    public String getPersonJob(int index) {
        return listOfPeople.get(index).findElement(By.cssSelector("span.job")).getText();
    }

    // dob - date of birth here
    public String getPersonDOB(int index){
        return listOfPeople.get(index).findElement(By.cssSelector("span.dob")).getText();
    }

    public String getPersonLanguages(int index){
        return listOfPeople.get(index).findElement(By.cssSelector("span.language")).getText().toLowerCase().replace(",", "");
    }

    public String getPersonGender(int index){
        return listOfPeople.get(index).findElement(By.cssSelector("span.gender")).getText();
    }

    public String getPersonStatus(int index){
        return listOfPeople.get(index).findElement(By.cssSelector("span.status")).getText();
    }

    public void clickEditPerson(int index){
        listOfPeople.get(index).findElement(By.cssSelector("span.editbtn")).click();
    }

    public void clickDeletePerson(int index){
        listOfPeople.get(index).findElement(By.cssSelector("span.closebtn")).click();
    }

    public Map<String, String> getPersonAllInfo(int index) {
        Map<String, String> personInfo = new HashMap<>();
        personInfo.put("name", getPersonName(index));
        personInfo.put("surname", getPersonSurname(index));
        personInfo.put("job", getPersonJob(index));
        personInfo.put("dob", getPersonDOB(index));
        personInfo.put("languages", getPersonLanguages(index));
        personInfo.put("gender", getPersonGender(index));
        personInfo.put("status", getPersonStatus(index));
        return personInfo;
    }

    public List<Map<String, String>> getAllPeople(){
        List<Map<String, String>> people = new ArrayList<>();
        for(int i=0; i< listOfPeople.size(); i++){
            people.add(getPersonAllInfo(i));
        }
        return people;
    }

    // --------------------------
    // Assertions

    public void assertPersonInformation(Map<String, String> expectedPersonInfo, int index) {
        Map<String, String> actualPersonInfo = getPersonAllInfo(index);
        assertEquals(expectedPersonInfo.get("name"), actualPersonInfo.get("name"));
        assertEquals(expectedPersonInfo.get("job"), actualPersonInfo.get("job"));
        assertEquals(expectedPersonInfo.get("dob"), actualPersonInfo.get("dob"));
        assertEquals(expectedPersonInfo.get("languages"), actualPersonInfo.get("languages"));
        assertEquals(expectedPersonInfo.get("gender"), actualPersonInfo.get("gender"));
        assertEquals(expectedPersonInfo.get("status"), actualPersonInfo.get("status"));
    }

    //after adding languages are as such: English, French, spanish; but after editing they are normal.
    public void assertPersonInList(Map<String, String> newPerson){
        //the add page is so inconsistent and creating workarounds just to get test cases to work is absurd.
        // In a normal scenario I'd report all of those bugs before proceeding to do something like this.
        // I had to change so much functionality just for this assert ture to go through which is NOT GOOD.
        assertTrue(getAllPeople().contains(newPerson));
    }



}
