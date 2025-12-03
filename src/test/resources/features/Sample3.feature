@regression
Feature: Introduction to cucumber part 3
  As a test engineer
  I want to be able to write and execute a scenario outline

  @debugs
  Scenario Outline: a new scenario outline
    Given I am on age page
    When I enter name: "<name>"
    And I enter age: <age>
    And I click submit age
    Then I see message: "<message>"
  @working
    Examples:
      | name  | age | message                        |
      | Ann   | 5   | Hello, Ann, you are a kid      |
      | Marry | 50  | Hello, Marry, you are an adult |
      | Bob   | 61  | Hello, Bob, you are an adult   |
  @not_working
    Examples:
      | name | age | message                   |
      | Tom  | 15  | Hello, Tom, you are a kid |


#   TODO - create Scenario Outline for 'Give us your feedback!' page
  Scenario Outline: Verify Feedback page
    Given I am on feedback page
    When I set name: "<name>"
    And I set age: <age>
    And I click Send button
    Then I see "<name>" in check feedback page
    And I see <age> in check feedback page
    Examples:
      | name | age |
      | John | 5  |
      | Ann  | 25 |
      | Bob  | 61 |
