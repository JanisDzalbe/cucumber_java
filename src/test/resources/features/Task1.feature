# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://acctabootcamp.github.io/site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
  Feature: Individual task 1 for TA bootcamp test automation
    Background:
      Given I am on the enter number page

    Scenario Outline: Error cases
      When I enter a number: "<number>"
      And I click submit
      Then I see error message: "<message>"
      Examples:
        | number | message               |
        | 40     | Number is too small   |
        | 1      | Number is too small   |
        | 120    | Number is too big     |
        | 300    | Number is too big     |
        | text   | Please enter a number |
    Scenario: Correct number case
      When I enter a number: "70"
      And I click submit
      Then I check 70 square root alert message
      And I do not see error message