# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Task 2
  As a test engineer
  I want to implement a bunch of scenarios and steps for fully testing list of people page

  Background:
    Given I am on the list of people with jobs page

  Scenario: Add a new person to the list
    When I click add person button
    And I enter person name: "Beate"
    And I enter person job: "Quality Assurance Engineer"
    And I click add button
    Then I should see person "Beate" with job "Quality Assurance Engineer" in the list

  Scenario: Edit an existing person
    Given I see person "Jill" with job "Support" in the list
    When I click edit button for person "Jill"
    And I enter person name: "Jill"
    And I enter person job: "QA Engineer"
    And I click update edit button
    Then I should see person "Jill" with job "QA Engineer" in the list

  Scenario: Remove an existing person
#     could technically check just for the name and not job but
#     in previous scenario something similar with job is implemented, the given part
#     so I will just use that instead even though job isn't neccessary here
    Given I see person "Jill" with job "Support" in the list
    When I click remove button for person "Jill"
    Then I should not see person "Jill" in the list

  Scenario: Reset the list of people to original state
#    When I click add person button
#    And I enter person name: "Beate"
#    And I enter person job: "Quality Assurance Engineer"
#    And I click add button
    When I click the reset list button
#     Pasting previous snippets for testing the test
#     When I click edit button for person "Jill"
#     And I enter person name: "Jill"
#     And I enter person job: "QA Engineer"
#     And I click update edit button
    Then I should see the original people in the list