# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: People with jobs

  Background:
    Given user opens the People with jobs page

  Scenario: Add a new person
    When user adds person "Kath" with job "Tester"
    Then person "Kath" with job "Tester" should be displayed

  Scenario: Edit an existing person
    When user changes the job of "Jane" to "Janitor"
    Then person "Jane" should have job "Janitor"

  Scenario: Remove an existing person
    When user removes person "Sarah"
    Then person "Sarah" should not be displayed

  Scenario Outline: Reset original list after changes
    When user <action>
    And user clicks the Reset List button
    Then the original list should be displayed:
      | Name   | Job               |
      | Alex   | DevOps Engineer   |
      | Carlos | Data Analyst      |
      | Mike   | Web Designer      |
      | Jane   | Accountant        |
      | David  | Project Manager   |
      | Sarah  | Product Manager   |
      | Jill   | Support           |
      | Emily  | UX Designer       |
      | John   | Software Engineer |
      | Maria  | QA Engineer       |

    Examples:
      | action                                         |
      | adds person "Kath" with job "Tester"           |
      | changes the job of "Jane" to "Janitor"  |
      | removes person "Sarah"                          |