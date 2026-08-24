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
        register("yesRadioLabel", "Yes radio button option label", By.xpath("//label[@for='yesRadio']"));
        register("impressiveRadioLabel", "Impressive radio button option label", By.xpath("//label[@for='impressiveRadio']"));
        register("noRadioInput", "No radio button input (disabled)", By.id("noRadio"));
        register("resultSuccess", "Selected radio button result text", By.className("text-success"));
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
        WebElement res = DriverManager.getDriver().findElement(By.className("text-success"));
        return res.getText().trim();
    }
}
