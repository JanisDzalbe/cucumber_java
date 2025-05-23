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
# - check that clear button on adding a person works correctly
@regression
Feature: Introduction to cucumber part 3: Homework, Task 2

  Background:
    Given I am on People with job page

#Test 1
Scenario Outline: User can add a new person

When I click on Add person button
Then I am redirected to add new person page
And I enter name "<name>" and job "<job>"
And I click on Add button
Then I am redirected to main page
And I can see record number has increased
  Examples:
  |name    | job     |
  |Jonathan|Doctor   |
  |Peter   |Scientist|
  |Irvin   |Artist   |

#Test 2
Scenario  User can edit a person
#person0.fa-pencil
#person0.getValue
When I click on pencil icon near the record to edit record
Then I am redirected to add new person page
And I enter a new name "Simon" and a new job "Florist"
And I click on Add button
Then I am redirected to main page
And I can see changes are saved

#Test 3
Scenario  User can remove a person
#person2 > span.closebtn
First get a count of records/ get a value of record is displayed
When I click on x icon near the record to delete record
And ? Checkc if you still page; check if record count became smaller/ try to find a record


#Scenario Outline: Error cases scenario
#Given I am on Enter a number page
#When I enter a number: "<number>"
#And I click on submit button
#Then I see an error "<error>" message
#Examples:
#| number  | error                |
#| 23      | Number is too small  |
#| 111     | Number is too big    |
#| seven   | Please enter a number|
#
#Scenario: Correct input in page
#Given I am on Enter a number page
#When I enter a number: "63"
#And I click on submit button
#Then I see a pop up message with an answer for a number: "63"