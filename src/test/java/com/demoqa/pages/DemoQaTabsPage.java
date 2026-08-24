package com.demoqa.pages;

import com.automation.components.TabsComponent;
import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaTabsPage extends BasePage {

    public TabsComponent tabs;

    public DemoQaTabsPage() {
        super("DemoQaTabsPage");
    }

    @Override
    protected void initElements() {
        register("tabsContainer", "Tabs navigation container", By.id("tabsContainer"));
        register("tabWhat", "What tab link", By.id("demo-tab-what"));
        register("tabOrigin", "Origin tab link", By.id("demo-tab-origin"));
        register("tabUse", "Use tab link", By.id("demo-tab-use"));
        register("tabMore", "More tab link (disabled)", By.id("demo-tab-more"));

        tabs = initComponent(TabsComponent.class, getElement("tabsContainer"));
    }

    public void selectTab(String tabName) {
        Log.info("Selecting Tab: [" + tabName + "]");
        String tabId = switch (tabName.toLowerCase()) {
            case "what" -> "demo-tab-what";
            case "origin" -> "demo-tab-origin";
            case "use" -> "demo-tab-use";
            case "more" -> "demo-tab-more";
            default -> "demo-tab-what";
        };
        WebElement tab = DriverManager.getDriver().findElement(By.id(tabId));
        JavaScriptUtils.clickElement(tab);
        ElementActions.pause(300);
    }

    public String getTabPaneContent(String tabName) {
        String paneId = switch (tabName.toLowerCase()) {
            case "what" -> "demo-tabpane-what";
            case "origin" -> "demo-tabpane-origin";
            case "use" -> "demo-tabpane-use";
            default -> "demo-tabpane-what";
        };
        WebElement pane = DriverManager.getDriver().findElement(By.id(paneId));
        return pane.getText().trim();
    }

    public boolean isMoreTabDisabled() {
        WebElement moreTab = DriverManager.getDriver().findElement(By.id("demo-tab-more"));
        String classes = moreTab.getAttribute("class");
        String ariaDisabled = moreTab.getAttribute("aria-disabled");
        return (classes != null && classes.contains("disabled")) || "true".equalsIgnoreCase(ariaDisabled);
    }
}
