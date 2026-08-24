@DemoQa @AlertsWindows @ModalDialogs
Feature: DemoQA Modal Dialogs Automation

  Background:
    Given I open the DemoQA Modal Dialogs page

  @Smoke
  Scenario: Trigger and dismiss Small Modal
    When I open the Small Modal
    Then the small modal title should be "Small Modal"
    When I close the Small Modal

  @Regression
  Scenario: Trigger and dismiss Large Modal
    When I open the Large Modal
    Then the large modal title should be "Large Modal"
    When I close the Large Modal
