### Structure
#### Same but without Page Object:
* For running tests use [CucumberRunner.java](../main/src/test/java/cucumber/runners/CucumberRunner.java)
* For step definition use [SampleSteps.java](../main/src/test/java/cucumber/stepDefinitions/SampleSteps.java)
* Simple scenario example: [Sample1.feature](../main/src/test/resources/features/Sample1.feature)
* Simple scenario with parameters in steps: [Sample2.feature](../main/src/test/resources/features/Sample2.feature)
* Scenario outline example: [Sample3.feature](../main/src/test/resources/features/Sample3.feature)

### Tasks
#### Task 1
In [Task1.feature](../main/src/test/resources/features/Task1.feature) create 1 scenario outline and
1 scenario for page with url:
"https://janisdzalbe.github.io/example-site/tasks/enter_a_number"
  * Scenario outline for error cases:
      * enter number too small
      * enter number too big
      * enter text instead of the number
  * Scenario for correct number

#### Task 2
In [Task2.feature](../main/src/test/resources/features/Task2.feature) create scenarios (or scenario outlines) for page https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html
in order to test that user can:
  * add a new person
  * edit a person
  * remove a person
  * reset original list after changes (adding a person, editing a person or removing a person)

_Note_: Make sure that changes to the list are asserted

### Setup
[see wiki for Project Setup](https://github.com/JanisDzalbe/cucumber_java/wiki/Project-Setup)

[see wiki for WebDriver Setup](https://github.com/JanisDzalbe/cucumber_java/wiki/WebDriver-Setup)

---
### GIT

[see wiki for Create-a-branch](https://github.com/JanisDzalbe/selenium_java/wiki/Create-new-branch)

[see wiki for Git-add-commit-pust](https://github.com/JanisDzalbe/selenium_java/wiki/Committing-code-and-pushing-to-remote)




