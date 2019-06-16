package mercurysd;

import cucumber.api.java.en.Given;
import managers.TestContext;
import mercurypo.PageNavigation;

public class PageNavigationSteps {
	TestContext tc;
	PageNavigation nav;
	
	public PageNavigationSteps(TestContext context) {
		tc = context;
		nav = tc.getPageObject().getPage();
	}

	@Given("^user opens the home page$")
	public void user_opens_the_home_page()  {
		nav.open_HomePage();
	}
}
