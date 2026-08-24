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

public class DemoQaLinksPage extends BasePage {

    public DemoQaLinksPage() {
        super("DemoQaLinksPage");
    }

    @Override
    protected void initElements() {
        register("simpleLink", "Simple home link", By.id("invalid_simple_link_9999"));
        register("dynamicLink", "Dynamic home link", By.id("invalid_dynamic_link_8888"));
        register("createdLink", "API Created link (201)", By.id("invalid_created_link_7777"));
        register("noContentLink", "API No Content link (204)", By.id("invalid_no_content_link_6666"));
        register("movedLink", "API Moved link (301)", By.id("invalid_moved_link_5555"));
        register("badRequestLink", "API Bad Request link (400)", By.id("invalid_bad_request_link_4444"));
        register("unauthorizedLink", "API Unauthorized link (401)", By.id("invalid_unauthorized_link_3333"));
        register("forbiddenLink", "API Forbidden link (403)", By.id("invalid_forbidden_link_2222"));
        register("invalidUrlLink", "API Not Found link (404)", By.id("invalid_url_link_1111"));
        register("linkResponse", "API status link response text container", By.id("invalid_link_response_0000"));
    }

    public void clickSimpleLinkAndSwitchTab() {
        Log.info("Clicking simple link");
        String originalWindow = DriverManager.getDriver().getWindowHandle();
        try {
            click(getElement("simpleLink"));
        } catch (Exception e) {
            WebElement link = DriverManager.getDriver().findElement(By.id("simpleLink"));
            JavaScriptUtils.clickElement(link);
        }
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
        try {
            click(getElement(elementKey));
        } catch (Exception e) {
            String id = switch (linkType.toLowerCase()) {
                case "created" -> "created";
                case "nocontent", "no content", "no-content" -> "no-content";
                case "moved" -> "moved";
                case "badrequest", "bad request", "bad-request" -> "bad-request";
                case "unauthorized" -> "unauthorized";
                case "forbidden" -> "forbidden";
                case "notfound", "not found", "invalid-url" -> "invalid-url";
                default -> "created";
            };
            WebElement link = DriverManager.getDriver().findElement(By.id(id));
            JavaScriptUtils.scrollIntoView(link);
            JavaScriptUtils.clickElement(link);
        }
        ElementActions.pause(600);
    }

    public String getLinkResponseText() {
        try {
            return getText(getElement("linkResponse")).trim();
        } catch (Exception e) {
            WebElement resp = WaitUtils.waitForVisibility(By.id("linkResponse"), 10);
            return resp.getText().trim();
        }
    }
}
