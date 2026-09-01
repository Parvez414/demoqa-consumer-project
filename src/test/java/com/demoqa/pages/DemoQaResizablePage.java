package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
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
        register("restrictedBox", "Restricted resizable box container", By.id("resizableBoxWithRestriction"));
        register("restrictedHandle", "Restricted resizable handle", By.cssSelector("#resizableBoxWithRestriction .react-resizable-handle"));
        register("unrestrictedBox", "Unrestricted resizable box container", By.id("resizable"));
        register("unrestrictedHandle", "Unrestricted resizable handle", By.cssSelector("#resizable .react-resizable-handle"));
    }

    public void resizeRestrictedBox(int xOffset, int yOffset) {
        Log.info("Resizing restricted box by offset (" + xOffset + ", " + yOffset + ")");
        WebElement handle = waitForVisibility(getElement("restrictedHandle"), 5);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.clickAndHold(handle).moveByOffset(xOffset, yOffset).release().perform();
        ElementActions.pause(300);
    }

    public Dimension getRestrictedBoxSize() {
        WebElement box = waitForVisibility(getElement("restrictedBox"), 5);
        return box.getSize();
    }
}
