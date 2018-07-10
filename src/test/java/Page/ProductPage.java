package Page;

import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Resources.CommonLibs;
import Resources.CommonUtility;

public class ProductPage {
	private WebDriver driver;
	CommonLibs generic = new CommonLibs();
	CommonUtility util = new CommonUtility();
	
	private By productImage = By.xpath("//*[@class='android.widget.ImageView']");
	private By productName = By.id("com.ebay.mobile:id/textview_item_name");
	private By productPrice = By.id("com.ebay.mobile:id/textview_item_price");
	private By btnByItNow = By.id("com.ebay.mobile:id/button_bin");
	private By btnReview = By.xpath("//*[@text='REVIEW']");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isProductImageDisplayed(){
		boolean isImageDisplayed =false;
		try {
			isImageDisplayed = generic.isMobileElementDisplayed(driver, productImage);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return isImageDisplayed;
	}

	public String getProductName() {
		return generic.getText(driver, productName);
	}

	public String getProductPrice() {
	String price = generic.getText(driver, productPrice);
	price=price.replaceAll("[^\\x00-\\x7F]"," ");
	price =price +".00";
	return price;
	}

	public void clickByItNow() {
		generic.click(driver, btnByItNow);
	}

	public CheckoutPage clickReview() {
		generic.click(driver, btnReview);
		return new CheckoutPage(driver);
	}

}
