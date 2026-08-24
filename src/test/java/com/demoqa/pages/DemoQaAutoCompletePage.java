package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DemoQaAutoCompletePage extends BasePage {

    public DemoQaAutoCompletePage() {
        super("DemoQaAutoCompletePage");
    }

    @Override
    protected void initElements() {
        register("multipleInput", "Multiple colors autocomplete input", By.id("autoCompleteMultipleInput"));
        register("singleInput", "Single color autocomplete input", By.id("autoCompleteSingleInput"));
        register("singleValue", "Single color selected value", By.className("auto-complete__single-value"));
    }

    public void addMultipleColors(List<String> colors) {
        Log.info("Adding multiple colors: " + colors);
        WebElement input = waitForVisibility(getElement("multipleInput"));
        for (String c : colors) {
            sendKeys(getElement("multipleInput"), c);
            ElementActions.pause(300);
            input.sendKeys(Keys.ENTER);
            ElementActions.pause(200);
        }
    }

    public List<String> getSelectedMultipleColors() {
        List<WebElement> badges = DriverManager.getDriver().findElements(By.className("auto-complete__multi-value__label"));
        return badges.stream().map(b -> b.getText().trim()).toList();
    }

    public void removeMultipleColorBadge(String color) {
        Log.info("Removing color badge: " + color);
        WebElement removeBtn = DriverManager.getDriver().findElement(By.xpath(
                "//div[contains(@class,'auto-complete__multi-value__label') and text()='" + color + "']/following-sibling::div[contains(@class,'auto-complete__multi-value__remove')]"
        ));
        JavaScriptUtils.clickElement(removeBtn);
        ElementActions.pause(300);
    }

    public void selectSingleColor(String color) {
        Log.info("Selecting single color: " + color);
        WebElement input = waitForVisibility(getElement("singleInput"));
        sendKeys(getElement("singleInput"), color);
        ElementActions.pause(300);
        input.sendKeys(Keys.ENTER);
        ElementActions.pause(200);
    }

    public String getSelectedSingleColor() {
        try {
            return getText(getElement("singleValue")).trim();
        } catch (Exception e) {
            return "";
        }
    }
}
