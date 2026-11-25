# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Error scenario validation
  As a test engineer
  I want to be able to write and execute a scenario with parameters

  @tests
  Scenario Outline: a new Scenario for invalid inputs
    Given I am on Enter a number Page
    When I enter "<value>" in input field
    And I click submit button
    Then  I see error "<errorMessage>"

    Examples:
      | value | errorMessage |
      | 49 | Number is too small |
      | 101 | Number is too big  |
      | Hi| Please enter a number |

  @tests
  Scenario: a new Scenario for valid inputs
    Given I am on Enter a number Page
    When I enter value: 50
    And I click submit button
    Then  I see popUp message: "Square root of 50 is 7.07"

