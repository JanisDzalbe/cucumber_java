# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
@regression @people
Feature: List of people with jobs
  # Covers add, edit, remove and reset of the people list

  Background:
    Given I open people list page using PO
  # Add: person appears with the right job, list grows by 1. Then Reset restores the original list.
  Scenario Outline: Add a person, then reset
    When I add person "<name>" with job "<job>" using PO
    Then the list contains person "<name>" with job "<job>" using PO
    And the list size changed by 1 using PO
    When I reset the list using PO
    Then the list does not contain person "<name>" using PO
    And the list equals the original one using PO

    Examples:
      | name  | job        |
      | Ilya  | Teacher    |
      | Janis | Director   |

  # Edit: new name present, old name isn't. Then Reset restores the original list.
  Scenario Outline: Rename a person, then reset
    When I rename person "<oldName>" to "<newName>" using PO
    Then the list contains person "<newName>" with job "<job>" using PO
    And the list does not contain person "<oldName>" using PO
    When I reset the list using PO
    Then the list contains person "<oldName>" with job "<job>" using PO
    And the list equals the original one using PO

    Examples:
      | oldName | newName  | job                |
      | John    | Jonathon | Software Engineer  |
      | Sarah   | Siri     | Product Manager    |

  # Remove: person is gone, list shrinks by 1. Then Reset restores the original list.
  Scenario Outline: Remove a person, then reset
    When I delete person "<name>" using PO
    Then the list does not contain person "<name>" using PO
    And the list size changed by -1 using PO
    When I reset the list using PO
    Then the list contains person "<name>" with job "<job>" using PO
    And the list equals the original one using PO

    Examples:
      | name  | job             |
      | Sarah | Product Manager |
      | David | Project Manager |