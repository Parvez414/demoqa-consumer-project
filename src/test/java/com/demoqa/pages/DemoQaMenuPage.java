package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DemoQaMenuPage extends BasePage {

    public DemoQaMenuPage() {
        super("DemoQaMenuPage");
    }

    @Override
    protected void initElements() {
        register("mainItem2", "Main Item 2 navigation item", By.xpath("//a[@id='invalid_main_item_2_9999']"));
        register("subSubList", "Sub Sub List item container", By.xpath("//a[@id='invalid_sub_sub_list_8888']"));
        register("subSubItem1", "Sub Sub Item 1 navigation item", By.xpath("//a[@id='invalid_sub_sub_item_1_7777']"));
        register("subSubItem2", "Sub Sub Item 2 navigation item", By.xpath("//a[@id='invalid_sub_sub_item_2_6666']"));
    }

    public void hoverOverMainItem2() {
        Log.info("Hovering over Main Item 2");
        WebElement el = waitForVisibility(getElement("mainItem2"), 10);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(el).perform();
        ElementActions.pause(300);
    }

    public void hoverOverSubSubList() {
        Log.info("Hovering over SUB SUB LIST");
        WebElement el = waitForVisibility(getElement("subSubList"), 10);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(el).perform();
        ElementActions.pause(300);
    }

    public boolean isSubSubItemVisible(String subItemText) {
        if ("Sub Sub Item 1".equalsIgnoreCase(subItemText)) {
            try {
                return isDisplayed(getElement("subSubItem1"));
            } catch (Exception ignored) {}
        } else if ("Sub Sub Item 2".equalsIgnoreCase(subItemText)) {
            try {
                return isDisplayed(getElement("subSubItem2"));
            } catch (Exception ignored) {}
        }
        WebElement el = WaitUtils.waitForVisibility(By.xpath("//a[contains(text(),'" + subItemText + "')]"), 5);
        return el != null && el.isDisplayed();
    }
}
