package com.demoqa.pages;

import com.automation.components.ButtonComponent;
import com.automation.driver.DriverManager;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.JavaScriptUtils;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class DemoQaWebTablesPage extends BasePage {

    public ButtonComponent addNewRecordBtn;
    public ButtonComponent submitRecordBtn;

    public DemoQaWebTablesPage() {
        super("DemoQaWebTablesPage");
    }

    @Override
    protected void initElements() {
        register("searchBox", "Search web tables input field", By.id("searchBox"));
        register("addNewRecordButton", "Add new record button", By.id("addNewRecordButton"));
        register("firstNameInput", "Registration form First Name", By.id("firstName"));
        register("lastNameInput", "Registration form Last Name", By.id("lastName"));
        register("userEmailInput", "Registration form Email", By.id("userEmail"));
        register("ageInput", "Registration form Age", By.id("age"));
        register("salaryInput", "Registration form Salary", By.id("salary"));
        register("departmentInput", "Registration form Department", By.id("department"));
        register("submitButton", "Registration modal Submit button", By.id("submit"));
        register("tableContainer", "Web table container element", By.className("rt-table"));

        addNewRecordBtn = initComponent(ButtonComponent.class, getElement("addNewRecordButton"));
        submitRecordBtn = initComponent(ButtonComponent.class, getElement("submitButton"));
    }

    public void searchTable(String query) {
        Log.info("Searching Web Table for: [" + query + "]");
        WebElement search = com.automation.utils.WaitUtils.waitForVisibility(getElement("searchBox").getCurrentBy(), 10);
        search.click();
        search.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        search.sendKeys(query);
        ElementActions.pause(600);
    }

    public void clickAddNewRecord() {
        Log.info("Clicking Add New Record button");
        click(getElement("addNewRecordButton"));
        ElementActions.pause(400);
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String age, String salary, String department) {
        Log.info("Filling Registration Form for: " + firstName + " " + lastName);
        sendKeys(getElement("firstNameInput"), firstName);
        sendKeys(getElement("lastNameInput"), lastName);
        sendKeys(getElement("userEmailInput"), email);
        sendKeys(getElement("ageInput"), age);
        sendKeys(getElement("salaryInput"), salary);
        sendKeys(getElement("departmentInput"), department);
        
        WebElement submit = DriverManager.getDriver().findElement(getElement("submitButton").getCurrentBy());
        JavaScriptUtils.clickElement(submit);
        ElementActions.pause(800);
    }

    public void editRecord(String firstName, String newSalary, String newDept) {
        Log.info("Editing record for [" + firstName + "] with salary: " + newSalary + ", dept: " + newDept);
        try {
            By by = By.xpath(
                    "//div[contains(@class,'rt-tr-group') or contains(@class,'rt-tr')][.//div[contains(text(),'" + firstName + "')]]//span[@title='Edit' or contains(@id,'edit')] | " +
                    "//span[@title='Edit']"
            );
            WebElement editBtn = com.automation.utils.WaitUtils.waitForPresence(by, 8);
            JavaScriptUtils.clickElement(editBtn);
        } catch (Exception e) {
            JavaScriptUtils.executeScript(
                    "let rows = Array.from(document.querySelectorAll('.rt-tr-group, .rt-tr'));" +
                    "let row = rows.find(r => r.textContent.includes(arguments[0]));" +
                    "let btn = row ? (row.querySelector('span[title=\"Edit\"], [id*=\"edit\"]') || row.querySelector('.action-buttons span')) : document.querySelector('span[title=\"Edit\"]');" +
                    "if (btn) btn.click();",
                    firstName
            );
        }
        ElementActions.pause(500);

        WebElement salaryInput = com.automation.utils.WaitUtils.waitForPresence(By.id("salary"), 5);
        salaryInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        salaryInput.sendKeys(newSalary);

        WebElement deptInput = DriverManager.getDriver().findElement(By.id("department"));
        deptInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        deptInput.sendKeys(newDept);

        WebElement submit = DriverManager.getDriver().findElement(By.id("submit"));
        JavaScriptUtils.clickElement(submit);
        ElementActions.pause(600);
    }

    public void deleteRecord(String firstName) {
        Log.info("Deleting record for: [" + firstName + "]");
        try {
            By by = By.xpath(
                    "//div[contains(@class,'rt-tr-group') or contains(@class,'rt-tr')][.//div[contains(text(),'" + firstName + "')]]//span[@title='Delete' or contains(@id,'delete')] | " +
                    "//span[@title='Delete']"
            );
            WebElement deleteBtn = com.automation.utils.WaitUtils.waitForPresence(by, 8);
            JavaScriptUtils.clickElement(deleteBtn);
        } catch (Exception e) {
            JavaScriptUtils.executeScript(
                    "let rows = Array.from(document.querySelectorAll('.rt-tr-group, .rt-tr'));" +
                    "let row = rows.find(r => r.textContent.includes(arguments[0]));" +
                    "let btn = row ? (row.querySelector('span[title=\"Delete\"], [id*=\"delete\"]') || row.querySelectorAll('.action-buttons span')[1]) : document.querySelector('span[title=\"Delete\"]');" +
                    "if (btn) btn.click();",
                    firstName
            );
        }
        ElementActions.pause(500);
    }

    public void selectRowsPerPage(String rowsCount) {
        Log.info("Selecting page size rows: " + rowsCount);
        try {
            By by = By.cssSelector("select[aria-label='rows per page'], .select-wrap select, .-pageSizeOptions select, select");
            WebElement selectEl = com.automation.utils.WaitUtils.waitForPresence(by, 8);
            JavaScriptUtils.scrollIntoView(selectEl);
            Select select = new Select(selectEl);
            select.selectByValue(rowsCount);
        } catch (Exception e) {
            JavaScriptUtils.executeScript(
                    "let sel = document.querySelector(\"select[aria-label='rows per page'], .select-wrap select, .-pageSizeOptions select, select\");" +
                    "if (sel) { sel.value = arguments[0]; sel.dispatchEvent(new Event('change', { bubbles: true })); }",
                    rowsCount
            );
        }
        ElementActions.pause(500);
    }

    public List<String> getTableRowsText() {
        ElementActions.pause(400);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        String tableText = (String) js.executeScript(
                "var el = document.querySelector('.rt-table') || document.querySelector('.ReactTable') || document.querySelector('.rt-tbody');" +
                "return el ? el.innerText : document.body.innerText;"
        );
        if (tableText == null || tableText.isBlank()) {
            return List.of();
        }
        return Arrays.stream(tableText.split("\n"))
                .map(s -> s.trim())
                .filter(t -> !t.isBlank() && !t.equals("\u00a0"))
                .toList();
    }
}
