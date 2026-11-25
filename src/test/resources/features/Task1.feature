# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

@regression
Feature: Validate number input

  Scenario Outline: Validate error messages
    Given I am on task page
    When I enter a number "<Input>"
    And I click submit button
    Then I see an error message: "<Error>"

    Examples:
      | Input   | Error |
      | 2       |   Number is too small   |
      | 101     |   Number is too big     |
      | Hello   |   Please enter a number |

  Scenario: Enter a correct number

    Given I am on task page
    When I enter a correct number
    And I click submit button
    Then I see the Valid message