Feature: Enter a number task
# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
  Background:
    Given I am on the enter a number page

  @errorCases
  Scenario Outline: a new scenario outline
  //Given I am on the enter a number page
  When I enter number "<number>"
  And I click submit button
  Then I should see message : "<message>"

  @working
  Examples:
| number | message                   |
| 1      | Number is too small       |
| 101    | Number is too big         |
| a      | Please enter a number     |

    @correctNumber
    Scenario: a new scenario for correct number
      When I enter number "52"
      And I click submit button
      Then I should see alert with message "Square root of 52 is 7.21"
