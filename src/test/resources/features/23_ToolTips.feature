@DemoQa @Widgets @ToolTips
Feature: DemoQA Tooltip Popover Automation

  Background:
    Given I open the DemoQA Tool Tips page

  @Regression
  Scenario: Hover over button and assert tooltip popover text
    When I hover over the tooltip button
    Then the tooltip text should be "You hovered over the Button"

  @Regression
  Scenario: Hover over textfield and assert tooltip popover text
    When I hover over the tooltip text field
    Then the tooltip text should be "You hovered over the text field"
