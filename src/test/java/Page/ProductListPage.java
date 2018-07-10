package Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Resources.CommonLibs;
import Resources.CommonUtility;
import io.appium.java_client.MobileElement;

public class ProductListPage {
	private WebDriver driver;
	CommonLibs generic = new CommonLibs();
	CommonUtility util = new CommonUtility();
	private By searchResult = By.id("com.ebay.mobile:id/cell_collection_item");
	private By screenHeader = By.id("com.ebay.mobile:id/title");
	
		
	public ProductListPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getScreenHeader() {
		return generic.getText(driver, screenHeader);
	}
		
	public ProductPage selectProduct(){
		List<MobileElement> listEleProducts = generic.getAllElement(driver, searchResult);
		int totalNumOfProducts = listEleProducts.size();
		int index = util.getRandomNumberInRange(0, totalNumOfProducts-1);
		listEleProducts.get(index).click();
		return new ProductPage(driver);
	}
}
