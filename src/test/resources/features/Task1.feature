# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
  Feature: Test the Enter a number page error cases and functionality

    Background:
      Given I am on the Enter a number page

    Scenario Outline: Enter a number page error cases
      When I enter an input: <input>
      And I click the submit button
      Then Error message is displayed: <error>
      Examples:
        | input | error |
        | 25    | Number is too small |
        | 150    | Number is too big |
        | This is not a number | Please enter a number |

    Scenario: Correct number is entered
      When I enter an input: 64
      And I click the submit button
      Then I get a pop-up with the correct answer for number: 64
      And No error message is displayed after pop-up is closed
