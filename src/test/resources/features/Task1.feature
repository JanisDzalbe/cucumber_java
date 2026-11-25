# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number


Feature: Enter a number and verify the result

  Scenario Outline: Verify error messages for invalid input
    Given I open the Enter a Number page
    When I enter "<input>" in the number field
    And I click the Submit button
    Then I should see the error message "<errorMessage>"

    Examples:
      | input | errorMessage          |
      | 20    | Number is too small   |
      | 200   | Number is too big     |
      | jha   | Please enter a number |

  Scenario: Verify square root for a correct number
    Given I open the Enter a Number page
    When I enter "64" in the number field
    And I click the Submit button
    Then I should see the square root alert "Square root of 64 is 8.00"
    And I accept the square root alert
    And I should see no error message
