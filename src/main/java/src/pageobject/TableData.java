package src.pageobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import src.baseclasses.ObjectRepository;

public class TableData {
	 
	WebDriver driver;
	
	public TableData(WebDriver driver) {
		this.driver=driver;
		
	}

	public Map<String,List<WebElement>> getTableData(WebDriver driver)
	{
			
		Map<String,List<WebElement>> listValues = new HashMap<String,List<WebElement>>();
		
		List<WebElement> rows= driver.findElements(By.xpath(ObjectRepository.TABLEROWS));
		int numberOfRows= rows.size();
		
		List<WebElement> tableHeaders= driver.findElements(By.xpath("//table//tr//th"));
		int numOfColumns=tableHeaders.size();
		
		for(int i=1;i<=numOfColumns;i++)
		{
			String key= driver.findElement(By.xpath("//th["+i+"]")).getText();
			List<WebElement> colList = new ArrayList<WebElement>();
			for(int j=1;j<=numberOfRows;j++) {			
				
				colList.add(driver.findElement(By.xpath("//tbody//tr["+j+"]//td["+i+"]")));
			
		}
			
		listValues.put(key, colList);
			System.out.println(listValues.toString());
		}
		
		return listValues;
		
	}
	
	public List<String> getTheKeySet( WebDriver driver){


		System.out.println("mapOfTableHeaderAndValues object contains data from the table in the UI");
		Map<String, List<WebElement>> mapOfTableHeaderAndValues = getTableData(driver);

		System.out.println("Keys of Map will hold the Table Header values");
		List<String> keys = new ArrayList<String>(mapOfTableHeaderAndValues.keySet());
		return keys;
	}
	

	
		
	}
	
