package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaNestedFramesPage extends BasePage {

    public DemoQaNestedFramesPage() {
        super("DemoQaNestedFramesPage");
    }

    @Override
    protected void initElements() {
        register("parentFrame", "Parent iFrame container", By.id("invalid_parent_frame_9999"));
        register("childFrame", "Child iFrame container inside parent", By.xpath("//iframe[@id='invalid_child_frame_8888']"));
    }

    public String getParentFrameText() {
        Log.info("Switching to Parent Frame");
        DriverManager.getDriver().switchTo().defaultContent();
        WebElement parent;
        try {
            parent = waitForVisibility(getElement("parentFrame"), 10);
        } catch (Exception e) {
            parent = DriverManager.getDriver().findElement(By.id("frame1"));
        }
        DriverManager.getDriver().switchTo().frame(parent);
        String text = DriverManager.getDriver().findElement(By.tagName("body")).getText().trim();
        DriverManager.getDriver().switchTo().defaultContent();
        return text;
    }

    public String getChildIframeText() {
        Log.info("Switching to Child IFrame inside Parent");
        DriverManager.getDriver().switchTo().defaultContent();
        WebElement parent;
        try {
            parent = waitForVisibility(getElement("parentFrame"), 10);
        } catch (Exception e) {
            parent = DriverManager.getDriver().findElement(By.id("frame1"));
        }
        DriverManager.getDriver().switchTo().frame(parent);

        WebElement child;
        try {
            child = waitForVisibility(getElement("childFrame"), 5);
        } catch (Exception e) {
            child = DriverManager.getDriver().findElement(By.tagName("iframe"));
        }
        DriverManager.getDriver().switchTo().frame(child);

        String text = DriverManager.getDriver().findElement(By.tagName("p")).getText().trim();
        DriverManager.getDriver().switchTo().defaultContent();
        ElementActions.pause(200);
        return text;
    }
}
