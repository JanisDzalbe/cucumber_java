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

Feature: Task2

  Background:
    Given people with jobs page is displayed

  Scenario Outline: add person
    When press add person button
    And enter "<name>" and "<job>"
    And press add button
    Then New person with "<name>" and "<job>" is displayed

    Examples:
      | name  | job       |
      | Jack  | Builder   |
      | Karen | Secretary |
      | Ahmed | Driver    |

  Scenario: edit person
    When press pencil button
    And enter new name and job
    And press edit button
    Then new name and job is displayed

  Scenario: remove person
    When press delete button
    Then person deleted and is not displayed

    Scenario Outline: reset after person added
      When press add person button
      And enter "<name>" and "<job>"
      And press add button
      Then New person with "<name>" and "<job>" is displayed
      And press reset button and return page to primary state
      Examples:
        | name  | job       |
        | Jack  | Builder   |

    Scenario: reset after person edited
      When press pencil button
      And enter new name and job
      And press edit button
      Then new name and job is displayed
      And press reset button and return page to primary state

    Scenario: reset after person deleted
      When press delete button
      Then person deleted and is not displayed
      And press reset button and return page to primary state

    Scenario Outline: clear button
      When press add person button
      And enter "<name>" and "<job>"
      And press clear all fields button
      Then all fields are clear
      Examples:
        | name  | job     |
        | Jack  | Builder |
