# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Task 2
  I want to test reset functionality

  Background:
    Given I am on people with jobs page

  Scenario: add a new person
    When I click add person button
    And I input name "Artis" and job "Tester"
    And I click add button
    Then List contains person with name "Artis" and job "Tester"

  Scenario: edit a person
    When I click edit button for "Jill"
    And I change job to "Tester"
    And I click save edit button
    Then List contains person with name "Jill" and job "Tester"

  Scenario: remove a person
    When I click remove button for "Jill"
    Then List doesn't contain person with name "Jill"

  Scenario: reset list
    When I click remove button for "Jill"
    And I click reset list button
    Then List contains person with name "Jill"

