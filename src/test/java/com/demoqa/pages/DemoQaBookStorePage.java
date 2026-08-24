package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DemoQaBookStorePage extends BasePage {

    public DemoQaBookStorePage() {
        super("DemoQaBookStorePage");
    }

    @Override
    protected void initElements() {
        register("searchBox", "Search books input box", By.id("searchBox"));
        register("tableContainer", "Books table container", By.className("rt-table"));
    }

    public void searchBook(String query) {
        Log.info("Searching Book Store for: [" + query + "]");
        WebElement search = WaitUtils.waitForVisibility(By.id("searchBox"), 10);
        search.click();
        search.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        search.sendKeys(query);
        ElementActions.pause(500);
    }

    public List<String> getBookTitles() {
        List<WebElement> links = DriverManager.getDriver().findElements(By.xpath("//span[contains(@id,'see-book-')]//a"));
        return links.stream().map(l -> l.getText().trim()).toList();
    }

    public void clickBookByTitle(String title) {
        Log.info("Clicking book title: [" + title + "]");
        WebElement link = DriverManager.getDriver().findElement(By.xpath("//a[contains(text(),'" + title + "')]"));
        JavaScriptUtils.scrollIntoView(link);
        JavaScriptUtils.clickElement(link);
        ElementActions.pause(500);
    }

    public String getBookDetailValue(String fieldLabel) {
        By valBy = By.xpath(
                "//div[@id='" + fieldLabel.toLowerCase() + "-wrapper']//*[@id='userName-value'] | " +
                "//div[@id='" + fieldLabel.toLowerCase() + "-wrapper']//div[contains(@class,'col-md-9')]//label | " +
                "//div[contains(@id,'" + fieldLabel.toLowerCase() + "')]//*[@id='userName-value']"
        );
        WebElement el = WaitUtils.waitForVisibility(valBy, 10);
        return el.getText().trim();
    }

    public void clickBackToBookStore() {
        Log.info("Clicking Back To Book Store button");
        WebElement btn = DriverManager.getDriver().findElement(By.xpath("//button[contains(text(),'Back To Book Store') or @id='addNewRecordButton']"));
        JavaScriptUtils.scrollIntoView(btn);
        JavaScriptUtils.clickElement(btn);
        ElementActions.pause(500);
    }
}
