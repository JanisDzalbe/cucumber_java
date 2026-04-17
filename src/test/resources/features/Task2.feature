# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Manage people list

  Scenario: Add a new person
    Given user is on people with jobs page
    And user stores initial list size
    When user adds a new person with name "Yusuf" and job "Bootcamp Intern"
    Then list size increases

  Scenario: Remove a person
    Given user is on people with jobs page
    And user stores initial list size
    When user deletes first person
    Then list size decreases

  Scenario: Edit a person
    Given user is on people with jobs page
    When user edits first person to name "EditedUser" and job "EditedJob"
    Then edited person is updated

  Scenario: Reset list
    Given user is on people with jobs page
    And user stores initial list size
    When user adds a new person with name "SpongeBob" and job "CrustyCrabChef"
    And user resets list
    Then list returns to original size
