# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Enter a number
  As a test engineer
  I want to check the enter a number page with valid and invalid input

  Scenario Outline: enter a number error cases
    Given I am on enter number page
    When I enter number: "<number>"
    And I click submit number
    Then I see number error: "<error>"
    Examples:
      | number| error                |
      | 39    |Number is too small   |
      | 102   |Number is too big     |
      | hi    |Please enter a number |
  Scenario: enter a correct number
    Given I am on enter number page
    When I enter number: "100"
    And I click submit number
    Then I see alert: "Square root of 100 is 10.00"
