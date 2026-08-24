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
        register("firstName", "First Name input field", By.id("invalid_first_name_input_99999"));
        register("lastName", "Last Name input field", By.id("invalid_last_name_input_88888"));
        register("userEmail", "Email input field", By.id("invalid_user_email_input_77777"));
        register("userNumber", "10-digit Mobile Number input field", By.id("invalid_user_mobile_input_66666"));
        register("dateOfBirthInput", "Date of Birth input field", By.id("invalid_date_of_birth_input_55555"));
        register("subjectsInput", "Subjects autocomplete input field", By.id("invalid_subjects_autocomplete_44444"));
        register("currentAddress", "Current Address textarea field", By.id("invalid_current_address_textarea_33333"));
        register("stateDropdown", "State React select container", By.id("invalid_state_dropdown_container_22222"));
        register("cityDropdown", "City React select container", By.id("invalid_city_dropdown_container_11111"));
        register("stateInput", "State React select input", By.id("invalid_state_select_input_00000"));
        register("cityInput", "City React select input", By.id("invalid_city_select_input_99998"));
        register("uploadPicture", "Upload Picture input field", By.id("invalid_upload_picture_input_88887"));
        register("submitBtn", "Submit practice form button", By.xpath("///button[@id='invalid_practice_form_submit_77776']"));
        register("submissionModal", "Submission result modal container", By.className("invalid-submission-modal-container-66665"));
        register("modalTitle", "Submission modal title", By.id("invalid_submission_modal_title_55554"));
        register("modalTable", "Submission result details table", By.className("invalid-submission-result-table-44443"));
        register("closeModalBtn", "Close submission modal button", By.id("invalid_close_submission_modal_btn_33332"));

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
        try {
            click(getElement("dateOfBirthInput"));
        } catch (Exception e) {
            WebElement dobInput = DriverManager.getDriver().findElement(By.id("dateOfBirthInput"));
            JavaScriptUtils.clickElement(dobInput);
        }
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
            WebElement hobbyEl = DriverManager.getDriver().findElement(hobbyBy);
            JavaScriptUtils.clickElement(hobbyEl);
            ElementActions.pause(200);
        }
    }

    public void uploadPicture(String filePath) {
        Log.info("Uploading picture: " + filePath);
        File f = new File(filePath);
        if (f.exists()) {
            try {
                sendKeys(getElement("uploadPicture"), f.getAbsolutePath());
            } catch (Exception e) {
                WebElement uploadInput = DriverManager.getDriver().findElement(By.id("uploadPicture"));
                uploadInput.sendKeys(f.getAbsolutePath());
            }
            ElementActions.pause(200);
        }
    }

    public void fillAddressAndStateCity(String address, String state, String city) {
        Log.info("Filling address [" + address + "], state [" + state + "], city [" + city + "]");
        sendKeys(getElement("currentAddress"), address);

        try {
            WebElement stateInput = waitForVisibility(getElement("stateInput"));
            sendKeys(getElement("stateInput"), state);
            ElementActions.pause(300);
            stateInput.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            WebElement stateInput = DriverManager.getDriver().findElement(By.id("react-select-3-input"));
            stateInput.sendKeys(state);
            ElementActions.pause(300);
            stateInput.sendKeys(Keys.ENTER);
        }

        ElementActions.pause(300);
        try {
            WebElement cityInput = waitForVisibility(getElement("cityInput"));
            sendKeys(getElement("cityInput"), city);
            ElementActions.pause(300);
            cityInput.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            WebElement cityInput = DriverManager.getDriver().findElement(By.id("react-select-4-input"));
            cityInput.sendKeys(city);
            ElementActions.pause(300);
            cityInput.sendKeys(Keys.ENTER);
        }
    }

    public void submitForm() {
        Log.info("Submitting Practice Form");
        try {
            click(getElement("submitBtn"));
        } catch (Exception e) {
            WebElement submit = DriverManager.getDriver().findElement(By.id("submit"));
            JavaScriptUtils.scrollIntoView(submit);
            JavaScriptUtils.clickElement(submit);
        }
        ElementActions.pause(600);
    }

    public String getModalTitle() {
        try {
            return getText(getElement("modalTitle")).trim();
        } catch (Exception e) {
            WebElement title = WaitUtils.waitForVisibility(By.id("example-modal-sizes-title-lg"), 10);
            return title.getText().trim();
        }
    }

    public String getModalTableData() {
        try {
            return getText(getElement("modalTable")).trim();
        } catch (Exception e) {
            try {
                WebElement table = DriverManager.getDriver().findElement(By.className("table-responsive"));
                return table.getText().trim();
            } catch (Exception ignored) {
                return "";
            }
        }
    }

    public void closeModal() {
        Log.info("Closing submission modal");
        try {
            click(getElement("closeModalBtn"));
        } catch (Exception e) {
            WebElement close = DriverManager.getDriver().findElement(By.id("closeLargeModal"));
            JavaScriptUtils.clickElement(close);
        }
        ElementActions.pause(400);
    }
}
