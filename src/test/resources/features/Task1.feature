
Feature: Enter a number validation

  Scenario Outline: Verify error messages for invalid numbers
    Given I am on enter a number page
    When I enter number "<number>"
    And I click number submit
    Then I should see number error: "<error>"

    Examples:
      | number | error                    |
      | 20     | Number is too small      |
      | 150    | Number is too big        |
      | abc    | Please enter a number    |

  Scenario: Calculate square root for a correct number
    Given I am on enter a number page
    When I enter number "64"
    And I click number submit
    Then I should see alert: "Square root of 64 is 8.00"
