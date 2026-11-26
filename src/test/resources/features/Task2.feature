# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person


Feature: Manage people and their jobs

  Background:
    Given I open the People with Jobs page
    And I reset the people list

  Scenario Outline: Add a new person
    When I click Add person
    And I enter name "<name>" and job "<job>"
    And I click Add
    Then I should see "<name>" with job "<job>" in the list
    And the people list should contain 11 entries

    Examples:
      | name         | job               |
      | Jha          | Automation Tester |
      | Peter Parker | Sr. QA Engineer   |

  Scenario: Edit an existing person
    When I click the pencil icon for "John"
    Then the Name field should contain "John" and Job field should contain "Software Engineer"
    When I change the Job field to "Senior Software Engineer"
    And I click Edit
    Then I should see "John" with job "Senior Software Engineer" in the list
    And the people list should contain 10 entries

  Scenario: Remove an existing person
    When I click the cross icon for "John"
    Then "John" should not be in the people list
    And the people list should contain 9 entries

  Scenario Outline: Reset the original list after adding editing or removing a person
    When I modify the people list by "<action>" for "<name>" with job "<job>"
    Then the "<action>" modification should be visible for "<name>" with job "<job>"
    When I click Reset List
    Then the original people and jobs should be restored:
      | name   | job               |
      | Mike   | Web Designer      |
      | Jill   | Support           |
      | Jane   | Accountant        |
      | John   | Software Engineer |
      | Sarah  | Product Manager   |
      | Carlos | Data Analyst      |
      | Emily  | UX Designer       |
      | David  | Project Manager   |
      | Maria  | QA Engineer       |
      | Alex   | DevOps Engineer   |

    Examples:
      | action | name | job                      |
      | add    | Jha  | Automation Tester        |
      | edit   | John | Senior Software Engineer |
      | remove | John | Software Engineer        |
