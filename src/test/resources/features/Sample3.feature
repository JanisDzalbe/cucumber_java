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

  @test
  Scenario Outline: a new feedback page scenario outline

    #   Navigate to page
    Given I am on feedback page

    #   Set name and age based on test Examples
    When I enter name "<name>" in feedback page
    And I enter age <age> in feedback page

    #   Click "Send" button and verify that previous input is displayed in correct fields
    And I click send feedback
    Then I see name "<name>" in feedback review page
    And I see age <age> in feedback review page

    Examples:
      | name | age |
      | Mike | 5   |
      | Nike | 55  |