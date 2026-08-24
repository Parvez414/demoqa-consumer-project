package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DemoQaResizablePage extends BasePage {

    public DemoQaResizablePage() {
        super("DemoQaResizablePage");
    }

    @Override
    protected void initElements() {
        register("restrictedBox", "Restricted resizable box container", By.id("invalid_restricted_box_9999"));
        register("restrictedHandle", "Restricted resizable handle", By.cssSelector("#invalid_restricted_handle_8888"));
        register("unrestrictedBox", "Unrestricted resizable box container", By.id("invalid_unrestricted_box_7777"));
        register("unrestrictedHandle", "Unrestricted resizable handle", By.cssSelector("#invalid_unrestricted_handle_6666"));
    }

    public void resizeRestrictedBox(int xOffset, int yOffset) {
        Log.info("Resizing restricted box by offset (" + xOffset + ", " + yOffset + ")");
        WebElement handle;
        try {
            handle = waitForVisibility(getElement("restrictedHandle"), 5);
        } catch (Exception e) {
            handle = DriverManager.getDriver().findElement(By.cssSelector("#resizableBoxWithRestriction .react-resizable-handle"));
        }
        JavaScriptUtils.scrollIntoView(handle);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.clickAndHold(handle).moveByOffset(xOffset, yOffset).release().perform();
        ElementActions.pause(300);
    }

    public Dimension getRestrictedBoxSize() {
        try {
            WebElement box = waitForVisibility(getElement("restrictedBox"), 5);
            return box.getSize();
        } catch (Exception e) {
            WebElement box = DriverManager.getDriver().findElement(By.id("resizableBoxWithRestriction"));
            return box.getSize();
        }
    }
}
