package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.components.ModalComponent;
import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

public class DemoQaPracticeFormPage extends BasePage {

    public ButtonComponent submitBtn;
    public ModalComponent submissionModal;

    public DemoQaPracticeFormPage() {
        super("DemoQaPracticeFormPage");
    }

    @Override
    protected void initElements() {
        register("firstName", "First Name input field", By.id("firstName"));
        register("lastName", "Last Name input field", By.id("lastName"));
        register("userEmail", "Email input field", By.id("userEmail"));
        register("userNumber", "10-digit Mobile Number input field", By.id("userNumber"));
        register("dateOfBirthInput", "Date of Birth input field", By.id("dateOfBirthInput"));
        register("subjectsInput", "Subjects autocomplete input field", By.id("subjectsInput"));
        register("currentAddress", "Current Address textarea field", By.id("currentAddress"));
        register("stateDropdown", "State React select container", By.id("state"));
        register("cityDropdown", "City React select container", By.id("city"));
        register("submitBtn", "Submit practice form button", By.id("submit"));
        register("submissionModal", "Submission result modal container", By.className("modal-content"));

        submitBtn = initComponent(ButtonComponent.class, getElement("submitBtn"));
        submissionModal = initComponent(ModalComponent.class, getElement("submissionModal"));
    }

    public void fillPersonalDetails(String firstName, String lastName, String email, String gender, String mobile) {
        Log.info("Filling Personal Details for: " + firstName + " " + lastName);
        sendKeys(getElement("firstName"), firstName);
        sendKeys(getElement("lastName"), lastName);
        sendKeys(getElement("userEmail"), email);

        // Select Gender
        By genderBy = By.xpath("//label[text()='" + gender + "'] | //input[@value='" + gender + "']/following-sibling::label");
        WebElement genderEl = DriverManager.getDriver().findElement(genderBy);
        JavaScriptUtils.clickElement(genderEl);

        sendKeys(getElement("userNumber"), mobile);
    }

    public void setDateOfBirth(String day, String month, String year) {
        Log.info("Setting Date of Birth: " + day + " " + month + " " + year);
        WebElement dobInput = DriverManager.getDriver().findElement(By.id("dateOfBirthInput"));
        JavaScriptUtils.clickElement(dobInput);
        ElementActions.pause(200);

        // Select Month
        WebElement monthSelect = DriverManager.getDriver().findElement(By.className("react-datepicker__month-select"));
        new org.openqa.selenium.support.ui.Select(monthSelect).selectByVisibleText(month);

        // Select Year
        WebElement yearSelect = DriverManager.getDriver().findElement(By.className("react-datepicker__year-select"));
        new org.openqa.selenium.support.ui.Select(yearSelect).selectByVisibleText(year);

        // Select Day
        By dayBy = By.xpath("//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='" + day + "']");
        WebElement dayEl = DriverManager.getDriver().findElement(dayBy);
        JavaScriptUtils.clickElement(dayEl);
        ElementActions.pause(200);
    }

    public void addSubjects(List<String> subjects) {
        Log.info("Adding subjects: " + subjects);
        WebElement input = DriverManager.getDriver().findElement(By.id("subjectsInput"));
        for (String subject : subjects) {
            input.sendKeys(subject);
            ElementActions.pause(300);
            input.sendKeys(Keys.ENTER);
            ElementActions.pause(200);
        }
    }

    public void selectHobbies(List<String> hobbies) {
        Log.info("Selecting hobbies: " + hobbies);
        for (String hobby : hobbies) {
            By hobbyBy = By.xpath("//label[text()='" + hobby + "']");
            WebElement hobbyEl = DriverManager.getDriver().findElement(hobbyBy);
            JavaScriptUtils.clickElement(hobbyEl);
            ElementActions.pause(200);
        }
    }

    public void uploadPicture(String filePath) {
        Log.info("Uploading picture: " + filePath);
        File f = new File(filePath);
        if (f.exists()) {
            WebElement uploadInput = DriverManager.getDriver().findElement(By.id("uploadPicture"));
            uploadInput.sendKeys(f.getAbsolutePath());
            ElementActions.pause(200);
        }
    }

    public void fillAddressAndStateCity(String address, String state, String city) {
        Log.info("Filling address [" + address + "], state [" + state + "], city [" + city + "]");
        sendKeys(getElement("currentAddress"), address);

        WebElement stateInput = DriverManager.getDriver().findElement(By.id("react-select-3-input"));
        stateInput.sendKeys(state);
        ElementActions.pause(300);
        stateInput.sendKeys(Keys.ENTER);

        ElementActions.pause(300);
        WebElement cityInput = DriverManager.getDriver().findElement(By.id("react-select-4-input"));
        cityInput.sendKeys(city);
        ElementActions.pause(300);
        cityInput.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
        Log.info("Submitting Practice Form");
        WebElement submit = DriverManager.getDriver().findElement(By.id("submit"));
        JavaScriptUtils.scrollIntoView(submit);
        JavaScriptUtils.clickElement(submit);
        ElementActions.pause(600);
    }

    public String getModalTitle() {
        WebElement title = WaitUtils.waitForVisibility(By.id("example-modal-sizes-title-lg"), 10);
        return title.getText().trim();
    }

    public String getModalTableData() {
        try {
            WebElement table = DriverManager.getDriver().findElement(By.className("table-responsive"));
            return table.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void closeModal() {
        Log.info("Closing submission modal");
        WebElement close = DriverManager.getDriver().findElement(By.id("closeLargeModal"));
        JavaScriptUtils.clickElement(close);
        ElementActions.pause(400);
    }
}
