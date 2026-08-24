package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaTextBoxPage extends BasePage {

    public ButtonComponent submitBtn;

    public DemoQaTextBoxPage() {
        super("DemoQaTextBoxPage");
    }

    @Override
    protected void initElements() {
        register("userName", "Full Name text input", By.id("invalid_broken_user_name_99999"));
        register("userEmail", "Email address input field", By.xpath("//input[@id='broken_user_email_88888']"));
        register("currentAddress", "Current Address textarea field", By.cssSelector("#brokenCurrentAddressTextarea"));
        register("permanentAddress", "Permanent Address textarea field", By.id("invalid_permanent_address_77777"));
        register("submitBtn", "Submit form button", By.xpath("///button[@id='invalid_submit_btn_66666']"));
        register("outputCard", "Submitted form output details container", By.id("invalid_output_card_container_55555"));

        submitBtn = initComponent(ButtonComponent.class, getElement("submitBtn"));
    }

    public void fillForm(String fullName, String email, String currentAddress, String permanentAddress) {
        Log.info("Filling Text Box form for user: " + fullName);
        sendKeys(getElement("userName"), fullName);
        sendKeys(getElement("userEmail"), email);
        sendKeys(getElement("currentAddress"), currentAddress);
        sendKeys(getElement("permanentAddress"), permanentAddress);
    }

    public void submitForm() {
        Log.info("Submitting Text Box Form");
        click(getElement("submitBtn"));
        ElementActions.pause(300);
    }

    public String getOutputText() {
        try {
            return getText(getElement("outputCard"));
        } catch (Exception e) {
            WebElement output = DriverManager.getDriver().findElement(By.id("output"));
            return output.getText();
        }
    }
}
