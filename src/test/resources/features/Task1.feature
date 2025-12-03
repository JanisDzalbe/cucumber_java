# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
Feature: Task 1
@test
Scenario Outline: Verify Number Input
  Given I am on Number page
  When I enter "<input>" in the number field
  And I press the submit button
  Then I see the error message "<errorMessage>"

  Examples:
    | input | errorMessage          |
    | 10    | Number is too small   |
    | 200   | Number is too big     |
    | abc   | Please enter a number |


# - Scenario for correct number
  Scenario: Enter a valid number
    Given I am on Number page
    When I enter number: 99
    And I press the submit button
    Then I see the popup window for number: 99