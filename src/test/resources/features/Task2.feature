# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Task 2 - List of people with jobs
  As a Test Engineer
  I want to check that a user can add, edit and remove people and reset the list

  Background:
    Given I am on People with jobs page

  Scenario: add a new person
    When I add person "Krishna Prabhu" with job "Test Specialist"
    Then person "Krishna Prabhu" with job "Test Specialist" is added to the list
    And I see the people list should contain 11 entries

  Scenario: edit a person
    When I click the Edit icon for "Jill"
    Then I edit the Job field to "QA Engineer" and see the updated job "QA Engineer" in the list
    And I see the people list should contain 10 entries

  Scenario: remove a person
    When I remove person "Sarah"
    Then I see person "Sarah" with job "Product Manager" is removed from the initial list
    And I see the people list should contain 9 entries

  Scenario: reset the list after adding a person
    When I add person "Krishna Prabhu" with job "Test Specialist"
    Then person "Krishna Prabhu" with job "Test Specialist" is added to the list
    When I reset the list
    Then I see the initial list

  Scenario: reset the list after editing a person
    When I click the Edit icon for "Jill"
    Then I edit the Job field to "QA Engineer" and see the updated job "QA Engineer" in the list
    When I reset the list
    Then I see the initial list

  Scenario: reset the list after removing a person
    When I remove person "Sarah"
    Then I see person "Sarah" with job "Product Manager" is removed from the initial list
    When I reset the list
    Then I see the initial list