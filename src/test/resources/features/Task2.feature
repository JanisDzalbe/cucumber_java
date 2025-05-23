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

  Feature: List of people funcionality
    Background:
      Given I am on the list of people page
      And Default list is displayed

    Scenario Outline: Adding persons will full parameters
      When I click add person button
      And I enter "<name>" and "<job>"
      And I click add button
      Then Person with "<name>" and "<job>" is added to displayed list

      Examples:
      |name|job|
      |Damian|Frycook|
      |Elizabeth|Janitor|
      |CoolDude |Be Cool|
      |Hagrid   |IT Specialist|
      |John     |             |
      |         |Developer    |

    Scenario: Cancelling while adding a person
      When I click add person button
      And I enter "John" and "Butcher"
      And I click cancel button
      Then Person with name "John" and job "Butcher" is not added to list

      #Here object iteration starts from one
    Scenario: Editing persons job
      When I click on edit button in 1 item of list
      And I enter "Philosopher" in the job input
      And I click the edit button
      Then 1 item in list has "Philosopher" as a job

    Scenario: Editing persons name
      When I click on edit button in 2 item of list
      And I enter "Terminator" in the name input
      And I click the edit button
      Then 2 item in list has "Terminator" as a name

    Scenario: Editing both name and job
      When I click on edit button in 3 item of list
      And I enter "Nelson" and "Plumber"
      And I click the edit button
      Then 3 item in list has name "Nelson" and job "Plumber"

      #In edit testing I used indexes here im running loops and searching for elements and I am not sure which way is more correct
    Scenario: Remove a person
      When I click on remove button in "Jill" item of list
      Then "Jill" is not part of the list
      And List is shorter

    Scenario: Reset original list after adding a person
      When I click add person button
      And I enter "Damian" and "Chiller"
      And I click add button
      Then Person with "Damian" and "Chiller" is added to displayed list
      When I click reset list button
      Then Default list is displayed

    Scenario: Reset original list after editing a person
      When I click on edit button in 1 item of list
      And I enter "Philosopher" in the job input
      And I click the edit button
      Then 1 item in list has "Philosopher" as a job
      When I click reset list button
      Then Default list is displayed

    Scenario: Reset original list after removing a person
      When I click on remove button in "Mike" item of list
      Then "Mike" is not part of the list
      And List is shorter
      When I click reset list button
      Then Default list is displayed

    Scenario: Clear button works correctly
      When I click add person button
      And I enter "Test" and "Text"
      And I click clear all fields button
      Then Input fields are empty
