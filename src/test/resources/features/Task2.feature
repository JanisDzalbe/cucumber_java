# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Independent task2 completion
  As a test engineer
  I want to be able to write and execute a scenario outline

  Background:
    Given I am on the "People with Job" page

  Scenario Outline: Add a new person
    When I can see both buttons are enabled and displayed
    And I click on Reset to reset the list
    And I click on Add to add a new person
    And I am on Add page
    And I enter a name and job:
      | name | <name> |
      | job  | <job>  |
    And I click on Add
    Then I validated that new person is added:
      | name | <name> |
      | job  | <job>  |
    Examples:
    | name   | job                    |
    | Maksym | Java Software Engineer |
    | Lika   | QA Automation Engineer |

  Scenario: Edit a person
    When I can see both buttons are enabled and displayed
    And I click on Reset to reset the list
    And I click on Edit icon to edit Mike
    And I am on Edit page
    And I see filled data: "Mike" and "Web Designer"
    And I change data for job: "HR"
    Then I click on Edit to submit
    And I check if "Mike" has modified job: "HR"

  Scenario: Remove a person
    When I can see both buttons are enabled and displayed
    And I click on Reset to reset the list
    And I click on the remove icon for the first person: "Mike"
    Then I validate that first person is no longer in the list: "Mike"

  Scenario: Reset list after adding a person
    When I can see both buttons are enabled and displayed
    And I click on Reset to reset the list
    And I click on the remove icon for the first person: "Mike"
    And I validate that first person is no longer in the list: "Mike"
    Then I click on Reset to reset the list
    And I validate the initial state of the list

