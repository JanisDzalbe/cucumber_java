# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"

Feature: Cucumber Task 1 solving
  As a beginner tester
  I want to consolidate the knowledge I have gained from my 3rd day of training

  Background:
    Given I am on Enter a number page

# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number

  Scenario Outline: Error cases scenario
    When I enter <testData> on Enter a number page
    And I click Submit button
    Then I see "<errorMessage>"

    Examples:
      | testData | errorMessage          |
      | 49       | Number is too small   |
      | 101      | Number is too big     |
      | test     | Please enter a number |


# - Scenario for correct number

  Scenario: Correct case scenario
    When I enter CorrectData on Enter a number page
    And I click Submit button
    Then I see an alert with correct calculation

