package src.com.test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import src.baseclasses.DriverClass;
import src.pageobject.SearchElement;

public class VerifyElementIsSearchedWithMatchCase extends DriverClass {
	@Test(priority = 1, enabled = true)

	public void searchElementTest() throws Exception {


		System.out.println("Open the Chrome Browser");
		DriverClass.openBrowser();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		SearchElement callingFunction= new SearchElement(driver);
		
		System.out.println("Verification of the checkbox for match case is checked");		
		boolean checker= callingFunction.checkTheBox();		
		Assert.assertEquals(true, checker, "The Checkbox is checked");

		
		System.out.println("Fetching List of values from dropown and selecting a value from the user ");
		String valueSelected = callingFunction.chooseTheFilter();
		System.out.println(valueSelected);
		
		
		System.out.println("Enter element in text box");
		String textEntered = callingFunction.enterTextTobeSearched();
		Assert.assertNotEquals(textEntered, null);		
		

		System.out.println("Searching the text entered is from the table \");");
		boolean check = callingFunction.verifytheResultBasedOnExactMatch(textEntered, valueSelected);
		Assert.assertEquals(true, check, "Verify that the search is working Fine");
		

		callingFunction.verifytabledataisdisplayedcorrectlyAfterSearchForMatchedCase(valueSelected,textEntered);
		
		
		
	}

}
