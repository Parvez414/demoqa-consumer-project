@DemoQa @AlertsWindows @Frames
Feature: DemoQA iFrames Component Automation

  Background:
    Given I open the DemoQA Frames page

  @Regression
  Scenario: Switch to first iframe and verify heading text
    Then the text in iframe "frame1" should be "This is a sample page"

  @Regression
  Scenario: Switch to second iframe and verify heading text
    Then the text in iframe "frame2" should be "This is a sample page"
