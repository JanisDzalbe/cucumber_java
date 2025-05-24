# TODO - Create 1 scenario outline and create scenario or scenario outlines for page
# https://acctabootcamp.github.io/site/tasks/list_of_people.html OR
# https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after:
#   * adding a person
#   * editing a person
#   * removing a person
# - check that clear button on adding a user works correctly
Feature: Task 2 - manage people with jobs from "Breaking Bad" series

  Background:
    Given I open people with jobs page

  Scenario Outline: Add a new person to the list
    When I click the add person button
    And I enter the following details:
      | name | <name> |
      | job  | <job>  |
    And I click the confirm button
    Then I should see "<name>" with job "<job>" in the list
    Examples:
      | name         | job        |
      | Walter White | Teacher    |
      | Skyler White | Bookkeeper |

  Scenario: Edit a person in the list
    Given I have added a person "Skyler White" with job "QA Engineer"
    When I edit the job for "Skyler White" to "Bookkeeper"
    And I click edit button
    Then I should see "Skyler White" with job "Bookkeeper" in the list

  Scenario: Remove a person from the list
    Given I have added a person "Walter White" with job "Teacher"
    When I remove "Walter White" from the list
    Then "Walter White" should no longer be in the list

  Scenario: Reset the list after adding a person
    Given I have added a person "Walter White" with job "Teacher"
    When I click the reset list button
    Then "Walter White" should no longer be in the list

  Scenario: Reset the list after editing a person
    Given I have added a person "Skyler White" with job "QA Engineer"
    When I edit the job for "Skyler White" to "Bookkeeper"
    And I click edit button
    And I click the reset list button
    Then "Skyler White" should no longer be in the list
#
  Scenario: Reset the list after removing a person
    Given I have added a person "Walter White" with job "Teacher"
    When I remove "Walter White" from the list
    Then "Walter White" should no longer be in the list
    And I click the reset list button
    Then "Walter White" should no longer be in the list

  Scenario Outline: Clear button clears the form inputs
    When I click the add person button
    And I enter the following details:
      | name | <name> |
      | job  | <job>  |
    When I click the clear all fields button
    Then the name and job fields should be empty
    Examples:
      | name         | job        |
      | Walter White | Teacher    |
      | Skyler White | Bookkeeper |