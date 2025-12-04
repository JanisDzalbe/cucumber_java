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

 # TODO - create Scenario Outline for 'Give us your feedback!' page
  @test
  Scenario Outline: Provide feedback with name, age and genre
    Given I am on feedback page
    When I enter feedback details:
      | name  | <name>  |
      | age   | <age>   |
      | genre | <genre> |
    And I press the Send button
    Then I should see feedback name: "<name>"
    And I should see feedback age: "<age>"
    And selected genre is "<genre>"

    Examples:
      | name | age | genre  |
      | Ann  | 5   | Male   |
      | Bob  | 61  | Female |

