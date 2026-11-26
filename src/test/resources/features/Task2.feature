# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

@task2
Feature: List of people with jobs management
  As a test engineer
  I want to verify adding, editing, removing, and resetting people in the list

  Background:
    Given I am on the list of people with jobs page

  Scenario: Add a new person to the list
    When I add a new person with name "Anna" and job "QA Automation Engineer"
    Then the people list contains 11 entries
    And person "Anna" with job "QA Automation Engineer" is in the people list

  Scenario: Edit an existing person in the list
    When I edit person "Mike" to have job "Senior Web Designer"
    Then the people list contains 10 entries
    And person "Mike" with job "Senior Web Designer" is in the people list
    And person "Mike" with job "Web Designer" is not in the people list

  Scenario: Remove an existing person from the list
    When I remove person "Jill" from the people list
    Then the people list contains 9 entries
    And person "Jill" with job "Support" is not in the people list

  Scenario: Reset list after adding a person
    When I add a new person with name "Anna" and job "QA Automation Engineer"
    Then the people list contains 11 entries
    And person "Anna" with job "QA Automation Engineer" is in the people list
    When I click the Reset List button
    Then the people list matches the original 10 entries

  Scenario: Reset list after editing a person
    When I edit person "Mike" to have job "Senior Web Designer"
    Then person "Mike" with job "Senior Web Designer" is in the people list
    When I click the Reset List button
    Then the people list matches the original 10 entries

  Scenario: Reset list after removing a person
    When I remove person "Jill" from the people list
    Then the people list contains 9 entries
    And person "Jill" with job "Support" is not in the people list
    When I click the Reset List button
    Then the people list matches the original 10 entries