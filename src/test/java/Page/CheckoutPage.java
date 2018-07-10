package Page;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Resources.CommonLibs;
import Resources.CommonUtility;

public class CheckoutPage {
	private WebDriver driver;
	CommonLibs generic = new CommonLibs();
	CommonUtility util = new CommonUtility();
	private By screenHeader = By.id("com.ebay.mobile:id/toolbar_centered_title");
	private By productName = By.id("com.ebay.mobile:id/item_title");
	private By productPrice = By.id("com.ebay.mobile:id/item_price");
	
		
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getScreenHeader() {
		return generic.getText(driver, screenHeader);
	}
		
	public String getProductName(){
		return generic.getText(driver, productName);
	}
	
	public String getProductPrice(){
		return generic.getText(driver, productPrice);
	}
	
}
