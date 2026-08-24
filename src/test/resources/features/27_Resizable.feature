@DemoQa @Interactions @Resizable
Feature: DemoQA Resizable Dimension Element Automation

  Background:
    Given I open the DemoQA Resizable page

  @Regression
  Scenario: Resize restricted element box
    When I resize the restricted box by width 50 and height 50
    Then the restricted box dimensions should be greater than initial size
