@regression @part4
Feature: Introduction to cucumber part 4
  As a test engineer
  I want to be able to write and execute a scenario with steps that have 2 column tables

  Background:
    Given I am on age page

  Scenario: a new scenario with 2-column table
    When I enter values:
      | name | Ann |
      | age  | 5   |
    And I click submit age
    Then I see message: "Hello, Ann, you are a kid"

  Scenario: another new scenario with 2-column table
    When I enter values:
      | name | Bob |
      | age  | 61  |
    And I click submit age
    Then I see message: "Hello, Bob, you are an adult"

  Scenario Outline: a new scenario outline 2
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
  # Navigate to the page
  # Set Name, Age and Genre
  # Click "Send" button and verify that previous input is displayed in correct fields
  # - All input MUST be done in single step
  # - All input MUST use Examples for data
  # - Step can use Map or DataTable

    Scenario Outline: a new scenario outline 3
      Given I am on feedback page
      When I input Name, Age and Genre in feedback
      | name  | <name>  |
      | age   | <age>   |
      | genre | <genre> |
      And I click send feedback
      Then I see submitted name "<name>"
      And I see submitted age <age>
      And I can see "<genre>" selected in the genre field
      And I see Name, Age and Genre in feedback review page
        | name  | <name>  |
        | age   | <age>   |
        | genre | <genre> |

      Examples:
        | name  | age | genre  |
        | Katya | 20  | Female |
        | John  | 30  | Male   |



