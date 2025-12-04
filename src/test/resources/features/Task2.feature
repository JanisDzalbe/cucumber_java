# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: Manage list of people with jobs
  In order to maintain a list of people and their jobs
  As a user of the People with jobs page
  I want to add, edit, remove people and reset the list to its original state

  Background:
    Given I am on people with jobs page using PO
    And I see original people list using PO

  Scenario Outline: Add a new person to the list
    When I add a new person with name "<name>" and job "<job>" using PO
    Then I see person with name "<name>" and job "<job>" in list using PO

    Examples:
      | name         | job            |
      | John Smith   | Software dev   |
      | Anna Johnson | QA engineer    |

  Scenario: Edit an existing person in the list
    When I add a new person with name "Peter Parker" and job "Intern" using PO
    And I edit person with name "Peter Parker" changing name to "Peter P." and job to "Senior Engineer" using PO
    Then I see person with name "Peter P." and job "Senior Engineer" in list using PO
    And I do not see person with name "Peter Parker" in list using PO

  Scenario: Remove a person from the list
    When I add a new person with name "Mary Jane" and job "Project manager" using PO
    And I remove person with name "Mary Jane" using PO
    Then I do not see person with name "Mary Jane" in list using PO

  Scenario: Reset original list after modifications
    When I add a new person with name "Tony Stark" and job "Consultant" using PO
    And I add a new person with name "Bruce Wayne" and job "Investor" using PO
    And I remove person with name "Tony Stark" using PO
    And I edit person with name "Bruce Wayne" changing name to "Bruce W." and job to "CEO" using PO
    And I click reset list using PO
    Then I see that list is reset to original state using PO
    And I do not see person with name "Tony Stark" in list using PO
    And I do not see person with name "Bruce Wayne" in list using PO
