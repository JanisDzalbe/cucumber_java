Feature: Number validation tasks

  Background:
    Given I am on enter a number page

  Scenario Outline: Enter invalid numbers or text
    When I enter "<input>" in number field
    And I click submit number
    Then I see number error: "<error_message>"

    Examples:
      | input | error_message       |
      | 49    | Number is too small |
      | 101   | Number is too big   |
      | abc   | Please enter a number |

  Scenario: Enter a correct number
    When I enter "64" in number field
    And I click submit number
    Then I see square root message: "Square root of 64 is 8.00"