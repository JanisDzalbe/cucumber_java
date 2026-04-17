# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: People job management

  Background:
    Given I am on enter jobs page

    Scenario Outline: Add a new person
      When I click "Add person" button
      Then I write a name field: "<name>"
      And I write a job field: "<job>"
      And I click "Add" button
      Then I click "Reset List" button
     And I check a original list
    Examples:
      | name  |       job        |
      | Jana  | Manual Tester    |
      | Janis | Automation Tester|

    Scenario Outline: Edit a person
      When I want edit person named: "<name>"
      Then I edit the person job: "<job>"
      And I click "Edit" button
      Then I check the person "<name>" and his new job: "<job>"
      Then I click "Reset List" button
      And I check a original list
    Examples:
      | name | job           |
      | Mike | Manual Tester |
      | Jill | Tester        |

    Scenario Outline: Remove a person
       When I want delete person named: "<name>"
       Then I should not see the person "<name>"
       Then I click "Reset List" button
       And I check a original list
        Examples:
          | name |
          | Mike |

