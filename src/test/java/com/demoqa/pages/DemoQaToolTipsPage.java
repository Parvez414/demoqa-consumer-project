package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
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
        register("toolTipButton", "Hover to see tooltip button", By.id("toolTipButton"));
        register("toolTipTextField", "Hover to see tooltip textfield", By.id("toolTipTextField"));
        register("tooltipInner", "Tooltip popover inner text container", By.className("tooltip-inner"));
    }

    public void hoverOverButton() {
        Log.info("Hovering over toolTipButton");
        WebElement btn = waitForVisibility(getElement("toolTipButton"), 5);
        new Actions(DriverManager.getDriver()).moveToElement(btn).pause(java.time.Duration.ofMillis(300)).perform();
        ElementActions.pause(300);
    }

    public void hoverOverTextField() {
        Log.info("Hovering over toolTipTextField");
        WebElement field = waitForVisibility(getElement("toolTipTextField"), 5);
        new Actions(DriverManager.getDriver()).moveToElement(field).pause(java.time.Duration.ofMillis(300)).perform();
        ElementActions.pause(300);
    }

    public String getTooltipText() {
        try {
            return WaitUtils.waitForCondition(driver -> {
                List<WebElement> list = driver.findElements(getEffectiveBy(getElement("tooltipInner")));
                if (!list.isEmpty() && !list.get(0).getText().trim().isEmpty()) {
                    return list.get(0).getText().trim();
                }
                return null;
            }, 5);
        } catch (Exception e) {
            List<WebElement> inners = findElements(getElement("tooltipInner"));
            if (!inners.isEmpty()) {
                return inners.get(0).getText().trim();
            }
            return "";
        }
    }
}
