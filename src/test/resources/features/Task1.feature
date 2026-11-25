# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Enter a number

  Background:
    Given I am on the Enter a Number page

  Scenario Outline: Show an error for invalid input
    When I enter "<input>" in the number field
    And I click Submit on the number page
    Then I see the number error "<error>"

    Examples:
      | input | error                 |
      | 49    | Number is too small   |
      | 101   | Number is too big     |
      | abc   | Please enter a number |

  Scenario: Show the square root of a valid number
    When I enter "68" in the number field
    And I click Submit on the number page
    Then I see an alert with the text "Square root of 68 is 8.25"

    Scenario: Show the square root for valid numbers
    When I enter "50" in the number field
    And I click Submit on the number page
    Then I see an alert with the text "Square root of 50 is 7.07"

    When I enter "100" in the number field
    And I click Submit on the number page
    Then I see an alert with the text "Square root of 100 is 10.00"