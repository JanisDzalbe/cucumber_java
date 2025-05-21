# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

  #Should I do @working and @not working here? I'll be honest, I don't quite understand them, however I was not able to attend on thursday
Feature: Task 1 test

  Background:
  Given I am on the enter a number page

  Scenario Outline: Invalid number input test
    When I enter number: "<number>"
    And I click submit
    Then I receive error: "<error>"
    Examples:
      | number | error                 |
      | 12     | Number is too small   |
      | 999    | Number is too big     |
      | abc    | Please enter a number |

    Scenario Outline: Valid number input
      When I enter number: "<number>"
      And I click submit
      Then I get the correct square root of number: "<number>"
      Examples:
        | number |
        | 58     |
        | 49     |
        | 79     |