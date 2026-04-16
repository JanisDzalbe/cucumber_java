package cucumber.runners;

// Runs only @test scenarios
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:cucumber-report/html-report",
                "junit:cucumber-report/junit-report.xml",
                "json:cucumber-report/json-report.json"},
        tags = "@test",
        glue = {"cucumber.stepDefinitions"}
)
public class CucumberRunnerTest {
}

// Runs only @regression scenarios
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:cucumber-report/html-report",
                "junit:cucumber-report/junit-report.xml",
                "json:cucumber-report/json-report.json"},
        tags = "@regression",
        glue = {"cucumber.stepDefinitions"}
)
public class CucumberRunnerRegression {
}