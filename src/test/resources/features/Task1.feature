# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Task 1
  This is my TODO of the Task1

  Scenario Outline: Enter a number - error cases
    Given I am on the enter a number page
    When I enter number value: "<input>"
    And I click submit number button
    Then I should see an error: "<error_message>"

    Examples:
      | input | error_message         |
      | 10    | Number is too small   |
      | 101   | Number is too big     |
      | abc   | Please enter a number |


  Scenario: Enter a number - correct case
    Given I am on the enter a number page
    When I enter number value: "66"
    And I click submit number button
    Then I should see a success alert for number "66"
    And I should not see any error message