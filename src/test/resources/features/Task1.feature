# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

@regression
Feature: Enter a number to validate
  As a test engineer
  I want to check all messages and correct result for "Enter a number" page

  Background:
    Given I am on enter number page

  @Errors
  Scenario Outline: Incorrect number displays the correct error message
    When I enter number "<value>"
    And I click submit button
    Then I see the corresponding error "<error>"

    Examples:
      | value | error                  |
      | 23    | Number is too small    |
      | 1111  | Number is too big      |
      | erw   | Please enter a number  |

  @CorrectNumbers
  Scenario: Correct number shows its square root
    When I enter number "81"
    And I click submit button
    Then I see the message "Square root of 81 is 9.00"
    And No errors are shown