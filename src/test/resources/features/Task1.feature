Feature: Task 1 - Enter a number
  As a test engineer
  I want to check that a square root is calculated only for numbers from 50 to 100

  Background:
    Given I am on enter a number page

  Scenario Outline: error when <case>
    When I submit value "<value>"
    Then I see number error "<error>"
    Examples:
      | case                | value | error                 |
      | number is too small | 49    | Number is too small   |
      | number is too big   | 101   | Number is too big     |
      | text is entered     | text  | Please enter a number |

  Scenario: square root of a correct number
    When I submit value "55"
    Then I see alert "Square root of 55 is 7.42" and accept it
    And I do not see number error
