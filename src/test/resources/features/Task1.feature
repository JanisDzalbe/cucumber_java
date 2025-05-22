# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Enter a number
  Background:
    Given I am on enter a number page

  @errorCases
  Scenario Outline: error cases for enter a number
    When I enter a number: "<number>"
    And I click submit number button
    Then I get error message: "<error>"
  @working
    Examples:
      | number  | error                 |
      | 17      | Number is too small   |
      | 177     | Number is too big     |
      | seven   | Please enter a number |

  @happyPath
  Scenario: correct number case
    When I enter a number: "67"
    And I click submit number button
    Then I see correct message and square root for number: 67
    And I see no error messages