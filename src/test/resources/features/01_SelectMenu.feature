@DemoQa @SelectMenu
Feature: DemoQA Comprehensive Select Menu Component Automation

  Background:
    Given I open the DemoQA Select Menu page

  @Smoke @SelectValue
  Scenario: Select Option with Grouping from React Select dropdown
    When I select option "Group 2, option 1" from the Select Value group dropdown
    Then the selected group option should be "Group 2, option 1"

  @Smoke @SelectTitle
  Scenario: Select Title from React Select One dropdown
    When I select title "Dr." from the Select One dropdown
    Then the selected title should be "Dr."

  @Regression @OldSelect
  Scenario Outline: Select colors from Old Style standard HTML Select Menu
    When I select color "<Color>" from the Old Style Select Menu
    Then the selected old style color should be "<Color>"

    Examples:
      | Color   |
      | Purple  |
      | Aqua    |
      | Voilet  |

  @Regression @MultiSelectReact
  Scenario: Select multiple colors from React multiselect dropdown
    When I select multiple colors "Green, Blue, Black" from the React multiselect dropdown
    Then the selected React multiselect badges should contain "Green, Blue, Black"

  @Regression @StandardCars
  Scenario: Select multiple cars from Standard HTML multi-select
    When I select cars "Volvo, Audi, Saab" from the Standard multi-select
    Then the selected cars should contain "Volvo, Audi, Saab"
