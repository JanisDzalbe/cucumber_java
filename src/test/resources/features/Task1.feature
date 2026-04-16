# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
  Feature: Number Validation test

    Background:
      Given I am on enter number page

      Scenario Outline: Enter invalid number in field
        When I enter "<number>" in number field
        And I click Submit button
        Then I see error message": "<error_message>"
        Examples:
          |  number  |    error_message      |
          |   48    | Number is too small   |
          |   49    | Number is too small   |
          |   101   |  Number is too big    |
          |   102   |  Number is too big    |
          | Edvards | Please enter a number |


      Scenario: Enter valid number in field
        When I enter "81" in number field
        And I click Submit button
        Then I see square massage: "Square root of 81 is 9.00"

