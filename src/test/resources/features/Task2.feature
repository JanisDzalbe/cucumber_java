Feature: people with jobs page
  As a test engineer
  I want to be able to write and execute a scenario outline


  Scenario: Add a new person
    Given I am on people with jobs page
    When I click add person button
    And I fill name as "Adam" and job as "Driver" fields
    And I click Add
    Then I should see new person is added to the list with name "Adam" and job "Driver"

  Scenario: Edit a person
    Given I am on people with jobs page
    When I click pencil icon for existing person
    And I check values in Name and Job fields
    And I change job field to "Teacher"
    And I click edit
    Then I check that the person is updated in the list with new job "Teacher"
      | name  | job |
      | Mike  | Web Designer|
      | Jill  | Support  |
      | Jane  | Accountant|
      | John  | Software Engineer  |
      | Sarah | Product Manager |
      | Carlos| Data Analyst  |
      | Emily | UX Designer  |
      | David | Project Manager  |
      | Maria | QA Engineer  |
      | Alex  | DevOps Engineer  |

  Scenario: Remove a person
    Given I am on people with jobs page
    When I click cross x icon for an existing person
    Then I check that the person is removed from the list
      | name  | job |
      | Mike  | Web Designer|
      | Jill  | Support  |
      | Jane  | Accountant|
      | John  | Software Engineer  |
      | Sarah | Product Manager |
      | Carlos| Data Analyst  |
      | Emily | UX Designer  |
      | David | Project Manager  |
      | Maria | QA Engineer  |
      | Alex  | DevOps Engineer  |

  Scenario: Reset original list after removing a person
    Given I am on people with jobs page
    When I click cross x icon for an existing person
    And I click Reset List
    Then I check that the list is back to initial state with 10 original entries
    | name  | job |
    | Mike  | Web Designer|
    | Jill  | Support  |
    | Jane  | Accountant|
    | John  | Software Engineer  |
    | Sarah | Product Manager |
    | Carlos| Data Analyst  |
    | Emily | UX Designer  |
    | David | Project Manager  |
    | Maria | QA Engineer  |
    | Alex  | DevOps Engineer  |