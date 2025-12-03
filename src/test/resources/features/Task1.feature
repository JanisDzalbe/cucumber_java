# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

  Feature: Task 1 implementation
    As a test engineer
    I want to be able to write and execute a scenario outline

  Background:
    Given I am on EnterNumber page

  Scenario Outline: I enter incorrect input
    When I enter value: "<input>"
    And I click submit button
    Then I see an error: "<message>"

    Examples:
      | input | message                |
      | 12    | Number is too small    |
      | 120   | Number is too big      |
      | aaaa  | Please enter a number  |

    Scenario: I enter correct input
      When I enter number: "78"
      And I click submit button
      Then I see success alert for number "78"
      And I do not see an error message

