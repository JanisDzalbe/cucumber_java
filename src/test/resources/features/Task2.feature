# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Task 2
  As a test engineer
  I want to be able to write and execute a scenario with parameters

  Background:
    Given I am on People with jobs page

  Scenario Outline: add a new person
    When I press Add person button
    And I enter the values in form:
      | name  | <name> |
      | job   | <job>  |
    And I press the Add button
    Then I check if the person is added to the list:
      | name  | <name> |
      | job   | <job>  |

    Examples:
    | name | job |
    | Mariss | Test Engineer |


  Scenario Outline: edit a person
    When I press the Edit button for "<name>"
    And I check the person values are prepopulated
      | name  | <name> |
      | previousJob   | <previousJob>  |
    And I edit the current job to "<job>"
    And I press the Edit button
    Then I check if the person is updated in the list:
      | name  | <name> |
      | job   | <job>  |

    Examples:
      | name | job          | previousJob|
      | John | Test Subject | Software Engineer |


  Scenario Outline: remove a person
    When I press the Delete button for "<name>"
    Then I check if the person "<name>" is removed from the list

    Examples:
      | name |
      | Carlos |


  Scenario Outline: reset original list after removing a person
    When I press the Delete button for "<name>"
    And I check if the person "<name>" is removed from the list
    Then I press Reset List button
    And I check if the list is in initial state

    Examples:
      | name |
      | Carlos |