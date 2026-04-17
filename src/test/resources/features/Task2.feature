Feature: Test list of people
# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
  @checkListOfPeople

    Scenario: list of people
    Given I navigate to list of people page
    When I add a new person "Dima" with job "QA"
    Then new person "Dima" is in the list with job "QA"
    And I edit person "Dima" job to "Dev"
    Then person "Dima" has job "Dev"
    And I remove person "Dima"
    Then person "Dima" is not in the list
    And I reset the list
    Then the list has 10 original people