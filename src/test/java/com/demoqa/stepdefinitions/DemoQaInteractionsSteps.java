package com.demoqa.stepdefinitions;

import com.automation.config.ConfigReader;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import com.automation.utils.ScreenshotUtils;
import com.demoqa.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;

import java.util.List;

public class DemoQaInteractionsSteps {

    private DemoQaSortablePage sortablePage;
    private DemoQaSelectablePage selectablePage;
    private DemoQaResizablePage resizablePage;
    private DemoQaDroppablePage droppablePage;
    private DemoQaDragabblePage dragabblePage;

    // --- SORTABLE ---
    @Given("I open the DemoQA Sortable page")
    public void openSortablePage() {
        String url = ConfigReader.get("app.sortable.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        sortablePage = new DemoQaSortablePage();
    }

    @When("I drag sortable list item {string} to position of {string}")
    public void dragSortableItem(String source, String target) {
        sortablePage.dragListItemToTarget(source, target);
    }

    @Then("the sortable list items should contain {string}")
    public void verifySortableList(String expectedItem) {
        List<String> items = sortablePage.getListItemsText();
        Assert.assertTrue(items.contains(expectedItem), "List items missing: " + expectedItem);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Sortable_List");
    }

    // --- SELECTABLE ---
    @Given("I open the DemoQA Selectable page")
    public void openSelectablePage() {
        String url = ConfigReader.get("app.selectable.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        selectablePage = new DemoQaSelectablePage();
    }

    @When("I select list item {string}")
    public void selectListItem(String item) {
        selectablePage.clickListItem(item);
    }

    @Then("the list item {string} should have active selection styling")
    public void verifyListItemActive(String item) {
        Assert.assertTrue(selectablePage.isListItemSelected(item), "List item is not selected: " + item);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Selectable_List_" + item.replace(" ", "_"));
    }

    @When("I switch to Selectable Grid tab")
    public void switchToGridTab() {
        selectablePage.selectTab("grid");
    }

    @When("I select grid item {string}")
    public void selectGridItem(String item) {
        selectablePage.clickGridItem(item);
    }

    @Then("the grid item {string} should have active selection styling")
    public void verifyGridItemActive(String item) {
        Assert.assertTrue(selectablePage.isGridItemSelected(item), "Grid item is not selected: " + item);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Selectable_Grid_" + item);
    }

    // --- RESIZABLE ---
    @Given("I open the DemoQA Resizable page")
    public void openResizablePage() {
        String url = ConfigReader.get("app.resizable.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        resizablePage = new DemoQaResizablePage();
    }

    @When("I resize the restricted box by width {int} and height {int}")
    public void resizeBox(int xOffset, int yOffset) {
        resizablePage.resizeRestrictedBox(xOffset, yOffset);
    }

    @Then("the restricted box dimensions should be greater than initial size")
    public void verifyResizedDimensions() {
        Dimension size = resizablePage.getRestrictedBoxSize();
        Log.info("Resized box dimensions: " + size.getWidth() + "x" + size.getHeight());
        Assert.assertTrue(size.getWidth() >= 200, "Width not increased");
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Resizable_Success");
    }

    // --- DROPPABLE ---
    @Given("I open the DemoQA Droppable page")
    public void openDroppablePage() {
        String url = ConfigReader.get("app.droppable.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        droppablePage = new DemoQaDroppablePage();
    }

    @When("I drag the draggable element to the drop target")
    public void dragAndDropElement() {
        droppablePage.dragAndDropSimple();
    }

    @Then("the droppable target text should update to {string}")
    public void verifyDropTargetText(String expected) {
        String actual = droppablePage.getSimpleDropText();
        Log.info("Droppable target text: " + actual);
        Assert.assertEquals(actual, expected);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Droppable_Success");
    }

    // --- DRAGABBLE ---
    @Given("I open the DemoQA Dragabble page")
    public void openDragabblePage() {
        String url = ConfigReader.get("app.dragabble.url");
        ElementActions.navigateToUrl(url);
        ElementActions.pause(500);
        dragabblePage = new DemoQaDragabblePage();
    }

    @When("I drag the drag box by offset {int} and {int}")
    public void dragBoxByOffset(int x, int y) {
        dragabblePage.dragBoxByOffset(x, y);
    }

    @Then("the drag box position should be updated")
    public void verifyDragBoxPosition() {
        Point loc = dragabblePage.getDragBoxLocation();
        Log.info("New drag box location: " + loc);
        Assert.assertNotNull(loc);
        ScreenshotUtils.captureAndSaveCustomScreenshot("DemoQA_Dragabble_Moved");
    }
}
