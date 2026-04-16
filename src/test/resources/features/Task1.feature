# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: As a test engineer
  I want to test the number validation and to execute scenario outline

  Background:
    Given I am on the enter number page

    Scenario Outline: Enter invalid numbers and text
      When I write "<input>" in field
      And I click on submit
      Then I see errormessage: "<error_message>"
      Examples:
        | input  | error_message         |
        | 20     | Number is too small   |
        | 120    | Number is too big     |
        | abc    | Please enter a number |

      Scenario: Enter the correct number
        When I write "81" in field
        And I click on submit
        Then I see squareroot message: "Square root of 81 is 9.00"