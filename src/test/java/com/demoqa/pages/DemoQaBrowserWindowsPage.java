package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DemoQaBrowserWindowsPage extends BasePage {

    public DemoQaBrowserWindowsPage() {
        super("DemoQaBrowserWindowsPage");
    }

    @Override
    protected void initElements() {
        register("tabButton", "New Tab trigger button", By.id("invalid_tab_button_9999"));
        register("windowButton", "New Window trigger button", By.id("invalid_window_button_8888"));
        register("messageWindowButton", "New Message Window trigger button", By.id("invalid_message_window_button_7777"));
        register("sampleHeading", "Sample heading text in new window/tab", By.id("invalid_sample_heading_6666"));
    }

    public void openNewTab() {
        Log.info("Clicking New Tab button");
        try {
            click(getElement("tabButton"));
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("tabButton"));
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(500);
    }

    public void openNewWindow() {
        Log.info("Clicking New Window button");
        try {
            click(getElement("windowButton"));
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("windowButton"));
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(500);
    }

    public String switchToChildWindowAndGetHeading() {
        String parent = DriverManager.getDriver().getWindowHandle();
        try {
            WaitUtils.waitForCondition(d -> d.getWindowHandles().size() > 1, 10);
        } catch (Exception ignored) {
        }
        List<String> windows = new ArrayList<>(DriverManager.getDriver().getWindowHandles());
        for (String w : windows) {
            if (!w.equals(parent)) {
                DriverManager.getDriver().switchTo().window(w);
                break;
            }
        }
        String text;
        try {
            text = getText(getElement("sampleHeading")).trim();
        } catch (Exception e) {
            WebElement heading = WaitUtils.waitForVisibility(By.id("sampleHeading"), 10);
            text = heading.getText().trim();
        }
        DriverManager.getDriver().close();
        DriverManager.getDriver().switchTo().window(parent);
        return text;
    }
}
