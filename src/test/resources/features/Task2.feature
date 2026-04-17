# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: People with jobs

  Scenario Outline: Add a new person
    Given I am on people list page
    When I click Add person
    And I add person "<name>" with job "<job>"
    Then I should see "<name>" with job "<job>" in the list

    Examples:
      | name | job     |
      | Sonakshi | Tester  |


  Scenario Outline: Edit a person
    Given I am on people list page
    When I edit person "<name>" to have job "<newJob>"
    Then I should see "<name>" with job "<newJob>" in the list

    Examples:
      | name | newJob       |
      | Mike | QA Engineer  |


  Scenario Outline: Remove a person
    Given I am on people list page
    When I remove person "<name>"
    Then I should not see "<name>" in the list

    Examples:
      | name |
      | David |


  Scenario Outline: Reset list after changes
    Given I am on people list page
    When I perform "<action>" on person "<name>" with job "<job>"
    And I click Reset List
    Then I should see default people in the list

    Examples:
      | action | name    | job    |
      | add    | Sonakshi| Tester |
      | edit   | Mike    | Manager|
      | remove | Jill    | Support|