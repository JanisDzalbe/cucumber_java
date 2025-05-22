



@regression
Feature: Introduction to cucumber part 2
  As a test engineer
  I want to be able to write and execute a scenario with parameters

  Scenario Outline: Enter invalid text and see error message
    Given I am on the number entry page
    When I enter "<input>" into the number field
    And I click the check button
    Then I should see error message: "<error>"

    Examples:
      | input | error                         |
      | abc   | Please enter a number         |

  Scenario Outline: Enter invalid number and see error message
    Given I am on the number entry page
    When I enter number <input> into the number field
    And I click the check button
    Then I should see error message: "<error>"

    Examples:
      | input | error                         |
      | 17    | Number is too small           |
      | 117   | Number is too big             |

  Scenario: Enter a valid number
    Given I am on the number entry page
    When I enter number 60 into the number field
    And I click the check button
    Then I should see a success alert with the correct square root of 60

# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

