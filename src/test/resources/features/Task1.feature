# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
@regression
Feature: Enter a number validation

  Background:
    Given I am on enter a number page


  Scenario Outline: Error message is displayed for incorrect input
    When I enter number "<input>"
    And I click submit number button
    Then I should see number error "<error>"

    Examples:
      | input | error                  |
      | 40    | Number is too small    |
      | 150   | Number is too big      |
      | abc   | Please enter a number  |


  Scenario: Correct number produces square root
    When I enter number "64"
    And I click submit number button
    Then I should see result "8.00"
