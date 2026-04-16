# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Introduction to cucumber part 3
  As a test engineer
  I want to test if "Enter the number" page works correctly

  Scenario Outline: Check functionality of "Enter the number"
    Given I am on "Enter a number" page
    When I enter text: "<text>"
    And I click submit button
    Then I see error message: "<message>"
    Examples:
      | text  | message               |
      | 9     | Number is too small   |
      | 191   | Number is too big     |
      | Bob   | Please enter a number |

  Scenario: Check the case of correct number entered
    Given I am on "Enter a number" page
    When I enter text: "100"
    And I click submit button
    Then I see the correct alert message
    And I see no error message

