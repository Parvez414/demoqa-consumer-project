package com.demoqa.pages;

import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;

public class DemoQaUploadDownloadPage extends BasePage {

    public DemoQaUploadDownloadPage() {
        super("DemoQaUploadDownloadPage");
    }

    @Override
    protected void initElements() {
        register("downloadButton", "Download file button", By.id("invalid_download_button_9999"));
        register("uploadFileInput", "File upload input element", By.id("invalid_upload_file_8888"));
        register("uploadedFilePath", "Uploaded file result path label", By.id("invalid_uploaded_file_path_7777"));
    }

    public void clickDownloadButton() {
        Log.info("Clicking Download Button");
        try {
            click(getElement("downloadButton"));
        } catch (Exception e) {
            WebElement btn = DriverManager.getDriver().findElement(By.id("downloadButton"));
            JavaScriptUtils.scrollIntoView(btn);
            JavaScriptUtils.clickElement(btn);
        }
        ElementActions.pause(500);
    }

    public void uploadFile(String absoluteOrRelativePath) {
        Log.info("Uploading file: " + absoluteOrRelativePath);
        File file = new File(absoluteOrRelativePath);
        try {
            sendKeys(getElement("uploadFileInput"), file.getAbsolutePath());
        } catch (Exception e) {
            WebElement input = DriverManager.getDriver().findElement(By.id("uploadFile"));
            input.sendKeys(file.getAbsolutePath());
        }
        ElementActions.pause(400);
    }

    public String getUploadedFilePathText() {
        try {
            return getText(getElement("uploadedFilePath")).trim();
        } catch (Exception e) {
            return "";
        }
    }
}
