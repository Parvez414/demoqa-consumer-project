@DemoQa @Elements @TextBox
Feature: DemoQA Text Box User Input Form Automation

  Scenario: Submit user details using strongly-typed JSON profile
    Given I open the DemoQA Text Box page
    When I submit the text box form using profile "validUser" from JSON
    Then the output card should contain "Sarah Jenkins"
    And the output card should contain "sarah.jenkins@testmail.com"
    And the output card should contain "123 Innovation Way"
