@DemoQa @Interactions @Sortable
Feature: DemoQA Drag and Drop Sortable Automation

  Background:
    Given I open the DemoQA Sortable page

  @Regression
  Scenario: Drag list items to reorder
    When I drag sortable list item "One" to position of "Two"
    Then the sortable list items should contain "One"
    And the sortable list items should contain "Two"
