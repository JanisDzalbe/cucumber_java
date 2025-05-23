@regression
Feature: Manage list of employees
  As an admin
  I want to add, edit and remove people to list of employees

  Background:
    Given I am on People with jobs page

  Scenario Outline: Add a new person to the list
    When I click Add person
    When I enter name: "<name>" and job: "<job>" on the add page
    And I click Add on the add page
    Then I see name: "<name>" with job: "<job>" in the table

    Examples:
      | name   | job         |
      | Alice  | Engineer    |
      | Bob    | Designer    |

  Scenario: Reset original list after adding
    When I click Reset List
    Then I see 3 persons on the list

  Scenario: Edit an existing person
    When I click edit next to Mike
    And I change job to "CEO"
    And I click edit on Edit page
    Then I see Mike with "CEO" job title

  Scenario: Reset original list after editing
    When I click Reset List
    Then I see Mike with "Web Designer" job title

  Scenario: Remove an existing person
    When I click remove next to Mike
    Then I see no Mike in the list

  Scenario: Reset original list after removal
    When I click Reset List
    Then I see 3 persons on the list


  Scenario: Clear all fields
    When I click Add person
    When I enter name: "Dora" and job: "HR Recruiter" on the add page
    And  I click Clear all fields on the add page
    Then I see name and job fields empty


# TODO - Create 1 scenario outline and create scenario or scenario outlines for page
# https://acctabootcamp.github.io/site/tasks/list_of_people.html OR
# https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after: (doesn't have to be as detailed as above, can check just based on the number of ppl)
#   * adding a person
#   * editing a person
#   * removing a person
# - check that clear button on adding a user works correctly
