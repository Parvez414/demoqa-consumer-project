@DemoQa @Widgets @ProgressBar
Feature: DemoQA Progress Bar State Automation

  Background:
    Given I open the DemoQA Progress Bar page

  @Regression
  Scenario: Start progress bar and wait for full completion then reset
    When I start the progress bar and wait for 100% completion
    And I reset the progress bar
