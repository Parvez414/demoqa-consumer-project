@DemoQa @Alerts
Feature: DemoQA JavaScript Popups and Modal Alerts Automation

  Background:
    Given I open the DemoQA Alerts page

  Scenario: Trigger and accept simple JavaScript alert
    When I trigger and accept the simple alert

  Scenario: Trigger confirm box and handle acceptance
    When I trigger the confirm alert and choose "Accept"
    Then the confirm selection result should be "You selected Ok"

  Scenario: Trigger prompt alert, provide custom input and verify result
    When I trigger the prompt alert, enter "Automation Engineer" and accept
    Then the prompt response result should display "You entered Automation Engineer"
