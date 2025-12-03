# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Number input validation

  Scenario Outline: User enters invalid value and receives an error message
    Given the user is on the Enter a number page
    When the user enters "<inputValue>" into the number field
    And the user clicks the Submit button
    Then the error message "<expectedError>" should be displayed

    Examples:
      | inputValue | expectedError                               |
      | 10         | Number is too small                         |
      | 150        | Number is too big                           |
      | abc        | Please enter a number                       |

  Scenario: User enters a correct number and sees calculated square root
    Given the user is on the Enter a number page
    When the user enters "64" into the number field
    And the user clicks the Submit button
    Then the result "8.00" should be displayed
