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
# - check that clear button on adding a user works correctly

Feature: Persons

  Scenario: Add a new person
    When I go to the person page
    And I click on the Add person button
    And I enter name "John"
    And I enter surname "Don"
    And I enter job "Singer"
    And I enter Date of Birth "01/01/1995"
    And I choose language "spanish"
    And I choose gender "male"
    And I choose employee status "intern"
    And I click on the Add button
    And I should see person with name "John" and surname "Don" and job "Singer"
    Then I Click Reset list button


  Scenario: Edit person
    When I go to the person page
    And I click Edit button
    And I enter name "Johnny"
    And I enter surname "Depp"
    And I enter job "Actor"
    And I enter Date of Birth "09/06/1963"
    And I choose language "spanish"
    And I choose gender "male"
    And I choose employee status "intern"
    And I click on the Edit button
    Then I Click Reset list button

  Scenario: Delete person
    When I go to the person page
    And  I Click Delete button
    And I should not see person with name "Mike" and surname "Kid"
    Then I Click Reset list button

    Scenario: Check that clear button on adding a user works correctly
      When I go to the person page
      And I click on the Add person button
      And I enter name "John"
      And I enter surname "Don"
      And I enter job "Singer"
      And I enter Date of Birth "01/01/1995"
      And I choose language "spanish"
      And I choose gender "male"
      And I choose employee status "intern"
      And I click on Clear all fields button
      Then I should see empty fields


  @debug
  Scenario Outline: Add person
    When I go to the person page
    And I click on the Add person button
    And I enter name "<name>"
    And I enter surname "<surname>"
    And I enter job "<job>"
    And I enter Date of Birth "<date>"
    And I click on the Add button


    @working
    Examples:
      | name  | surname | job | date |
      | Marry | Jane  | Reporter | 30.05.1982 |
      | Tom | Holland | Actor  | 01.06.1996 |

