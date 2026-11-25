# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Number validation

  Scenario Outline: Enter invalid number
    Given I am on enter a number page
    When I entered number "<number>"
    And I clicked submit number button
    Then I see number error message "<error>"

    Examples:
      | number | error                 |
      | 10     | Number is too small   |
      | 150    | Number is too big     |
      | Sru    | Please enter a number |

  Scenario: Enter valid number
    Given I am on enter a number page
    When I entered number "81"
    And I clicked submit number button
    Then I see valid number result "Square root of 81 is 9.00"