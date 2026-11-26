# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: Manage people with jobs

    Background:
    Given I open the list of people with jobs page

    Scenario: Add a new person
    When I add "Taylor Morgan" with job "Test Engineer"
    Then "Taylor Morgan" is listed with job "Test Engineer"

    Scenario: Edit an existing person
    When I edit "John" to "John Miller" with job "QA Lead"
    Then "John Miller" is listed with job "QA Lead"
    And "John" is absent from the people list 
    #11

    Scenario: Remove an existing person
    When I remove "John" from the people list
    Then "John" is absent from the people list
    #11

    Scenario: Reset the list after adding a person
    When I add "Taylor Morgan" with job "Test Engineer"
    Then "Taylor Morgan" is listed with job "Test Engineer"
    When I click Reset List
    Then the original people list is restored
    #1,9

    Scenario: Reset the list after editing a person
    When I edit "John" to "John Miller" with job "QA Lead"
    Then "John Miller" is listed with job "QA Lead"
    When I click Reset List
    Then the original people list is restored
    #2.67

    Scenario: Reset the list after removing a person
    When I remove "John" from the people list
    Then "John" is absent from the people list
    When I click Reset List
    Then the original people list is restored
    #12