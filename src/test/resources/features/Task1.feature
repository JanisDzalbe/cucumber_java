# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

Feature: Enter a number

  Scenario Outline: Verify error messages

    Given I am on enter a number page
    When I enter "<input>"
    And I submit the number
    Then I see number validation error: "<expectedError>"

    Examples:
      | input | expectedError |
      |   25    |   Number is too small            |
      |   150   |   Number is too big              |
      |   yo    |   Please enter a number          |


  Scenario: Enter a correct number

    Given I am on enter a number page
    When I enter a correct number
    And I submit the number
    Then I see the correct result