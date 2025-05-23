# TODO - Create 1 scenario outline and create scenario or scenario outlines for page
# https://acctabootcamp.github.io/site/tasks/list_of_people.html OR
# https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after:
#   * adding a person
#   * editing a person
#   * removing a person
# - check that clear button on adding a user works correctly
Feature: People List Management

  Background:
    Given I open the people list page
    And The default list is visible

  Scenario Outline: Add new person
    When I open add person dialog
    And I input "<Name>" and "<Job>"
    And I confirm adding the person
    Then The list includes a person named "<Name>" with job "<Job>"

    Examples:
      |Name       |Job          |
      |Alice      |Developer    |
      |Daniils    |Programmer   |
      |Mark       |Web Developer|


  Scenario: Edit a Person
    When I edit the 3rd person
    And I update the name to "Inga" and job to "Teacher"
    And Press edit Button
    Then The 3rd person’s name is "Inga" and job is "Teacher"

  Scenario: Delete a person
    When I delete the person name "Mike"
    Then The list does not contain "Mike"
    And The total number of people decreased

  Scenario: Reset list after adding a person
    When I open add person dialog
    And I input "Marcis" and "Programmer"
    And I confirm adding the person
    Then The list includes a person named "Marcis" with job "Programmer"
    When I reset the people list
    Then The default list is shown

  Scenario: Reset list after editing a person
    When I edit the 1st person
    And I update the name to "Inga" and job to "Teacher"
    And Press edit Button
    Then The 1st person’s name is "Inga" and job is "Teacher"
    When I reset the people list
    Then The default list is shown

  Scenario: Reset list after deleting a person
    When I delete the person name "Mike"
    Then The list does not contain "Mike"
    And The total number of people decreased
    When I reset the people list
    Then The default list is shown

  Scenario: Clear input fields on add dialog
    When I open add person dialog
    And I input "Test" and "Text"
    And I clear all inputs
    Then All input fields are empty
