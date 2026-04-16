# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Independent task completion
  As a test engineer
  I want to be able to write and execute a scenario and scenario outline

  Background:
    Given I am on the enter number page

  Scenario Outline: a new scenario to check incorrect numbers
    When I enter number: "<number>"
    And I press Submit button
    Then I see an Error: "<message>"
    Examples:
      | number| message               |
      | 1     | Number is too small   |
      | 102   | Number is too big     |
      | test  | Please enter a number |


  Scenario: a new scenario for correct number
    When I enter number: "64"
    And I press Submit button
    Then I see an alert and check result
    And I accept alert
    And I see that's no errors displayed