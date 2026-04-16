# Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Task 1

  @test
  Scenario Outline: error outline
    Given I am on enter a number page
    When I enter number: "<number>"
    And I click submit number
    Then I see number error: "<message>"
    Examples:
      | number |  message                     |
      | 49     | Number is too small          |
      | 101    | Number is too big            |
      | h      | Please enter a number        |
      |        | You haven't entered anything |

  @test
  Scenario: valid input
    Given I am on enter a number page
    When I enter number: "64"
    And I click submit number
    Then I get popup: "Square root of 64 is 8.00"
    And I accept popup
    And I see no error message
