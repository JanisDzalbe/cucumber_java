# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Enter a number

  Scenario Outline: Error cases for entering a number
    Given I am on the enter a number page
    When I enter the number "<number>"
    And I click the number submit button
    Then I should see the number error message "<message>"

    Examples:
      | number | message                  |
      | 30     | Number is too small      |
      | 150    | Number is too big        |
      | abc    | Please enter a number  |

  Scenario: Correct number returns square root
    Given I am on the enter a number page
    When I enter the number "64"
    And I click the number submit button
    Then I should see an alert with message "Square root of 64 is 8.00"