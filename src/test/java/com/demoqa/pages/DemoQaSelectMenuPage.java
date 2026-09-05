package com.demoqa.pages;

import com.automation.components.SelectComponent;
import com.automation.pages.BasePage;
import com.automation.utils.Log;
import org.openqa.selenium.By;

import java.util.List;

/**
 * DemoQA Select Menu Page Object.
 * Demonstrates Component-Based Architecture utilizing SelectComponent
 * for native HTML and SPA/React dropdowns.
 */
public class DemoQaSelectMenuPage extends BasePage {

    public SelectComponent selectValueDropdown;
    public SelectComponent selectOneDropdown;
    public SelectComponent oldSelectMenuDropdown;
    public SelectComponent multiSelectDropdown;
    public SelectComponent standardMultiSelectDropdown;

    public DemoQaSelectMenuPage() {
        super("DemoQaSelectMenuPage");
    }

    @Override
    protected void initElements() {
        register("pageHeader", "Select Menu main title header", By.className("main-header"));
        register("selectValueDropdown", "Select Value react dropdown input container", By.id("withOptGroup"));
        register("selectOneDropdown", "Select One title react dropdown container", By.id("selectOne"));
        register("oldSelectMenu", "Old style HTML standard select dropdown", By.id("oldSelectMenu"));
        register("multiSelectInput", "Multiselect react dropdown input container", By.xpath("//input[contains(@id,'react-select-4')]"));
        register("standardMultiSelect", "Standard HTML multi select cars", By.id("cars"));

        // Initialize reusable SelectComponent for each dropdown type
        selectValueDropdown = initComponent(SelectComponent.class, getElement("selectValueDropdown"));
        selectOneDropdown = initComponent(SelectComponent.class, getElement("selectOneDropdown"));
        oldSelectMenuDropdown = initComponent(SelectComponent.class, getElement("oldSelectMenu"));
        multiSelectDropdown = initComponent(SelectComponent.class, getElement("multiSelectInput"));
        standardMultiSelectDropdown = initComponent(SelectComponent.class, getElement("standardMultiSelect"));
    }

    public void selectGroupOption(String optionText) {
        Log.info("Selecting Option: [" + optionText + "] using SelectComponent on selectValueDropdown");
        selectValueDropdown.selectByText(optionText);
    }

    public String getSelectedGroupOptionText() {
        return selectValueDropdown.getSelectedText();
    }

    public void selectTitleOne(String titleText) {
        Log.info("Selecting Title: [" + titleText + "] using SelectComponent on selectOneDropdown");
        selectOneDropdown.selectByText(titleText);
    }

    public String getSelectedTitleOneText() {
        return selectOneDropdown.getSelectedText();
    }

    public void selectOldStyleOption(String color) {
        Log.info("Selecting Option: [" + color + "] using SelectComponent on oldSelectMenuDropdown");
        oldSelectMenuDropdown.selectByVisibleText(color);
    }

    public String getSelectedOldStyleOption() {
        return oldSelectMenuDropdown.getSelectedText();
    }

    public void selectMultiColorOptions(List<String> colors) {
        Log.info("Selecting Multiple Colors: " + colors + " using SelectComponent on multiSelectDropdown");
        multiSelectDropdown.selectMultipleByText(colors);
    }

    public List<String> getSelectedMultiColorOptions() {
        return multiSelectDropdown.getAllSelectedTexts();
    }

    public void selectStandardCars(List<String> carNames) {
        Log.info("Selecting Cars: " + carNames + " using SelectComponent on standardMultiSelectDropdown");
        standardMultiSelectDropdown.selectMultipleByText(carNames);
    }

    public List<String> getSelectedStandardCars() {
        return standardMultiSelectDropdown.getAllSelectedTexts();
    }
}
