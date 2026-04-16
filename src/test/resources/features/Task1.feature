# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Enter a number validation

  Scenario Outline: Invalid number inputs
    Given I am on number page
    When I enter number "<value>"
    And I click submit number
    Then I should see error message "<message>"

    Examples:
      | value | message               |
      | 25    | Number is too small   |
      | 101   | Number is too big     |
      | abc   | Please enter a number |

  Scenario: Valid number input
    Given I am on number page
    When I enter number "89"
    And I click submit number
    Then I should see alert with text "Square root of 89 is 9.43"
    And I see no error message
