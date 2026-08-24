package com.demoqa.stepdefinitions;

import com.automation.ai.HealingAuditLogger;
import com.automation.utils.Log;
import com.automation.utils.ScreenshotUtils;
import com.demoqa.pages.DemoQaBrokenHealingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;

public class DemoQaHealingSteps {

    private DemoQaBrokenHealingPage brokenHealingPage;

    @When("the AI pre-flight agent validates the broken DemoQA page elements")
    public void validateBrokenDemoQaElements() {
        brokenHealingPage = new DemoQaBrokenHealingPage();
        Log.info("Pre-flight validating broken elements on DemoQA page...");
        brokenHealingPage.validateAndHealPageElements();
    }

    @And("I enter {string} into the broken user name field")
    public void enterBrokenUserName(String name) {
        brokenHealingPage.enterBrokenUserName(name);
    }

    @And("I click the broken submit button")
    public void clickBrokenSubmit() {
        brokenHealingPage.clickBrokenSubmit();
    }

    @Then("the AI element healing JSON report should be generated in the demoqa project")
    public void verifyDemoQaHealingReport() {
        HealingAuditLogger.exportJsonReport();

        File file = new File("target/element-healing-history.json");
        Assert.assertTrue(file.exists(), "Healing audit JSON report must exist at target/element-healing-history.json");
        Assert.assertTrue(file.length() > 0, "Healing audit JSON report must not be empty.");

        Log.info("Verified DemoQA healing JSON report at: " + file.getAbsolutePath() + " (size: " + file.length() + " bytes)");
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Healing_Report_Verified");
    }
}
