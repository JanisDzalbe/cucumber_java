@regression @part4
Feature: Introduction to cucumber part 4
  As a test engineer
  I want to be able to write and execute a scenario with steps that have 2 column tables

  Background:
    Given I am on age page

  Scenario: a new scenario with 2-column table
    When I enter values:
      | name | Ann |
      | age  | 5   |
    And I click submit age
    Then I see message: "Hello, Ann, you are a kid"

  Scenario: another new scenario with 2-column table
    When I enter values:
      | name | Bob |
      | age  | 61  |
    And I click submit age
    Then I see message: "Hello, Bob, you are an adult"

  Scenario Outline: a new scenario outline 2
    When I enter values:
      | name | <name> |
      | age  | <age>  |
    And I click submit age
    Then I see message: "<message>"

    Examples:
      | name | age | message                      |
      | Ann  | 5   | Hello, Ann, you are a kid    |
      | Bob  | 61  | Hello, Bob, you are an adult |

  @test
  Scenario Outline: feedback page with 2-column table
    Given I am on feedback page
    When I enter feedback values:
      | name   | <name>   |
      | age    | <age>    |
      | option | <option> |
      | comment | <comment> |
    And I click send feedback
    Then I should see feedback name: "<name>"
    And I should see feedback age: "<age>"
    And I should see feedback option: "<option>"
    And I should see feedback comment: "<comment>"

    Examples:
      | name  | age | option | comment           |
      | Anna  | 22  | Good   | Very nice page    |
      | Bob   | 35  | Bad    | Needs improvement |
      | Marry | 41  | Why me?| Strange question  |