@DemoQa @Widgets @DatePicker
Feature: DemoQA Date Picker Calendar and DateTime Automation

  Background:
    Given I open the DemoQA Date Picker page

  @Smoke
  Scenario: Select custom date in date picker
    When I set the Select Date to "08/15/2026"
    Then the Select Date input value should be "08/15/2026"

  @Regression
  Scenario: Select custom date and time in date time picker
    When I set the Date and Time to "August 15, 2026 5:30 PM"
    Then the Date and Time input value should be "August 15, 2026 5:30 PM"
