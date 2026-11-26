# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html

Feature: Cucumber Task 2
  As a beginner tester
  I want to consolidate the knowledge I have gained from my 4th day of training

  Background:
    Given I am on People with jobs page
    And the employee list contains 10 people

# in order to test that user can:
# - add a new person // assert that actions resulted into sth you expected
  Scenario Outline: Adding a new employee
    When I click Add person button
    And I enter name "<name>" and job "<job>"
    And I click Add button
    Then I see employee "<name>" with job "<job>" in the list
    And the employee list contains 11 people

    Examples:
      | name | job              |
      | Anna | Senior Developer |
      | Leon | SMM-specialist   |
      | Tom  | Psychologist     |

# - edit a person
  Scenario: Editing an employee in the list
    When I click Pencil button for "Carlos" with job "Data Analyst"
    And I enter new data
      | name | Bob    |
      | job  | Doctor |
    And I click Edit Button
    Then I see employee "Bob" with job "Doctor" in the list
    And the employee list contains 10 people

# - remove a person
  Scenario Outline: Removing an employee
    When I click the cross button for "<name>" with job "<job>"

    # it takes too much time (around 35 sec) to check that the person is NOT in the list because of implicit wait
    # therefore, for such cases, the structure of implicit wait needs to be changed
    Then I see that the employee "<name>" with job "<job>" is not in the list
    And the employee list contains 9 people

    Examples:
      | name  | job             |
      | David | Project Manager |
      | Jill  | Support         |
      | Jane  | Accountant      |


# - reset original list after adding a person, editing a person or removing a person
  Scenario: Resetting the employee list
    When I click the cross button for "Sarah" with job "Product Manager"
    Then I see that the employee "Sarah" with job "Product Manager" is not in the list
    When I click Reset List button
    Then I see the original employee list

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
