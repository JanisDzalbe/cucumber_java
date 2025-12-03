@regression @task2
Feature: Manage list of people
  As a user
  I want to manage the list of people
  So that I can add, edit, remove and reset the list correctly

  Background:
    Given I am on list of people page

  Scenario Outline: Add a new person
    When I add a new person with name "<name>", age "<age>" and job "<job>"
    Then I see person "<name>" with age "<age>" and job "<job>" in the list

    Examples:
      | name | age | job         |
      | Ann  | 25  | Developer   |
      | Bob  | 30  | QA engineer |

  Scenario: Edit a person
    Given person "John" with age "40" and job "Manager" exists in the list
    When I edit this person to name "John", age "41" and job "Senior manager"
    Then I see person "John" with age "41" and job "Senior manager" in the list

  Scenario: Remove a person
    Given person "Anna" with age "22" and job "Intern" exists in the list
    When I remove person "Anna"
    Then I do not see person "Anna" in the list

  Scenario: Reset original list after adding a person
    When I add a new person with name "Kate", age "28" and job "Designer"
    And I click reset list button
    Then I see the original list of people
    And I do not see person "Kate" in the list

  Scenario: Reset original list after editing a person
    Given person "Peter" with age "35" and job "Developer" exists in the list
    When I edit this person to name "Peter", age "36" and job "Senior developer"
    And I click reset list button
    Then I see the original list of people

  Scenario: Reset original list after removing a person
    Given person "Maria" with age "32" and job "QA" exists in the list
    When I remove person "Maria"
    And I click reset list button
    Then I see the original list of people

  Scenario: Clear button on adding a user works correctly
    When I fill add user form with name "Lucy", age "27" and job "Tester"
    And I click clear add user form button
    Then add user form fields are empty
