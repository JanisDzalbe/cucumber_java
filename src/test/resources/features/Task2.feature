# Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: Task 2 - manage list of people with jobs
  As a user
  I want to manage a list of people
  So that I can add, edit, remove, and reset entries

  Background:
  Given I am on page list of people with jobs

  @Test
  Scenario: Add a new person
    When I click the Add Person button
    Then I am navigated to New Person page
    And I see that the name and job field is empty
    When I add a new person with name "Erik" and job "Developer"
    And I click the Add button
    Then I see "Erik" with job "Developer" in the list
    And I see list increased by 1

  @Test
  Scenario: Edit a person
    When I click the edit button for "Jill" with job "Support"
    Then I see the name and job field contains "Jill" and "Support"
    When I enter "Head of Support" into the job field
    And I click the Edit button
    Then I see "Jill" with job "Head of Support" in the list

  @Test
  Scenario: Remove a person
    When I click remove button for "Jill" with job "Support"
    Then I see "Jill" with job "Support" is not in the list
    And I see list decreased by 1

  @Test
  Scenario: Use reset list button
    When I click the Add Person button
    Then I am navigated to New Person page
    When I add a new person:
      | name | TestUser |
      | job  | Tester   |
    And I click the Add button
    Then I see "TestUser" with job "Tester" in the list
    And I see list increased by 1
    When I click the Reset List button
    Then I see the list matches the original default entries