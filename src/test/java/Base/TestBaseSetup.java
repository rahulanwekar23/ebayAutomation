package Base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import Resources.CommonUtility;
import Resources.FileLookup;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBaseSetup {
	private static WebDriver driver;
	CommonUtility util = new CommonUtility();
	Properties prop = util.getConfigData(FileLookup.CONFIG_FILE);
	
	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String deviceType, String serverURL) {
		
		URL url = null;
		try {
			url = new URL(serverURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.APP, FileLookup.APK_PATH);
		caps.setCapability(MobileCapabilityType.PLATFORM, prop.getProperty("Platform"));
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("Device_Name"));

		switch (deviceType) {
		case "android":
			driver = new AndroidDriver<MobileElement>(url, caps);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			break;
		case "ios":
			driver = new IOSDriver<MobileElement>(url, caps);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			break;
		
		default:
			System.out.println("Driver : " + deviceType + " is invalid, Launching Android driver..");
			driver = new AndroidDriver<MobileElement>(url, caps);
		}
	}

	@Parameters({ "deviceType","serverURL" })
	@BeforeClass(alwaysRun=true)
	public void initializeTestBaseSetup(String deviceType, String serverURL) {
		try {
			setDriver(deviceType,serverURL);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	

}