
Feature: Provide feedback
  As a user
  I want to submit feedback so that my values are displayed correctly

  Scenario Outline: Submitting feedback shows correct values
    Given I am on the feedback page
    When I enter feedback "<name>", <age>, "<genre>"
    And I click send feedback
    Then I should see feedback name "<name>"
    And I should see feedback age "<age>"

    Examples:
      | name  | age | genre  |
      | Ann   | 25  | female |
      | John  | 31  | male   |