@DemoQa @Elements @CheckBox
Feature: DemoQA Tree Node Checkbox Automation

  Background:
    Given I open the DemoQA Check Box page

  @Smoke
  Scenario: Expand all tree nodes and toggle Home checkbox
    When I expand all checkbox tree nodes
    And I toggle the checkbox node "Home"
    Then the checkbox result display should contain "home"
    And the checkbox result display should contain "desktop"
    And the checkbox result display should contain "documents"
    And the checkbox result display should contain "downloads"

  @Regression
  Scenario: Select specific subfolder nodes in Checkbox tree
    When I expand all checkbox tree nodes
    And I toggle the checkbox node "Desktop"
    Then the checkbox result display should contain "desktop"
    And the checkbox result display should contain "notes"
    And the checkbox result display should contain "commands"
