# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: Manage people with jobs

  Background:
    Given I open the People with Jobs page

  Scenario: Add a new person
    When I click Add person
    And I enter name "John Smith" and job "QA Engineer"
    And I click the Add button
    Then I should see "John Smith" with job "QA Engineer" in the list
    When I reset the original list
    Then I should see the original list

  Scenario: Edit an existing person
    When I click the Edit button for "Mike"
    And I change his name to "Peter Parker"
    And I click the Edit button
    Then I should see "Peter Parker" with job "Web Designer" in the list
    When I reset the original list
    Then I should see the original list

  Scenario: Remove an existing person
    When I remove "Mike" from the list
    Then I should not see "Mike" in the list
    When I reset the original list
    Then I should see the original list

