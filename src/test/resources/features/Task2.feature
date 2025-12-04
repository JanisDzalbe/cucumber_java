# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: List of people with jobs page
  As a user
  I want to edit the list of people in the page

  Background:
    Given I am on the list of people with jobs page

  Scenario: Add a new person
    When I click add person
    And I fill fields
      | name | Jusus   |
      | job  | Savior  |
    And I click Add button
    Then I see person added to the list
      | name | Jusus   |
      | job  | Savior  |


  Scenario: Edit a person
    When I click Edit button for person: "John"
    And I see person values
      | name | John              |
      | job  | Software Engineer |
    And I change Job: "Gas Station Casher"
    And I click Edit button
    Then I see person updated
      | name | John               |
      | job  | Gas Station Casher |


  Scenario: Remove a person
    When I click Delete button for person: "John"
    Then I see person "John" is removed from the list


  Scenario: reset original list after removing a person
    When I click Delete button for person: "Emily"
    And I see person "Emily" is removed from the list
    And click Reset List
    Then I see list is back to initial state