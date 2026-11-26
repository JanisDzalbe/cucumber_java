# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: As a tester i want to add, edit and remove person from List of people with jobs

  Background:
    Given I navigate to list of people with jobs page

  Scenario Outline: a new scenario to Add a new person to the list
    When I click Add person button
    And I add a new person with details:
      | name | <name> |
      | job  | <job>  |
    And I click Add button
    Then I should see "<name>" with job "<job>" in the people list

    Examples:
      | name    | job             |
      | Smrithy | QA Engineer     |
      | Mark    | Test Automation |

  Scenario: a new scenario to Edit an existing person in the list
    When I press the edit button for "Mike"
    And I enter the person name: "Smrithy"
    And I enter the person job: "Tester"
    And I press the Edit button
    Then I see "Smrithy" with job "Tester" in the list
    And I see "Mike" is invalid in the list



  Scenario Outline:a new scenario to Remove a person from the list
    When I remove person "<name>" from the list
    Then I should not see "<name>" in the people list

    Examples:
      | name |
      | John |

@resetlist
  Scenario:a new scenario to Reset original list after adding a person
    When I add a new person with details:
      | name | Francis     |
      | job  | QA Engineer |
    And I click Add button
    And I click reset list button
    Then I should see the original number of list of people restored
    And I should not see "Francis" in the people list

  Scenario:a new scenario to Reset original list after editing a person
    When I press the edit button for "Mike"
    And I enter the person name: "Smrithy"
    And I enter the person job: "Tester"
    And I press the Edit button
    And I click reset list button
    Then I should see the original number of list of people restored
    And I should not see "Smrithy" in the people list

  Scenario:a new scenario to Reset original list after removing a person
    When I remove person "John" from the list
    And I click reset list button
    Then I should see the original number of list of people restored
    And I should see "John" in the people list