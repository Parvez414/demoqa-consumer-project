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
    }

    public void hoverOverButton() {
        Log.info("Hovering over toolTipButton");
        WebElement btn = DriverManager.getDriver().findElement(By.id("toolTipButton"));
        JavaScriptUtils.scrollIntoView(btn);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(btn).perform();
        ElementActions.pause(400);
    }

    public void hoverOverTextField() {
        Log.info("Hovering over toolTipTextField");
        WebElement field = DriverManager.getDriver().findElement(By.id("toolTipTextField"));
        JavaScriptUtils.scrollIntoView(field);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(field).perform();
        ElementActions.pause(400);
    }

    public String getTooltipText() {
        WebElement tooltip = WaitUtils.waitForVisibility(By.cssSelector(".tooltip-inner, div[role='tooltip']"), 10);
        return tooltip.getText().trim();
    }
}
