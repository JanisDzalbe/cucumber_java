Feature: Number input page test

# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Scenario Outline: Verify number input error cases
    Given I am on number input page
    When I enter number "<number>"
    And I click submit button
    Then I see error message "<error_message>"
  Examples:
    | number | error_message        |
    | -1     | Number is too small  |
    | 101    | Number is too big    |
    | abc    | Please enter a number|

Scenario: Verify correct number input
    Given I am on number input page
    When I enter number "51"
    And I click submit button
    Then I see popup with answer "Square root of 51 is 7.14"