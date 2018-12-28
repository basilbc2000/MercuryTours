package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepDefinitions"},        
        features = {"src/test/resources/features/ApplicationPages.feature"},
        plugin = {"html:target/cucumber-reports", "json:target/cucumber-reports/Cucumber.json"}
)

public class RunnerTest {

	public RunnerTest() {
	}

}
