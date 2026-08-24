@DemoQa @Widgets @Tabs
Feature: DemoQA Tabbed Navigation Automation

  Background:
    Given I open the DemoQA Tabs page

  @Smoke
  Scenario: Switch between active tabs and verify disabled tab
    When I select tab "What"
    Then the active tab pane should contain "Lorem Ipsum is simply dummy text"
    When I select tab "Origin"
    Then the active tab pane should contain "Contrary to popular belief"
    When I select tab "Use"
    Then the active tab pane should contain "It is a long established fact"
    And the More tab should be disabled
