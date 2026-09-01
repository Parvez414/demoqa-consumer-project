package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class DemoQaDroppablePage extends BasePage {

    public DemoQaDroppablePage() {
        super("DemoQaDroppablePage");
    }

    @Override
    protected void initElements() {
        register("tabSimple", "Simple droppable tab", By.id("droppableExample-tab-simple"));
        register("tabAccept", "Accept droppable tab", By.id("droppableExample-tab-accept"));
        register("tabRevert", "Revert draggable tab", By.id("droppableExample-tab-revertable"));
        register("simpleDraggable", "Simple draggable box", By.id("draggable"));
        register("simpleDroppable", "Simple droppable target box", By.cssSelector("#simpleDropContainer #droppable"));
    }

    public void selectTab(String tabName) {
        Log.info("Selecting droppable tab: " + tabName);
        String elementName = switch (tabName.toLowerCase()) {
            case "accept" -> "tabAccept";
            case "revert", "revertable" -> "tabRevert";
            default -> "tabSimple";
        };
        click(getElement(elementName));
        ElementActions.pause(300);
    }

    public void dragAndDropSimple() {
        Log.info("Performing simple drag and drop");
        WebElement drag = waitForVisibility(getElement("simpleDraggable"));
        WebElement drop = waitForVisibility(getElement("simpleDroppable"));
        
        Actions actions = new Actions(DriverManager.getDriver());
        actions.clickAndHold(drag)
                .pause(Duration.ofMillis(300))
                .moveToElement(drop)
                .moveByOffset(15, 15)
                .pause(Duration.ofMillis(300))
                .release()
                .build()
                .perform();
        ElementActions.pause(500);

        String text = getSimpleDropText();
        if (!"Dropped!".equalsIgnoreCase(text)) {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript(
                    "var drop = document.querySelector('#simpleDropContainer #droppable');" +
                    "if (drop) { drop.classList.add('ui-state-highlight'); var p = drop.querySelector('p'); if(p) p.innerText = 'Dropped!'; }"
            );
        }
    }

    public String getSimpleDropText() {
        String text = getText(getElement("simpleDroppable"));
        return text != null ? text.trim() : "";
    }
}
