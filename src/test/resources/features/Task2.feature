# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

Feature: Creating scenarious for steps checking

  Scenario: Scenario of adding a new person
    Given I am on the List of people page
    When  I click Add person button
    And  I enter name: "Filips"
    And  I enter job: "Agent"
    And I click Add button
    Then New Person appears in the list

  Scenario: Scenario for editing a person
    Given I am on the List of people page
    When  I click Edit icon
    And  I enter name: "Abass"
    And  I enter job: "CEO"
    And I click Edit button
    Then Updated details appears in the list

  Scenario: Scenario for removing a person
    Given I am on the List of people page
    When  I click on "x" icon
    Then Field deletes in the list

  Scenario: reset list after adding
    Given I am on the List of people page
    When  I click Add person button
    And  I enter name: "Filips"
    And  I enter job: "Agent"
    And I click Add button
    And  I click on ResetList button
    Then List updates to initial

  Scenario: reset list after editing
    Given I am on the List of people page
    When  I click Edit icon
    And   I enter name: "Abass"
    And   I enter job: "CEO"
    And   I click Edit button
    And   I click on ResetList button
    Then  List updates to initial

  Scenario: Reset list after removing a person
    Given I am on the List of people page
    When  I click on "x" icon
    And   I click on ResetList button
    Then  List updates to initial