package managers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import enums.DriverType;
import enums.EnvironmentType;

public class WebDrivers {

	private WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

	public WebDrivers() {
		driverType = FileHandlers.handle().configFile().getBrowser();
		environmentType = FileHandlers.handle().configFile().getEnvironment();
	}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
		switch (environmentType) {	    
		case LOCAL : driver = createLocalDriver();
		break;
		case REMOTE : driver = createRemoteDriver();
		break;
		}
		return driver;
	}

	private WebDriver createRemoteDriver() {
		//throw new RuntimeException("RemoteWebDriver is not yet implemented");
		//Capabilities chromeCapabilities = DesiredCapabilities.chrome();
		Capabilities firefoxCapabilities = DesiredCapabilities.firefox();
		RemoteWebDriver /*chrome,*/ firefox = null;
		
		try {
			//chrome = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
			firefox = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxCapabilities);
			// run against chrome
			//chrome.get("https://www.google.com");
			//System.out.println(chrome.getTitle());

			// run against firefox
			//firefox.get("https://www.google.com");
			//System.out.println(firefox.getTitle());

			//chrome.quit();
			//firefox.quit();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return firefox;
		 
	}

	private WebDriver createLocalDriver() {
		
		switch (driverType) {	    
		
		case FIREFOX : driver = new FirefoxDriver(); break;
		case INTERNETEXPLORER : driver = new InternetExplorerDriver(); break;
		
		case CHROME : 
			System.setProperty(CHROME_DRIVER_PROPERTY, FileHandlers.handle().configFile().getDriverPath());
			driver = new ChromeDriver(); break;					
		}

		if(FileHandlers.handle().configFile().getBrowserWindowSize()) driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(FileHandlers.handle().configFile().getImplicitlyWait(), TimeUnit.SECONDS);
		return driver;
	}	

	public void closeDriver() {
		driver.quit();
	}

}
