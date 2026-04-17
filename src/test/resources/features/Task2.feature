# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: List of people with jobs
    Background:
      Given I am on list of people with jobs page


  Scenario:Add new person to the list of people whit jobs
    When I click "Add person" button
    And I enter name: "Nik"
    And I enter job: "Tester"
    Then I click "Add" button
    And I check if the new person "Nik" the "Tester" is added
  Scenario:Edit person in the list of people whit jobs
    When I click Edit button for "David"
    And I change name to "Andy"
    And I change job to "Senior Tester"
    Then I click "Edit" button
    And I check if the person is changed to "Andy" the "Senior Tester"
  Scenario:Remove person from the list of people whit jobs
    When I click Delete button for "Carlos"
    Then I check if the person "Carlos" is removed from the list
  Scenario: Reset original list after adding a person
    When I click "Add person" button
    And I enter name: "Nik"
    And I enter job: "Tester"
    Then I click "Add" button
    And I click "Reset List" button
    Then I check if the original list is restored