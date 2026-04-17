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

  Scenario Outline: test feedback page
    Given I am on feedback page
    When I set info in feedback: "<name>", <age> and "<genre>"
    And I click send feedback
    Then I see feedback name: "<name>"
    And I see feedback age: <age>
    And I see feedback genre: "<genre>"
    Examples:
      | name  | age | genre  |
      | Ann   | 32  | female |
      | Bob   | 39  | male   |
      | Janis | 30  | male   |

  Scenario Outline: Test feedback page 2
    Given I am on feedback page
    When I set feedback details
      | name  | <name>  |
      | age   | <age>   |
      | genre | <genre> |
    And I click send feedback
    Then I see feedback details
      | name  | <name>  |
      | age   | <age>   |
      | genre | <genre> |
    Examples:
      | name  | age | genre  |
      | Ann   | 32  | female |
      | Bob   | 39  | male   |
      | Janis | 30  | male   |