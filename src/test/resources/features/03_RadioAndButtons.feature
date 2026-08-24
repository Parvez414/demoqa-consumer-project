@DemoQa @Elements @Buttons
Feature: DemoQA Radio Buttons and Interactive Action Buttons Automation

  Scenario: Select radio button options and verify reactive status
    Given I open the DemoQA Radio Button page
    When I select the "Yes" radio button
    Then the radio selection result text should display "Yes"
    When I select the "Impressive" radio button
    Then the radio selection result text should display "Impressive"

  Scenario: Perform double click, right click, and dynamic clicks
    Given I open the DemoQA Buttons page
    When I perform a double click on the Double Click button
    Then the double click message should display "You have done a double click"
    When I perform a right click on the Right Click button
    Then the right click message should display "You have done a right click"
    When I perform a standard click on the Dynamic Click button
    Then the dynamic click message should display "You have done a dynamic click"
