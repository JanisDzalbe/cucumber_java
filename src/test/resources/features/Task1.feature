Feature: Task 1
  As a user
  I want to be able to get a square root of input number

@Task1test
Scenario Outline: Error guessing
  Given I am on Enter a number page
  When I enter a value: "<text>"
  And I click submit
  Then I get an error message: "<message>"


  @not_working
  Examples:
    | text | message                       |
    | 4    | Number is too small           |
    | 123  | Number is too big             |
    | aaa  | Please enter a number         |

   @working
  Scenario: success
    Given I am on Enter a number page
    When I enter a value: "88"
    And I click submit
    Then I see a window with result "Square root of 88 is 9.38"


# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number