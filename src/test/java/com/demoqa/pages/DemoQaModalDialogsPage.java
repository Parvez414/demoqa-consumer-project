package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.components.ModalComponent;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
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
        click(getElement("showSmallModalBtn"));
        ElementActions.pause(300);
    }

    public void openLargeModal() {
        Log.info("Opening Large Modal");
        click(getElement("showLargeModalBtn"));
        ElementActions.pause(300);
    }

    public String getSmallModalTitle() {
        return getText(getElement("smallModalTitle")).trim();
    }

    public String getLargeModalTitle() {
        return getText(getElement("largeModalTitle")).trim();
    }

    public String getModalBodyText() {
        return getText(getElement("modalBody")).trim();
    }

    public void closeSmallModal() {
        Log.info("Closing Small Modal");
        try {
            ((org.openqa.selenium.JavascriptExecutor) com.automation.driver.DriverManager.getDriver())
                .executeScript("var el = document.getElementById('closeSmallModal'); if (el) el.click();");
        } catch (Exception e) {
            click(getElement("closeSmallModalBtn"));
        }
        ElementActions.pause(500);
    }

    public void closeLargeModal() {
        Log.info("Closing Large Modal");
        try {
            ((org.openqa.selenium.JavascriptExecutor) com.automation.driver.DriverManager.getDriver())
                .executeScript("var el = document.getElementById('closeLargeModal'); if (el) el.click();");
        } catch (Exception e) {
            click(getElement("closeLargeModalBtn"));
        }
        ElementActions.pause(500);
    }
}
