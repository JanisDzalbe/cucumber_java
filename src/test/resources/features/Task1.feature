# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: number input validation


  # - Scenario outline for error cases
  Scenario Outline: testing invalid number input
  Given I am on Enter a number page
  When I enter number "<number>" on the page
  And I click Submit
  Then I see error "<error>"


  Examples:
  | number | error                 |
  | 30     | Number is too small   |
  | 120    | Number is too big     |
  | candy  | Please enter a number |


  # - Scenario for correct number
  Scenario: testing valid number input
  Given I am on Enter a number page
  When I enter number "64" on the page
  And I click Submit
  Then I see alert: "Square root of 64 is 8.00"
    When I accept the alert
    Then I see no error message

