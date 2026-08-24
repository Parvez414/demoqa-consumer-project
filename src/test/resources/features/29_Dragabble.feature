@DemoQa @Interactions @Dragabble
Feature: DemoQA Dragabble Position Automation

  Background:
    Given I open the DemoQA Dragabble page

  @Regression
  Scenario: Drag element box by coordinate offset
    When I drag the drag box by offset 40 and 40
    Then the drag box position should be updated
