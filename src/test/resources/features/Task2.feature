# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

  Feature: Task 2 implementation
    As a user
    I want to be able add a new person, edit a person, remove people in the list and reset the list to original state

  Background:
    Given I am on the List of people page

    Scenario Outline: Adding person
      When I click add person
      And I enter name "<name>"
      And I enter job "<job>"
      Then I click add button
      And I see name: "<name>" and job "<job>" in the List

      Examples:
        | name | job             |
        | John | QA Engineer     |
        | Tom  | DevOps Engineer |

      Scenario: Edit a person
        When I see person "Mike" with job "Web Designer"
        And I click edit person "Mike"
        And I enter name "Mike"
        And I enter job "DevOps Engineer"
        Then I click Edit button
        And I see name: "Mike" and job "DevOps Engineer" in the List

      Scenario: Remove a person
        When I see person "Mike" with job "Web Designer"
        And I click remove person "Mike"
        Then I should not see a person "Mike" in the list

      Scenario: Reset original list
        When I see person "Mike" with job "Web Designer"
        And I click remove person "Mike"
        Then I should not see a person "Mike" in the list
        And I click reset button
        Then I see person "Mike" with job "Web Designer"

