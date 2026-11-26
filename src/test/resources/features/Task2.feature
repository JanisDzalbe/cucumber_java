# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: Manage people with jobs

  Background:
    Given I am on people with jobs page

    Scenario: Add a new person
      When I add a person with details
        | name | Sophie     |
        | job  | Accountant |
      Then I see the person in the list
        | name | Sophie     |
        | job  | Accountant |

    Scenario: Edit a person
      Given I add a person with details
        | name | Oliver     |
        | job  | Technician |
      And I see the person in the list
        | name | Oliver     |
        | job  | Technician |
      When I edit person "Oliver" with details
        | name | Oliver     |
        | job  | Engineer   |
      Then I see the person in the list
        | name | Oliver     |
        | job  | Engineer   |
      And I do not see the person in the list
        | name | Oliver     |
        | job  | Technician |

    Scenario: Remove a person
      Given I add a person with details
        | name | Natalie |
        | job  | Teacher |
      And I see the person in the list
        | name | Natalie |
        | job  | Teacher |
      When I remove person "Natalie"
      Then I do not see the person in the list
        | name | Natalie |
        | job  | Teacher |

    Scenario: Reset list after adding a person
      Given I save the original list
      When I add a person with details
        | name | Emma  |
        | job  | Nurse |
      And I see the person in the list
        | name | Emma  |
        | job  | Nurse |
      When I click Reset List
      Then I see the original list

    Scenario: Reset list after editing a person
      Given I save the original list
      And I see the person in the list
        | name | Maria       |
        | job  | QA Engineer |
      When I edit person "Maria" with details
        | name | Maria  |
        | job  | Tester |
      And I see the person in the list
        | name | Maria  |
        | job  | Tester |
      And I do not see the person in the list
        | name | Maria       |
        | job  | QA Engineer |
      When I click Reset List
      Then I see the original list

      Scenario: Reset list after removing a person
        Given I save the original list
        And I see the person in the list
          | name | Emily       |
          | job  | UX Designer |
        When I remove person "Emily"
        And I do not see the person in the list
          | name | Emily       |
          | job  | UX Designer |
        When I click Reset List
        Then I see the original list

