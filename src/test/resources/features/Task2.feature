# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

  Feature:
    As a test engineer
  I want to test the job management page

  Background:
    Given I am on list of people with jobs page

    Scenario: Add a new person
       When I click Add Person button
      And I enter name: "David S" and job: "QA"
      Then I click on Add button
      And I see name: "David S" with job: "QA" in the list
      When I click on the Reset list button
      Then I should see the original list

    Scenario: Remove a person
      When I remove person "John"
      Then I should not see "John" in list
      When I click on the Reset list button
      Then I should see "John" in the list

    Scenario Outline: Edit a person
      When I edit name: "<name>" with new name: "<newName>" and new job: "<newJob>"
      And I see name: "<newName>" with job: "<newJob>" in the list
      And I click on the Reset list button
      Then I should see the original list
      Examples:
        | name  | newName | newJob   |
        | John  | Pluto   | Engineer |