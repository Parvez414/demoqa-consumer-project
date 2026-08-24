@DemoQa @BookStore @Login
Feature: DemoQA Book Store User Authentication Automation

  Background:
    Given I open the DemoQA Login page

  @Regression
  Scenario: Attempt login with invalid user credentials and verify rejection message
    When I attempt login using profile "invalidLogin" from JSON
    Then the login error message should display "Invalid username or password!"
