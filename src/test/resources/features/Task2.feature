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
Feature: Task 2
  Background:
    Given I enter people with jobs page

  Scenario Outline: Add a new person
    When I click Add person button
    Then I enter "<name>" and "<job>"
    And I click add
    Then I check that a person with "<name>" and "<job>" is on the list
    Examples:
      | name  | job            |
      | John  | Retailer       |
      | Ann   | Lawyer         |
      | Benny | Police officer |

    Scenario Outline: Edit a person
      When I click edit on "<name>", "<job>"
      Then I enter "<newName>" and "<newJob>"
      And I click edit
      Then I check that a person with "<newName>" and "<newJob>" is on the list
      And I check that a person "<name>" and "<job>" is not on the list
      Examples:
        | name | job          | newName | newJob      |
        | Mike | Web Designer | Micheal | UX Designer |
        | Jill | Support      | Ann     | Support     |
        | Jane | Accountant   | Jane    | Consultant  |

    Scenario: Remove a person
      When I click delete button on "Mike" and "Web Designer"
      Then  I check that a person "Mike" and "Web Designer" is not on the list

    Scenario: Reset list after adding a person
      When I click Add person button
      Then I enter "John" and "Retailer"
      And I click add
      Then I check that a person with "John" and "Retailer" is on the list
      And I click Reset List
      Then I check that a list is reset


    Scenario: Reset list after editing a person
      When I click edit on "Mike", "Web Designer"
      Then I enter "Micheal" and "UX Designer"
      And I click edit
      Then I check that a person with "Micheal" and "UX Designer" is on the list
      And I check that a person "Mike" and "Web Designer" is not on the list
      And I click Reset List
      Then I check that a list is reset

    Scenario: Reset list after removing a person
      When I click delete button on "Mike" and "Web Designer"
      Then  I check that a person "Mike" and "Web Designer" is not on the list
      And I click Reset List
      Then I check that a list is reset

    Scenario: Clear button is working in adding person page
      When I click Add person button
      Then I enter "John" and "Retailer"
      And I click clear all fields
      Then I check fields are cleared


