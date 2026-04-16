# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Enter a number validation

  Background:
    Given I open enter number page

  Scenario Outline: Error cases for invalid input
    When I enter value "<value>"
    And I click submit number
    Then I see error message "<message>"

    Examples:
      | value | message                              |
      | 10    | Number is too small   |
      | 150   | Number is too big    |
      | text  | Please enter a number          |

  Scenario: Correct number
    When I enter value "81"
    And I click submit number
    Then I see result "Square root of 81 is 9.00"