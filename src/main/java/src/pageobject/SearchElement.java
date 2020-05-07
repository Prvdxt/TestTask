package src.pageobject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import src.baseclasses.ObjectRepository;

public class SearchElement {
	WebDriver driver;

	public SearchElement(WebDriver driver) {
		this.driver = driver;

	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public String enterTextTobeSearched() throws Exception {

		System.out.println("Enter the text you want to search for?");
		String s = br.readLine();
		String s1 = s.trim();

		if (s.isEmpty()) {
			System.out.println("Please enter a value");
			enterTextTobeSearched();
		} else {
			WebElement searchBox = driver.findElement(By.cssSelector((ObjectRepository.SEARCHBOX)));
			searchBox.sendKeys(s1);
		}
		return s;
	}

	public String chooseTheFilter() throws Exception {

		List<String> options = getListOfTheOption();

		System.out.println("Choose one from option here" + options);

		String s2 = br.readLine();
		boolean found = false;
		for (String a : options) {
			if (a.equalsIgnoreCase(s2)) {
				s2 = a;
				Select sel = new Select(driver.findElement(By.cssSelector(ObjectRepository.SELECTBOX)));
				sel.selectByValue(a);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Choose the correct options from the list");
			chooseTheFilter();
		}
		return s2;

	}

	public List<String> getListOfTheOption() {
		List<String> sl = new ArrayList<String>();

		Select sel = new Select(driver.findElement(By.cssSelector(ObjectRepository.SELECTBOX)));
		List<WebElement> options = sel.getOptions();
		int k = options.size();
		String[] linkText = new String[k];
		int i = 0;
		for (WebElement a : options) {
			System.out.println(options);
			linkText[i] = a.getText();
			sl.add(linkText[i]);
			i++;
		}
		return sl;
	}

	public boolean verifytheResult(String valueEnteredinSearchBox, String valueSelectedFromDropDown) {

		TableData td = new TableData(driver);

		System.out.println("mapOfTableHeaderAndValues object contains data from the table in the UI");
		Map<String, List<WebElement>> mapOfTableHeaderAndValues = td.getTableData(driver);

		System.out.println("Keys of Map will hold the Table Header values");
		List<String> keys = new ArrayList<String>(mapOfTableHeaderAndValues.keySet());

		if (keys.contains(valueSelectedFromDropDown)) {

			System.out.println("Based on the header like ID Name etc, selected fetching the column details");
			List<WebElement> columnDataFromChosenTableHeader = mapOfTableHeaderAndValues.get(valueSelectedFromDropDown);
			System.out.println("Creating a  string list of the WebElements from the column");
			for (WebElement element : columnDataFromChosenTableHeader) {

				if (!(element.getText().toLowerCase()).contains(valueEnteredinSearchBox.toLowerCase())) {
					return false;
				}

			}
		}
		return true;

	}

	public boolean verifyTheBoxIsUnchecked() {

		WebElement ele = driver.findElement(By.cssSelector(ObjectRepository.CHECKBOX));
		if (ele.isSelected()) {
			return false;
		} else {
			System.out.println("The Match Case is not checked");
			return true;
		}

	}

	public void verifytabledataisdisplayedcorrectlyAfterSearch(String valueSelected, String textEntered)
			throws Exception {

		WebElement table = driver.findElement(By.xpath(ObjectRepository.TABLE));
		boolean tab = table.isDisplayed();

		if (tab) {
			verifyValuesForUnMatchedCase(valueSelected, textEntered);
		}

	}

	private void verifyValuesForUnMatchedCase(String valueSelected, String textEntered) throws Exception {

		List<WebElement> numberOfRows = driver.findElements(By.xpath("//table//tr"));
		int size = numberOfRows.size();
		System.out.println(size);
		if (size == 1) {
			WebElement banner = driver.findElement(By.xpath("//span[@id='table-resume']"));

			String ban = banner.getText();
			System.out.println(ban);
			if (ban.contains("0")) {
				System.out.println("String match was unsuccessful");
				WebElement clearFilter = driver.findElement(By.cssSelector("button[id='clear-button']"));
				clearFilter.click();
				System.out.print("Please select another filer and eneter other value");
				verifyTheBoxIsUnchecked();
				String newValueSelected = chooseTheFilter();
				String newText = enterTextTobeSearched();
				verifytheResult(newText, newValueSelected);
				verifytabledataisdisplayedcorrectlyAfterSearch( newValueSelected,newText);

			}
		} else {
			System.out.println("Verify that the based on selected value the result is displayed");
			List<WebElement> tableHeader = driver.findElements(By.xpath("//table//th"));
			for (WebElement a : tableHeader) {
				if (a.getText().equals(valueSelected)) {
					if (valueSelected.equals("Id")) {
						List<WebElement> l = driver.findElements(By.xpath("//table//tr//td[1]"));
						System.out.println("The Table Contains "+l.size()+" rows for the searched value");
						for (WebElement b : l) {
							String val = b.getText();
							if ((val.toLowerCase()).contains(textEntered.toLowerCase())) {
								System.out.println("The Table Contains the Searched ID value"+val+" for entered text "   +textEntered);

							}
						}
					}
					if (valueSelected.equals("Name")) {
						List<WebElement> l = driver.findElements(By.xpath("//table//tr//td[2]"));
						System.out.println("The Table Contains "+l.size()+" rows for the searched value");
						for (WebElement b : l) {
							String val = b.getText();
							if ((val.toLowerCase()).contains(textEntered.toLowerCase())) {
								System.out.println("The Table Contains the Searched Name value"+val+" for entered text "   +textEntered);

							}
						}
					}
					if (valueSelected.equals("Email")) {
						List<WebElement> l = driver.findElements(By.xpath("//table//tr//td[3]"));
						System.out.println("The Table Contains "+l.size()+" rows for the searched value");
						for (WebElement b : l) {
							String val = b.getText();
							if ((val.toLowerCase()).contains(textEntered.toLowerCase())) {
								System.out.println("The Table Contains the Searched Email value"+val+" for entered text "   +textEntered);
							}
						}
					}
					if (valueSelected.equals("City")) {
						List<WebElement> l = driver.findElements(By.xpath("//table//tr//td[4]"));
						System.out.println("The Table Contains "+l.size()+" rows for the searched value");
						for (WebElement b : l) {
							String val = b.getText();
							if ((val.toLowerCase()).contains(textEntered.toLowerCase())) {
								System.out.println("The Table Contains the Searched City value"+val+" for entered text "   +textEntered);
							}
						}
					}

				}
			}

		}
	}

	public boolean checkTheBox() {
		WebElement ele = driver.findElement(By.cssSelector(ObjectRepository.CHECKBOX));
		ele.click();
		if (ele.isEnabled()) {
			return true;
		} else {
			System.out.println("The Match Case is not checked");
			return false;
		}
	}

	public boolean verifytheResultBasedOnExactMatch(String valueEnteredinSearchBox, String valueSelectedFromDropDown) {

		TableData td = new TableData(driver);

		System.out.println("mapOfTableHeaderAndValues object contains data from the table in the UI");
		Map<String, List<WebElement>> mapOfTableHeaderAndValues = td.getTableData(driver);

		System.out.println("Keys of Map will hold the Table Header values");
		List<String> keys = new ArrayList<String>(mapOfTableHeaderAndValues.keySet());

		if (keys.contains(valueSelectedFromDropDown)) {

			System.out.println("Based on the header like ID Name etc, selected fetching the column details");
			List<WebElement> columnDataFromChosenTableHeader = mapOfTableHeaderAndValues.get(valueSelectedFromDropDown);

			System.out.println("Creating a  string list of the WebElements from the column");
			for (WebElement element : columnDataFromChosenTableHeader) {

				if (!(element.getText()).contains(valueEnteredinSearchBox)) {
					return false;
				}

			}

		}
		return true;

	}

	public void verifytabledataisdisplayedcorrectlyAfterSearchForMatchedCase(String valueSelected, String textEntered)
			throws Exception {

		WebElement table = driver.findElement(By.xpath(ObjectRepository.TABLE));
		boolean tab = table.isDisplayed();

		if (tab) {
			verifyValuesForMatchedCase(valueSelected, textEntered);
		}

	}

	private void verifyValuesForMatchedCase(String valueSelected, String textEntered) throws Exception {

		List<WebElement> numberOfRows = driver.findElements(By.xpath("//table//tr"));
		int size = numberOfRows.size();
		if (size == 1) {
			WebElement banner = driver.findElement(By.xpath("//span[@id='table-resume']"));

			String ban = banner.getText();
			System.out.println(ban);
			if (ban.contains("0")) {
				System.out.println("String match was unsuccessful");
				WebElement clearFilter = driver.findElement(By.cssSelector("button[id='clear-button']"));
				clearFilter.click();
				System.out.print("Please select another filer and eneter other value");
				verifyTheBoxIsUnchecked();
				String newValueSelected = chooseTheFilter();
				String newText = enterTextTobeSearched();
				verifytheResult(newText, newValueSelected);
				System.out.println(newText + "-" + newValueSelected);
				verifytabledataisdisplayedcorrectlyAfterSearchForMatchedCase(newValueSelected,newText);
		

			}
		} else {
			System.out.println("Verify that the based on selected value the result is displayed");
			TableData td = new TableData(driver);
			List<String> tableHeader = td.getTheKeySet(driver);
			for (String a : tableHeader) {
				
				if (a.equals(valueSelected)) {
					if (valueSelected.equals("Id")) {
						List<WebElement> l = driver.findElements(By.xpath("//table//tr//td[1]"));
						System.out.println("The Table Contains "+l.size()+" rows for the searched value");
						for (WebElement b : l) {
							String val = b.getText();
							if (val.contains(textEntered)) {
								System.out.println("The Table Contains the Searched ID value"+val+" for entered text "   +textEntered);

							}
						}
					}
					if (valueSelected.equals("Name")) {
						
						List<WebElement> l = driver.findElements(By.xpath("//table//tr//td[2]"));
						System.out.println("The Table Contains "+l.size()+" rows for the searched value");
						for (WebElement b : l) {
							String val = b.getText();
							if (val.contains(textEntered)) {
								System.out.println("The Table Contains the Searched Name value"+val+" for entered text "   +textEntered);

							}
						}
					}
					if (valueSelected.equals("Email")) {
						List<WebElement> l = driver.findElements(By.xpath("//table//tr//td[3]"));
						System.out.println("The Table Contains "+l.size()+" rows for the searched value");
						for (WebElement b : l) {
							String val = b.getText();
							if (val.contains(textEntered)) {
								System.out.println("The Table Contains the Searched Email value"+val+" for entered text "   +textEntered);
							}
						}
					}
					if (valueSelected.equals("City")) {
						List<WebElement> l = driver.findElements(By.xpath("//table//tr//td[4]"));
						System.out.println("The Table Contains "+l.size()+" rows for the searched value");
						for (WebElement b : l) {
							String val = b.getText();
							if (val.contains(textEntered)) {
								System.out.println("The Table Contains the Searched City value"+val+" for entered text "   +textEntered);
							}
						}
					}

				}
			}

		}
	}

}
