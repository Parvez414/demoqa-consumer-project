package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaRadioButtonPage extends BasePage {

    public DemoQaRadioButtonPage() {
        super("DemoQaRadioButtonPage");
    }

    @Override
    protected void initElements() {
        register("yesRadioLabel", "Yes radio button option label", By.xpath("//label[@for='invalid_yes_radio_9999']"));
        register("impressiveRadioLabel", "Impressive radio button option label", By.xpath("//label[@for='invalid_impressive_radio_8888']"));
        register("noRadioInput", "No radio button input (disabled)", By.id("invalid_no_radio_input_7777"));
        register("resultSuccess", "Selected radio button result text", By.className("invalid-radio-result-success-6666"));
    }

    public void selectYesRadio() {
        Log.info("Selecting [Yes] radio button");
        click(getElement("yesRadioLabel"));
        ElementActions.pause(200);
    }

    public void selectImpressiveRadio() {
        Log.info("Selecting [Impressive] radio button");
        click(getElement("impressiveRadioLabel"));
        ElementActions.pause(200);
    }

    public String getSelectedResultText() {
        try {
            return getText(getElement("resultSuccess")).trim();
        } catch (Exception e) {
            WebElement res = DriverManager.getDriver().findElement(By.className("text-success"));
            return res.getText().trim();
        }
    }
}
