Feature: Task1

  Background:
    Given I am on enter a number page

  Scenario Outline: outline for error cases
    When I enter number: <number>
    Then I click submit
    And I see text: "<error>"

    Examples:
      | number  | error                 |
      | hello   | Please enter a number |
      | 25      | Number is too small   |
      | 105     |  Number is too big    |

  Scenario: Correct number
    When I enter correct number: 64
    Then I click submit
    Then I see the pop-up with answer for number: 64
    And No error is visible after pop-up is closed


# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
