# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Enter a number validation
  As a test engineer
  I want to be able check number validation

@Invalidnumbervalidation
  Scenario Outline: Verify error messages for numbers not valid
    Given I navigate to enter a number page
    When I enter "<input>" in number field
    And I click submit number button
    Then I should see error message "<error_message>"

    Examples:
      | input | error_message               |
      | 1    | Number is too small         |
      | 155   | Number is too big           |
      | abc   | Please enter a number |

@validnumbervalidation
  Scenario: Verify valid number submission
    Given I navigate to enter a number page
    When I enter valid number 75 in number field
    And I click submit number button
    Then I should see square root alert for number "Square root of 75 is 8.66"