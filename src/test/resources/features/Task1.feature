# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

  @Task1Test

  Feature: Check number input

    Scenario Outline: Error cases scenario
      Given I am on the enter a number page
      When I enter a number: "<number>"
      And I click submit number
      Then I see an error "<error>"
      Examples:
        | number | error                 |
        | 10     | Number is too small   |
        | 125    | Number is too big     |
        | text   | Please enter a number |

    Scenario: Correct number scenario
      Given I am on the enter a number page
      When I enter a number: 54
      And I click submit number
      Then I see an alert with correct answer