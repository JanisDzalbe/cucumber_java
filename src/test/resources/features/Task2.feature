# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
@regression
Feature: List of people with jobs

  Background:
    Given I am on page with people list
    And I press the Reset List button
    And I remember the original list

  Scenario: Add a new person
    When I press the Add person button
    And I enter person name: "Josh"
    And I enter person job: "Developer"
    And I press the Add button
    Then I see "Josh" with job "Developer" in the list
    And I see 11 people in the list

  Scenario: Edit a person
    When I press the edit button for "Mike"
    And I enter person name: "Michael"
    And I enter person job: "Tester"
    And I press the Edit button
    Then I see "Michael" with job "Tester" in the list
    And I do not see "Mike" in the list
    And I see 10 people in the list

  Scenario: Remove a person
    When I press the remove button for "Mike"
    Then I do not see "Mike" in the list
    And I see 9 people in the list

  Scenario: Reset list after adding a person
    When I press the Add person button
    And I enter person name: "Josh"
    And I enter person job: "Developer"
    And I press the Add button
    Then I see "Josh" with job "Developer" in the list
    When I press the Reset List button
    Then I do not see "Josh" in the list
    And I see the original list

  Scenario: Reset list after editing a person
    When I press the edit button for "Mike"
    And I enter person name: "Michael"
    And I enter person job: "Tester"
    And I press the Edit button
    Then I see "Michael" with job "Tester" in the list
    When I press the Reset List button
    Then I see "Mike" with job "Web Designer" in the list
    And I do not see "Michael" in the list
    And I see the original list

  Scenario: Reset list after removing a person
    When I press the remove button for "Mike"
    Then I do not see "Mike" in the list
    When I press the Reset List button
    Then I see "Mike" with job "Web Designer" in the list
    And I see the original list