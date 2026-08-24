package com.demoqa.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.demoqa.pages.DemoQaBrowserWindowsPage;
import com.demoqa.pages.DemoQaFramesPage;
import com.demoqa.pages.DemoQaModalDialogsPage;
import com.demoqa.pages.DemoQaNestedFramesPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DemoQaAlertsWindowsSteps {

    private DemoQaBrowserWindowsPage browserWindowsPage;
    private DemoQaFramesPage framesPage;
    private DemoQaNestedFramesPage nestedFramesPage;
    private DemoQaModalDialogsPage modalDialogsPage;

    // --- BROWSER WINDOWS ---
    @Given("I open the DemoQA Browser Windows page")
    public void openBrowserWindowsPage() {
        String url = ConfigReader.get("app.browserwindows.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        browserWindowsPage = new DemoQaBrowserWindowsPage();
    }

    @When("I open a new tab and assert child page heading is {string}")
    public void openNewTabAndAssert(String expectedHeading) {
        browserWindowsPage.openNewTab();
        String actualHeading = browserWindowsPage.switchToChildWindowAndGetHeading();
        Log.info("Child tab heading: " + actualHeading);
        Assert.assertEquals(actualHeading, expectedHeading);
    }

    @When("I open a new window and assert child page heading is {string}")
    public void openNewWindowAndAssert(String expectedHeading) {
        browserWindowsPage.openNewWindow();
        String actualHeading = browserWindowsPage.switchToChildWindowAndGetHeading();
        Log.info("Child window heading: " + actualHeading);
        Assert.assertEquals(actualHeading, expectedHeading);
    }

    // --- FRAMES ---
    @Given("I open the DemoQA Frames page")
    public void openFramesPage() {
        String url = ConfigReader.get("app.frames.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        framesPage = new DemoQaFramesPage();
    }

    @Then("the text in iframe {string} should be {string}")
    public void verifyIframeText(String frameId, String expectedText) {
        String actualText = framesPage.getTextFromFrame(frameId);
        Log.info("Frame [" + frameId + "] text: " + actualText);
        Assert.assertEquals(actualText, expectedText);
    }

    // --- NESTED FRAMES ---
    @Given("I open the DemoQA Nested Frames page")
    public void openNestedFramesPage() {
        String url = ConfigReader.get("app.nestedframes.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        nestedFramesPage = new DemoQaNestedFramesPage();
    }

    @Then("the parent frame text should be {string}")
    public void verifyParentFrameText(String expected) {
        String actual = nestedFramesPage.getParentFrameText();
        Assert.assertTrue(actual.contains(expected), "Parent frame missing: " + expected);
    }

    @Then("the child iframe text should be {string}")
    public void verifyChildIframeText(String expected) {
        String actual = nestedFramesPage.getChildIframeText();
        Assert.assertEquals(actual, expected);
    }

    // --- MODAL DIALOGS ---
    @Given("I open the DemoQA Modal Dialogs page")
    public void openModalDialogsPage() {
        String url = ConfigReader.get("app.modaldialogs.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        modalDialogsPage = new DemoQaModalDialogsPage();
    }

    @When("I open the Small Modal")
    public void openSmallModal() {
        modalDialogsPage.openSmallModal();
    }

    @Then("the small modal title should be {string}")
    public void verifySmallModalTitle(String expected) {
        Assert.assertEquals(modalDialogsPage.getSmallModalTitle(), expected);
    }

    @When("I close the Small Modal")
    public void closeSmallModal() {
        modalDialogsPage.closeSmallModal();
    }

    @When("I open the Large Modal")
    public void openLargeModal() {
        modalDialogsPage.openLargeModal();
    }

    @Then("the large modal title should be {string}")
    public void verifyLargeModalTitle(String expected) {
        Assert.assertEquals(modalDialogsPage.getLargeModalTitle(), expected);
    }

    @When("I close the Large Modal")
    public void closeLargeModal() {
        modalDialogsPage.closeLargeModal();
    }
}
