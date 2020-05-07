package src.baseclasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	public static WebDriverWait wait;

	public static WebElement getElementByXpath(WebDriver driver, String locator) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		WebElement element = driver.findElement(By.xpath(locator));
		return element;

	}

	public static WebElement getElementByTagName(WebDriver driver, String locator) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locator)));
		WebElement element = driver.findElement(By.tagName(locator));
		return element;

	}

	public static WebElement getElementById(WebDriver driver, String locator) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
		WebElement element = driver.findElement(By.id(locator));
		return element;

	}

	public static WebElement getElementByName(WebDriver driver, String locator) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
		WebElement element = driver.findElement(By.name(locator));
		return element;

	}

	public static WebElement getElementByCssSelector(WebDriver driver, String locator) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
		WebElement element = driver.findElement(By.cssSelector(locator));
		return element;

	}

	public static WebElement getElementByClassName(WebDriver driver, String locator) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
		WebElement element = driver.findElement(By.className(locator));
		return element;

	}

	public static WebElement getElementByLinkText(WebDriver driver, String locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
		WebElement element = driver.findElement(By.linkText(locator));
		return element;

	}

	public static WebElement getElementByPartialLinkText(WebDriver driver, String locator) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locator)));
		WebElement element = driver.findElement(By.partialLinkText(locator));
		return element;

	}

	public static void clickElement(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void windowMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public static void enterTextInBox(WebDriver driver, WebElement element, String text) {
		waitForElementimplicityly(driver);
		element.sendKeys(text);

	}

	public static void clickonSubmitButton(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.submit();

	}

	public static String getTextofElement(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		String str = element.getText();
		return str;
	}

	public static void hoverMouseOverElement(WebDriver driver, WebElement element1, WebElement displayedDivision,
			WebElement element2) {

		Actions builder = new Actions(driver);
		builder.moveToElement(element1).perform();

		wait.until(ExpectedConditions.visibilityOf(displayedDivision));

		// element2.click();
		builder.moveToElement(element2).click().perform();

	}

	public static boolean checkEnabled(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean check = element.isEnabled();
		return check;

	}

	public static boolean checkSelection(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean check = element.isSelected();
		return check;

	}

	public static boolean checkDisplay(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean check = element.isDisplayed();
		return check;

	}

	public static List<WebElement> getListOfElements(WebDriver driver, String locator) {

		List<WebElement> listOfElement = driver.findElements(By.xpath(locator));

		return listOfElement;
	}

	public static void waitForElementimplicityly(WebDriver driver)

	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	public static void waitForElementInvisibility(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
}
