# TODO - Create 1 scenario outline and 1 scenario for page with url:
#  "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct numbers

@regression
Feature: Task 3
  Background:
    Given I am on The Roots of a quadratic equation page
  @debugs
  Scenario Outline: Error cases for entering a number
    When I enter number: "<number>"
    And I click submit number
    Then I see number error: "<error>"
    @not_working
    Examples:
      | number | error                 |
      | 49     | Number is too small   |
      | 101    | Number is too big     |
      | fifty  | Please enter a number |

  @debugs
  Scenario Outline: Correct number
    When I enter number: "<number>"
    And I click submit number
    Then I see square root result: "<result>"
    @working
    Examples:
      | number                                                | result |
      | 50                                                    | 7.07   |
      | 100                                                   | 10.00  |
      | 64                                                    | 8.00   |
      | 64.0                                                  | 8.00   |
      | 65                                                    | 8.06   |
      | 65.00000000000000000000000000000000000000000000000001 | 8.06   |

