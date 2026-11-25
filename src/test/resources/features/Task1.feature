# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
  #   * enter number too small
#   * enter number too big
#   * enter text instead of the number
Feature: Enter a number page

Scenario Outline: Testing Enter a number page for error cases
Given I am on the Enter a number page
When I enter number "<number>" into the number field
And I click the submit button
Then I see the error message "<message>"

Examples:
| number | message                |
| 12     | Number is too small    |
| 120    | Number is too big      |
| five   | Please enter a number  |


# - Scenario for correct number
  Scenario Outline: Testing Enter a number page with correct number
    Given I am on the Enter a number page
    When I enter number "<number>" into the number field
    And I click the submit button
    Then I see the alert "<correctAnswer>" message

    Examples:
      | number | correctAnswer                          |
      | 64     |  Square root of 64 is 8.00       |
      | 100    |  Square root of 100 is 10.00     |


