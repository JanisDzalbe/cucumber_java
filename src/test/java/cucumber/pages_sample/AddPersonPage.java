package cucumber.pages_sample;

import io.cucumber.java.ca.Cal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO -> reformat this so everything is defined above and functions are below
public class AddPersonPage {

    @FindBy(xpath = "//button[text() = 'Clear all fields']")
    private WebElement clearAllFieldsButton;
    @FindBy(xpath = "//button[text() = 'Add']")
    private WebElement addButton;
    @FindBy(xpath = "//button[text() = 'Edit']")
    private WebElement editButton;

    @FindBy (xpath = "//button[text() = 'Cancel']")
    private WebElement cancelButton;

    @FindBy(css = "input#name")
    private WebElement nameInput;


    @FindBy(css = "input#surname")
    private WebElement surnameInput;

    @FindBy(css = "input#job")
    private WebElement jobInput;

    @FindBy(css = "input#dob")
    private WebElement dobInput;

    // These two elements only exist to help dob input manually
    @FindBy(id="ui-datepicker-div")
    private WebElement dateBox;
    @FindBy(css="div h2")
    private WebElement header; //exists only to click off for date changes

    // Language selection. In an ideal case I'd use a list that gets all languages, unfortunately I couldn't get that to work properly
    @FindBy (css="input[name='language']")
    List<WebElement> languageInputs;

    @FindBy(css="input[type=radio][name='gender']")
    List<WebElement> genderInputs;

    @FindBy(css="select#status")
    private WebElement statusSelect;
    public Select getStatusSelect() {
        return new Select(statusSelect);
    }

    public void clickClearAllFieldsButton() { clearAllFieldsButton.click(); }

    public void clickAddButton() { addButton.click(); }

    public void clickCancelButton() { cancelButton.click(); }

    public void clickEditButton() { editButton.click(); }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public String getName() { return nameInput.getAttribute("value"); }
    public void enterSurname(String surname) {
        surnameInput.clear();
        surnameInput.sendKeys(surname);
    }

    public String getSurname() { return surnameInput.getAttribute("value"); }

    public void enterJob(String job) {
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public String getJob() { return jobInput.getAttribute("value"); }

    public void enterDOBText(String dob) {
        dobInput.clear();
        dobInput.sendKeys(dob);
        header.click();
    }

    public void selectDOBDate(String inputDate){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(inputDate, formatter);

        //clear and click off so it works while editing as well
        dobInput.clear();
        header.click();

        // https://stackoverflow.com/questions/28177370/how-to-format-localdate-to-string

        LocalDate startDate = LocalDate.now();
        // How many times to click the back button
        int diffMonths = (int) Period.between(date, startDate).toTotalMonths();

        dobInput.click();

        for(int i=0; i<diffMonths; i++){
            dateBox.findElement(By.className("ui-datepicker-prev")).click();
        }
        dateBox.findElement(By.xpath("//a[text() = '"+ date.getDayOfMonth() + "']")).click();

        // just to make sure input is corect.
        assertEquals(inputDate, dobInput.getAttribute("value"));
    }

    public void selectLanguages(String languages){
        //uncheck all checkboxes
        for(WebElement checkbox: languageInputs){
            if(checkbox.isSelected()){
                checkbox.click();
            }
        }

        if(Objects.equals(languages, "undefined")){
            return;
        }
        //I dislike passing it as a string, but not sure how else to make it work in a table with information of all others
        List<String> languagesList = Arrays.asList(languages.split(" "));

        // I dislike how I implemented it here, essentially O(n^2), ew. But I couldn't think of a way to be able to select the elements then..
        for(WebElement checkbox : languageInputs){
            for(String language : languagesList){
                if(checkbox.getAttribute("id").equals(language.toLowerCase()) && !checkbox.isSelected()){
                    checkbox.click();
                }
            }
        }
    }

    // not a fan of how I did this as well tbf, but I didn't want to create a seperate element for each gender
    public void selectGender(String gender){
        if(gender == "undefined") return;
        for(WebElement radio : genderInputs){
            if(radio.getAttribute("id").equals(gender.toLowerCase())){
                radio.click();
            }
        }
    }

    public void selectStatus(String status){
        String initCapStatus = status.substring(0, 1).toUpperCase() + status.substring(1);
        // the inconsistency between status being initcap in add page and not in the main one is annoying, this is somewaht of a work around lol
        getStatusSelect().selectByVisibleText(initCapStatus);
    }

    public boolean areLanguagesDefault(){
        return languageInputs.get(0).isSelected() && !languageInputs.get(1).isSelected() && !languageInputs.get(2).isSelected();
    }

    public boolean isGenderDefault(){
        for(WebElement ginput : genderInputs){
            if(ginput.isSelected()) return false;
        }
        return true;
    }

    public void assertAllInputEmpty(){
        assertEquals("", nameInput.getAttribute("value"));
        assertEquals("", surnameInput.getAttribute("value"));
        assertEquals("", jobInput.getAttribute("value"));
        assertEquals("", dobInput.getAttribute("value"));
        assertTrue(areLanguagesDefault());
        assertTrue(isGenderDefault());
        assertEquals("", statusSelect.getAttribute("value"));
    }
}