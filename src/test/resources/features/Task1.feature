@regression
Feature: Introduction to cucumber task 1
  As a test engineer
  I want to be able to write and execute a scenario outline
# TODO - Create 1 scenario outline and 1 scenario for page with url: "https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
# - Scenario outline for error cases. One example for each case:
    @debugs
    Scenario Outline: an error scenario outline
      Given I am on enter a number page
      When I enter "<input>" in field
      And I press submit button
      Then I see an error
      And I see correct error "<message>"

      @working
      Examples:
        | input | message|
        |  49    | Number is too small|
        |   112    | Number is too big|
        |   text    | Please enter a number|

    Scenario: a correct scenario
      Given I am on enter a number page
      When I enter "64" in field
      And I press submit button
      Then I see alert

#   * enter number too small
#   * enter number too big
#   * enter text instead of the number
# - Scenario for correct number
