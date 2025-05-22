# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Validate number input and calculate square root
  As a test engineer
  I want to be able to write and execute a scenario outline

  Scenario Outline: Show error message for invalid input
    Given I open the number input page
    When I enter "<input>" into the field
    And I press Submit button
    Then I should see error message "<error>"

    Examples:
      | input | error                 |
      | 7     | Number is too small   |
      | 121   | Number is too big     |
      | abc   | Please enter a number |

  Scenario: Show square root for correct input
    Given I open the number input page
    When I enter "81" into the field
    And I press Submit button
    Then I should see alert with square root result "Square root of 81 is 9.00"
    And No error should be displayed after closing the alert
