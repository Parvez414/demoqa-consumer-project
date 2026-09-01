package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class DemoQaLinksPage extends BasePage {

    public DemoQaLinksPage() {
        super("DemoQaLinksPage");
    }

    @Override
    protected void initElements() {
        register("simpleLink", "Simple home link", By.id("simpleLink"));
        register("dynamicLink", "Dynamic home link", By.id("dynamicLink"));
        register("createdLink", "API Created link (201)", By.id("created"));
        register("noContentLink", "API No Content link (204)", By.id("no-content"));
        register("movedLink", "API Moved link (301)", By.id("moved"));
        register("badRequestLink", "API Bad Request link (400)", By.id("bad-request"));
        register("unauthorizedLink", "API Unauthorized link (401)", By.id("unauthorized"));
        register("forbiddenLink", "API Forbidden link (403)", By.id("forbidden"));
        register("invalidUrlLink", "API Not Found link (404)", By.id("invalid-url"));
        register("linkResponse", "API status link response text container", By.id("linkResponse"));
    }

    public void clickSimpleLinkAndSwitchTab() {
        Log.info("Clicking simple link");
        String originalWindow = DriverManager.getDriver().getWindowHandle();
        click(getElement("simpleLink"));
        ElementActions.pause(500);

        List<String> windows = new ArrayList<>(DriverManager.getDriver().getWindowHandles());
        for (String w : windows) {
            if (!w.equals(originalWindow)) {
                DriverManager.getDriver().switchTo().window(w);
                break;
            }
        }
    }

    public void clickApiLink(String linkType) {
        Log.info("Clicking API link: " + linkType);
        String elementKey = switch (linkType.toLowerCase()) {
            case "created" -> "createdLink";
            case "nocontent", "no content", "no-content" -> "noContentLink";
            case "moved" -> "movedLink";
            case "badrequest", "bad request", "bad-request" -> "badRequestLink";
            case "unauthorized" -> "unauthorizedLink";
            case "forbidden" -> "forbiddenLink";
            case "notfound", "not found", "invalid-url" -> "invalidUrlLink";
            default -> "createdLink";
        };
        click(getElement(elementKey));
        ElementActions.pause(600);
    }

    public String getLinkResponseText() {
        WaitUtils.waitForCondition(d -> {
            try {
                String t = getText(getElement("linkResponse")).trim();
                return !t.isEmpty();
            } catch (Exception e) {
                return false;
            }
        }, 10);
        return getText(getElement("linkResponse")).trim();
    }
}
