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
import org.openqa.selenium.WebElement;

public class DemoQaModalDialogsPage extends BasePage {

    public ButtonComponent smallModalBtn;
    public ButtonComponent largeModalBtn;
    public ModalComponent modalDialog;

    public DemoQaModalDialogsPage() {
        super("DemoQaModalDialogsPage");
    }

    @Override
    protected void initElements() {
        register("showSmallModalBtn", "Small Modal trigger button", By.id("showSmallModal"));
        register("showLargeModalBtn", "Large Modal trigger button", By.id("showLargeModal"));
        register("modalContent", "Modal dialog content container", By.className("modal-content"));
        register("smallModalTitle", "Small Modal title header", By.id("example-modal-sizes-title-sm"));
        register("largeModalTitle", "Large Modal title header", By.id("example-modal-sizes-title-lg"));
        register("modalBody", "Modal dialog body text", By.className("modal-body"));
        register("closeSmallModalBtn", "Close Small Modal button", By.id("closeSmallModal"));
        register("closeLargeModalBtn", "Close Large Modal button", By.id("closeLargeModal"));

        smallModalBtn = initComponent(ButtonComponent.class, getElement("showSmallModalBtn"));
        largeModalBtn = initComponent(ButtonComponent.class, getElement("showLargeModalBtn"));
        modalDialog = initComponent(ModalComponent.class, getElement("modalContent"));
    }

    public void openSmallModal() {
        Log.info("Opening Small Modal");
        try {
            click(getElement("showSmallModalBtn"));
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("showSmallModal"));
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(300);
    }

    public void openLargeModal() {
        Log.info("Opening Large Modal");
        try {
            click(getElement("showLargeModalBtn"));
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("showLargeModal"));
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(300);
    }

    public String getSmallModalTitle() {
        try {
            return getText(getElement("smallModalTitle")).trim();
        } catch (Exception e) {
            WebElement el = WaitUtils.waitForVisibility(By.id("example-modal-sizes-title-sm"), 10);
            return el.getText().trim();
        }
    }

    public String getLargeModalTitle() {
        try {
            return getText(getElement("largeModalTitle")).trim();
        } catch (Exception e) {
            WebElement el = WaitUtils.waitForVisibility(By.id("example-modal-sizes-title-lg"), 10);
            return el.getText().trim();
        }
    }

    public String getModalBodyText() {
        try {
            return getText(getElement("modalBody")).trim();
        } catch (Exception e) {
            WebElement el = DriverManager.getDriver().findElement(By.className("modal-body"));
            return el.getText().trim();
        }
    }

    public void closeSmallModal() {
        Log.info("Closing Small Modal");
        try {
            click(getElement("closeSmallModalBtn"));
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("closeSmallModal"));
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(300);
    }

    public void closeLargeModal() {
        Log.info("Closing Large Modal");
        try {
            click(getElement("closeLargeModalBtn"));
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("closeLargeModal"));
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(300);
    }
}
