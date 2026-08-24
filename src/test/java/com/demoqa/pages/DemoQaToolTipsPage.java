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

public class DemoQaToolTipsPage extends BasePage {

    public DemoQaToolTipsPage() {
        super("DemoQaToolTipsPage");
    }

    @Override
    protected void initElements() {
        register("toolTipButton", "Hover to see tooltip button", By.id("toolTipButton"));
        register("toolTipTextField", "Hover to see tooltip textfield", By.id("toolTipTextField"));
        register("tooltipInner", "Tooltip popover inner text container", By.cssSelector(".tooltip-inner, div[role='tooltip']"));
    }

    public void hoverOverButton() {
        Log.info("Hovering over toolTipButton");
        WebElement btn;
        try {
            btn = waitForVisibility(getElement("toolTipButton"), 5);
        } catch (Exception e) {
            btn = DriverManager.getDriver().findElement(By.id("toolTipButton"));
        }
        JavaScriptUtils.scrollIntoView(btn);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(btn).perform();
        ElementActions.pause(400);
    }

    public void hoverOverTextField() {
        Log.info("Hovering over toolTipTextField");
        WebElement field;
        try {
            field = waitForVisibility(getElement("toolTipTextField"), 5);
        } catch (Exception e) {
            field = DriverManager.getDriver().findElement(By.id("toolTipTextField"));
        }
        JavaScriptUtils.scrollIntoView(field);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(field).perform();
        ElementActions.pause(400);
    }

    public String getTooltipText() {
        try {
            return getText(getElement("tooltipInner")).trim();
        } catch (Exception e) {
            WebElement tooltip = WaitUtils.waitForVisibility(By.cssSelector(".tooltip-inner, div[role='tooltip']"), 10);
            return tooltip.getText().trim();
        }
    }
}
