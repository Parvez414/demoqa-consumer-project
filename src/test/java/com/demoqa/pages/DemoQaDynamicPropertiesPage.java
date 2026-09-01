package com.demoqa.pages;

import com.automation.pages.BasePage;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaDynamicPropertiesPage extends BasePage {

    public DemoQaDynamicPropertiesPage() {
        super("DemoQaDynamicPropertiesPage");
    }

    @Override
    protected void initElements() {
        register("enableAfterBtn", "Will enable 5 seconds button", By.id("enableAfter"));
        register("colorChangeBtn", "Color change button", By.id("colorChange"));
        register("visibleAfterBtn", "Visible after 5 seconds button", By.id("visibleAfter"));
    }

    public boolean waitForButtonToBeEnabled(int timeoutSeconds) {
        Log.info("Waiting for enableAfter button to be enabled (up to " + timeoutSeconds + "s)");
        WebElement el = waitForClickable(getElement("enableAfterBtn"), timeoutSeconds);
        return el != null && el.isEnabled();
    }

    public String getColorChangeButtonClass() {
        return waitForVisibility(getElement("colorChangeBtn"), 5).getAttribute("class");
    }

    public boolean waitForColorChange(int timeoutSeconds) {
        Log.info("Waiting for color change button to get danger/red styling");
        return WaitUtils.waitForCondition(d -> {
            try {
                WebElement btn = waitForVisibility(getElement("colorChangeBtn"), 5);
                String classes = btn.getAttribute("class");
                return classes != null && classes.contains("text-danger");
            } catch (Exception e) {
                return false;
            }
        }, timeoutSeconds);
    }

    public boolean waitForButtonToBeVisible(int timeoutSeconds) {
        Log.info("Waiting for visibleAfter button to be visible");
        WebElement el = waitForVisibility(getElement("visibleAfterBtn"), timeoutSeconds);
        return el != null && el.isDisplayed();
    }
}
