# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: enter a number and validate the result

  Scenario Outline: check error messages for invalid numbers
    Given I am on enter a number page
    When I enter value: "<value>"
    And I click submit
    Then I see error "<error>" on page

    Examples:
      | value | error                 |
      | 49    | Number is too small   |
      | 101   | Number is too big     |
      | abc   | Please enter a number |

  Scenario: calculate square root with valid numbers
    Given I am on enter a number page
    When I enter value: "64"
    And I click submit
    Then I see square root alert: "Square root of 64 is 8.00"