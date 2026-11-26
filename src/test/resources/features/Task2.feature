
@regression @task2

Feature: Managing people with jobs

  Scenario: Add a new person
    Given I am on people with jobs page
    When I add person "Anna" with job "QA Engineer"
    Then I should see person "Anna" with job "QA Engineer"

  Scenario: Edit an existing person
    Given I am on people with jobs page
    When I edit person "Alex" to name "Alex Updated" and job "Senior DevOps Engineer"
    Then I should see person "Alex Updated" with job "Senior DevOps Engineer"
    And person "Alex" should not be in the list

  Scenario: Remove an existing person
    Given I am on people with jobs page
    When I remove person "Alex"
    Then person "Alex" should not be in the list

  Scenario: Reset list after adding a person
    Given I am on people with jobs page
    When I add person "Anna" with job "QA Engineer"
    Then I should see person "Anna" with job "QA Engineer"
    When I reset the people list
    Then the original people list should be restored

  Scenario: Reset list after editing a person
    Given I am on people with jobs page
    When I edit person "Alex" to name "Alex Updated" and job "Senior DevOps Engineer"
    Then I should see person "Alex Updated" with job "Senior DevOps Engineer"
    When I reset the people list
    Then the original people list should be restored

  Scenario: Reset list after removing a person
    Given I am on people with jobs page
    When I remove person "Alex"
    Then person "Alex" should not be in the list
    When I reset the people list
    Then the original people list should be restored
