@regression
Feature: Introduction to cucumber part 3: Homework
  As a test engineer
  I want to be able to write and execute a scenario outline

# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Scenario Outline: Error cases scenario
Given I am on Enter a number page
When I enter a number: "<number>"
And I click on submit button
Then I see an error "<error>" message
Examples:
| number  | error                |
| 23      | Number is too small  |
| 111     | Number is too big    |
| seven   | Please enter a number|

Scenario: Correct input in page
  Given I am on Enter a number page
  When I enter a number: "63"
  And I click on submit button
  Then I see a pop up message with an answer for a number: "63"
