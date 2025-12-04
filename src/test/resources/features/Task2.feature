# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Testing List of People With Jobs Page

  Background:
    Given I am on List of People With Jobs page

  Scenario Outline: Add a new person to the list
    When I click on add person button
    Then I am redirected to Enter a New Person page
    When I enter name and job
      | name | <name> |
      | job  | <job>  |
    And I click Add button
    Then I see added person in the end of the list
      | name | <name> |
      | job  | <job>  |
    Examples:
      | name    | job    |
      | Tatjana | Tester |

  Scenario Outline: Edit a person on the list
    When I click on edit button for person with index: <index>
    Then I am redirected to Enter a New Person page
    When I enter name and job
      | name | <name> |
      | job  | <job>  |
    And I click Edit button
    Then I see edited person with <index> on the list
      | name | <name> |
      | job  | <job>  |
    Examples:
      | name    | job           | index |
      | Tatjana | Web Developer | 3     |

  Scenario: Remove a person from the list
    When I click on remove button for person with index 6
    Then The removed person is not on the list

  Scenario: Reset the list
    When I click on first person's edit button
    Then I am redirected to Enter a New Person page
    When I enter a different name
    And I click Edit button
    Then I see first person's edited name
    When I click on Reset List button
    Then I see that the list is reset