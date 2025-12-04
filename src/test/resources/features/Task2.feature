# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
@regression
Feature: Manage list of people with jobs
  As a test engineer
  I want to test adding, editing, removing and resetting a list of people

  Background:
    Given I am on people with jobs page

  Scenario: User can add a new person
    When I click add person button
    And I enter person name "Juris"
    And I enter person job "Cool guy"
    And I confirm add person
    Then I should see person "Juris" with job "Cool guy"

  Scenario: User can edit an existing person
    When I click edit button for person 1
    And I update person name "Mike"
    And I update person job "Architect"
    And I confirm edit person
    Then I should see person "Mike" with job "Architect"

  Scenario: User can remove a person
    When I click delete button for person 1
    Then person 1 should not exist

  Scenario: Reset restores original list
    When I click reset list button
    Then original people list is restored

