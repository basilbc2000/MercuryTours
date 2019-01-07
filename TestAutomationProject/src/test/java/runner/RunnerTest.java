package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features/ApplicationPages.feature"},
        glue = {"stepDefinitions"},                
        plugin = {"html:target/cucumber-reports", "json:target/cucumber-reports/Cucumber.json"}
)

public class RunnerTest {

}
