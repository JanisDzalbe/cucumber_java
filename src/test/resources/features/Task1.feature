# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Task 1
  scenario outline for error cases. One example for each case

  Background:
    Given I am on the numbers page

  Scenario Outline: Verify invalid input on numbers page
    When I enter a number: "<number>"
    And I click submit button
    Then I see error message: "<message>"

    Examples:
      | number | message                     |
      | 1      | Number is too small         |
      | 9001   | Number is too big           |
      | aa     | Please enter a number       |


  Scenario: Verify valid input on numbers page
    When I enter a number: "67"
    And I click submit button
    Then I see message "Square root of 67 is 8.19"
    And I do not see any error message