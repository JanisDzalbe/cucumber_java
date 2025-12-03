# Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Task 1
  As a test engineer
  I want to be able to write and execute a scenario with parameters

  Background:
    Given I am on Enter a number page

  Scenario Outline: handling error cases
    When I enter "<input>" in number field
    And I press Submit button
    Then I see the error: "<message>"

    Examples:
      | input | message               |
      | 49    | Number is too small   |
      | 101   | Number is too big     |
      | aaa   | Please enter a number |

  Scenario Outline: entering correct number
    When I enter "<input>" in number field
    And I press Submit button
    Then I confirm the alert shows the square root for "<input>"
    And I check no error message is shown after closing the alert

    Examples:
      | input |
      | 51    |
      | 99    |