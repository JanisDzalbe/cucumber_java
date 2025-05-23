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

  #I regret doing the list of people.
  # I believe it's a very poorly implemented page.
  Feature: List of people test

    Background:
      Given I am on the list of people page

      # Should I make certain things like languages, gender, status be essentially enums?
    # Why do languages have an extra , i.e. English, French, ; when adding, but not when editing??
    # also why is spanish not capitalized when adding, but capitalized when editing
  Scenario Outline: Add a person
    With calendar enter as text
    When I get list of all people
    And I click add person button
    Then I enter information:
   | name      | <name>       |
   | surname   | <surname>    |
   | job       | <job>        |
   | dob       | <dob>        |
   | languages | <languages>  |
   | gender    | <gender>     |
   | status    | <status>     |
  And I click add
  Then I make sure the list contains the person I added
  Examples:
  | name | surname | job | dob | languages | gender | status |
  | John | Boulevard| Cat collector | 05/14/2010 | english spanish | male   | contractor |
  | Anna | Lalalina | Homeless      | 01/01/1000 | english         | female | employee   |
  | Anna | Lalalina | Billionaire      | 12/12/2030 | undefined         | undefined | intern   |
  | Anananananananaananananananaananaanananannaannanananananananannanananananana | Lalalina | Factory worker      | 01/01/1000 | english         | female | employee   |

    Scenario Outline: Add a person w/ via calendar
    With calendar enter via calendar selector
      When I get list of all people
      And I click add person button
      Then I enter information with calendar selector:
        | name      | <name>       |
        | surname   | <surname>    |
        | job       | <job>        |
        | dob       | <dob>        |
        | languages | <languages>  |
        | gender    | <gender>     |
        | status    | <status>     |
      And I click add
      Then I make sure the list contains the person I added
      Examples:
        | name | surname | job | dob | languages | gender | status |
        | John | Boulevard| Cat collector | 05/14/2020 | english spanish | male   | contractor |
#        | Test | Test     | Test          | 05/14/2030 | undefined       | female | employee   |
  #Should I do this commented one as like @not_working or something? because yeah I'd expect you to not be able to input 2030 What's best practice here?

  Scenario Outline: Edit a person
    When I get list of all people
    And I click edit person button for "<index>" person in list
     #here it's n-th person in list, via index
    Then I enter edit information:
      | name      | <name>       |
      | surname   | <surname>    |
      | job       | <job>        |
      | dob       | <dob>        |
      | languages | <languages>  |
      | gender    | <gender>     |
      | status    | <status>     |
  And I click edit
  Then I make sure the list of same size contains the person I added
   Examples:
    | index | name | surname | job | dob | languages | gender | status |
    | 0   | John | Boulevard| Cat collector | 05/14/2010 | english spanish | male   | contractor |
    | 1   | Anna | Lalalina | Mango eater      | 01/01/1998 | english         | female | employee   |

    Scenario Outline: Remove a person
      When I get list of all people
      And I click remove person for "<index>" person in list
      Then I make sure the list is smaller and the person is no longer in it
      Examples:
      |index|
      |0 |
      |1 |
      | 2 |

      Scenario: Reset list after adding a person
        When I get list of all people
        And I click add person button
        Then I enter information:
          | name      | John       |
          | surname   | Cook    |
          | job       | Sous-chef        |
          | dob       | 01/02/2003        |
          | languages | english  |
          | gender    | male     |
          | status    | employee     |
        And I click add
        Then I make sure the list contains the person I added
        And I click reset list
        Then I make sure the list is the same as in the begginning



    Scenario Outline: Edit a person and reset
      When I get list of all people
      And I click edit person button for "<index>" person in list
     #here it's n-th person in list, via index
      Then I enter edit information:
        | name      | <name>       |
        | surname   | <surname>    |
        | job       | <job>        |
        | dob       | <dob>        |
        | languages | <languages>  |
        | gender    | <gender>     |
        | status    | <status>     |
      And I click edit
      Then I make sure the list of same size contains the person I added
      And I click reset list
      Then I make sure the list is the same as in the begginning
      Examples:
        | index | name | surname | job | dob | languages | gender | status |
        | 0   | John | Boulevard| Cat collector | 05/14/2010 | english spanish | male   | contractor |
        | 1   | Anna | Lalalina | Mango eater      | 01/01/1998 | english         | female | employee   |
        | 2   | Anna | Lalalina | Mango eater      | 01/01/1998 | english         | female | employee   |



    Scenario Outline: Remove a person and reset
      When I get list of all people
      And I click remove person for "<index>" person in list
      Then I make sure the list is smaller and the person is no longer in it
      And I click reset list
      Then I make sure the list is the same as in the begginning
      Examples:
        |index|
        |0 |
        |1 |
        | 2 |

    Scenario: Add person and clear text
    With calendar enter as text
      When I get list of all people
      And I click add person button
      Then I enter information:
        | name      | John       |
        | surname   | Cook    |
        | job       | Sous-chef        |
        | dob       | 01/02/2003        |
        | languages | english  |
        | gender    | male     |
        | status    | employee     |
      And I click clear all fields
      And I click add
      Then I make sure the list does not contain the person I wanted to add
