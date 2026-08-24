package com.demoqa.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.driver.DriverManager;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.ScreenshotUtils;
import com.demoqa.pages.DemoQaAlertsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DemoQaAlertsSteps {

    private DemoQaAlertsPage alertsPage;

    @Given("I open the DemoQA Alerts page")
    public void openAlertsPage() {
        String url = ConfigReader.get("app.alerts.url");
        DriverManager.getDriver().get(url);
        ElementActions.pause(500);
        alertsPage = new DemoQaAlertsPage();
    }

    @When("I trigger and accept the simple alert")
    public void triggerSimpleAlert() {
        alertsPage.triggerAndAcceptSimpleAlert();
    }

    @When("I trigger the confirm alert and choose {string}")
    public void triggerConfirmAlert(String choice) {
        boolean accept = "Accept".equalsIgnoreCase(choice) || "OK".equalsIgnoreCase(choice);
        alertsPage.triggerConfirmAlert(accept);
    }

    @Then("the confirm selection result should be {string}")
    public void verifyConfirmResult(String expected) {
        String actual = alertsPage.getConfirmResultText();
        Log.info("Verified confirm alert result: " + actual);
        Assert.assertEquals(actual, expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Confirm_Alert_Result");
    }

    @When("I trigger the prompt alert, enter {string} and accept")
    public void triggerPromptAlert(String text) {
        alertsPage.triggerPromptAlertAndEnterText(text);
    }

    @Then("the prompt response result should display {string}")
    public void verifyPromptResult(String expected) {
        String actual = alertsPage.getPromptResultText();
        Log.info("Verified prompt alert result: " + actual);
        Assert.assertTrue(actual.contains(expected), "Prompt result does not contain: " + expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Prompt_Alert_Result");
    }
}
