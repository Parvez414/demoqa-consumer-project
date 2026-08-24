@DemoQa @Forms @PracticeForm
Feature: DemoQA Student Registration Practice Form Automation

  Background:
    Given I open the DemoQA Automation Practice Form page

  @Smoke @Regression
  Scenario: Complete full student registration form submission and verify modal summary
    When I fill and submit the student registration form using profile "studentRegistration" from JSON
    Then the student registration submission modal title should be "Thanks for submitting the form"
    And the student submission summary table should contain "Emily Davis"
    And the student submission summary table should contain "emily.davis@university.edu"
    And the student submission summary table should contain "Female"
    And the student submission summary table should contain "9876543210"
    And the student submission summary table should contain "15 July,1998"
    And the student submission summary table should contain "Maths, Computer Science"
    And the student submission summary table should contain "Sports, Reading, Music"
    And the student submission summary table should contain "456 Academy Blvd, Cambridge, MA"
    And the student submission summary table should contain "NCR Delhi"
    When I close the student registration modal
