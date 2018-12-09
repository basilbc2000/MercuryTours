package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageObjects.HomePage;

public class HomePageSteps {

	TestContext tc;
	HomePage hp;
	
	public HomePageSteps(TestContext context) {
		tc = context;
		hp = tc.getPageObjectManager().getHomePage();
	}

	@Given("^user opens the home page$")
	public void user_opens_the_home_page()  {
		hp.open_HomePage();
		hp.is_HomePage();
	}
	
	@And("^enters username \"([^\"]*)\"$")
	public void enters_username(String arg1)  {
		hp.type_userName(arg1);
	}

	@And("^enters password \"([^\"]*)\"$")
	public void enters_password(String arg1)  {
		hp.type_password(arg1);	    
	}

	@When("^login button is clicked$")
	public void login_button_is_clicked()  {
		hp.click_login();	   
	}
	
	@And("^register link is clicked$")
	public void register_link_is_clicked() {
		hp.click_register();
	}

}
