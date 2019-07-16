package com.ibibo.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ibibo.ReusableLibrary.GenericUtilities;

public class SearchFlightPageObject {

	public static List<String>flightprice = null;
	@FindBy(css="span[class='curPointFlt switchAct']")
	public WebElement typeOfJourney;
	@FindBy(xpath="//input[@placeholder='Departure']")
	public WebElement departureDatePicker;
	@FindBy(xpath="//ul[contains(@class,’autoSuggestBoxList’)]")
	public WebElement autoSuggestionBox;
	@FindBy(xpath="//div[contains(@class,'card fl')]")
	public List<WebElement> flightContentPage;
	@FindBy(xpath="//div[contains(@class,'fltPrice')]//following::span[contains(@class,'fr fb')]")
	public List<WebElement>flatPrice;
	@FindBy(css="li[id='sortByPriceOnwli'] a")
	public WebElement priceSortButton;
	
	
	public void flightPriceList(WebDriver driver){
		flightprice = new ArrayList<String>();
		GenericUtilities.scrollDownToCertainPosition(driver);
		for(int count=1;count<=flatPrice.size();count++){
				
			 GenericUtilities.scrollIntoView(driver, driver.findElement(By.xpath("(//div[contains(@class,'fltPrice')]//following::span[contains(@class,'fr fb')])["+count+"]")));
			  String flightPrice = driver.findElement(By.xpath("(//div[contains(@class,'fltPrice')]//following::span[contains(@class,'fr fb')])["+count+"]")).getAttribute("innerText");
			  flightprice.add(flightPrice);
		}
		
		}
	
	public void getFlightPriceandPrint(){
		try{
			for(int count =0;count<=flightprice.size();count++){
				String priceValue = flightprice.get(count);
				System.out.println("Price: "+count+"="+priceValue);
	}
		}catch(Exception e){
			System.out.println("Exception occured during parsing List values");
		}
		
	}
}
