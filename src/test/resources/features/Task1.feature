# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Enter a number

  Scenario Outline: Check error messages for incorrect input
    Given I am on enter a number page
    When I enter number: "<number>"
    And I click submit number
    Then I see number error: "<error>"

    Examples:
      | number | error                  |
      | 5      | Number is too small    |
      | 300    | Number is too big      |
      | woah   | Please enter a number |

  Scenario: Calculate square root of a correct number
    Given I am on enter a number page
    When I enter number: "77"
    And I click submit number
    Then I see square root result: "Square root of 77 is 8.77"