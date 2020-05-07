package src.baseclasses;

public class ObjectRepository {



	public static String CHROMEDRIVERPATH = System.getProperty("user.dir") + "//driver//chromedriver.exe";
	

	public static String URL = System.getProperty("user.dir") + "//project//index.html";
	public static String destFile =  "C:\\";

	/******** HomePage ********/

	public static String PAGEHEADING = "//h3[contains(text(),'Customers')]";
	public static String SEARCHBOX = "input[id='search-input']";
	public static String SELECTBOX = "select[id='search-column']";
	public static String CHECKBOX = "input[id='match-case']";
	public static String LABEL = "//label//input//following-sibling::text()";
	public static String TABLEHEADERID = "//th[text()='Id']";
	public static String TABLEHEADEREMAIL = "//th[text()='Name']";
	public static String TABLEHEADERCITY = "//th[text()='Email']";
	public static String TABLEHEADERNAME = "//th[text()='City']";
	public static String LOWERHEADER = "span[id='table-resume']";
	public static String TABLEROWS = "//tbody//tr";
	public static String TABLE ="//table" ;
	

}
