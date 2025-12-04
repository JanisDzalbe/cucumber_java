# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Introduction to cucumber part 6
  As a test engineer
  I want to be able to write and execute multiple scenarios

  Background:
    Given I am on People with jobs page

  Scenario: a new scenario where i add a new person
    When I click Add person button
    And I add a new person
      | name | Kristiana |
      | job  | Tester    |
    And I click Add button
    Then I can see added person in People with jobs list
      | name | Kristiana |
      | job  | Tester    |


  Scenario: a new scenario where i edit existing person
    When I click Edit person button
    And I change Job field
      | job | Manager |
    And I click Edit button
    Then I can see persons new job
      | job | Manager |


  Scenario: a new scenario where i remove a person
    And I click Remove person button
    Then I can see person is removed from the list


  Scenario: a new scenario where i reset original list after changes
    When I click Add person button
    And I add a new person
      | name | Kristiana |
      | job  | Tester    |
    And I click Add button
    Then I can see added person in People with jobs list
      | name | Kristiana |
      | job  | Tester    |
    When I click Reset List button
    Then I can see original list