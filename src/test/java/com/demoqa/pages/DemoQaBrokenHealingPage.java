package com.demoqa.pages;

import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;

public class DemoQaBrokenHealingPage extends BasePage {

    public DemoQaBrokenHealingPage() {
        super("DemoQaBrokenHealingPage");
    }

    @Override
    protected void initElements() {
        // Intentionally broken locators to verify AI Core SDK healing across different
        // applications
        register("brokenUserName", "Full Name text input field", By.id("userName"));
        register("brokenSubmitBtn", "Submit form button", By.id("submit"));
        register("brokenOldSelect", "Old style HTML select dropdown", By.xpath("//li[normalize-space()='Select Menu']"));
    }

    public void enterBrokenUserName(String name) {
        Log.info("Entering text into dynamically healed brokenUserName: " + name);
        sendKeys(getElement("brokenUserName"), name);
        ElementActions.pause(200);
    }

    public void clickBrokenSubmit() {
        Log.info("Clicking dynamically healed brokenSubmitBtn");
        click(getElement("brokenSubmitBtn"));
        ElementActions.pause(200);
    }
}
