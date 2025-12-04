# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

#  Scenario Outline:
Feature: Task2
   @addPerson
   Scenario: Add a person
   Given I am on the People with jobs page
   And Add precondition list with Names and Jobs
   When  I add new person with Name and Job
       |  Name  |  Boris |
       |   Job  |  HR    |
   Then I compare and prove list

  @editPerson
  Scenario: Edit a person number 4
  Given I am on the People with jobs page
  And Add precondition list with Names and Jobs
  When   I edit a person to
    |  Name    |  Frank  |
    |   Job    |  DevOps |
  Then I compare and prove list


  @removePerson
  Scenario: Remove a person 3
  Given I am on the People with jobs page
  And Add precondition list with Names and Jobs
  When  I remove a person 3
  Then I compare and prove list

  @resetAndCheck
  Scenario: Reset list and check
  Given I am on the People with jobs page
  And Add precondition list with Names and Jobs
  And I remove a person 3
  When I reset list to original
  Then I compare and prove list
