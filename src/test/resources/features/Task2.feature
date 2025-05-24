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

Feature: Cucumber Task 2
  I want to be good at writing and executing scenarios and scenario outlines within this task

  Background:
    Given I am on People with jobs page

  #──────────────────────────────────────────────────────────────
  #                      Scenario Outline
  #──────────────────────────────────────────────────────────────
  Scenario: Add multiple people cumulatively
    Given I am on People with jobs page
    When I add the following people:
      | name   | job                  |
      | Kirils | Tester               |
      | Daria  | Data analyst         |
      | Julia  | Finance Specialist   |
      | Alina  | 3D Artist            |
    Then I see people:
      | name   | job                  |
      | Mike   | Web Designer         |
      | Jill   | Support              |
      | Jane   | Accountant           |
      | Kirils | Tester               |
      | Daria  | Data analyst         |
      | Julia  | Finance Specialist   |
      | Alina  | 3D Artist            |

  #──────────────────────────────────────────────────────────────
  #                          Scenario
  #──────────────────────────────────────────────────────────────
  Scenario: Add, Remove, Edit and Reset scenario
    # Add
    When I click Add person button
    And I am navigated to the Add person page
    And I write name: "Bill"
    And I write job: "QA"
    And I click Add button
    Then I see new instance of a person "Bill" who is "QA"
    # Remove
    When I click Remove person button for "Jill" who is "Support"
    Then I do not see "Jill" who is "Support" in the list
    # Edit
    When I click Edit person button for "Mike" who is "Web Designer"
    And I am navigated to the Edit person page
    And I write name: "Michael"
    And I write job: "Software Developer"
    And I click Edit button
    Then I see new instance of a person "Michael" who is "Software Developer"
    # Reset back to defaults
    When I click Reset list button
    Then I see default list of people
