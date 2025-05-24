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

  @Task2Test


  Feature: Test that user can manage the list of people

    Background:
      Given I am on the list of people page

    Scenario Outline: Add a new person scenario
      When I click add person button
      And I enter values: "<name>" and "<job>"
      And I click add button
      Then I should see the person with name "<name>" and  job "<job>" in the list
      Examples:
        | name   |     job     |
        | German | QA Engineer |

    Scenario: Edit a person scenario
      When I click edit 1 person
      And I change name to "John" and job "Senior Java Developer"
      And I click button Edit
      Then I should see the person with name "John" and  job "Senior Java Developer" in the list

    Scenario: Remove a person scenario
      When I click remove person with name "Jill"
      Then I should see that person with name "Jill" is not in the list

    Scenario Outline: Reset original list after adding a person scenario
      When I click add person button
      And I enter values: "<name>" and "<job>"
      And I click add button
      Then I should see the person with name "<name>" and  job "<job>" in the list
      When I click reset list button
      Then I should see the person with name "<name>" and  job "<job>" in the list
      Examples:
      | name |    job    |
      | Tom  | Team Lead |

    Scenario: Reset original list after editing a person scenario
      When I click edit 2 person
      And I change name to "Michael" and job "Junior Java Developer"
      And I click button Edit
      Then I should see the person with name "Michael" and  job "Junior Java Developer" in the list
      When I click reset list button
      Then I should see the person with name "Michael" and  job "Junior Java Developer" in the list

      Scenario: Reset original list after removing a person scenario
        When I click remove person with name "Jane"
        Then I should see that person with name "Jane" is not in the list
        When I click reset list button
        Then I should see that person with name "Jane" is not in the list

      Scenario: Clear button on adding a user works correctly scenario
        When I click add person button
        And I enter values: "Hello" and "World"
        And I click clear all fields button
        Then I should see that all fields are empty
