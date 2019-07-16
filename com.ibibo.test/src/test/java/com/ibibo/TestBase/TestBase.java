package com.ibibo.TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ibibo.PageObjects.HomePageObject;
import com.ibibo.PageObjects.SearchFlightPageObject;
import com.ibibo.ReusableLibrary.GenericUtilities;
import com.ibibo.ReusableLibrary.GlobalProperties;
import com.ibibo.ReusableLibrary.Reporting;




public class TestBase {
	
	WebDriver driver = null;
	private String scriptiD = null;
	private String scriptInstanceID = null;
	private String scriptdescription = null;
	private String testResultBaseFolder = null;
	private String testResultFolder = null;
	private Reporting reporting = null;
	
	public TestBase(String scriptID, String description){
		scriptiD = scriptID;
		scriptdescription = description;
		
		try{
			
			GlobalProperties properties = new GlobalProperties();
			testResultBaseFolder = properties.readFile().getProperty("RESULT_FOLDER_PATH");
			scriptInstanceID = GenericUtilities.generateRandomNumber();
			testResultFolder = testResultBaseFolder + "\\" + scriptID + "_" + scriptInstanceID;
			System.out.println("Test Result Folder = " + testResultFolder);
			System.out.println("Script Instance ID = " + scriptInstanceID);
			GenericUtilities.CreateResultsFolder(testResultFolder);
			setReport(driver);
			}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}
	}
	public void setReport(WebDriver driver) throws Exception{
		reporting =  new Reporting(driver, scriptiD, scriptdescription, scriptInstanceID, testResultFolder);
		reporting.startTest();
	}
	
	public Reporting getReport(){
		return reporting;
	}
}
