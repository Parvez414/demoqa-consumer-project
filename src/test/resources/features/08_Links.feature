@DemoQa @Elements @Links
Feature: DemoQA Links and API Status Response Automation

  Background:
    Given I open the DemoQA Links page

  @Smoke
  Scenario: Click Simple Home link and switch to new browser tab
    When I click the Simple link and switch to new tab
    Then the new tab URL should contain "demoqa.com"

  @Regression
  Scenario Outline: Click API response links and verify status code response
    When I click the API link "<LinkType>"
    Then the link status response text should contain "<StatusCode>"
    And the link status response text should contain "<StatusText>"

    Examples:
      | LinkType     | StatusCode | StatusText   |
      | Created      | 201        | Created      |
      | No Content   | 204        | No Content   |
      | Moved        | 301        | Moved        |
      | Bad Request  | 400        | Bad Request  |
      | Unauthorized | 401        | Unauthorized |
      | Forbidden    | 403        | Forbidden    |
      | Not Found    | 404        | Not Found    |
