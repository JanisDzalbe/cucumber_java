# TODO - Create scenarios (or scenario outlines) for page
@regression
Feature: People with jobs
  As a user
  I want to manage people with jobs
  So that I can add, edit, remove, and restore the list

  Background:
    Given I am on people with jobs page

  Scenario: Add a new person
    When I add person with values:
      | name | Anna |
      | job  | Tester |
    Then I can see person "Anna" with job "Tester"

  Scenario: Edit a person
    When I edit person "John" and change job to "Senior Software Engineer"
    Then I can see person "John" with job "Senior Software Engineer"

  Scenario: Remove a person
    When I remove person "Jane"
    Then I cannot see person "Jane"

  Scenario: Reset original list after adding a person
    When I add person with values:
      | name | Anna |
      | job  | Tester |
    And I click reset list
    Then I can see original list with 10 people

  Scenario: Reset original list after editing a person
    When I edit person "John" and change job to "Architect"
    And I click reset list
    Then I can see original list with 10 people

  Scenario: Reset original list after removing a person
    When I remove person "Jane"
    And I click reset list
    Then I can see original list with 10 people
