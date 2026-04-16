Feature: People with jobs management

  Background:
    Given I am on people with jobs page

  Scenario: Add a new person and reset
    When I click "Add person" button
    And I fill in name: "John Doe" and job: "Tester"
    And I click "Add" button
    Then I see "John Doe" with job "Tester" in the list
    When I click "Reset List" button
    Then I should see the original list of people

  Scenario: Remove a person and reset
    When I remove person "Mike" from the list
    Then I should not see "Mike" in the list
    When I click "Reset List" button
    Then I should see "Mike" in the list

  Scenario Outline: Edit a person
    When I edit person "<old_name>" to have name "<new_name>" and job "<new_job>"
    Then I see "<new_name>" with job "<new_job>" in the list
    Examples:
      | old_name | new_name | new_job      |
      | Maria    | Mary     | Senior QA    |