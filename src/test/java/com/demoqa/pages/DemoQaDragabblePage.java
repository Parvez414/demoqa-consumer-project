package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DemoQaDragabblePage extends BasePage {

    public DemoQaDragabblePage() {
        super("DemoQaDragabblePage");
    }

    @Override
    protected void initElements() {
        register("tabSimple", "Simple draggable tab", By.id("draggableExample-tab-simple"));
        register("tabAxis", "Axis restricted draggable tab", By.id("draggableExample-tab-axisRestriction"));
        register("dragBox", "Simple drag box", By.id("dragBox"));
    }

    public void selectTab(String tabName) {
        Log.info("Selecting draggable tab: " + tabName);
        String elementName = "axis".equalsIgnoreCase(tabName) ? "tabAxis" : "tabSimple";
        try {
            click(getElement(elementName));
        } catch (Exception e) {
            String id = "axis".equalsIgnoreCase(tabName) ? "draggableExample-tab-axisRestriction" : "draggableExample-tab-simple";
            WebElement tab = DriverManager.getDriver().findElement(By.id(id));
            JavaScriptUtils.clickElement(tab);
        }
        ElementActions.pause(300);
    }

    public Point getDragBoxLocation() {
        WebElement box = waitForVisibility(getElement("dragBox"));
        return box.getLocation();
    }

    public void dragBoxByOffset(int xOffset, int yOffset) {
        Log.info("Dragging dragBox by (" + xOffset + ", " + yOffset + ")");
        WebElement box = waitForVisibility(getElement("dragBox"));
        Actions actions = new Actions(DriverManager.getDriver());
        actions.dragAndDropBy(box, xOffset, yOffset).perform();
        ElementActions.pause(300);
    }
}
