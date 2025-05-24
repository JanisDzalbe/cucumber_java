# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Cucumber Task 1
  I want to be good at writing and executing scenarios and scenario outlines within this task

  Background:
    Given I am on the Enter a number page

  Scenario Outline: Error cases scenario
    When I enter number: "<number>"
    And I click submit button
    Then I see error message: "<message>"

    Examples:
      | number |        message         |
      |   4    |  Number is too small   |
      |  404   |  Number is too big     |
      |  abc   |  Please enter a number |

    Scenario: Correct number scenario
      When I enter number: "81"
      And I click submit button
      Then I see popup alert for entered number
