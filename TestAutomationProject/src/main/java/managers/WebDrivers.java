package managers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import enums.DriverType;
import enums.EnvironmentType;
import managers.FileHandlers;

public class WebDrivers {

	private WebDriver driver;
	private RemoteWebDriver rDriver;
	private static DriverType driverType;
	private static EnvironmentType runLocation;
	private static Boolean isHeadless, isMaximize;
	private static String url, path;
	private static Long delay;
	private static Logger log = LogManager.getLogger(WebDrivers.class.getName());

	public WebDrivers() {

		driverType = FileHandlers.handle().configFile().getBrowser();
		runLocation = FileHandlers.handle().configFile().getRunLocation();
		// isHeadless = FileHandlers.handle().configFile().isHeadless();
		isMaximize = FileHandlers.handle().configFile().getBrowserWindowSize();
		delay = FileHandlers.handle().configFile().getImplicitlyWait();

		log.info("Key Configs:" + "runLocation:" + runLocation + ",driverType:" + driverType + ",isHeadless:"
				+ isHeadless + ",isMaximize:" + isMaximize);
	}

	public WebDriver getDriver() {
		if (driver == null)
			driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {

		log.debug("");

		switch (runLocation) {
		case LOCAL:
			path = FileHandlers.handle().configFile().getDriverPath();
			log.debug(path);
			driver = createLocalDriver();
			break;
		case REMOTE:
			url = FileHandlers.handle().configFile().getHubUrl();
			log.info("Hub URL: " + url);
			driver = createRemoteDriver();
			break;
		}
		return driver;
	}

	private WebDriver createRemoteDriver() {

		// throw new RuntimeException("RemoteWebDriver is not yet implemented");
		// Capabilities chromeCapabilities = DesiredCapabilities.chrome();
		Capabilities firefoxCapabilities = DesiredCapabilities.firefox();
		RemoteWebDriver /* chrome, */ firefox = null;

		try {
			// chrome = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
			// chromeCapabilities);
			firefox = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxCapabilities);
			// run against chrome
			// chrome.get("https://www.google.com");
			// System.out.println(chrome.getTitle());

			// run against firefox
			// firefox.get("https://www.google.com");
			// System.out.println(firefox.getTitle());

			// chrome.quit();
			// firefox.quit();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return firefox;
	}

	private WebDriver createLocalDriver() {

		switch (driverType) {

		case FIREFOX:
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile ffprofile = profile.getProfile("TestProfile");
			System.setProperty("webdriver.gecko.driver", path + "geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(ffprofile);
			if (isHeadless)
				options.setHeadless(true);
			options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
			driver = new FirefoxDriver(options);
			break;

		case CHROME:
			log.debug("");
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.setCapability(ChromeOptions.CAPABILITY, cOptions);
			System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
			// if (isHeadless) cOptions.setHeadless(true);
			driver = new ChromeDriver(cOptions);
			break;

		case INTERNETEXPLORER:
			driver = new InternetExplorerDriver();
			break;

		case EDGE:
			System.setProperty("webdriver.edge.driver", path + "MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			break;
		}

		// if (isMaximize) driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(delay,TimeUnit.SECONDS);
		// driver.get("www.google.com");

		return driver;
	}

	public void closeDriver() {
		driver.quit();
	}

}