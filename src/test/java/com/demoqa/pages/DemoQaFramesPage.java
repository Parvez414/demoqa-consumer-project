package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaFramesPage extends BasePage {

    public DemoQaFramesPage() {
        super("DemoQaFramesPage");
    }

    @Override
    protected void initElements() {
        register("frame1", "First iFrame container", By.id("invalid_frame1_container_9999"));
        register("frame2", "Second iFrame container", By.id("invalid_frame2_container_8888"));
        register("sampleHeading", "Sample heading text inside iframe", By.id("invalid_sample_heading_7777"));
    }

    public String getTextFromFrame(String frameId) {
        Log.info("Switching to iframe: " + frameId);
        DriverManager.getDriver().switchTo().defaultContent();
        WebElement iframe;
        try {
            iframe = waitForVisibility(getElement(frameId), 10);
        } catch (Exception e) {
            iframe = DriverManager.getDriver().findElement(By.id(frameId));
        }
        DriverManager.getDriver().switchTo().frame(iframe);

        String text;
        try {
            text = getText(getElement("sampleHeading")).trim();
        } catch (Exception e) {
            WebElement heading = WaitUtils.waitForVisibility(By.id("sampleHeading"), 10);
            text = heading.getText().trim();
        }

        DriverManager.getDriver().switchTo().defaultContent();
        ElementActions.pause(200);
        return text;
    }
}
