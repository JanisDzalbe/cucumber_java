# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: People list and their job

  Background:
    Given I am on list of people page

  Scenario: Add a new person
    When I click add person button
    And I enter person name "Sruthy" and job "QA Engineer"
    And I click add button
    Then I can see person "Sruthy" with job "QA Engineer" in the list

  Scenario: Edit a person
    When I click edit button for person "Mike"
    And I update person name to "John" and job to "Lead Developer"
    And I click edit save button
    Then I can see person "John" with job "Lead Developer" in the list

  Scenario: Remove a person
    When I click remove button for person "Jill"
    Then I cannot see person "Jill" in the list

  Scenario: Reset original list
    When I click remove button for person "Jill"
    And I click reset list button
    Then I can see person "Jill" in the list