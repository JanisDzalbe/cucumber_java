# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Introduction to cucumber part 3
  As a test engineer
  I want to be able to write and execute a scenario outline


  Scenario Outline: a scenario outline for error cases
    Given I am on enter a number page
    When I enter a not valid number <invalidNumber>
    And I click submit
    Then I see error message "<errorMessage>"
    Examples:
      | invalidNumber  | errorMessage|
      | 20   | Number is too small|
      | 140 | Number is too big|
      | test   | Please enter a number|

  Scenario: a Scenario for correct number
    Given I am on enter a number page
    When I enter a number 60
    And I click submit
    And I see alert with correct SquareRoot
    Then I should not see error msg
