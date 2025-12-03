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
#   Navigate to page
#   Set name and age based on test Examples
#   Click "Send" button and verify that previous input is displayed in correct fields

  @Sample_3_scenario
  @debugs
  Scenario Outline: Sample_3_custom_scenarion
    Given I am on Feedback page
    When I enter name into feedback: "<name>"
    And I enter age into feedback: <age>
    And I click submit feedback
    Then check form data Name: "<name>"
    Then check form data Age: <age>
#    Then check form data "<name>" and <age>
    @working
    Examples:
      | name  | age |
      | John   | 30   |
      | Bob   | 14   |
      | Jack   | 25   |

