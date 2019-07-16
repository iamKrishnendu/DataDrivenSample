package com.ibibo.ReusableLibrary;

import org.openqa.selenium.WebDriver;

import com.ibibo.TestBase.TestBase;

public class ExcelParser {
	
	
	public static void main(String arg[]) throws Exception{
		
		BrowserSelection browser = BrowserSelection.getinstanceOfBrowserSelection();
		WebDriver driver = browser.getDriver();
		//driver.get("http://www.google.com");
		TestBase testBase = new TestBase("Test", "testDes");
		Reporting reporting = null;
		 testBase.setReport(driver);
		 reporting = testBase.getReport();
		 reporting.passTest("Test", "Test desc", "test");
		 reporting.endReport();
		
	}

}
