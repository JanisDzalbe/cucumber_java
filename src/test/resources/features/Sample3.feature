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


#   create Scenario Outline for 'Give us your feedback!' page
    @test
    Scenario Outline: entering only name and surname in feedback
      Given I am on Provide Feedback page
      When  I enter the name: "<name>"
      And   I enter the age: <age>
      And   I click Send
      Then  I see the confirmation check page
      And   I see the name is "<name>" and age is <age>

      Examples:
        | name  | age |
        | Ann   | 5   |
        | Marry | 50  |

#   URL: https://janisdzalbe.github.io/example-site/tasks/provide_feedback
#   Navigate to page
#   Set name and age based on test Examples
#   Click "Send" button and verify that previous input is displayed in correct fields
