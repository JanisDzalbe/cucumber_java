# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Enter a number

  Background:
    Given I am on the "Enter a number" page

  Scenario Outline: Error validation for wrong inputs
    When I type number "<number>"
    And I click Submit
    Then I should see error "<error_message>"

    Examples:
      | number | error_message          |
      | test   | Please enter a number  |
      | 20     | Number is too small    |
      | 120    | Number is too big      |

  Scenario: Correct square root is shown for valid number
    When I type number "64"
    And I click Submit
    Then I should see square root "Square root of 64 is 8.00"
    And I should see no error message
