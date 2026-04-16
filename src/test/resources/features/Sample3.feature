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

  @test
  Scenario Outline: Provide feedback form
    Given I am on provide feedback page
    When I select language: "<language>"
    And I select genre: "<genre>"
    And I select rating: "<rating>"
    And I enter comment: "<comment>"
    And I click Send button
    Then I see submitted language: "<language>"
    And I see submitted genre: "<genre>"
    And I see submitted rating: "<rating>"
    And I see submitted comment: "<comment>"

    Examples:
      | language | genre  | rating     | comment         |
      | English  | Male   | Good       | Great service!  |
      | French   | Female | Ok, i guess| Could be better |
      | Spanish  | Male   | Bad        | Not happy       |
      | Chinese  | Female | Why me?    | No comment      |