# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

  Feature: Manage people with jobs
    As a user
    I want to manage the list of people with jobs
    So that I can add, edit, remove people and reset the list

  Background:
    Given I am on People with jobs page

    Scenario: Add a new Person
      When I click "Add person" button
      And I enter "<Name>" and "<Job>"
      And I click "Add" button
      Then I see a new person's "<Name>" and "<Job>" in the list

      Examples:
      |Name   |  Job                      |
      |Nikita | Site Reliability Engineer |
      |Julija | Functional Tester         |
      |Alisa  | Product Owner             |

    Scenario: Edit a Person
      Given I see person "Mike" with job "Web Designer" in the list
      When I click Edit button for person: "Mike"
      And I edit the name to "Mikel" and "Intern"
      And I click "Edit" button
      Then I see person "Mikel" with job "Intern" in the list

    Scenario: Remove a Person
      When I click Remove button for person "Mikel"
      Then I see person "Mikel" with job "Intern" removed from the list

    Scenario: Reset list after changes
      When I click "Add person" button
      And I enter name "Nikita" and job "SRE"
      And I click "Add" button
      And I click "Reset List" button
      Then I see the original list of people with jobs




