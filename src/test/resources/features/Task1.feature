# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
  Feature: As a test engineer I want to test messages for incorrect values and correct behavior
    Background:
      Given I am on enter a number page

  Scenario Outline: Enter a number or text
    When I enter incorrect "<value>"
    And I click submit button
    Then I see error "<message>"

    Examples:
    | value | message |
    | 49    | Number is too small |
    | 101   | Number is too big   |
    | abc   | Please enter a number|

    Scenario:
      When I enter correct value 64
      And I click submit button
      Then I see message: Square root of 64 is 8.00









