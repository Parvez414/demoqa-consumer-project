package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoQaAlertsPage extends BasePage {

    public ButtonComponent simpleAlertBtn;
    public ButtonComponent timerAlertBtn;
    public ButtonComponent confirmAlertBtn;
    public ButtonComponent promptAlertBtn;

    public DemoQaAlertsPage() {
        super("DemoQaAlertsPage");
    }

    @Override
    protected void initElements() {
        register("alertButton", "Simple alert trigger button", By.id("alerttButton"));
        register("timerAlertButton", "5 second timer alert button", By.id("timerAlertButton"));
        register("confirmButton", "Confirm box trigger button", By.id("confirmButton2"));
        register("promtButton", "Prompt box trigger button", By.id("promptButtonBroken"));
        register("confirmResult", "Confirm alert user selection result text", By.id("confirmResult"));
        register("promptResult", "Prompt alert user input response result text", By.id("promptResult"));

        simpleAlertBtn = initComponent(ButtonComponent.class, getElement("alertButton"));
        timerAlertBtn = initComponent(ButtonComponent.class, getElement("timerAlertButton"));
        confirmAlertBtn = initComponent(ButtonComponent.class, getElement("confirmButton"));
        promptAlertBtn = initComponent(ButtonComponent.class, getElement("promtButton"));
    }

    private Alert waitForAlert() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void triggerAndAcceptSimpleAlert() {
        Log.info("Triggering and accepting Simple JavaScript Alert");
        click(getElement("alertButton"));
        Alert alert = waitForAlert();
        alert.accept();
        ElementActions.pause(300);
    }

    public void triggerConfirmAlert(boolean accept) {
        Log.info("Triggering Confirm Alert, accept=" + accept);
        click(getElement("confirmButton"));
        Alert alert = waitForAlert();
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        ElementActions.pause(300);
    }

    public String getConfirmResultText() {
        return getText(getElement("confirmResult")).trim();
    }

    public void triggerPromptAlertAndEnterText(String inputText) {
        Log.info("Triggering Prompt Alert and entering text: [" + inputText + "]");
        try {
            ((org.openqa.selenium.JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("window.prompt = function(msg, def) { return '" + inputText + "'; };");
            click(getElement("promtButton"));
            ElementActions.pause(400);
        } catch (Exception e) {
            click(getElement("promtButton"));
            Alert alert = waitForAlert();
            try {
                alert.sendKeys(inputText);
            } catch (Exception ignored) {}
            alert.accept();
            ElementActions.pause(400);
        }
    }

    public String getPromptResultText() {
        return getText(getElement("promptResult")).trim();
    }
}
