# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Testing functionality of list of people with jobs
  As a test engineer
  I want to test if page of people with jobs works properly

  Scenario: Check functionality of Add person
    Given I am on page "People with jobs"
    When I click button "Add new person"
    And I enter new person's name "Ilmars"
    And I enter new person's job "Junior tester"
    And I click button to confirm new person's information
    Then I see that "Ilmars" is a "Junior tester"

  Scenario: Check functionality of Edit person
    Given I am on page "People with jobs"
    When I click edit button of person "Mike"
    And I change person's job to "CEO"
    And I confirm the edit
    Then I see that "Mike" is a "CEO"

  Scenario: Check functionality of Remove person
    Given I am on page "People with jobs"
    When I click remove button of person "Mike"
    Then I see no person with name "Mike"

  Scenario: Check functionality of Reset
    Given I am on page "People with jobs"
    When I click remove button of person "Mike"
    And I click on Reset button
    Then I see the initial list of persons

