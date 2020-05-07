package src.com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import src.baseclasses.DriverClass;
import src.pageobject.UICheck;
/**
 * This method is used to test the UI of Page is displayed correctly.
 * @author PoorvaDixit
 *
 */
public class UICheckTest extends DriverClass {
	@Test(priority=3,enabled=true)
	public void checkUI() {
		
	System.out.println("Verification of all UI elements are displayed correctly on the UI")	;
		UICheck uiCheck = new UICheck(driver);
		boolean val= uiCheck.verifyUI(); 
		Assert.assertEquals(val, true);

		
}
}
