package com.ibibo.TestSuite;

import java.util.Collections;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ibibo.PageObjects.HomePageObject;
import com.ibibo.PageObjects.SearchFlightPageObject;
import com.ibibo.ReusableLibrary.BrowserSelection;
import com.ibibo.ReusableLibrary.GenericUtilities;
import com.ibibo.ReusableLibrary.ParseJSON;
import com.ibibo.ReusableLibrary.Reporting;
import com.ibibo.TestBase.TestBase;

public class TS_ibibo_validationOfHomePageElements_SearchFlights_PriceOrder extends ParseJSON {

	BrowserSelection browserSelection = null;
	TestBase testBase = null;
	Reporting reporting = null;
	String scriptID = this.getClass().getSimpleName();
	String scriptDescription = "Verify the basic elements of Homepage and Search flights based on decending price order";
	String methodName = "TS_ibibo_validationOfHomePageElements_SearchFlights_PriceOrder";
	WebDriver driver = null;
	public Map<String,String> testData = null;
	SoftAssert assertion = null;

	@BeforeSuite
	public void startTest(){
		System.out.println("Running Before Method");
		System.out.println("Testing");
		System.out.println("strScriptId = " + scriptID);
		ParseJSON.scriptName = scriptID;
		testBase = new TestBase(scriptID,scriptDescription);
	}

	@BeforeTest
	public void luanchApplication(){
		try{
			browserSelection = BrowserSelection.getinstanceOfBrowserSelection();
			driver = browserSelection.getDriver();
			testBase.setReport(driver);
			reporting = testBase.getReport();

		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}

	}

	@Test(priority=0)
	public void validatePageElementsOfHomePage(){
		try{

			HomePageObject homepageObject = PageFactory.initElements(driver, HomePageObject.class);


			assertion = new SoftAssert();
			String pageHeader = homepageObject.homePageHeader.getAttribute("innerText");
			assertion.assertEquals(pageHeader, "Flight Ticket Booking", "Page header value validated successfully");
			if(homepageObject.inputSource.isDisplayed() && homepageObject.inputDestination.isDisplayed()){
				assertion.assertTrue(true, "Source and Destination textboxes are visible");
			}
			if(homepageObject.buttonSearch.isDisplayed()){
				assertion.assertTrue(true, "Search button is visble");
			}
			if(!((homepageObject.pageMenuOptions.size())<=0) ){
				assertion.assertTrue(true, "All links on the page menu bar are loaded and visible properly");
			}
			reporting.passTest("validatePageElementsOfHomePage", "Home page validation", "Homepage elements");
		}catch(Exception e){
			reporting.failTest("Failed to validate homepage elements", "validatePageElementsOfHomePage", "pageelements");
			assertion.fail();
		}


	}

	@Test(priority=1,dataProvider="testDataInventory")
	public void searchFlight(Map<String, String> testData){
		try{

			HomePageObject homepageObject = PageFactory.initElements(driver, HomePageObject.class);
			SearchFlightPageObject searchFlightObject = PageFactory.initElements(driver, SearchFlightPageObject.class);
			String classPropertyOfJourneyType = searchFlightObject.typeOfJourney.getAttribute("class");
			String  journeyTypeFromPage = searchFlightObject.typeOfJourney.getAttribute("innerText");
			String sourceLocation = testData.get("SOURCE");
			String destinationLocation = testData.get("DESTINATION");
			String journeyType = testData.get("JOURNEY TYPE");
			String journeyDate = testData.get("DATE");
			int monthCount = Integer.parseInt( testData.get("MONTH COUNT"));
			assertion = new SoftAssert();

			if(classPropertyOfJourneyType.contains("switchAct") && journeyTypeFromPage.equalsIgnoreCase(journeyType)){
				assertion.assertTrue(true, "Journey type selected as "+journeyType);
				reporting.passTest("searchFlight", "To check the journey type", "journey Type "+journeyType);
				GenericUtilities.enterText(driver, homepageObject.inputSource, sourceLocation);
				GenericUtilities.hardwait();
				if( homepageObject.inputSource.getAttribute("aria-expanded").equalsIgnoreCase("true")){
					GenericUtilities.pressDownKeyandEnter();
				}
				GenericUtilities.enterText(driver, homepageObject.inputDestination, destinationLocation);
				GenericUtilities.hardwait();
				if( homepageObject.inputDestination.getAttribute("aria-expanded").equalsIgnoreCase("true")){
					GenericUtilities.pressDownKeyandEnter();
				}
				GenericUtilities.click(driver, searchFlightObject.departureDatePicker);
				GenericUtilities.selectDateFromDatePicker(driver, journeyDate, monthCount);
				GenericUtilities.click(driver, homepageObject.buttonSearch);
				GenericUtilities.awaitVisibilityOfElements(driver, searchFlightObject.flightContentPage);
			}

		}catch(Exception e){
			reporting.failTest("Failed to validate homepage elements", "validatePageElementsOfHomePage", "pageelements");
			assertion.fail();
		}
	}
	@Test(priority=3)
	public void searchFlightInCostDecendingOrder(){

		assertion = new SoftAssert();
		try{
			SearchFlightPageObject searchFlightObject = PageFactory.initElements(driver, SearchFlightPageObject.class);
			//searchFlightObject.flightPriceList(driver);
			assertion.assertTrue(true, "Price list is in ascending order");
			reporting.passTest("searchFlightInCostDecendingOrder", "Flight price in Ascending order", "priceAscendingOrder");
			GenericUtilities.click(driver, searchFlightObject.priceSortButton);
			GenericUtilities.hardwait();
			searchFlightObject.flightPriceList(driver);
			searchFlightObject.getFlightPriceandPrint();
			assertion.assertTrue(true, "Price list is in descending order");
			reporting.passTest("searchFlightInCostDecendingOrder", "Flight price in Descending order", "DescendingOrder");
		}catch(Exception e){
			reporting.failTest("Failed to validateFlight price values", "validatePageElementsOfHomePage", "pageelements");
			assertion.fail();
		}
	}

	@AfterTest
	public void endTest(){
		driver.close();
		driver.quit();
		reporting.endReport();
	}

}
