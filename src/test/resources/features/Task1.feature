Feature: Task 1 | Enter a number page
  As a test engineer
  I want to be able to check errors and square root on enter a number page

#   TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
#   - Scenario outline for error cases. One example for each case:
#     * enter number too small
#     * enter number too big
#     * enter text instead of the number
#   - Scenario for correct number

  Scenario Outline: testing enter a number page errors
    Given I am on enter a number page
    When I enter value "<value>" in enter a number page
    And I click submit in enter a number page
    Then I see error "<error>" in enter a number page

    Examples:
      | value      | error                 |
      | 49         | Number is too small   |
      | 101        | Number is too big     |
      | Anastasija | Please enter a number |

  Scenario: testing enter a number page correct number
    Given I am on enter a number page
    When I enter value "81" in enter a number page
    And I click submit in enter a number page
    Then I see alert "Square root of 81 is 9.00" in enter a number page
    And I accept alert in enter a number page
    And I see no error in enter a number page