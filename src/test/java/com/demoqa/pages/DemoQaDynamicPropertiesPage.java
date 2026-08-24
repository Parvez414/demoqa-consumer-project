package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.id("enableAfter")));
        return el != null && el.isEnabled();
    }

    public String getColorChangeButtonClass() {
        WebElement btn = DriverManager.getDriver().findElement(By.id("colorChange"));
        return btn.getAttribute("class");
    }

    public boolean waitForColorChange(int timeoutSeconds) {
        Log.info("Waiting for color change button to get danger/red styling");
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
        return wait.until(d -> {
            WebElement btn = d.findElement(By.id("colorChange"));
            String classes = btn.getAttribute("class");
            return classes != null && classes.contains("text-danger");
        });
    }

    public boolean waitForButtonToBeVisible(int timeoutSeconds) {
        Log.info("Waiting for visibleAfter button to be visible");
        WebElement el = WaitUtils.waitForVisibility(By.id("visibleAfter"), timeoutSeconds);
        return el != null && el.isDisplayed();
    }
}
