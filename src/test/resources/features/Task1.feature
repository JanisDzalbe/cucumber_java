# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Enter a number page

  Background:
    Given I am on the enter a number page

  Scenario Outline: Error is shown for invalid input
    When I enter "<input>" into the number field
    And I click the Submit button
    Then I should see the error message "<errorMessage>"

    Examples:
      | input | errorMessage                        |
      | 10    | Number is too small                 |
      | 200   | Number is too big                   |
      | abc   | Please enter a number                |

  Scenario: Correct number shows square root result
    When I enter "64" into the number field
    And I click the Submit button
    Then I should see the result "Square root of 64 is 8.00"