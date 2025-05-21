@regression
Feature: Introduction to cucumber part 3
  As a test engineer
  I want to be able to write and execute a scenario outline

  @debugs
  Scenario Outline: a new scenario outline
    Given I am on age page
    When I enter name: "<name>"
    And I enter age: <age>
    And I click submit age
    Then I see message: "<message>"
  @working
    Examples:
      | name  | age | message                        |
      | Ann   | 5   | Hello, Ann, you are a kid      |
      | Marry | 50  | Hello, Marry, you are an adult |
      | Bob   | 61  | Hello, Bob, you are an adult   |
  @not_working
    Examples:
      | name | age | message                   |
      | Tom  | 15  | Hello, Tom, you are a kid |


#   TODO - create Scenario Outline for 'Give us your feedback!' page
#   URL: https://acctabootcamp.github.io/site/tasks/provide_feedback
#   Navigate to page
#   Set name and age based on test Examples
#   Click "Send" button and verify that previous input is displayed in correct fields

  #I don't quite understand certain tags -> ask about them more
  # Is it possible to define certain steps differently, i.e. instead of id="name", get it to use id="fb_name"?
  # Also, background here doesn't quite work, do I need to define a new feature for that?
  Scenario Outline: Provide feedback input test
    Given I am on the provide feedback page
    When I enter my name: "<name>"
    And I enter my age: "<age>"
    And I select my languages: "<languages>"
    And I click send
    Then I see my name is: "<name>"
    And I see my age is: "<age>"
    And I see my languages are: "<languages>"
    #should I use plural or not while writing a case like this? ^
    @working
    Examples:
    | name | age | languages        |
    | John | 12  | English          |
    | Mary | 21  | English Spanish  |
    | Ann  | 99  | French Chinese   |
    @not_working # Is this a valid example of not working? It theoretically shouldn't even go through the submit
    Examples:
    | name | age | languages |
    | Tom  | abc |  English  |


