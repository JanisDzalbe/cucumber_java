
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
      | name  | age | message                         |
      | Ann   | 5   | Hello, Ann, you are a kid       |
      | Marry | 50  | Hello, Marry, you are an adult  |
      | Bob   | 61  | Hello, Bob, you are an adult    |

    @not_working
    Examples:
      | name | age | message                  |
      | Tom  | 15  | Hello, Tom, you are a kid |

  # Activity 3 - Feedback form

  Scenario Outline: Verify submitted feedback name and age
    Given I am on feedback page
    When I enter feedback name: "<name>"
    And I enter feedback age: <age>
    And I click Send
    Then submitted name should be "<name>"
    And submitted age should be "<age>"

    Examples:
      | name | age |
      | Ann  | 5   |
      | Bob  | 61  |
