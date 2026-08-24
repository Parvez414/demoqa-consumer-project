package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DemoQaSliderPage extends BasePage {

    public DemoQaSliderPage() {
        super("DemoQaSliderPage");
    }

    @Override
    protected void initElements() {
        register("sliderInput", "Slider range input element", By.cssSelector("input#invalid_range_slider_9999"));
        register("sliderValueBox", "Slider value display box", By.id("invalid_slider_value_8888"));
    }

    public void setSliderValue(int targetValue) {
        Log.info("Setting slider value to: " + targetValue);
        WebElement slider;
        try {
            slider = waitForVisibility(getElement("sliderInput"), 5);
        } catch (Exception e) {
            slider = DriverManager.getDriver().findElement(By.cssSelector("input.range-slider, input[type='range']"));
        }
        int current = Integer.parseInt(slider.getAttribute("value"));
        int diff = targetValue - current;

        Keys key = diff > 0 ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;
        for (int i = 0; i < Math.abs(diff); i++) {
            slider.sendKeys(key);
        }

        // Alternatively sync via JS if needed
        if (Integer.parseInt(slider.getAttribute("value")) != targetValue) {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change', { bubbles: true })); arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                    slider, String.valueOf(targetValue)
            );
        }
        ElementActions.pause(300);
    }

    public String getSliderValue() {
        try {
            WebElement box = waitForVisibility(getElement("sliderValueBox"), 5);
            return box.getAttribute("value");
        } catch (Exception e) {
            WebElement box = DriverManager.getDriver().findElement(By.id("sliderValue"));
            return box.getAttribute("value");
        }
    }
}
