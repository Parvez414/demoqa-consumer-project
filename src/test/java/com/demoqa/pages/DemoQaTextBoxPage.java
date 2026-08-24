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
        register("userName", "Full Name text input", By.id("userName"));
        register("userEmail", "Email address input field", By.id("userEmail"));
        register("currentAddress", "Current Address textarea field", By.id("currentAddress"));
        register("permanentAddress", "Permanent Address textarea field", By.id("permanentAddress"));
        register("submitBtn", "Submit form button", By.id("submit"));
        register("outputCard", "Submitted form output details container", By.id("output"));

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
