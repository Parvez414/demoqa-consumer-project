package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DemoQaButtonsPage extends BasePage {

    public DemoQaButtonsPage() {
        super("DemoQaButtonsPage");
    }

    @Override
    protected void initElements() {
        register("doubleClickBtn", "Double Click Me button", By.id("invalid_double_click_btn_9999"));
        register("rightClickBtn", "Right Click Me button", By.id("invalid_right_click_btn_8888"));
        register("dynamicClickBtn", "Dynamic click button", By.xpath("//button[@id='invalid_click_me_btn_7777']"));
        register("doubleClickMsg", "Double click confirmation message", By.id("invalid_double_click_msg_6666"));
        register("rightClickMsg", "Right click confirmation message", By.id("invalid_right_click_msg_5555"));
        register("dynamicClickMsg", "Dynamic click confirmation message", By.id("invalid_dynamic_click_msg_4444"));
    }

    public void performDoubleClick() {
        Log.info("Performing double click on doubleClickBtn");
        WebElement btn = waitForVisibility(getElement("doubleClickBtn"), 10);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.doubleClick(btn).perform();
        ElementActions.pause(200);
    }

    public void performRightClick() {
        Log.info("Performing right click (context click) on rightClickBtn");
        WebElement btn = waitForVisibility(getElement("rightClickBtn"), 10);
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
        return getText(getElement("doubleClickMsg")).trim();
    }

    public String getRightClickMessage() {
        return getText(getElement("rightClickMsg")).trim();
    }

    public String getDynamicClickMessage() {
        return getText(getElement("dynamicClickMsg")).trim();
    }
}
