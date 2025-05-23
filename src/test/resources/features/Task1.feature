# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Number input validation

  Scenario Outline: Invalid input shows proper error message
    Given I am on the number input page
    When I enter "<input>" and click submit
    Then I should see error message "<error>"

    Examples:
      | input | error                 |
      | 20    | Number is too small   |
      | 120   | Number is too big     |
      | hund  | Please enter a number |

  Scenario Outline: Valid input returns square root
    Given I am on the number input page
    When I enter "<input>" and click submit
    Then I should see square root result for "<input>" as "<output>"

    Examples:
      | input | output  |
      | 81    | 9.00    |
      | 64    | 8.00    |
      | 50    | 7.07    |