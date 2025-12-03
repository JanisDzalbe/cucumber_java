package cucumber.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resources/features/Sample1.feature",
     //   features = "src/test/resources/features/",
        plugin = {"pretty", "html:cucumber-report/html-report",
                "junit:cucumber-report/junit-report.xml",
                "json:cucumber-report/json-report.json"},
       tags = "@test",
        //tags = "@exampleScenario or @anotherExample",
        //tags = "@test and not(@anotherExample)",
//tags = "not @test",
        glue = {"cucumber.stepDefinitions"}
)
public class CucumberRunner {

}
