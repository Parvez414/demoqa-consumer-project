package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        try {
            WebElement el = waitForClickable(getElement("enableAfterBtn"), timeoutSeconds);
            return el != null && el.isEnabled();
        } catch (Exception e) {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
            WebElement el = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(By.id("enableAfter")));
            return el != null && el.isEnabled();
        }
    }

    public String getColorChangeButtonClass() {
        try {
            return waitForVisibility(getElement("colorChangeBtn"), 5).getAttribute("class");
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("colorChange"));
            return btn.getAttribute("class");
        }
    }

    public boolean waitForColorChange(int timeoutSeconds) {
        Log.info("Waiting for color change button to get danger/red styling");
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
        return wait.until(d -> {
            try {
                WebElement btn = waitForVisibility(getElement("colorChangeBtn"), 5);
                String classes = btn.getAttribute("class");
                return classes != null && classes.contains("text-danger");
            } catch (Exception e) {
                WebElement btn = d.findElement(By.id("colorChange"));
                String classes = btn.getAttribute("class");
                return classes != null && classes.contains("text-danger");
            }
        });
    }

    public boolean waitForButtonToBeVisible(int timeoutSeconds) {
        Log.info("Waiting for visibleAfter button to be visible");
        try {
            WebElement el = waitForVisibility(getElement("visibleAfterBtn"), timeoutSeconds);
            return el != null && el.isDisplayed();
        } catch (Exception e) {
            WebElement el = com.automation.utils.WaitUtils.waitForVisibility(By.id("visibleAfter"), timeoutSeconds);
            return el != null && el.isDisplayed();
        }
    }
}
