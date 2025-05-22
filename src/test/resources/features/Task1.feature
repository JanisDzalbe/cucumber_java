# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Checking entering a number for computing a square root value
  As a test engineer
  I want to be able to check the functionality of the square root program

  Scenario Outline: incorrect input
    Given I am on enter a number page
    When I enter an incorrect number value "<number>"
    Then I see an error message "<message>"
    Examples:
      | number | message |
      | 30 | Number is too small |
      | 450 | Number is too big |
      | aaa | Please enter a number |

  Scenario Outline: correct input
    Given I am on enter a number page
    When I enter a correct number value <number>
    Then I see an alert with "<number>" and the result
    Examples:
      | number |
      | 81     |
      | 64     |