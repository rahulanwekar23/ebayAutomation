package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Base.TestBaseSetup;
import Module.OrderProductModule;
import Resources.CommonUtility;
import Resources.ExcelUtil;
import Resources.FileLookup;

public class TestEbayApp extends TestBaseSetup {
	String currentMethodName = "";
	ITestResult result;
	OrderProductModule orderProduct = new OrderProductModule();
	private WebDriver driver;
	CommonUtility util = new CommonUtility();
	Map<String, String> inputValue = new HashMap<String, String>();

	@BeforeClass(alwaysRun=true)
	public void setup() {
		driver = getDriver();
	}

	@Test(dataProvider = "Place Order")
	public void test_place_order(Object moduleName, Object testDataRow) throws MalformedURLException {
		String sheetName = moduleName.toString();
		int rownum = (Integer) testDataRow;
		try {
			inputValue = ExcelUtil.TestData(sheetName, rownum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		result = Reporter.getCurrentTestResult();
		currentMethodName = result.getName().toString().trim();
		orderProduct.performLogin(driver, inputValue);
		
		String screenHeader = orderProduct.navigateToProductListPage(driver, inputValue);
		Assert.assertEquals(screenHeader, inputValue.get("Product Name"), "Screen header is not same");
		
		boolean isProductImageDisplayed = orderProduct.navigateToProductPage();
		Assert.assertTrue(isProductImageDisplayed,"Product image is not displayed");
		
		Map<String,String> actualProductDetails = orderProduct.getActProductDetails();
		
		screenHeader = orderProduct.navigateToCheckoutPage(driver);
		Assert.assertEquals(screenHeader, "Checkout", "Screen header is not same");
		
		Map<String,String> expectedProductDetails = orderProduct.getexpProductDetails();
		
		for (String key : actualProductDetails.keySet()) {
			Assert.assertEquals(actualProductDetails.get(key), expectedProductDetails.get(key),"Product Details is not same");
		}
	}
	
	@AfterMethod(alwaysRun=true)
	public void testListener(ITestResult result) throws IOException{
		String filePathOnFailure = FileLookup.SCREENSHOT_ON_FAILURE;
		String filePathOnSuccess = FileLookup.SCREENSHOT_ON_SUCCESS;
		String filePath = "";
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			filePath = String.format(filePathOnSuccess, currentMethodName);
			util.captureScreenshot(currentMethodName,filePath,driver);
			break;
		case ITestResult.FAILURE:
			filePath = String.format(filePathOnFailure, currentMethodName);
			util.captureScreenshot(currentMethodName,filePath,driver);
			break;
		default:
			break;
		}
	}
	
	@DataProvider(name = "Place Order")
	Object[][] dataProviderForbusinessrisknotification() throws Exception {
		ExcelUtil.setExcelFile(FileLookup.DATA_FILE_MASTER_EXCEL,"Sheet1");
		Object[][] testObjArray = ExcelUtil.getTableArray(FileLookup.DATA_FILE_MASTER_EXCEL,"Sheet1","test_place_order");
    	return (testObjArray);
	}
	}
	

