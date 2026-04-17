# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

@regression
Feature: Manage people with jobs
  As a user
  I want to manage people list
  So that I can add, edit, remove and reset people

#  Add Person:
  Scenario Outline: Add a new person
    Given I am on people with jobs page
    When I click "Add person"
    And I enter name "<name>" and job "<job>"
    And I click "Add"
    Then I should see "<name>" with job "<job>" in the list

    Examples:
      | name               | job          |
      | Surendar Rajendran | Intern       |
      | Ilamrs             | Tester       |

  # Edit Person
  Scenario Outline: Edit an existing person
    Given I am on people with jobs page
    When I click edit for "<existingName>"
    And I update name to "<newName>" and job to "<newJob>"
    And I click "Edit"
    Then I should see "<newName>" with job "<newJob>" in the list

    Examples:
      | existingName | newName | newJob        |
      | Mike         | Michael | Architect     |

  # Remove Person
  Scenario Outline: Remove a person
    Given I am on people with jobs page
    When I remove person "<name>"
    Then I should not see "<name>" in the list

    Examples:
      | name |
      | Mike |

  # Reset List (After Modification)
  Scenario: Reset list after modification
    Given I am on people with jobs page
    When I click "Add person"
    And I enter name "Surendar Rajendran" and job "Intern"
    And I click "Add"
    And I click "Reset List"
    Then I should see original list of people