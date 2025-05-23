Feature: Task1
  Needed to test Enter a number page

  Background:
    Given I go to Enter a number page

  Scenario Outline: for checking error messages
    When I enter "<value>" in textfield
    And I press submit button on Enter a number page
    Then I see "<errorMessage>" on Enter a number page
    Examples:
      | value | errorMessage          |
      | 1     | Number is too small   |
      | 111   | Number is too big     |
      | test  | Please enter a number |

  Scenario: for checking correct value input
    When I enter 56.25 in textfield
    And I press submit button on Enter a number page
    Then I see correct alert message of calculating sqrt - 7.5 from 56.25

# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
