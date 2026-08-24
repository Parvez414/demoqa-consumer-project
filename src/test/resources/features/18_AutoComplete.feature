@DemoQa @Widgets @AutoComplete
Feature: DemoQA Auto Complete Tag and Single Selection Automation

  Background:
    Given I open the DemoQA Auto Complete page

  @Smoke
  Scenario: Add multiple color tags into autocomplete container and remove one tag
    When I add multiple colors "Red, Blue, Purple" into autocomplete
    Then the selected multiple color badges should contain "Red, Blue, Purple"
    When I remove the color badge "Blue"
    Then the selected multiple color badges should contain "Red, Purple"

  @Regression
  Scenario: Select single color in autocomplete input
    When I select single color "Yellow" into autocomplete
    Then the selected single color should be "Yellow"
