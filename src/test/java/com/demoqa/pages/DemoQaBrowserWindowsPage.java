package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class DemoQaBrowserWindowsPage extends BasePage {

    public DemoQaBrowserWindowsPage() {
        super("DemoQaBrowserWindowsPage");
    }

    @Override
    protected void initElements() {
        register("tabButton", "New Tab trigger button", By.id("tabButton"));
        register("windowButton", "New Window trigger button", By.id("windowButton"));
        register("messageWindowButton", "New Message Window trigger button", By.id("messageWindowButton"));
        register("sampleHeading", "Sample heading text in new window/tab", By.id("sampleHeading"));
    }

    public void openNewTab() {
        Log.info("Clicking New Tab button");
        click(getElement("tabButton"));
        ElementActions.pause(500);
    }

    public void openNewWindow() {
        Log.info("Clicking New Window button");
        click(getElement("windowButton"));
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
        String text = getText(getElement("sampleHeading")).trim();
        DriverManager.getDriver().close();
        DriverManager.getDriver().switchTo().window(parent);
        return text;
    }
}
