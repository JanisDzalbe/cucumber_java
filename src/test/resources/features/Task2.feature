# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
@regression
Feature: Manage list of people
  As a user
  I want to add, edit, remove and reset list
  So that the list works correctly

  Background:
    Given I am on "People with jobs" page

  #Add a new person
  Scenario: Add a new person
    When I click "Add person" button
    And I fill form with:
      | name | Ihor              |
      | job  | Automation Tester |
    And I click add button
    Then I should see person in the list:
      | name | Ihor              |
      | job  | Automation Tester |
    And list size should increase by one

  #Edit a person
  Scenario: Edit a person
    When I click edit for person "Mike"
    Then I should see prefilled form with:
      | name | Mike         |
      | job  | Web Designer |
    When I change job to "Tester"
    And I click edit button
    Then I should see person in the list:
      | name | Mike   |
      | job  | Tester |

  #Remove a person
  Scenario: Remove a person
    When I click delete a person "Mike"
    Then I should not see person "Mike" in the list

  # Reset original list
  Scenario: Reset original list
    When I add person "Ihor" with job "Tester"
    And I click reset list
    Then I should see original list