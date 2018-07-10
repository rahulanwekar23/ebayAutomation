package Resources;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import junit.framework.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonLibs {

	CommonUtility util = new CommonUtility();
	WebDriverWait wait;

	public void enterText(WebDriver driver, By locator, String stringValue) {
		try{
		MobileElement element = driver.findElement(locator);
		element.click();
		element.sendKeys(stringValue);
		}
		catch(Exception e){
			try{
				MobileElement element = driver.findElement(locator);
				element.sendKeys(stringValue);
			}
			catch(Exception e1){
				System.out.println("Exception while locating element");
			}
		}
	}
	
	public void click(WebDriver driver, By locator) {
		try {
			MobileElement element = driver.findElement(locator);
			element.click();
		} catch (NoSuchElementException Ex) {
			System.out.println("Exception while locating element");
		} catch (TimeoutException Ex) {
			Assert.fail("Timeout finding the element Located at " + locator);
		}
	}
	
	public String getText(WebDriver driver, By locator) {
			wait = new WebDriverWait(driver,50);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
			MobileElement element = driver.findElement(locator);
			String s = element.getText();
			return s;
	}

	public boolean isMobileElementDisplayed(WebDriver driver, By locator) throws InterruptedException {
		try {
			MobileElement eleFieldDisplayed = driver.findElement(locator);
			return eleFieldDisplayed.isDisplayed();
		} catch (NoSuchElementException Ex) {
			return false;
		}
	}

	public List<MobileElement> getAllElement(WebDriver driver, By locator) {
		List<MobileElement> eleCompanies = null;
		try {
			eleCompanies = driver.findElements(locator);
		} catch (NoSuchElementException Ex) {
			Assert.fail("Element Not Found " + locator);
		} catch (TimeoutException Ex) {
			Assert.fail("Timeout finding the element Located at " + locator);
		}
		return eleCompanies;
	}
}