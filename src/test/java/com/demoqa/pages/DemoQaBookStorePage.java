package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.ResilientActions;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
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
        register("backToBookStoreButton", "Back to book store button", By.xpath("//button[@id='addNewRecordButton' or contains(text(),'Back To Book Store')]"));
    }

    public void searchBook(String query) {
        Log.info("Searching Book Store for: [" + query + "]");
        sendKeys(getElement("searchBox"), query);
        ElementActions.pause(500);
    }

    public List<String> getBookTitles() {
        List<WebElement> links = DriverManager.getDriver().findElements(By.xpath("//span[contains(@id,'see-book-')]//a"));
        return links.stream().map(ResilientActions::getText).toList();
    }

    public void clickBookByTitle(String title) {
        Log.info("Clicking book title: [" + title + "]");
        By linkBy = By.xpath("//a[contains(text(),'" + title + "')]");
        ElementActions.click(linkBy);
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
        click(getElement("backToBookStoreButton"));
        ElementActions.pause(500);
    }
}
