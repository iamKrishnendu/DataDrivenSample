package com.ibibo.ReusableLibrary;

import java.io.File;

import org.openqa.selenium.WebDriver;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class Reporting {

	private ExtentReports extent;
	private ExtentTest test;
	private String strTestScriptId;
	private String strDescription;
	private String strScreenshotsFolder = "";
	private String strScreenshotPath = ""; 
	private WebDriver driver = null;

	public Reporting(WebDriver browser, String testcaseName, String description, String instanceId, String testResultsFolder) throws Exception
	{
		driver = browser;
		strTestScriptId = testcaseName;
		strDescription =  description;
		strScreenshotsFolder = testResultsFolder + "\\screenshots";
		extent = new ExtentReports (testResultsFolder + "\\" + instanceId +".html", true);

		extent
		.addSystemInfo("Project", "GoIbibo Test Project")
		.addSystemInfo("Test Suite", "Basic Functionality Validation")
		.addSystemInfo("Test Type", "Automated Testing");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\Configuration\\" + "extent-config.xml"));
	}

	public void startTest()
	{
		test = extent.startTest(strTestScriptId, strDescription);
	}

	public void passTest(String strStepName, String strDetails, String strScreenshotName) 
	{

		try {
			strScreenshotPath = strScreenshotsFolder + "\\" + strScreenshotName +GenericUtilities.generateRandomNumber()+".png";
			GenericUtilities.getScreenshot(driver, strScreenshotPath);
			String htmlRef = test.addScreenCapture(strScreenshotPath);
			test.log(LogStatus.PASS, strStepName, strDetails + htmlRef);
		}

		catch(Exception e) {

			e.printStackTrace();
		}
	}

	public void failTest(String strStepName, String strDetails, String strScreenshotName)
	{

		try {
			strScreenshotPath = strScreenshotsFolder + "\\" + strScreenshotName + GenericUtilities.generateRandomNumber()+ ".png";
			GenericUtilities.getScreenshot(driver, strScreenshotPath);
			String htmlRef = test.addScreenCapture(strScreenshotPath);
			test.log(LogStatus.FAIL, strStepName, strDetails + htmlRef);
			extent.endTest(test);
			extent.flush();

		}

		catch(Exception e) {

			e.printStackTrace();
		}
	}

	public void skipTest(String strStepName, String strDetails)
	{
		test.log(LogStatus.SKIP, strStepName, strDetails);
	}

	public void endTest()
	{
		extent.endTest(test);
	}

	public void endReport() 
	{
		extent.flush();
	}

}
