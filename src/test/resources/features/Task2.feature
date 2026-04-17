# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
@regression
Feature: List of people with jobs
  As a test engineer
  I want to test add, edit, remove and reset actions on people with jobs page

  Background:
    Given I am on people with jobs page

  Scenario: add a new person
    When I click add person button
    And I enter person name: "Martha"
    And I enter person job: "QA Intern"
    And I save person
    Then I should see person name: "Martha"
    And I should see person job: "QA Intern"

  Scenario: edit a person
    When I click edit button for person "John"
    And I enter person name: "Johnny"
    And I enter person job: "Senior QA"
    And I save person
    Then I should see person name: "Johnny"
    And I should see person job: "Senior QA"

  Scenario: remove a person
    When I remove person "John"
    Then I should not see person "John"

  Scenario: reset list after adding a person
    When I click add person button
    And I enter person name: "Martha"
    And I enter person job: "QA Intern"
    And I save person
    And I click reset list button
    Then I should see original person "John"
    And I should not see person "Martha"

  Scenario: reset list after editing a person
    When I click edit button for person "John"
    And I enter person name: "Johnny"
    And I enter person job: "Senior QA"
    And I save person
    And I click reset list button
    Then I should see original person "John"
    And I should not see person "Johnny"

  Scenario: reset list after removing a person
    When I remove person "John"
    And I click reset list button
    Then I should see original person "John"