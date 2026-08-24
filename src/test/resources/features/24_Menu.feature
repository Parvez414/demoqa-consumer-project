@DemoQa @Widgets @Menu
Feature: DemoQA Multi-Tier Nested Hover Menu Automation

  Background:
    Given I open the DemoQA Menu page

  @Regression
  Scenario: Hover through nested sub menus
    When I hover over Main Item 2 and SUB SUB LIST
    Then the sub sub menu item "Sub Sub Item 1" should be visible
    And the sub sub menu item "Sub Sub Item 2" should be visible
