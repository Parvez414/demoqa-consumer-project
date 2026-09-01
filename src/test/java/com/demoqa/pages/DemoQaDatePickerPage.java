package com.demoqa.pages;

import com.automation.components.DatePickerComponent;
import com.automation.pages.BasePage;
import com.automation.utils.ElementActions;
import com.automation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DemoQaDatePickerPage extends BasePage {

    public DatePickerComponent datePicker;

    public DemoQaDatePickerPage() {
        super("DemoQaDatePickerPage");
    }

    @Override
    protected void initElements() {
        register("selectDateInput", "Select Date input field", By.id("datePickerMonthYearInput"));
        register("dateAndTimeInput", "Date and Time picker input field", By.id("dateAndTimePickerInput"));

        datePicker = initComponent(DatePickerComponent.class, getElement("selectDateInput"));
    }

    public void setSelectDate(String dateString) {
        Log.info("Setting Select Date to: " + dateString);
        WebElement input = waitForVisibility(getElement("selectDateInput"));
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        sendKeys(getElement("selectDateInput"), dateString);
        input.sendKeys(Keys.ENTER);
        ElementActions.pause(300);
    }

    public String getSelectDateValue() {
        WebElement input = waitForVisibility(getElement("selectDateInput"));
        return input.getAttribute("value");
    }

    public void setDateAndTime(String dateTimeString) {
        Log.info("Setting Date and Time to: " + dateTimeString);
        WebElement input = waitForVisibility(getElement("dateAndTimeInput"));
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        sendKeys(getElement("dateAndTimeInput"), dateTimeString);
        input.sendKeys(Keys.ENTER);
        ElementActions.pause(300);
    }

    public String getDateAndTimeValue() {
        WebElement input = waitForVisibility(getElement("dateAndTimeInput"));
        return input.getAttribute("value");
    }
}
