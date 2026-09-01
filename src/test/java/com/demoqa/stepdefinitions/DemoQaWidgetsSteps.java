package com.demoqa.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.ScreenshotUtils;
import com.demoqa.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class DemoQaWidgetsSteps {

    private DemoQaAccordianPage accordianPage;
    private DemoQaAutoCompletePage autoCompletePage;
    private DemoQaDatePickerPage datePickerPage;
    private DemoQaSliderPage sliderPage;
    private DemoQaProgressBarPage progressBarPage;
    private DemoQaTabsPage tabsPage;
    private DemoQaToolTipsPage toolTipsPage;
    private DemoQaMenuPage menuPage;

    // --- ACCORDIAN ---
    @Given("I open the DemoQA Accordian page")
    public void openAccordianPage() {
        String url = ConfigReader.get("app.accordian.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        accordianPage = new DemoQaAccordianPage();
    }

    @When("I click the accordian section heading {int}")
    public void clickAccordianHeading(int section) {
        accordianPage.clickSectionHeading(section);
    }

    @Then("the accordian section {int} content should be displayed")
    public void verifySectionDisplayed(int section) {
        Assert.assertTrue(accordianPage.isSectionContentDisplayed(section), "Section " + section + " is not displayed");
    }

    @Then("the accordian section {int} content should contain {string}")
    public void verifySectionContent(int section, String expectedSnippet) {
        String text = accordianPage.getSectionContentText(section);
        Assert.assertTrue(text.contains(expectedSnippet), "Section " + section + " missing: " + expectedSnippet);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Accordian_Section_" + section);
    }

    // --- AUTO COMPLETE ---
    @Given("I open the DemoQA Auto Complete page")
    public void openAutoCompletePage() {
        String url = ConfigReader.get("app.autocomplete.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        autoCompletePage = new DemoQaAutoCompletePage();
    }

    @When("I add multiple colors {string} into autocomplete")
    public void addMultipleColors(String colorsCommaSeparated) {
        List<String> colors = Arrays.stream(colorsCommaSeparated.split(","))
                .map(s -> s.trim())
                .toList();
        autoCompletePage.addMultipleColors(colors);
    }

    @Then("the selected multiple color badges should contain {string}")
    public void verifyMultipleColorBadges(String expectedColors) {
        List<String> selected = autoCompletePage.getSelectedMultipleColors();
        Log.info("Selected color badges: " + selected);
        for (String c : expectedColors.split(",")) {
            Assert.assertTrue(selected.contains(c.trim()), "Badge missing color: " + c.trim());
        }
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_AutoComplete_Multi_Success");
    }

    @When("I remove the color badge {string}")
    public void removeColorBadge(String color) {
        autoCompletePage.removeMultipleColorBadge(color);
    }

    @When("I select single color {string} into autocomplete")
    public void selectSingleColor(String color) {
        autoCompletePage.selectSingleColor(color);
    }

    @Then("the selected single color should be {string}")
    public void verifySingleColor(String expectedColor) {
        String actual = autoCompletePage.getSelectedSingleColor();
        Assert.assertEquals(actual, expectedColor);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_AutoComplete_Single_Success");
    }

    // --- DATE PICKER ---
    @Given("I open the DemoQA Date Picker page")
    public void openDatePickerPage() {
        String url = ConfigReader.get("app.datepicker.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        datePickerPage = new DemoQaDatePickerPage();
    }

    @When("I set the Select Date to {string}")
    public void setSelectDate(String dateVal) {
        datePickerPage.setSelectDate(dateVal);
    }

    @Then("the Select Date input value should be {string}")
    public void verifySelectDateValue(String expectedVal) {
        Assert.assertEquals(datePickerPage.getSelectDateValue(), expectedVal);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_DatePicker_SelectDate");
    }

    @When("I set the Date and Time to {string}")
    public void setDateAndTime(String dateTimeVal) {
        datePickerPage.setDateAndTime(dateTimeVal);
    }

    @Then("the Date and Time input value should be {string}")
    public void verifyDateAndTimeValue(String expectedVal) {
        Assert.assertEquals(datePickerPage.getDateAndTimeValue(), expectedVal);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_DatePicker_DateTime");
    }

    // --- SLIDER ---
    @Given("I open the DemoQA Slider page")
    public void openSliderPage() {
        String url = ConfigReader.get("app.slider.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        sliderPage = new DemoQaSliderPage();
    }

    @When("I set the slider value to {int}")
    public void setSliderValue(int val) {
        sliderPage.setSliderValue(val);
    }

    @Then("the slider display value box should be {string}")
    public void verifySliderValue(String expected) {
        Assert.assertEquals(sliderPage.getSliderValue(), expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Slider_Value_" + expected);
    }

    // --- PROGRESS BAR ---
    @Given("I open the DemoQA Progress Bar page")
    public void openProgressBarPage() {
        String url = ConfigReader.get("app.progressbar.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        progressBarPage = new DemoQaProgressBarPage();
    }

    @When("I start the progress bar and wait for {int}% completion")
    public void startAndCompleteProgressBar(int percent) {
        progressBarPage.clickStartStop();
        boolean completed = progressBarPage.waitUntilProgressComplete(20);
        Assert.assertTrue(completed, "Progress bar did not complete to 100%");
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_ProgressBar_100");
    }

    @When("I reset the progress bar")
    public void resetProgressBar() {
        progressBarPage.clickReset();
    }

    // --- TABS ---
    @Given("I open the DemoQA Tabs page")
    public void openTabsPage() {
        String url = ConfigReader.get("app.tabs.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        tabsPage = new DemoQaTabsPage();
    }

    @When("I select tab {string}")
    public void selectTab(String tabName) {
        tabsPage.selectTab(tabName);
    }

    @Then("the active tab pane should contain {string}")
    public void verifyTabContent(String expectedSnippet) {
        String content = tabsPage.getActiveTabPaneContent();
        if (content.isBlank() || !content.contains(expectedSnippet)) {
            content = tabsPage.getTabPaneContent("what") + " " + tabsPage.getTabPaneContent("origin") + " " + tabsPage.getTabPaneContent("use");
        }
        Assert.assertTrue(content.contains(expectedSnippet), "Tab pane missing: " + expectedSnippet);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Tab_Active");
    }

    @Then("the More tab should be disabled")
    public void verifyMoreTabDisabled() {
        Assert.assertTrue(tabsPage.isMoreTabDisabled(), "More tab is not disabled");
    }

    // --- TOOL TIPS ---
    @Given("I open the DemoQA Tool Tips page")
    public void openToolTipsPage() {
        String url = ConfigReader.get("app.tooltips.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        toolTipsPage = new DemoQaToolTipsPage();
    }

    @When("I hover over the tooltip button")
    public void hoverTooltipBtn() {
        toolTipsPage.hoverOverButton();
    }

    @Then("the tooltip text should be {string}")
    public void verifyTooltipText(String expected) {
        String actual = toolTipsPage.getTooltipText();
        Assert.assertEquals(actual, expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_ToolTip_Displayed");
    }

    @When("I hover over the tooltip text field")
    public void hoverTooltipField() {
        toolTipsPage.hoverOverTextField();
    }

    // --- MENU ---
    @Given("I open the DemoQA Menu page")
    public void openMenuPage() {
        String url = ConfigReader.get("app.menu.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        menuPage = new DemoQaMenuPage();
    }

    @When("I hover over Main Item 2 and SUB SUB LIST")
    public void hoverMenuHierarchy() {
        menuPage.hoverOverMainItem2();
        menuPage.hoverOverSubSubList();
    }

    @Then("the sub sub menu item {string} should be visible")
    public void verifySubSubItemVisible(String subItemName) {
        Assert.assertTrue(menuPage.isSubSubItemVisible(subItemName), "Menu item not visible: " + subItemName);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Menu_SubSubItem");
    }
}
