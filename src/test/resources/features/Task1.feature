# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Introduction to cucumber part 4
  As a test engineer
  I want to validate number input page

  Scenario Outline: number input error cases
    Given I am on enter a number page
    When I enter number value: "<value>"
    And I click submit number
    Then I should see number error: "<error>"

    Examples:
      | value | error                 |
      | 12    | Number is too small   |
      | 200   | Number is too big     |
      | asd   | Please enter a number |

  Scenario: correct number
    Given I am on enter a number page
    When I enter number value: "81"
    And I click submit number
    Then I should see square root message: "Square root of 81 is 9.00"