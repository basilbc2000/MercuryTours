package mercurysd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.TestContext;

public class CucumberHooks {

	TestContext tc;
	public CucumberHooks(TestContext context) {
		tc = context;
	}

	@Before
	public void BeforeSteps() {
		System.out.println("Starting test...");
	}
	
	@After
	public void AfterSteps() {
		System.out.println("Ending Test...");
		tc.getDriver().closeDriver();
	}
}
