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
#   URL: https://janisdzalbe.github.io/example-site/tasks/provide_feedback
   Scenario Outline: A new Scenario Outline 'Give us your feedback'
     Given I am on feedback page
     When I enter the name: "<name>"
     And I enter the age: <age>
     And I click the send
     Then I see the name: "<name>" in feedback
     And I see the age: "<age>" in feedback

     @working
     Examples:
       | name  | age |
       | John  | 30 |
       | Anna  | 20 |