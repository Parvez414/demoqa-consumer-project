@DemoQa @Interactions @Droppable
Feature: DemoQA Drag and Drop Target Automation

  Background:
    Given I open the DemoQA Droppable page

  @Smoke @Regression
  Scenario: Drag and drop draggable onto target drop area
    When I drag the draggable element to the drop target
    Then the droppable target text should update to "Dropped!"
