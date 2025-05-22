# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number


@regression
Feature: Validate number input errors and correct results for square root calculator


  Scenario Outline: Invalid inputs show specific error messages
    Given I am on enter a number page
    When I enter "<number>" in number field
    And I click number submit button
    Then I see error message: "<error>"

    Examples:
      | number| error                |
      | 14    | Number is too small  |
      | 186   | Number is too big    |
      | error?  | Please enter a number|


  Scenario Outline: Valid numbers return correct square root
    Given I am on enter a number page
    When I enter "<number>" in number field
    And I click number submit button
    Then I see result message: "<output>"

    Examples:
      | number | output                          |
      | 55     | Square root of 55 is 7.42       |
      | 60     | Square root of 60 is 7.75       |
      | 72     | Square root of 72 is 8.49       |
      | 99     | Square root of 99 is 9.95       |