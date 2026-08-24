@DemoQa @Interactions @Selectable
Feature: DemoQA Multi-Selection Component Automation

  Background:
    Given I open the DemoQA Selectable page

  @Smoke
  Scenario: Select items in vertical list
    When I select list item "Cras justo odio"
    Then the list item "Cras justo odio" should have active selection styling
    When I select list item "Dapibus ac facilisis in"
    Then the list item "Dapibus ac facilisis in" should have active selection styling

  @Regression
  Scenario: Select items in grid view
    When I switch to Selectable Grid tab
    And I select grid item "One"
    Then the grid item "One" should have active selection styling
    When I select grid item "Five"
    Then the grid item "Five" should have active selection styling
