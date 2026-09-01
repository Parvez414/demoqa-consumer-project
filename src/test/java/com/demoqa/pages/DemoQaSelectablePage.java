package com.demoqa.pages;

import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;

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
        String elementName = "grid".equalsIgnoreCase(tabName) ? "tabGrid" : "tabList";
        click(getElement(elementName));
        ElementActions.pause(300);
    }

    public void clickListItem(String itemText) {
        Log.info("Selecting list item: " + itemText);
        By itemBy = By.xpath("//ul[@id='verticalListContainer']/li[contains(text(),'" + itemText + "')]");
        ElementActions.click(itemBy);
        ElementActions.pause(300);
    }

    public boolean isListItemSelected(String itemText) {
        By itemBy = By.xpath("//ul[@id='verticalListContainer']/li[contains(text(),'" + itemText + "')]");
        String classes = WaitUtils.waitForVisibility(itemBy, 5).getAttribute("class");
        return classes != null && classes.contains("active");
    }

    public void clickGridItem(String itemText) {
        Log.info("Selecting grid item: " + itemText);
        By itemBy = By.xpath("//div[@id='gridContainer']//li[contains(text(),'" + itemText + "')]");
        ElementActions.click(itemBy);
        ElementActions.pause(300);
    }

    public boolean isGridItemSelected(String itemText) {
        By itemBy = By.xpath("//div[@id='gridContainer']//li[contains(text(),'" + itemText + "')]");
        String classes = WaitUtils.waitForVisibility(itemBy, 5).getAttribute("class");
        return classes != null && classes.contains("active");
    }
}
