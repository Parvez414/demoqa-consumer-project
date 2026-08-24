package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DemoQaButtonsPage extends BasePage {

    public DemoQaButtonsPage() {
        super("DemoQaButtonsPage");
    }

    @Override
    protected void initElements() {
        register("doubleClickBtn", "Double Click Me button", By.id("doubleClickBtn"));
        register("rightClickBtn", "Right Click Me button", By.id("rightClickBtn"));
        register("dynamicClickBtn", "Dynamic click button", By.xpath("//button[text()='Click Me']"));
        register("doubleClickMsg", "Double click confirmation message", By.id("doubleClickMessage"));
        register("rightClickMsg", "Right click confirmation message", By.id("rightClickMessage"));
        register("dynamicClickMsg", "Dynamic click confirmation message", By.id("dynamicClickMessage"));
    }

    public void performDoubleClick() {
        Log.info("Performing double click on doubleClickBtn");
        WebElement btn = WaitUtils.waitForVisibility(getElement("doubleClickBtn").getCurrentBy(), 10);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.doubleClick(btn).perform();
        ElementActions.pause(200);
    }

    public void performRightClick() {
        Log.info("Performing right click (context click) on rightClickBtn");
        WebElement btn = WaitUtils.waitForVisibility(getElement("rightClickBtn").getCurrentBy(), 10);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.contextClick(btn).perform();
        ElementActions.pause(200);
    }

    public void performDynamicClick() {
        Log.info("Performing dynamic standard click on dynamicClickBtn");
        click(getElement("dynamicClickBtn"));
        ElementActions.pause(200);
    }

    public String getDoubleClickMessage() {
        return DriverManager.getDriver().findElement(By.id("doubleClickMessage")).getText().trim();
    }

    public String getRightClickMessage() {
        return DriverManager.getDriver().findElement(By.id("rightClickMessage")).getText().trim();
    }

    public String getDynamicClickMessage() {
        return DriverManager.getDriver().findElement(By.id("dynamicClickMessage")).getText().trim();
    }
}
