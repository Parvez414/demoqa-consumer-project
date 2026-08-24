@DemoQa @Widgets @Accordian
Feature: DemoQA Accordion Collapsible Panels Automation

  Background:
    Given I open the DemoQA Accordian page

  @Smoke
  Scenario: Verify first accordion section is expanded by default
    Then the accordian section 1 content should be displayed
    And the accordian section 1 content should contain "Lorem Ipsum is simply dummy text"

  @Regression
  Scenario: Toggle second accordion section and verify collapse/expand behavior
    When I click the accordian section heading 2
    Then the accordian section 2 content should be displayed
    And the accordian section 2 content should contain "Contrary to popular belief"
