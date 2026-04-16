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


  @feedback
  Scenario Outline: Provide feedback form
    Given I open feedback page
    When I enter feedback name "<name>"
    And I enter feedback age <age>
    And I click send feedback
    Then I see name "<name>" in input
    And I see age "<age>" in input

    Examples:
      | name  | age |
      | John  | 25  |
      | Anna  | 40  |
      | Mike  | 18  |