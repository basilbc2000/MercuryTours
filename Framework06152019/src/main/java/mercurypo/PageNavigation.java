package mercurypo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import managers.AutUtilities;

public class PageNavigation {
	
	WebDriver driver;
	private static Logger log = LogManager.getLogger(PageNavigation.class.getName());

	public PageNavigation (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean open_HomePage() {
		String url = AutUtilities.call().config().getProperty(AutUtilities.call().config().getProperty("WEBAPP")
				+ "_APP_URL");
		log.info("Loading ["+url+"]");
		driver.get(url);
		return driver.getTitle().equals("Welcome: Mercury Tours");
	}
	
}
