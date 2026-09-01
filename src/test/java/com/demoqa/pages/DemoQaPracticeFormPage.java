package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.components.ModalComponent;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
        register("stateInput", "State React select input", By.id("react-select-3-input"));
        register("cityInput", "City React select input", By.id("react-select-4-input"));
        register("uploadPicture", "Upload Picture input field", By.id("uploadPicture"));
        register("submitBtn", "Submit practice form button", By.id("submit"));
        register("submissionModal", "Submission result modal container", By.className("modal-dialog"));
        register("modalTitle", "Submission modal title", By.id("example-modal-sizes-title-lg"));
        register("modalTable", "Submission result details table", By.className("table-responsive"));
        register("closeModalBtn", "Close submission modal button", By.id("closeLargeModal"));

        submitBtn = initComponent(ButtonComponent.class, getElement("submitBtn"));
        submissionModal = initComponent(ModalComponent.class, getElement("submissionModal"));
    }

    public void fillPersonalDetails(String firstName, String lastName, String email, String gender, String mobile) {
        Log.info("Filling Personal Details for: " + firstName + " " + lastName);
        sendKeys(getElement("firstName"), firstName);
        sendKeys(getElement("lastName"), lastName);
        sendKeys(getElement("userEmail"), email);

        // Select Gender
        By genderBy = By
                .xpath("//label[text()='" + gender + "'] | //input[@value='" + gender + "']/following-sibling::label");
        ElementActions.click(genderBy);

        sendKeys(getElement("userNumber"), mobile);
    }

    public void setDateOfBirth(String day, String month, String year) {
        Log.info("Setting Date of Birth: " + day + " " + month + " " + year);
        click(getElement("dateOfBirthInput"));
        ElementActions.pause(200);

        // Select Month
        WebElement monthSelect = WaitUtils.waitForVisibility(By.className("react-datepicker__month-select"), 5);
        new Select(monthSelect).selectByVisibleText(month);

        // Select Year
        WebElement yearSelect = WaitUtils.waitForVisibility(By.className("react-datepicker__year-select"), 5);
        new Select(yearSelect).selectByVisibleText(year);

        // Select Day
        By dayBy = By.xpath(
                "//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='"
                        + day + "']");
        ElementActions.click(dayBy);
        ElementActions.pause(200);
    }

    public void addSubjects(List<String> subjects) {
        Log.info("Adding subjects: " + subjects);
        WebElement input = waitForVisibility(getElement("subjectsInput"));
        for (String subject : subjects) {
            sendKeys(getElement("subjectsInput"), subject);
            ElementActions.pause(300);
            input.sendKeys(Keys.ENTER);
            ElementActions.pause(200);
        }
    }

    public void selectHobbies(List<String> hobbies) {
        Log.info("Selecting hobbies: " + hobbies);
        for (String hobby : hobbies) {
            By hobbyBy = By.xpath("//label[text()='" + hobby + "']");
            ElementActions.click(hobbyBy);
            ElementActions.pause(200);
        }
    }

    public void uploadPicture(String filePath) {
        Log.info("Uploading picture: " + filePath);
        File f = new File(filePath);
        if (f.exists()) {
            sendKeys(getElement("uploadPicture"), f.getAbsolutePath());
            ElementActions.pause(200);
        }
    }

    public void fillAddressAndStateCity(String address, String state, String city) {
        Log.info("Filling address [" + address + "], state [" + state + "], city [" + city + "]");
        sendKeys(getElement("currentAddress"), address);

        WebElement stateInput = waitForVisibility(getElement("stateInput"));
        sendKeys(getElement("stateInput"), state);
        ElementActions.pause(300);
        stateInput.sendKeys(Keys.ENTER);

        ElementActions.pause(300);
        WebElement cityInput = waitForVisibility(getElement("cityInput"));
        sendKeys(getElement("cityInput"), city);
        ElementActions.pause(300);
        cityInput.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
        Log.info("Submitting Practice Form");
        click(getElement("submitBtn"));
        ElementActions.pause(600);
    }

    public String getModalTitle() {
        return getText(getElement("modalTitle")).trim();
    }

    public String getModalTableData() {
        return getText(getElement("modalTable")).trim();
    }

    public void closeModal() {
        Log.info("Closing submission modal");
        click(getElement("closeModalBtn"));
        ElementActions.pause(400);
    }
}
