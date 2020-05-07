package src.pageobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import src.baseclasses.BaseClass;
import src.baseclasses.DriverClass;
import src.baseclasses.ObjectRepository;

public class UICheck {
	WebDriver driver;

	public UICheck(WebDriver driver) {
		this.driver = driver;

	}

	public boolean verifyUI() {

		System.out.println("The Browser Opens");
		DriverClass.openBrowser();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		List<Boolean> l1= new ArrayList<Boolean>();
		

		System.out.println("Verify PageTitle is displayed");
		WebElement pageHeader = BaseClass.getElementByXpath(driver, ObjectRepository.PAGEHEADING);
		boolean val1=pageHeader.isDisplayed();
		l1.add(val1);

		System.out.println("Verify SearchBox is displayed");
		WebElement searchBox = BaseClass.getElementByCssSelector(driver, ObjectRepository.SEARCHBOX);
		boolean val2=searchBox.isDisplayed();
		l1.add(val2);
		
		System.out.println("Verify SelectBox is displayed");
		WebElement selectBox = BaseClass.getElementByCssSelector(driver, ObjectRepository.SELECTBOX);
		boolean val3=selectBox.isDisplayed();
		l1.add(val3);
		
		System.out.println("Verify Checkbox is displayed");
		WebElement checkBox = BaseClass.getElementByCssSelector(driver, ObjectRepository.CHECKBOX);
		boolean val4=checkBox.isDisplayed();
		l1.add(val4);
		
		System.out.println("Verify headerId is displayed");
		WebElement headerId = BaseClass.getElementByXpath(driver, ObjectRepository.TABLEHEADERID);
		boolean val6=headerId.isDisplayed();
		l1.add(val6);
		
		System.out.println("Verify headerName is displayed");
		WebElement headerName = BaseClass.getElementByXpath(driver, ObjectRepository.TABLEHEADERNAME);
		boolean val7=headerName.isDisplayed();l1.add(val7);
		
		System.out.println("Verify headerEmail is displayed");
		WebElement headerEmail = BaseClass.getElementByXpath(driver, ObjectRepository.TABLEHEADEREMAIL);
		boolean val8=headerEmail.isDisplayed();l1.add(val8);
		
		System.out.println("Verify headerCity is displayed");
		WebElement headerCity = BaseClass.getElementByXpath(driver, ObjectRepository.TABLEHEADERCITY);
		boolean val9=headerCity.isDisplayed();l1.add(val9);
		
		
		System.out.println("Verify lowerHeader is displayed");
		WebElement lowerHeader = BaseClass.getElementByCssSelector(driver, ObjectRepository.LOWERHEADER);
		boolean val10=lowerHeader.isDisplayed();l1.add(val10);
		
		System.out.println("Verify Table Data is displayed");
		boolean value=verifyTableDataIsDisplayed(driver);
		l1.add(value);
		
		return !(l1.contains(false));


		
		
	}

	private boolean verifyTableDataIsDisplayed(WebDriver driver) {
		TableData td=new TableData(driver);
		Map<String, List<WebElement>> mapView=td.getTableData(driver);
		List<String> keys= new ArrayList<String>(mapView.keySet());
			boolean val=true;
		System.out.print(keys);
		
		int k=keys.size();
		for(int i=0;i<k;i++)
		{
			List<WebElement> list= mapView.get(keys.get(i));
			if(list.isEmpty())
			{
				val=false;
			}
			
		}
		return val;
			}
	}


