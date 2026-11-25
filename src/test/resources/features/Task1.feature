# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Enter a number

  Background:
    Given I am on enter a number page

  Scenario Outline: Enter an invalid number
    When I enter number: "<number>"
    And I click Submit on number page
    Then I see number error: "<errorMessage>"

    Examples:
      | number| errorMessage          |
      | 36    | Number is too small   |
      | 993   | Number is too big     |
      | vika  | Please enter a number |

    Scenario: Enter a valid number
      When I enter number: "64"
      And I click Submit on number page
      Then I see alert: "Square root of 64 is 8.00"