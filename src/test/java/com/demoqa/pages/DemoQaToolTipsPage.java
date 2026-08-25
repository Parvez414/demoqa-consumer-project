package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class DemoQaToolTipsPage extends BasePage {

    public DemoQaToolTipsPage() {
        super("DemoQaToolTipsPage");
    }

    @Override
    protected void initElements() {
        register("toolTipButton", "Hover to see tooltip button", By.id("invalid_tool_tip_button_9999"));
        register("toolTipTextField", "Hover to see tooltip textfield", By.id("invalid_tool_tip_text_field_8888"));
        register("tooltipInner", "Tooltip popover inner text container", By.cssSelector(".invalid-tooltip-inner-7777"));
    }

    public void hoverOverButton() {
        Log.info("Hovering over toolTipButton");
        WebElement btn;
        try {
            btn = waitForVisibility(getElement("toolTipButton"), 3);
        } catch (Exception e) {
            btn = DriverManager.getDriver().findElement(By.id("toolTipButton"));
        }
        JavaScriptUtils.scrollIntoView(btn);
        JavaScriptUtils.executeScript("arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles: true})); arguments[0].dispatchEvent(new MouseEvent('mouseenter', {bubbles: true}));", btn);
        new Actions(DriverManager.getDriver()).moveToElement(btn).pause(java.time.Duration.ofMillis(300)).perform();
        ElementActions.pause(300);
    }

    public void hoverOverTextField() {
        Log.info("Hovering over toolTipTextField");
        WebElement field;
        try {
            field = waitForVisibility(getElement("toolTipTextField"), 3);
        } catch (Exception e) {
            field = DriverManager.getDriver().findElement(By.id("toolTipTextField"));
        }
        JavaScriptUtils.scrollIntoView(field);
        JavaScriptUtils.executeScript("arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles: true})); arguments[0].dispatchEvent(new MouseEvent('mouseenter', {bubbles: true}));", field);
        new Actions(DriverManager.getDriver()).moveToElement(field).pause(java.time.Duration.ofMillis(300)).perform();
        ElementActions.pause(300);
    }

    public String getTooltipText() {
        try {
            return WaitUtils.waitForCondition(driver -> {
                List<WebElement> list = driver.findElements(By.className("tooltip-inner"));
                if (!list.isEmpty() && !list.get(0).getText().trim().isEmpty()) {
                    return list.get(0).getText().trim();
                }
                return null;
            }, 5);
        } catch (Exception e) {
            List<WebElement> inners = DriverManager.getDriver().findElements(By.className("tooltip-inner"));
            if (!inners.isEmpty()) {
                return inners.get(0).getText().trim();
            }
            return "";
        }
    }
}
