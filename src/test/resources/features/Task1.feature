# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Task1

  Background:
    Given number page is displayed

  Scenario Outline: Outline for error cases
    When enter an invalid number value: "<number>"
    And press submit button
    Then the "<error>" text is displayed
    Examples:
      | number | error                 |
      | four   | Please enter a number |
      | 48     | Number is too small   |
      | 101    | Number is too big     |

  Scenario Outline: Correct number
    When enter valid "<number>"
    And press submit button
    Then the pop-up with "<number>" and correct answer is displayed

    Examples:
      | number |
      | 64     |
      | 89     |
      | 77     |


