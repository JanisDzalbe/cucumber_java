@regression
Feature: List of people with jobs
  As a test engineer
  I want to verify that users can manage the list of people with jobs

  Background:
    Given I am on list of people with jobs page

  @test
  Scenario: Add a new person
    When I click Add person button
    And I fill in person details:
      | name | Alice   |
      | job  | Tester  |
    And I click Save person button
    Then I should see person "Alice" with job "Tester" in the list

  @test
  Scenario: Edit a person
    When I click Edit button for person "Ann"
    And I clear and fill in person details:
      | name | Anna     |
      | job  | Designer |
    And I click Save person button
    Then I should see person "Anna" with job "Designer" in the list
    And I should not see person "Ann" in the list

  @test
  Scenario: Remove a person
    When I click Remove button for person "Ann"
    Then I should not see person "Ann" in the list

  @test
  Scenario Outline: Reset list after modifications
    When I <action> person "<name>"
    And I click Reset List button
    Then the list should be restored to original state

    Examples:
      | action    | name    |
      | add       | Charlie |
      | edit      | Ann     |
      | remove    | Ann     |