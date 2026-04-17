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
  # URL: https://janisdzalbe.github.io/example-site/tasks/provide_feedback
  # Navigate to page
  # Set Name, Age and Genre
  # - All input MUST be done in single step
  # - All input MUST use Examples for data
  # - Step can use Map
  # Click "Send" button and verify that previous input is displayed in correct fields

  Scenario Outline: Give us your feedback 2
    Given Im on feedback page
    When I add feedback details
    | name | <name>  |
    | age  | <age>   |
    |gender| <gender>|
    And I click send button
    Then I can see name "<name>" in feedback check
    And I can see age "<age>" in feedback check
    And I can see gender "<gender>" in feedback check
    And I can see details in feedback check
    | name | <name>  |
    | age  | <age>   |
    |gender| <gender>|
    Examples:
    |name|age|gender|
    | Ann | 5 | female |
    | Bob | 31| male   |
