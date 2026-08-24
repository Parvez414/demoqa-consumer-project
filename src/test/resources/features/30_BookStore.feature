@DemoQa @BookStore
Feature: DemoQA Book Store Application Search and Details Automation

  Background:
    Given I open the DemoQA Book Store page

  @Smoke
  Scenario: Search for book in Book Store catalog
    When I search for books with query "Git Pocket Guide"
    Then the book search results should contain "Git Pocket Guide"

  @Regression
  Scenario: View book details and return to store
    When I search for books with query "Git Pocket Guide"
    And I click the book title "Git Pocket Guide"
    Then the book "Author" field should be "Richard E. Silverman"
    And the book "Publisher" field should be "O'Reilly Media"
    When I click Back To Book Store button
