Feature: Task2
  Test list_of_people page

  Background:
    Given I am on List Of People Page

  Scenario Outline: Test add a new person function
    When I press Add Person button
    And I fulfill person information with: "<name>" and "<job>"
    And I press Add button
    Then I see extra field with "<name>" and "<job>" in person list
    Examples:
      | name  | job       |
      | Tom   | Support   |
      | Lisa  | Developer |
      | Jerry | Manager   |

  Scenario Outline: Test edit a person function
    When I press Edit Person button of "Mike" field
    And I edit name to: "<name>" and job to: "<job>"
    And I press Edit button
    Then I see updated Person field of "<name>" and "<job>"
    Examples:
      | name  | job       |
      | Tom   | Support   |
      | Lisa  | Developer |
      | Jerry | Manager   |

  Scenario: Test remove a person function
    When I press Remove Person button
      | Mike |
      | Jane |
    Then I see list of people without
      | Mike |
      | Jane |

  Scenario: Test to reset list after adding a person
    When I press Add Person button
    And I fill person information with: "Nadi" and "Teacher"
    And I press Add button
    And I press Reset List button
    Then I see original list of people


  Scenario: Test to reset list after editing a person
    When I press Edit Person button of "Mike" field
    And I edit name to: "Edgar" and job to: "Lawyer"
    And I press Edit button
    And I press Reset List button
    Then I see original list of people

  Scenario: Test to reset list after removing a person
    When I press Remove Person button
      | Mike |
      | Jane |
    And I press Reset List button
    Then I see original list of people

  Scenario: Test that clear button on adding a user works correctly
    When I press Add Person button
    And I fulfill person information with: "Alex" and "Singer"
    And I press Clear Button
    Then I see empty text-fields



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


