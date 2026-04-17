# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person
 Feature: As a te engineer I want to test " list of people with jobs" functionality
   Background:
     Given I am on people with jobs page


   Scenario: Add a new person
     When I click on a button "add person"
     And I enter new person name: "Jozeph"
     And I enter new person job: Driver
     And Click button Add
     Then New person is added

@Test
   Scenario: Edit a person
     When I click near fifth person (Sarah,Product Manager, index4) pencil button
     And I change job to Pilot
     And I click button edit
     Then Person job is Edited to pilot

   Scenario: Remove a person
      When I click on a cross button near sixth person (Carlos, Data Analyst,index5)
      Then I see that Carlos is removed from the list



