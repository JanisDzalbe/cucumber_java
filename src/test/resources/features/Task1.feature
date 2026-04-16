# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
  Feature: Input square root
    Background:
      Given I am on enter number page

    Scenario Outline: Enter incorrect values
      When I enter a number: <number>
      And I click submit
      Then I see that the error message matches "<expectedError>"
    @working
      Examples:
        | number  | expectedError         |
        |   6     | Number is too small   |
        |   999   | Number is too big     |
        |   asd   | Please enter a number |

    Scenario: Enter correct value
      When I enter a number: 67
      And I click submit
      Then I see that the alert shows the correct sqrt for 67
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
