@regression
Feature: Enter a number
  As a test engineer
  I want to verify the number input page handles valid and invalid inputs correctly

  Background:
    Given I am on enter a number page

  @test
  Scenario Outline: Error cases for invalid number input
    When I enter number: "<input>"
    And I click submit number
    Then I see number error: "<error>"

    Examples:
      | input | error                              |
      | 10    | Number is too small                |
      | 150   | Number is too big                  |
      | abc   | Please enter a valid number        |

  @test
  Scenario: Correct number returns square root
    When I enter number: "64"
    And I click submit number
    Then I see number result: "8.00"