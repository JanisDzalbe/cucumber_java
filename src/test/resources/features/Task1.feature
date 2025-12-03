# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Introduction to cucumber part 4
  As a test engineer
  I want to be able to write and execute a simple scenario and scenario outlet

  Background:
    Given I am on Enter a number page

    Scenario Outline: Check different invalid input scenarios
      When I enter invalid number or text: <input>
      And I press Submit button
      Then I see error message: "<message>"

      Examples:
        | input | message               |
        | 5     | Number is too small   |
        | 110   | Number is too big     |
        | hello | Please enter a number |

    Scenario: Check valid input scenario
      When I enter number: 81
      And I press Submit button
      Then I see alert message: "Square root of 81 is 9.00"
      And No error is shown
