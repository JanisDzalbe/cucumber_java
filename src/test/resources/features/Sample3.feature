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
      | name | age | message                        |
      | Tom  | 15  | Hello, Tom, you are a teenager |

  @test
  Scenario Outline: feedback page scenario outline
    Given I am on feedback page
    When I enter feedback name: "<name>"
    And I enter feedback age: <age>
    And I choose feedback option: "<option>"
    And I enter feedback comment: "<comment>"
    And I click send feedback
    Then I should see feedback name: "<name>"
    And I should see feedback age: "<age>"
    And I should see feedback option: "<option>"
    And I should see feedback comment: "<comment>"

    Examples:
      | name  | age | option | comment          |
      | Anna  | 22  | Good   | Very nice page   |
      | Bob   | 35  | Bad    | Needs improvement |
      | Marry | 41  | Why me?| Strange question |