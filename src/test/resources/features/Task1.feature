# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number

  Feature:  Task1 implementation
    As a Tester
    I want to verify Multiple test cases

    Background:
      Given I am on EnterNumber page

  Scenario Outline: I enter wrong input
    When I enter number in field "<Input>"
    And I click submit number
    Then I see error: "<message>"
    Examples:
    |Input | message                 |
    |1     |Number is too small      |
    |1111   |Number is too big        |
    |a      |Please enter a number    |
    |       |You haven't entered anything|


    Scenario Outline:  I enter correct input
      When I enter number in field "<Input>"
      And I click submit number
      Then I see alert <message>
      Examples:
        | Input  | message                     |
        | 55     |"Square root of 55 is 7.42"  |
        | 66     |"Square root of 66 is 8.12"  |
        | 77     |"Square root of 77 is 8.77"  |