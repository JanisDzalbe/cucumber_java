Feature: Task 2 - List of people with jobs
  As a test engineer
  I want to check that a user can add, edit and remove people and reset the list

  Background:
    Given I am on list of people page
    And there are 10 people in the list

  Scenario: add a new person
    When I add person "Anna" with job "Test Automation Engineer"
    Then person "Anna" with job "Test Automation Engineer" is added to the initial list

  Scenario Outline: edit a person
    When I change person "<name>" with job "<job>" to "<new name>" with job "<new job>"
    Then person "<name>" with job "<job>" is replaced by "<new name>" with job "<new job>" in the initial list
    Examples: only job, only name, both
      | name | job               | new name | new job            |
      | John | Software Engineer | John     | Software Architect |
      | Jill | Support           | Julia    | Support            |
      | Mike | Web Designer      | Michael  | Team Lead          |

  Scenario: remove a person
    When I remove person "Jane"
    Then person "Jane" with job "Accountant" is removed from the initial list

  Scenario: reset the list after adding a person
    When I add person "Anna" with job "Test Automation Engineer"
    Then person "Anna" with job "Test Automation Engineer" is added to the initial list
    When I reset the list
    Then I see the initial list

  Scenario: reset the list after editing a person
    When I change person "John" with job "Software Engineer" to "Johnny" with job "Software Architect"
    Then person "John" with job "Software Engineer" is replaced by "Johnny" with job "Software Architect" in the initial list
    When I reset the list
    Then I see the initial list

  Scenario: reset the list after removing a person
    When I remove person "Jane"
    Then person "Jane" with job "Accountant" is removed from the initial list
    When I reset the list
    Then I see the initial list
