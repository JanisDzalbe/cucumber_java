# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
Feature: Task1

  @part1
  Scenario Outline: Fill the with wrong symbols and numbers
    When I am on the Square root page
    And I fill the field with wrong <wrongValue> value
    And I press submit button
    Then I receive an error message <errorMessage>

    @without_result
    Examples:
      | wrongValue |   errorMessage    |
      | 5      | Number is too small   |
      | 105    | Number is too big     |
      | AAA    | Please enter a number |


    @part2.a_with_calculation
    Scenario Outline:  Fill the with correct numbers
      When I am on the Square root page
      And I fill the field with correct <suitableValue> value
      And I press submit button
      Then I send <suitableValue> to calculate an <result>
      And I check that no error message

    @working_a
    Examples:
      | suitableValue | result     | textResult |
      | 81            |    9.00    |    Square root of 81 is 9.00   |
      | 59            |    7.68    |    Square root of 59 is 7.68   |
      | 94            |    9.70    |   Square root of 94 is 9.70    |
      | 50            |    7.07    |   Square root of 50 is 7.07    |
      | 51            |    7.14    |    Square root of 51 is 7.14   |
      | 99            |    9.95    |    Square root of 99 is 9.95   |
      | 100           |    10.00    |    Square root of 100 is 10.00 |

  @part2.b_without_calculation
  Scenario Outline:  Fill the with correct numbers
    When I am on the Square root page
    And I fill the field with correct <suitableValue> value
    And I press submit button
    Then I compare suitableValue to an <textResult>
    And I check that no error message

    @working_b
    Examples:
      | suitableValue | result     | textResult |
      | 81            |    9.00    |    Square root of 81 is 9.00   |
      | 59            |    7.68    |    Square root of 59 is 7.68   |
      | 94            |    9.70    |   Square root of 94 is 9.70    |
      | 50            |    7.07    |   Square root of 50 is 7.07    |
      | 51            |    7.14    |    Square root of 51 is 7.14   |
      | 99            |    9.95    |    Square root of 99 is 9.95   |
      | 100           |    10.00    |    Square root of 100 is 10.00 |





