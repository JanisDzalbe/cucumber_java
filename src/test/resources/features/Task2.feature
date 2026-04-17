Feature: Task 2

  Background:
    Given I am on people with jobs page

  Scenario: Add a new person
    Given I check the list matches the initial list
    When I add a new person
      | name     | Bob      |
      | jobTitle | Musician |
    Then I check the list does not match the initial list
    And I check that Bob the Musician does exist in the list
    When I reset the entire list
    Then I check the list matches the initial list

  Scenario: Add a prefix to the first person's job title
    # - edit a person
    When I add a prefix to first person's job title
    | jobPrefix | Senior |
    Then I check that the first person's job title starts with Senior
    When I reset the entire list
    Then I check the list matches the initial list

  Scenario: Remove the first person
    When I remove the first person
    Then I check the list does not match the initial list
    When I reset the entire list
    Then I check the list matches the initial list