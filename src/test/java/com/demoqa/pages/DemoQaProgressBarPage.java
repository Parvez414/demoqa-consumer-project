package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoQaProgressBarPage extends BasePage {

    public ButtonComponent startStopBtn;

    public DemoQaProgressBarPage() {
        super("DemoQaProgressBarPage");
    }

    @Override
    protected void initElements() {
        register("startStopButton", "Start/Stop progress bar button", By.id("startStopButton"));
        register("progressBar", "Progress bar indicator", By.cssSelector(".progress-bar, #progressBar div"));
        register("resetButton", "Reset progress bar button", By.id("resetButton"));

        startStopBtn = initComponent(ButtonComponent.class, getElement("startStopButton"));
    }

    public void clickStartStop() {
        Log.info("Clicking Start/Stop button on Progress Bar");
        try {
            click(getElement("startStopButton"));
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("startStopButton"));
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(200);
    }

    public boolean waitUntilProgressComplete(int timeoutSeconds) {
        Log.info("Waiting for progress bar to reach 100%");
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
        return wait.until(d -> {
            try {
                WebElement pb = waitForVisibility(getElement("progressBar"), 5);
                String val = pb.getAttribute("aria-valuenow");
                return "100".equals(val) || "100%".equals(pb.getText().trim());
            } catch (Exception e) {
                WebElement pb = d.findElement(By.cssSelector(".progress-bar, #progressBar div"));
                String val = pb.getAttribute("aria-valuenow");
                return "100".equals(val) || "100%".equals(pb.getText().trim());
            }
        });
    }

    public String getCurrentProgressValue() {
        try {
            WebElement pb = waitForVisibility(getElement("progressBar"), 5);
            return pb.getAttribute("aria-valuenow");
        } catch (Exception e) {
            WebElement pb = DriverManager.getDriver().findElement(By.cssSelector(".progress-bar, #progressBar div"));
            return pb.getAttribute("aria-valuenow");
        }
    }

    public void clickReset() {
        Log.info("Clicking Reset button on Progress Bar");
        try {
            click(getElement("resetButton"));
        } catch (Exception e) {
            WebElement btn = WaitUtils.waitForClickable(By.id("resetButton"), 10);
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(300);
    }
}
