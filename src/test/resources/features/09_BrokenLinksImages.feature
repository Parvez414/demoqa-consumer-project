@DemoQa @Elements @Broken
Feature: DemoQA Broken Links and Images Automation

  Background:
    Given I open the DemoQA Broken Links and Images page

  @Regression
  Scenario: Validate display of valid image and detection of broken image
    Then the valid image should be rendered successfully
    And the broken image should be detected as broken

  @Regression
  Scenario: Click valid link navigation
    When I click the valid link on broken page

  @Regression
  Scenario: Click broken link navigation
    When I click the broken link on broken page
