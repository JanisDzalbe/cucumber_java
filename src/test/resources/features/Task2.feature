# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Manage list of people with jobs

  Background:
    Given I am on list of people page

  Scenario Outline: Add a new person
    When I click the Add person button
    And I enter name in page: "<name>"
    And I enter job: "<job>"
    And I click the Add button
    Then I should see "<name>" in the list with job "<job>"

    Examples:
      | name | job        |
      | Anna | QA Tester1 |


  Scenario: Edit an existing person
    When I click the edit icon for "Mike"
    Then I should see "Mike" in the name field
    And I should see "Web Designer" in the job field
    When I change the job to "Senior QA Tester1"
    And I click the Edit button
    Then I should see "Mike" in the list with job "Senior QA Tester1"



  Scenario: Remove an existing person
    When I click the remove icon for "Mike"
    Then I do not see "Mike" in the list

  Scenario Outline: Reset the original list with different actions
    Given I remember the initial number of people
    When I modify the people list by "<action>"
    And I click the Reset List button
    Then the original number of people is restored

    Examples:
      | action                       |
      | adding a new person          |
      | editing an existing person   |
      | removing an existing person  |