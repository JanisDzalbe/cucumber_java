# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

  Feature: Enter a number
    As a user
    I want to be able to find the square root of a valid number

  Scenario Outline: Verify Number Input
    Given I am on Number page
    When I enter a "<number>" in the number field
    And I click submit button
    Then I see the error message "<errorMessage>"

    Examples:
      | number | errorMessage                |
      | 1      | Number is too small         |
      | 567    | Number is too big           |
      | hi     | Please enter a number       |

    Scenario: Verify valid input on numbers page
      Given I am on Number page
      When I enter a number: 76
      And I click submit button
      Then I see message "Square root of 76 is 8.72" in the popup window
      And I do not see error message