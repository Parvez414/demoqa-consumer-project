package com.demoqa.pages;

import com.automation.components.TabsComponent;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
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
        String elementName = switch (tabName.toLowerCase()) {
            case "what" -> "tabWhat";
            case "origin" -> "tabOrigin";
            case "use" -> "tabUse";
            case "more" -> "tabMore";
            default -> "tabWhat";
        };
        click(getElement(elementName));
        ElementActions.pause(300);
    }

    public String getActiveTabPaneContent() {
        try {
            By by = By.cssSelector(".tab-content .tab-pane.active, .tab-content .tab-pane.show, .tab-pane[aria-hidden='false']");
            WebElement activePane = WaitUtils.waitForVisibility(by, 5);
            return activePane.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getTabPaneContent(String tabName) {
        String paneId = switch (tabName.toLowerCase()) {
            case "what" -> "demo-tabpane-what";
            case "origin" -> "demo-tabpane-origin";
            case "use" -> "demo-tabpane-use";
            default -> "demo-tabpane-what";
        };
        return ElementActions.getText(By.id(paneId)).trim();
    }

    public boolean isMoreTabDisabled() {
        WebElement moreTab = waitForVisibility(getElement("tabMore"), 5);
        String classes = moreTab.getAttribute("class");
        String ariaDisabled = moreTab.getAttribute("aria-disabled");
        return (classes != null && classes.contains("disabled")) || "true".equalsIgnoreCase(ariaDisabled);
    }
}
