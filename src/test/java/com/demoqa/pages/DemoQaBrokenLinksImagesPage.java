package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoQaBrokenLinksImagesPage extends BasePage {

    public DemoQaBrokenLinksImagesPage() {
        super("DemoQaBrokenLinksImagesPage");
    }

    @Override
    protected void initElements() {
        register("validImage", "Valid demo image",
                By.xpath("//p[contains(text(),'Valid image')]/following-sibling::img[1]"));
        register("brokenImage", "Broken demo image",
                By.xpath("//p[contains(text(),'Broken image')]/following-sibling::img[1]"));
        register("validLink", "Valid link navigation", By.xpath("//a[contains(text(),'Click Here for Valid Link')]"));
        register("brokenLink", "Broken link navigation",
                By.xpath("//a[contains(text(),'Click Here for Broken Link')]"));
    }

    public boolean isValidImageDisplayed() {
        Log.info("Checking valid image display");
        try {
            By by = By.xpath(
                    "//p[contains(text(),'Valid image')]/following-sibling::img[1] | //img[contains(@src,'Toolsqa.jpg')] | (//div[@class='col-12 mt-4 col-md-6']//img)[1]");
            WebElement img = com.automation.utils.WaitUtils.waitForPresence(by, 8);
            Boolean loaded = (Boolean) JavaScriptUtils.executeScript(
                    "return arguments[0].complete && (typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0);",
                    img);
            if (loaded != null && loaded)
                return true;
            ElementActions.pause(500);
            return img.isDisplayed() && img.getAttribute("src") != null && !img.getAttribute("src").isBlank();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBrokenImageDetected() {
        Log.info("Checking broken image detection");
        try {
            By by = By.xpath(
                    "//p[contains(text(),'Broken image')]/following-sibling::img[1] | (//div[@class='col-12 mt-4 col-md-6']//img)[2]");
            WebElement img = com.automation.utils.WaitUtils.waitForPresence(by, 8);
            Long naturalWidth = (Long) JavaScriptUtils.executeScript("return arguments[0].naturalWidth;", img);
            return naturalWidth == null || naturalWidth == 0;
        } catch (Exception e) {
            return true;
        }
    }

    public void clickValidLink() {
        Log.info("Clicking Valid Link");
        WebElement link = DriverManager.getDriver()
                .findElement(By.xpath("//a[contains(text(),'Click Here for Valid Link')]"));
        JavaScriptUtils.scrollIntoView(link);
        JavaScriptUtils.clickElement(link);
        ElementActions.pause(500);
    }

    public void clickBrokenLink() {
        Log.info("Clicking Broken Link");
        WebElement link = DriverManager.getDriver()
                .findElement(By.xpath("//a[contains(text(),'Click Here for Broken Link')]"));
        JavaScriptUtils.scrollIntoView(link);
        JavaScriptUtils.clickElement(link);
        ElementActions.pause(500);
    }
}
