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
Feature: checking functionality of list of people
    Background:
      Given I am on list of people page

    Scenario Outline: adding a new person
      When I navigate to new person page
      And I enter "<name>" and "<job>"
      And I click Add
      Then New person with "<name>" and "<job>" is present in the list
      And List size is increased by 1
      Examples:
        | name | job      |
        | Luna | Designer |

    Scenario: editing a person
      When I choose to edit person 3
      And I enter new name "Anna" and job "Designer"
      And I click edit
      Then I see new name "Anna" and job "Designer" for person 3

    Scenario: removing a person
      When I click remove "Jill"
      Then "Jill" record doesn't exist anymore
      And List size is decreased by 1

    Scenario: resetting a list after adding a new person
      When I navigate to new person page
      And I enter "Luna" and "Content creator"
      And I click Add
      Then New person with "Luna" and "Content creator" is present in the list
      And List size is increased by 1
      When I click reset
      Then Old list is restored

  Scenario: resetting a list after editing a person
    When I choose to edit person 2
    And I enter new name "Marie" and job "Teacher"
    And I click edit
    Then I see new name "Marie" and job "Teacher" for person 2
    When I click reset
    Then Old list is restored

  Scenario: resetting a list after removing a person
    When I click remove "Jill"
    Then "Jill" record doesn't exist anymore
    And List size is decreased by 1
    When I click reset
    Then Old list is restored

  Scenario: clear button works on adding the user
    When I navigate to new person page
    And I enter "Kristaps" and "Musician"
    And I click clear
    Then Fields are cleared


