# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Manage people with jobs list
  As a user
  I want to be able to add, edit, remove people in the list
  And reset the list to original state

  Background:
    Given the user is on the People with jobs list page

  # Add new person
  Scenario Outline: Add a new person
    When the user clicks the Add person button
    And the user fills the Add Person form with name "<name>" and job "<job>"
    And the user submits the Add Person form
    Then the list should contain a person with name "<name>" and job "<job>"

    Examples:
      | name   | job          |
      | Anna   | Designer     |
      | Robert | QA Engineer  |

  # Edit existing person
  Scenario Outline: Edit a person
    When the user clicks the Edit button for person "<originalName>"
    And the user changes the name to "<newName>" and job to "<newJob>"
#    And the user submits the form
    Then the list should contain a person with name "<newName>" and job "<newJob>"
    And the list should not contain a person with name "<originalName>"

    Examples:
      | originalName | newName | newJob          |
      | Mike         | Nurmukhammad | Senior Designer |
      | Jill         | Julia   | Support Lead    |

  # Remove a person
  Scenario Outline: Remove a person
    When the user clicks the Delete button for person "<name>"
    Then the list should not contain a person with name "<name>"

    Examples:
      | name   |
      | Jane   |
      | John   |

  # Reset list
  Scenario: Reset the list after changes
    When the user clicks the Add person button
    And the user fills the form with name "Temporary" and job "Temp Job"
#    And the user submits the Add Person form
#    And the user clicks the Reset List button
#    Then the list should contain the original person "Mike" with job "Web Designer"
#    And the list should not contain the person "Temporary"
