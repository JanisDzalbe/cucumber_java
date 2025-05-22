# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

@number_validation
Scenario Outline: Enter number error cases
  Given I navigate to number entry page
  When I enter number: "<input>"
  And I click submit number
  Then I see number error message: "<error_message>"

  Examples:
  | input | error_message                           |
  | 5     | Number is too small                     |
  | 150   | Number is too big                       |
  | abc   | Please enter a valid number             |

@number_validation @happy_path
Scenario: Enter correct number
  Given I navigate to number entry page
  When I enter number: "42"
  And I click submit number
  Then I see number success message: "Correct! You entered the right number."