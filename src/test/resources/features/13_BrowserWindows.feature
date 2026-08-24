@DemoQa @AlertsWindows @BrowserWindows
Feature: DemoQA Multiple Browser Windows and Tabs Automation

  Background:
    Given I open the DemoQA Browser Windows page

  @Smoke
  Scenario: Open new browser tab and verify child page heading
    When I open a new tab and assert child page heading is "This is a sample page"

  @Smoke
  Scenario: Open new browser window and verify child window heading
    When I open a new window and assert child page heading is "This is a sample page"
