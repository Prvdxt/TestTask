package src.baseclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverClass {
	public static WebDriver driver;

	@BeforeMethod
	public static WebDriver driverLaunch() {
		System.setProperty("webdriver.chrome.driver", ObjectRepository.CHROMEDRIVERPATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static void openBrowser() {
		driver.get(ObjectRepository.URL);

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
