# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: People with jobs
  As a test engineer
  I want to check that a person can be added, edited, removed and the list can be reset
  Background:
    Given I open the people list page
    And I click reset list

  Scenario: Add a new person
    When I add a person with name "Anna" and job "Tester"
    Then I see "Anna" with job "Tester" in the list
    And the list has 11 people

   Scenario: Edit a person
     When I edit person "Mike" to name "Michael" and job "Manager"
     Then I see "Michael" with job "Manager" in the list
     And I do not see "Mike" in the list
     And the list has 10 people

   Scenario: Remove a person
     When I remove person "Jill"
     Then I do not see "Jill" in the list
     And the list has 9 people

   Scenario: Reset list after adding a person
     When I add a person with name "Anna" and job "Tester"
     And I click reset list
     Then I do not see "Anna" in the list
     And the list has 10 people

   Scenario: Reset list after editing a person
     When I edit person "Mike" to name "Michael" and job "Manager"
     And I click reset list
     Then I see "Mike" with job "Web Designer" in the list
     And I do not see "Michael" in the list

   Scenario: Reset list after removing a person
     When I remove person "Jill"
     And I click reset list
     Then I see "Jill" with job "Support" in the list
     And the list has 10 people