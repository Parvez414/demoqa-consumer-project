package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaSelectablePage extends BasePage {

    public DemoQaSelectablePage() {
        super("DemoQaSelectablePage");
    }

    @Override
    protected void initElements() {
        register("tabList", "List selectable tab", By.id("demo-tab-list"));
        register("tabGrid", "Grid selectable tab", By.id("demo-tab-grid"));
    }

    public void selectTab(String tabName) {
        Log.info("Selecting selectable tab: " + tabName);
        String id = "grid".equalsIgnoreCase(tabName) ? "demo-tab-grid" : "demo-tab-list";
        WebElement tab = DriverManager.getDriver().findElement(By.id(id));
        JavaScriptUtils.clickElement(tab);
        ElementActions.pause(300);
    }

    public void clickListItem(String itemText) {
        Log.info("Selecting list item: " + itemText);
        WebElement item = DriverManager.getDriver().findElement(By.xpath(
                "//ul[@id='verticalListContainer']/li[contains(text(),'" + itemText + "')]"));
        JavaScriptUtils.clickElement(item);
        ElementActions.pause(300);
    }

    public boolean isListItemSelected(String itemText) {
        WebElement item = DriverManager.getDriver().findElement(By.xpath(
                "//ul[@id='verticalListContainer']/li[contains(text(),'" + itemText + "')]"));
        String classes = item.getAttribute("class");
        return classes != null && classes.contains("active");
    }

    public void clickGridItem(String itemText) {
        Log.info("Selecting grid item: " + itemText);
        WebElement item = DriverManager.getDriver().findElement(By.xpath(
                "//div[@id='gridContainer']//li[contains(text(),'" + itemText + "')]"));
        JavaScriptUtils.clickElement(item);
        ElementActions.pause(300);
    }

    public boolean isGridItemSelected(String itemText) {
        WebElement item = DriverManager.getDriver().findElement(By.xpath(
                "//div[@id='gridContainer']//li[contains(text(),'" + itemText + "')]"));
        String classes = item.getAttribute("class");
        return classes != null && classes.contains("active");
    }
}
