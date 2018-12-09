/*Sharing Test/Scenario Context with all StepDefinition files
 * using PicoContainer (Dependency Injection Container)
 * Steps files are using the following:-
 * --PageObjects provided by PageObjectManager
 * --WebDriver provided by WebDriverManager
 * --Properties provided by FileReaderManager
 * --Scenario Data provided by Scenario Context
 * Implement as following in each step def file...
	
	TestContext tc;
	SignOnPage sop;
	
	public SignOnPageSteps(TestContext context) {
		tc = context;
		sop = tc.getPageObjectManager().getSignOnPage();
	}

*/

package cucumber;

import managers.PageObjectManager;
import managers.WebDriverManager;

public class TestContext {

	private WebDriverManager wdm;
	private PageObjectManager pom;
	public ScenarioContext sc;
	
	public TestContext() {
		wdm = new WebDriverManager();
		pom = new PageObjectManager(wdm.getDriver());
		sc = new ScenarioContext();
	}
	
	public WebDriverManager getWebDriverManager() {
		return wdm;
	}
	
	public PageObjectManager getPageObjectManager() {
		return pom;
	}
	
	public ScenarioContext getScenarioContext() {
		return sc;
	}

}
