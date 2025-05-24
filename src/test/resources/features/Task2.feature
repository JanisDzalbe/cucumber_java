# TODO - Create 1 scenario outline and create scenario or scenario outlines for page
# https://acctabootcamp.github.io/site/tasks/list_of_people.html OR
# https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
#1st - add a new person
#2nd - edit a person
#3rd - remove a person
# - reset original list after:
#4th   * adding a person
#5th   * editing a person
#6th   * removing a person
#7th - check that clear button on adding a person works correctly
@regression
Feature: Introduction to cucumber part 3: Homework, Task 2

  Background:
    Given I am on People with job page

#Test 1
Scenario Outline: User can add a new person

When I click on the "Add person" button
Then I am redirected to the Add new person page
When I enter a new name "<name>" and a new job "<job>"
And I click on the "Add" button
Then I am redirected to the main page
And I can see that the number of records has increased
  Examples:
  |name    | job     |
  |Jonathan|Doctor   |
  |Peter   |Scientist|
  |Irvin   |Artist   |

#Test 2
Scenario  User can edit a person
#person0.fa-pencil
#person0.getValue
  When I choose a record and click on the pencil icon to edit it
  Then I am redirected to the Add new person page
  When I enter a new name "Simon" and a new job "Florist"
  And I click on the "Add" button
  Then I am redirected to the main page
  And I can see that the name and job have been updated to "Simon" and "Florist"

#Test 3
Scenario  User can remove a person
#person2 > span.closebtn
  When I click on the "x" icon next to the record to delete it
  Then I remain on the main page
  And I can see that the number of records has decreased

#Test 4
Scenario: User resets main page after adding a new person

  When I click on the "Add person" button
  Then I am redirected to the Add new person page
  When I enter a new name "Alise" and a new job "Dancer"
  And I click on the "Add" button
  Then I am redirected to the main page
  And I can see that the number of records has increased
  When I click on the "Reset List" button
  Then I can see the record number has returned to the previous value


#Test 5
Scenario: User resets main page after editing a person
  When I choose a record and click on the pencil icon to edit it
  Then I am redirected to the Add new person page
  When I enter a new name "Simon" and a new job "Florist"
  And I click on the "Add" button
  Then I am redirected to the main page
  When I click on reset button
  Then I can see changes for name "Simon" and job "Florist" are not saved

 #Test 6
Scenario: User resets main page after removing a person


