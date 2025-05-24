# TODO - Create 1 scenario outline and create scenario or scenario outlines for page
# https://acctabootcamp.github.io/site/tasks/list_of_people.html OR
# https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after:
#   * adding a person
#   * editing a person
#   * removing a person
# - check that clear button on adding a user works correctly
Feature: Manage people with jobs

Background:
Given I am on the People with jobs page

Scenario: Add a new person
 When I click the Add person button
 And I enter name Chris and job Plumber
 And I click Add button
 Then I should see Chris with job Plumber in the list


Scenario Outline: Edit an existing person
When I click the edit icon for <name>
And I update name to <new_name> and job to <new_job>
And I click the save button
Then I should see <new_name> with job <new_job> in the list
And I should not see <name> in the list
Examples:
| name  | new_name | new_job        |
| Mike  | Kristaps | Dish washer    |
| Jill  | Olgerts  | Chif Pilot     |

Scenario: Remove a person from the list
When I click the delete icon for <name>
Then <name> should not be in the list


Scenario: Reset the list after adding a person
When I add a new person Rick with job Plumber
And I click Reset List
Then I should see the original people:
| Mike |
| Jill |
| Jane |
And I should not see Rick in the list person

Scenario: Reset after editing a person
When I click the edit icon for <name>
When I edit Mike to name Mark and job Coder
And I click Edit button
And I click Reset List
Then I should see the original people:
| Mike |
| Jill |
| Jane |
And I should not see Mark in the list

Scenario: Reset after deleting a person
When I delete Jane
And I click Reset List
Then I should see the original people:
| Mike |
| Jill |
| Jane |

Scenario: Clear button clears the modal fields
When I click the Add person button
And I enter name Test and job Test
And I click Add button
Then I click Reset List
Then I should see the original people:
  | Mike |
  | Jill |
  | Jane |

