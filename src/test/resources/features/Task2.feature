Feature: Task 2 - List of people with jobs page
  As a test engineer
  I want to be able to add, edit, remove people and reset the list

# TODO - Create scenarios (or scenario outlines) for page
# https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
# in order to test that user can:
# - add a new person
# - edit a person
# - remove a person
# - reset original list after adding a person, editing a person or removing a person

  Scenario Outline: add, edit, remove a person and reset list
    Given I am on list of people page
    When I add a new person
      | name | <name> |
      | job  | <job>  |
    Then I see person "<name>" with job "<job>"
    When I change job of person "<editName>" to "<newJob>"
    Then I see person "<editName>" with job "<newJob>"
    When I remove person "<removeName>"
    Then I don't see person "<removeName>"
    When I click reset list
    Then I see original list of people
      | Mike   | Web Designer      |
      | Jill   | Support           |
      | Jane   | Accountant        |
      | John   | Software Engineer |
      | Sarah  | Product Manager   |
      | Carlos | Data Analyst      |
      | Emily  | UX Designer       |
      | David  | Project Manager   |
      | Maria  | QA Engineer       |
      | Alex   | DevOps Engineer   |

    Examples:
      | name | job           | editName | newJob            | removeName |
      | Anna | Automation QA | Jane     | Senior Accountant | John       |
      | Tom  | Developer     | Mike     | Team Lead         | Alex       |