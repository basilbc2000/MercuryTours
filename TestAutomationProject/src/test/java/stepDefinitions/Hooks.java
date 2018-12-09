package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	TestContext tc;
	public Hooks(TestContext context) {
		tc = context;
	}

	@Before
	public void BeforeSteps() {
		System.out.println("Starting test...");
	}
	
	@After
	public void AfterSteps() {
		System.out.println("Ending Test...");
		tc.getWebDriverManager().closeDriver();
	}
}
