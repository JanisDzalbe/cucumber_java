# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
Feature:
  As a User
  I can Add a person, Edit a person, Remove a person and Reset the list
  Background:
   Given I am on ListOfPeople page
#day 1 implementation dosent have data implementasations
  Scenario Outline: Adding a person
    When I click Add Person
    And  I enter name: "<name>"
    And  I enter Job: "<job>"
    And  I click Add
    Then I see name: "<name>" and job "<job>"

    Examples:
      | name   | job            |
      | aaa    | bbb            |
      | bbb    | ccc            |

    Scenario Outline: Edit a Person
      When I click EditPerson "<name>"
      And I Change job: "<job>"
      And i Click Edit
      Then I see name: "<name>" and job "<job>"
      Examples:
      |name         | job            |
      | Jane        | bbb            |
      | John        | ccc            |

  Scenario Outline: Remove a Person
    When I click RemovePerson name: "<name>" and job: "<job>"
    Then I do not see name: "<name>" and job "<job>"
    Examples:
      |name         | job                   |
      | Jane        | Accountant            |
      | John        | Software Engineer     |

  Scenario Outline: Reset list after removing person
    When I click RemovePerson name: "<name>" and job: "<job>"
    And I do not see name: "<name>" and job "<job>"
    And I click ResetPersonList
    Then I see name: "<name>" and job "<job>"
    And  I see Person Amount in List

    Examples:
      |name         | job                   |
      | Jane        | Accountant            |
      | John        | Software Engineer     |



#day 2 implementatio has data table implementation
  Scenario Outline: Adding a person
    When I click Add Person
    And I enter values:
    |name|"<name>" |
    |job |"<job>"  |
    And  I click Add
    Then I check for fields ofListOfPeople:
      |name|"<name>" |
      |job |"<job>"  |

    Examples:
      | name   | job            |
      | aaa    | bbb            |
      | bbb    | ccc            |

  Scenario Outline: Edit a Person
    When I click EditPerson "<name>"
    And I Change job: "<job>"
    And i Click Edit
    Then I check for fields ofListOfPeople:
      |name|"<name>" |
      |job |"<job>"  |
    Examples:
      |name         | job            |
      | Jane        | bbb            |
      | John        | ccc            |

  Scenario Outline: Reset list after removing person
    When I click RemovePerson name: "<name>" and job: "<job>"
    And I do not see name: "<name>" and job "<job>"
    And I click ResetPersonList
    Then I check for fields ofListOfPeople:
      |name|"<name>" |
      |job |"<job>"  |
    And  I see Person Amount in List

    Examples:
      |name         | job                   |
      | Jane        | Accountant            |
      | John        | Software Engineer     |