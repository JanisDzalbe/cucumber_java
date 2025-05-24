Feature: Task 2

  Background:
    Given I am on the page of People with jobs

  Scenario Outline: Add a new person to the list
    When I click Add person button
    When I enter the values:
      | name | <name> |
      | job  | <job>  |
    And I click the Add button
    And List size become bigger
    Examples:
      | name    | job       |
      | Alice   | Engineer  |
      | Bob     | Designer  |

  Scenario: Edit a person in a list
    When I click pencil button
    Then I enter name "Bob" and job "admin"
    And I click edit button
    And I see another name "Bob" and job "admin" for person 1

  Scenario: Remove a person from a list
    When I click remove button
    And List size become smaller

  Scenario: reset original list after adding a person
    When I click Add person button
    Then I enter name "Bob" and job "admin"
    And I click the Add button
    And List size become bigger
    Then I click Reset List button
    And Main list restored

  Scenario: reset original list after editing a person
    When I click pencil button
    Then I enter name "Bob" and job "admin"
    And I click edit button
    And I see another name "Bob" and job "admin" for person 1
    Then I click Reset List button
    And Main list restored

  Scenario: reset original list after removing a person
    When I click remove button
    And List size become smaller
    Then I click Reset List button
    And Main list restored

  Scenario: check that clear button on adding a user works correctly
    When I click Add person button
    Then I enter name "Bob" and job "admin"
    Then I click Clear all fields button
    And Fields with name and job are clear

  
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
