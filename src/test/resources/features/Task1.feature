# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Enter a number

  Scenario Outline: Invalid number inputs
    Given I am on enter number page
    When I enter value "<input>"
    And I click submit
    Then I should see error message "<error>"

    Examples:
      | input | error                  |
      | 10    | Number is too small   |
      | 150   | Number is too big     |
      | abc   | Please enter a number |


  Scenario: Valid number input
    Given I am on enter number page
    When I enter value "64"
    And I click submit
    Then I should see result "8.00"