@DemoQa @AlertsWindows @NestedFrames
Feature: DemoQA Nested iFrames Component Automation

  Background:
    Given I open the DemoQA Nested Frames page

  @Regression
  Scenario: Switch to parent frame and child iframe
    Then the parent frame text should be "Parent frame"
    And the child iframe text should be "Child Iframe"
