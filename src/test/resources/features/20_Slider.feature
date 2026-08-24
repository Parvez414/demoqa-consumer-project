@DemoQa @Widgets @Slider
Feature: DemoQA Range Slider Control Automation

  Background:
    Given I open the DemoQA Slider page

  @Regression
  Scenario Outline: Adjust range slider to various values
    When I set the slider value to <TargetValue>
    Then the slider display value box should be "<TargetValue>"

    Examples:
      | TargetValue |
      | 50          |
      | 75          |
      | 90          |
