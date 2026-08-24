package com.demoqa.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.driver.DriverManager;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.ScreenshotUtils;
import com.demoqa.pages.DemoQaSelectMenuPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class DemoQaSelectMenuSteps {

    private DemoQaSelectMenuPage selectMenuPage;

    @Given("I open the DemoQA Select Menu page")
    public void openSelectMenuPage() {
        String url = ConfigReader.get("app.selectmenu.url");
        Log.info("Navigating to DemoQA Select Menu page: " + url);
        DriverManager.getDriver().get(url);
        ElementActions.pause(1000);
        selectMenuPage = new DemoQaSelectMenuPage();
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Select_Menu_Page_Loaded");
    }

    @When("I select option {string} from the Select Value group dropdown")
    public void selectGroupOption(String option) {
        selectMenuPage.selectGroupOption(option);
    }

    @Then("the selected group option should be {string}")
    public void verifyGroupOption(String expected) {
        String actual = selectMenuPage.getSelectedGroupOptionText();
        Log.info("Verified group option: [" + actual + "]");
        Assert.assertEquals(actual, expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Group_Option_Selected");
    }

    @When("I select title {string} from the Select One dropdown")
    public void selectTitleOne(String title) {
        selectMenuPage.selectTitleOne(title);
    }

    @Then("the selected title should be {string}")
    public void verifyTitleOne(String expected) {
        String actual = selectMenuPage.getSelectedTitleOneText();
        Log.info("Verified title one: [" + actual + "]");
        Assert.assertEquals(actual, expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Title_One_Selected");
    }

    @When("I select color {string} from the Old Style Select Menu")
    public void selectOldStyleColor(String color) {
        selectMenuPage.selectOldStyleOption(color);
    }

    @Then("the selected old style color should be {string}")
    public void verifyOldStyleColor(String expected) {
        String actual = selectMenuPage.getSelectedOldStyleOption();
        Log.info("Verified old style color: [" + actual + "]");
        Assert.assertEquals(actual, expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_OldStyle_Color_Selected");
    }

    @When("I select multiple colors {string} from the React multiselect dropdown")
    public void selectMultipleReactColors(String colorsCsv) {
        List<String> colors = Arrays.stream(colorsCsv.split(",")).map(s -> s.trim()).toList();
        selectMenuPage.selectMultiColorOptions(colors);
    }

    @Then("the selected React multiselect badges should contain {string}")
    public void verifyMultipleReactBadges(String colorsCsv) {
        List<String> expected = Arrays.stream(colorsCsv.split(",")).map(s -> s.trim()).toList();
        List<String> actual = selectMenuPage.getSelectedMultiColorOptions();
        Log.info("Verified multi-select badges: " + actual);
        for (String exp : expected) {
            Assert.assertTrue(actual.contains(exp), "Missing selected badge: " + exp);
        }
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_MultiColor_Badges_Selected");
    }

    @When("I select cars {string} from the Standard multi-select")
    public void selectStandardCars(String carsCsv) {
        List<String> cars = Arrays.stream(carsCsv.split(",")).map(s -> s.trim()).toList();
        selectMenuPage.selectStandardCars(cars);
    }

    @Then("the selected cars should contain {string}")
    public void verifySelectedCars(String carsCsv) {
        List<String> expected = Arrays.stream(carsCsv.split(",")).map(s -> s.trim()).toList();
        List<String> actual = selectMenuPage.getSelectedStandardCars();
        Log.info("Verified selected standard cars: " + actual);
        for (String exp : expected) {
            Assert.assertTrue(actual.contains(exp), "Missing selected car: " + exp);
        }
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Standard_Cars_Selected");
    }
}
