package Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Resources.CommonLibs;
import io.appium.java_client.MobileElement;

public class HomePage {
	private WebDriver driver;
	CommonLibs generic = new CommonLibs();
	
	private By btnGoToSignIn = By.id("com.ebay.mobile:id/button_sign_in");
	private By txtUserName = By.xpath("//*[@text='Email or username']");
	private By txtPassword = By.xpath("//*[@text='Password']");
	private By btnSignIn = By.xpath("//*[@text='SIGN IN']");
	private By optionMayBeLater = By.xpath("//*[@text='MAYBE LATER']");
	private By txtSearchBox = By.id("com.ebay.mobile:id/search_box");
	private By txtSearch = By.id("com.ebay.mobile:id/search_src_text");
	
	private By suggestedProducts = By.id("com.ebay.mobile:id/text");
	private By screenHeader = By.xpath("//div[@class='caption sc_caption sc_caption_margin']");
	
		
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
		
	public String getScreenHeader() {
		return generic.getText(driver, screenHeader);
	}
	
	public void clickGoToignIn(){
		generic.click(driver, btnGoToSignIn);
	}
	
	public void enterUserName(String userName){
		generic.click(driver, txtUserName);
		generic.enterText(driver, txtUserName, userName);
	}
	
	public void enterPassword(String password){
		generic.click(driver, txtPassword);
		generic.enterText(driver, txtPassword, password);
	}

	public void clickSignIn(){
		generic.click(driver, btnSignIn);
	}
	
	public void clickMayBeLater(){
		generic.click(driver, optionMayBeLater);
	}

	
	public void enterProductName(String productName){
		generic.click(driver, txtSearchBox);
		generic.enterText(driver, txtSearch, productName);
	}
	
	public ProductListPage selectFromSuggetionList(){
		List<MobileElement> listEleSearchResult = generic.getAllElement(driver, suggestedProducts);
		listEleSearchResult.get(0).click();
		return new ProductListPage(driver);
	}
	
}
