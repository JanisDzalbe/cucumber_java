@regression
Feature: Provide feedback page
  As a user
  I want to submit feedback
  So that I can see my submitted values displayed correctly

  @debugs
  Scenario Outline: Submitting feedback shows correct values
    Given I am on the feedback page
    When I enter feedback name "<name>"
    And I enter feedback age <age>
    And I click send feedback
    Then I should see feedback name "<name>"
    And I should see feedback age "<age>"

    @working
    Examples:
      | name  | age |
      | Ann   | 5   |
      | Marry | 50  |
      | Bob   | 61  |
