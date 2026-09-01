package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaProgressBarPage extends BasePage {

    public ButtonComponent startStopBtn;

    public DemoQaProgressBarPage() {
        super("DemoQaProgressBarPage");
    }

    @Override
    protected void initElements() {
        register("startStopButton", "Start/Stop progress bar button", By.id("startStopButton"));
        register("progressBar", "Progress bar indicator", By.cssSelector(".progress-bar"));
        register("resetButton", "Reset progress bar button", By.id("resetButton"));

        startStopBtn = initComponent(ButtonComponent.class, getElement("startStopButton"));
    }

    public void clickStartStop() {
        Log.info("Clicking Start/Stop button on Progress Bar");
        click(getElement("startStopButton"));
        ElementActions.pause(200);
    }

    public boolean waitUntilProgressComplete(int timeoutSeconds) {
        Log.info("Waiting for progress bar to reach 100%");
        return WaitUtils.waitForCondition(d -> {
            try {
                WebElement pb = waitForVisibility(getElement("progressBar"), 5);
                String val = pb.getAttribute("aria-valuenow");
                return "100".equals(val) || "100%".equals(pb.getText().trim());
            } catch (Exception e) {
                return false;
            }
        }, timeoutSeconds);
    }

    public String getCurrentProgressValue() {
        WebElement pb = waitForVisibility(getElement("progressBar"), 5);
        return pb.getAttribute("aria-valuenow");
    }

    public void clickReset() {
        Log.info("Clicking Reset button on Progress Bar");
        click(getElement("resetButton"));
        ElementActions.pause(300);
    }
}
