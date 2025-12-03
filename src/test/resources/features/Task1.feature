# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Task 1
  I want to test correct and incorrect number input results

  @errors
  Scenario Outline: incorrect input
    Given I am on enter a number page
    When I enter number: "<number>"
    And I click submit
    Then I see error message: "<message>"

    Examples:
      | number | message               |
      | 10     | Number is too small   |
      | 9999   | Number is too big     |
      | hello  | Please enter a number |

  @working
  Scenario: correct input
    Given I am on enter a number page
    When I enter number: 72
    And I click submit
    Then I see popup message: "Square root of 72 is 8.49"
    And I don't see error message

