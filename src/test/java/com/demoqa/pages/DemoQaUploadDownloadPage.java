package com.demoqa.pages;

import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;

import java.io.File;

public class DemoQaUploadDownloadPage extends BasePage {

    public DemoQaUploadDownloadPage() {
        super("DemoQaUploadDownloadPage");
    }

    @Override
    protected void initElements() {
        register("downloadButton", "Download file button", By.id("downloadButton"));
        register("uploadFileInput", "File upload input element", By.id("uploadFile"));
        register("uploadedFilePath", "Uploaded file result path label", By.id("uploadedFilePath"));
    }

    public void clickDownloadButton() {
        Log.info("Clicking Download Button");
        click(getElement("downloadButton"));
        ElementActions.pause(500);
    }

    public void uploadFile(String absoluteOrRelativePath) {
        Log.info("Uploading file: " + absoluteOrRelativePath);
        File file = new File(absoluteOrRelativePath);
        sendKeys(getElement("uploadFileInput"), file.getAbsolutePath());
        ElementActions.pause(400);
    }

    public String getUploadedFilePathText() {
        return getText(getElement("uploadedFilePath")).trim();
    }
}
