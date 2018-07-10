package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

public class CommonUtility {

	public int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public Properties getConfigData(String fileName) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(fileName);
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	public String takeScreenShot1(String methodName, String filePath, WebDriver driver) {
		File f = new File(filePath + methodName + ".png");
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = filePath + methodName + ".png";
		String dest2 = methodName + ".png";
		File destination = new File(dest);
		File destination2 = new File(dest2);
		try {
			FileUtils.copyFile(source, destination);
			FileUtils.copyFile(source, destination2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest2;
	}
	
		public void captureScreenshot(String testName,String filePath,WebDriver driver) {
	    //String imagesLocation = "target/surefire-reports/screenshot/" + "android" + "/";
	    new File(filePath).mkdirs(); // Insure directory is there
	    String filename = filePath + testName + ".jpg";

	    try {
	        Thread.sleep(500);
	        WebDriver augmentedDriver = new Augmenter().augment(driver);
	        File scrFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(scrFile, new File(filename), true);
	    } catch (Exception e) {
	        System.out.println("Error capturing screen shot of " + testName + " test failure.");
	        // remove old pic to prevent wrong assumptions
	        File f = new File(filename);
	        f.delete(); // don't really care if this doesn't succeed, but would like it to.
	    }
	}

}
