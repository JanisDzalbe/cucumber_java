# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

@task1
Feature: Enter a number validation and square root calculation
  As a test engineer
  I want to verify error messages for invalid inputs and square root calculation for valid numbers

  Background:
    Given I am on the enter a number page

  Scenario Outline: Invalid input shows the correct validation error
    When I enter value "<input>" into the number field
    And I click the Submit number button
    Then I see number validation error "<error>"

    Examples:
      | input | error                 |
      | 49    | Number is too small   |
      | 101   | Number is too big     |
      | abc   | Please enter a number |

  Scenario: Valid number calculates the correct square root
    When I enter value "50" into the number field
    And I click the Submit number button
    Then I see square root alert with text "Square root of 50 is 7.07"
    And no number validation error is shown