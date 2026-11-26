# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature: Manage people with jobs

  Background:
    Given I am on people with jobs page
    When I reset the people list
    Then original people list is displayed

  Scenario Outline: Add new person
    When I add person "<name>" with job "<job>"
    Then I see person "<name>" with job "<job>"
    And I see 11 people in the list
    Examples:
      | name  | job              |
      | Liga  | Administrator    |
      | Tomas | Business Analyst |

  Scenario Outline: Edit existing person
    When I edit person "<name>" job from "<oldJob>" to "<newJob>"
    Then I see person "<name>" with job "<newJob>"
    And I see 10 people in the list
    Examples:
      | name | oldJob       | newJob                |
      | Mike | Web Designer | Senior Web Designer   |
      | Jill | Support      | Senior Support        |

  Scenario Outline: Remove an existing person
    When I remove person "<name>"
    Then I do not see person "<name>"
    And I see 9 people in the list
    Examples:
      | name |
      | Mike |
      | Jill |

  Scenario Outline: Reset list after adding person
    When I add person "<name>" with job "<job>"
    Then I see person "<name>" with job "<job>"
    And I see 11 people in the list
    When I reset the people list
    Then original people list is displayed
    And I do not see person "<name>"
    Examples:
      | name  | job              |
      | Liga  | Administrator    |
      | Tomas | Business Analyst |

  Scenario Outline:  Reset list after editing person
    When I edit person "<name>" job from "<oldJob>" to "<newJob>"
    Then I see person "<name>" with job "<newJob>"
    When I reset the people list
    Then original people list is displayed
    And I see person "<name>" with job "<oldJob>"
    Examples:
      | name | oldJob       | newJob                |
      | Mike | Web Designer | Senior Web Designer   |
      | Jill | Support      | Senior Support        |

  Scenario Outline: Reset list after removing a person
    When I remove person "<name>"
    Then I do not see person "<name>"
    And I see 9 people in the list
    When I reset the people list
    Then original people list is displayed
    And I see person "<name>" with job "<job>"
    Examples:
      | name | job          |
      | Mike | Web Designer |
      | Jill | Support      |