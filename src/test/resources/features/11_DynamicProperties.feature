@DemoQa @Elements @DynamicProperties
Feature: DemoQA Dynamic Properties and Delayed State Automation

  Background:
    Given I open the DemoQA Dynamic Properties page

  @Regression
  Scenario: Verify button enables after 5 seconds delay
    Then the enable after button should become clickable within 6 seconds

  @Regression
  Scenario: Verify button color change after 5 seconds
    Then the color change button should transition color within 6 seconds

  @Regression
  Scenario: Verify button becomes visible after 5 seconds
    Then the visible after button should become visible within 6 seconds
