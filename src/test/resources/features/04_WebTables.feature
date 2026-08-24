@DemoQa @Elements @WebTables
Feature: DemoQA Web Tables Search, CRUD and Pagination Automation

  Background:
    Given I open the DemoQA Web Tables page

  @Smoke
  Scenario: Search for existing employee records in the web table
    When I search the table for "Cierra"
    Then the table rows should contain "Cierra"
    And the table rows should contain "Vega"

  @Regression
  Scenario: Add a new employee record and verify in table
    When I add a new record using profile "newEmployee" from JSON
    And I search the table for "Alexander"
    Then the table rows should contain "Alexander"
    And the table rows should contain "Wright"
    And the table rows should contain "Quality Engineering"

  @Regression
  Scenario: Edit an existing employee record
    When I edit the record for "Cierra" with salary "120000" and department "Architecture & AI"
    And I search the table for "Cierra"
    Then the table rows should contain "120000"
    And the table rows should contain "Architecture & AI"

  @Regression
  Scenario: Delete an employee record from table
    When I delete the record for "Alden"
    And I search the table for "Alden"
    Then the table rows should contain ""

  @Regression
  Scenario Outline: Change rows per page in Web Table
    When I select rows per page as "<Rows>"
    Then the table rows should contain "Cierra"

    Examples:
      | Rows |
      | 5    |
      | 10   |
      | 20   |
