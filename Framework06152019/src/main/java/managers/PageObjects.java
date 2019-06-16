package managers;

import org.openqa.selenium.WebDriver;
import mercurypo.PageNavigation;

public class PageObjects {

	private WebDriver driver;
	private PageNavigation nav;
	
	public PageObjects (WebDriver driver) {
		this.driver = driver;		
	}
	
	public PageNavigation getPage() {
		return (nav == null) ? nav = new PageNavigation(driver) : nav;
	}	
	
}
