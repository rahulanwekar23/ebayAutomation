package Module;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import Page.ProductListPage;
import Page.ProductPage;
import Page.HomePage;
import Page.CheckoutPage;
import Resources.CommonUtility;

public class OrderProductModule {
	CommonUtility util = new CommonUtility();
	WebDriver driver;
	private HomePage home;
	private ProductListPage productList;
	private ProductPage product;
	private CheckoutPage checkout;
	
	public void performLogin(WebDriver driver, Map<String, String> inputValue){
		home = new HomePage(driver);
		home.clickGoToignIn();
		home.enterUserName(inputValue.get("User Name"));
		home.enterPassword(inputValue.get("Password"));
		home.clickSignIn();
		home.clickMayBeLater();
	}

	public String navigateToProductListPage(WebDriver driver, Map<String, String> inputValue) {
		home.enterProductName(inputValue.get("Product Name"));
		productList = home.selectFromSuggetionList();
		return productList.getScreenHeader();
	}

	public boolean navigateToProductPage() {
		product = productList.selectProduct();
		return product.isProductImageDisplayed();
	}

	public Map<String, String> getActProductDetails() {
		Map<String, String> productDetailsMap = new HashMap<String, String>();
		productDetailsMap.put("Product Name", product.getProductName());
		productDetailsMap.put("Product Price", product.getProductPrice());
		return productDetailsMap;
	}
	
	public String navigateToCheckoutPage(WebDriver driver) {
		product.clickByItNow();
		checkout = product.clickReview();
		return checkout.getScreenHeader();
	}

	public Map<String, String> getexpProductDetails() {
		Map<String, String> productDetailsMap = new HashMap<String, String>();
		productDetailsMap.put("Product Name", checkout.getProductName());
		productDetailsMap.put("Product Price", checkout.getProductPrice());
		return productDetailsMap;
	}
}
