@regression @task1
Feature: Validate number input on enter_a_number page
  As a user
  I want to see correct messages when I enter numbers

  Background:
    Given I am on enter number page

  Scenario Outline: Error cases for invalid numbers
    When I enter "<value>" into number field
    And I click check number button
    Then I see number message "<message>"

    Examples:
      | value | message                      |
      | -1    | Number is too small         |
      | 101   | Number is too big           |
      | text  | Please enter a valid number |

  Scenario: Correct number
    When I enter "50" into number field
    And I click check number button
    Then I see number message "Number is correct"
