# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

  Feature: testing People with jobs page

    # - Scenario Outline for adding a new person
    Scenario Outline: testing adding a new person to the list
      Given I am on People with jobs page
      When I click Add person
      Then I am redirected to People with jobs page form
      When I enter name "<name>"
      And I enter job "<job>"
      And I click Add
      Then I am redirected to People with jobs page
      And I see a person with name "<name>" and job "<job>" in the list

      Examples:
      | name  | job           |
      | Katya | Administrator |
      | Max   | Scrum Master  |
      | Anna  | Receptionist  |


      # - Scenario for editing a person
      Scenario: testing editing a person
        Given I am on People with jobs page
        When I click pencil icon for person "Mike"
        Then I am redirected to People with jobs edit page
        When I enter new name "Max"
        And I enter new job "Scrum Master"
        And I click Edit
        Then I am redirected to People with jobs page
        And I see a person with name "Max" and job "Scrum Master" in the list


     # - Scenario for removing a person
     Scenario: testing removing a person
       Given I am on People with jobs page
       When I click cross icon for person "Mike"
       Then I do not see a person with name "Mike" in the list


  # - Scenario for resetting original list after adding a person
  Scenario: testing resetting original list after adding a person
    Given I am on People with jobs page
    And I remember the original list of people
    When I click Add person
    And I enter name "Katya"
    And I enter job "Administrator"
    And I click Add
    Then I see a person with name "Katya" and job "Administrator" in the list
    When I click Reset List
    Then I see the original list of people


  # - Scenario for resetting original list after editing a person
    Scenario: testing resetting original list after editing a person
      Given I am on People with jobs page
      And I remember the original list of people
      When I click pencil icon for person "Mike"
      And I enter name "Anna"
      And I enter job "Receptionist"
      And I click Edit
      Then I see a person with name "Anna" and job "Receptionist" in the list
      When I click Reset List
      Then I see the original list of people


      # - Scenario for resetting original list after removing a person
    Scenario: testing resetting original list after removing a person
      Given I am on People with jobs page
      And I remember the original list of people
      When I click cross icon for person "Mike"
      Then I do not see a person with name "Mike" in the list
      When I click Reset List
      Then I see the original list of people








