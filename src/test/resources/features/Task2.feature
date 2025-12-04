# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Manage list of people with jobs
  As a user
  I want to manage the list of people with jobs
  So that I can add, edit, remove and reset the list

  Background:
    Given I am on list of people with jobs page

  Scenario: See initial list of people with jobs
    Then I should see initial list of 10 people with correct names and jobs

  Scenario: Add a new person
    When I add a person with name "Ravindu" and job "Test Engineer"
    Then the list should contain a person with name "Ravindu" and job "Test Engineer"

  Scenario: Edit an existing person
    Given the list contains a person with name "Mike" and job "Web Designer"
    When I change job for person "Mike" from "Web Designer" to "Senior Web Designer"
    Then the list should contain a person with name "Mike" and job "Senior Web Designer"
    And the list should not contain a person with name "Mike" and job "Web Designer"

  Scenario: Remove an existing person
    Given the list contains a person with name "Carlos" and job "Data Analyst"
    When I remove a person with name "Carlos" and job "Data Analyst"
    Then the list should not contain a person with name "Carlos" and job "Data Analyst"

  Scenario: Reset list after adding a person
    When I add a person with name "Temp Person" and job "Temp Job"
    Then the list should contain a person with name "Temp Person" and job "Temp Job"
    When I reset the list of people with jobs
    Then the list should not contain a person with name "Temp Person" and job "Temp Job"
    And I should see initial list of 10 people with correct names and jobs

