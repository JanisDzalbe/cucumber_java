# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
  Feature: List of people with jobs
    As a user I want to add, edit and remove
    people in the list of people with jobs
  List stays updated and I can restore it and get original list
  at any time

  Background:
    Given I open the List of people page
    Given I am on the List of people with jobs page
    And List of people is reset to the original list
    Then List contains 10 people


    Scenario Outline: User adds a new person with a job
      When I click the Add Person button
      Then I am on the Enter a new person with a job page
      When I enter "<name>" in the Name field
      And I enter "<job>" in the Job field
      And I click the Add button
      Then I am on the List of people with jobs page
      And List contains 11 people
      And Last person in the list is "<name>" and job "<job>"

      Examples:
        | name      | job                |
        | Anna      | Senior QA lead     |
        | Peter     | Scrum Master       |
        | Līga      | Business Analyst   |
        | Juris     | Team Lead          |

    Scenario Outline: User edits a persons name or job
      When I click the edit icon for "<original name>"
      And I replace the "Name" field with "<new name>"
      And I replace the "Job" field with "<new job>"
      And I click the Edit button
      And the list contains 10 people
      And the person at position <position> has name "<new name>" and job "<new job>"

      Examples: Change name only
        | original name | position | new name | new job           |
        | Mike          | 1        | Michael  | Web Designer      |

      Examples: Change job only
        | original name | position | new name | new job           |
        | Jill          | 2        | Jill     | Team Lead         |

      Examples: Change both name and job
        | original name | position | new name | new job           |
        | Alex          | 10       | Alexis   | Cloud Architect   |

    Scenario: User removes a person that was just added
      Given I add a person with name "Anna" and job "Senior QA Lead"
      When I click the remove  icon for "Anna"
      Then the list contains 10 people
      And the list does not contain a person with name "Anna"

    Scenario: Reset after editing a person
      Given I edit the person "Jane" to have name "Janet" and job "Auditor"
      When I click the Reset List button
      Then the list contains 10 people
      And the list contains a person with name "Jane" and job "Accountant"
      And the list does not contain a person with name "Janet"
      And the list matches the original list
