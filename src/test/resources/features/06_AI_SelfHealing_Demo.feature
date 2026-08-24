@DemoQa @SelfHealing
Feature: Cross-Application AI Dynamic Self-Healing Verification on DemoQA

  Scenario: AI Pre-Flight Agent heals deliberately broken locators on DemoQA
    Given I open the DemoQA Text Box page
    When the AI pre-flight agent validates the broken DemoQA page elements
    And I enter "Healed User" into the broken user name field
    And I click the broken submit button
    Then the AI element healing JSON report should be generated in the demoqa project
