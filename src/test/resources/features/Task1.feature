# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: As a tester
  I want to test a negative scenario

  Scenario Outline: A new scenario outline for error cases
    Given I am on the page
    When I enter value: "<value>"
    And I click submit button
    Then I see error: "<error>" on enter a number page

    Examples:

      | value | error                 |
      | 20    | Number is too small   |
      | 150   | Number is too big     |
      | Bob   | Please enter a number |

  Scenario: The scenario for correct number
    Given I am on the page
    When I enter value: "81"
    And I click submit button
    Then I see alert with text: "Square root of 81 is 9.00"