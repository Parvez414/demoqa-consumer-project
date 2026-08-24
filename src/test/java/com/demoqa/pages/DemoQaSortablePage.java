package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DemoQaSortablePage extends BasePage {

    public DemoQaSortablePage() {
        super("DemoQaSortablePage");
    }

    @Override
    protected void initElements() {
        register("tabList", "List sortable tab", By.id("demo-tab-list"));
        register("tabGrid", "Grid sortable tab", By.id("demo-tab-grid"));
    }

    public void selectTab(String tabName) {
        Log.info("Selecting sortable tab: " + tabName);
        String id = "grid".equalsIgnoreCase(tabName) ? "demo-tab-grid" : "demo-tab-list";
        WebElement tab = DriverManager.getDriver().findElement(By.id(id));
        JavaScriptUtils.clickElement(tab);
        ElementActions.pause(300);
    }

    public void dragListItemToTarget(String sourceText, String targetText) {
        Log.info("Dragging list item [" + sourceText + "] to [" + targetText + "]");
        WebElement source = DriverManager.getDriver().findElement(By.xpath(
                "//div[@id='demo-tabpane-list']//div[contains(@class,'list-group-item') and text()='" + sourceText + "']"
        ));
        WebElement target = DriverManager.getDriver().findElement(By.xpath(
                "//div[@id='demo-tabpane-list']//div[contains(@class,'list-group-item') and text()='" + targetText + "']"
        ));
        Actions actions = new Actions(DriverManager.getDriver());
        actions.clickAndHold(source)
                .moveToElement(target, 0, 10)
                .release()
                .build()
                .perform();
        ElementActions.pause(400);
    }

    public List<String> getListItemsText() {
        List<WebElement> items = DriverManager.getDriver().findElements(By.xpath(
                "//div[@id='demo-tabpane-list']//div[contains(@class,'list-group-item')]"
        ));
        return items.stream().map(e -> e.getText()).toList();
    }
}
