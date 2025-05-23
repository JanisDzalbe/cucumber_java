@regression @part4
Feature: Introduction to cucumber part 4
  As a test engineer
  I want to be able to write and execute a scenario with steps that have 2 column tables

  Scenario: a new scenario with 2-column table
    Given I am on age page
    When I enter values:
      | name | Ann |
      | age  | 5   |
    And I click submit age
    Then I see message: "Hello, Ann, you are a kid"

  Scenario: another new scenario with 2-column table
    Given I am on age page
    When I enter values:
      | name | Bob |
      | age  | 61  |
    And I click submit age
    Then I see message: "Hello, Bob, you are an adult"

  Scenario Outline: a new scenario outline 2
    Given I am on age page
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
  # URL: https://acctabootcamp.github.io/site/tasks/provide_feedback
  Scenario Outline: Give us your feedback!
  Given I am on provide feedback page
    When I enter values to send:
      | name    | <name>   |
      | age     | <age>    |
      | gender  | <gender> |
    And I click send feedback button
    Then I can see input in feedback check
      | name    | <name>   |
      | age     | <age>    |
      | gender  | <gender> |
    Examples:
      | name | age | gender |
      | Ann  | 5   | female |
      | Bob  | 61  | male   |
      | Jim  | 35  | male   |

