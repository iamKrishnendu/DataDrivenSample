package com.ibibo.ReusableLibrary;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtilities {


	public static final int PAGELOAD_MAX_TIMEOUT =50;

	public static String generateRandomNumber() throws Exception
	{
		String uniqueNum = "";
		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND); 
		uniqueNum = year + "_" + month + "_" + day + "_" + hour + "_" + min + "_" + sec;
		System.out.println("uniqueNum: "+ uniqueNum);
		return uniqueNum;
	}
	public static void getScreenshot(WebDriver driver, String strScreenshotPath)
	{
		try
		{
			System.out.println("Generic utils strScreenshotPath = " + strScreenshotPath);
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File finalDestination = new File(strScreenshotPath);
			FileUtils.copyFile(source, finalDestination);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void CreateResultsFolder(String basePath) throws Exception
	{
		System.out.println("basePath in CreateResultsFolder = "+basePath);
		String strResultsPath = basePath;
		File folder = new File(strResultsPath);
		if (folder.exists() == false)
		{	
			folder.mkdirs();
			Thread.sleep(5000);	            	
		}
		else{
			System.out.println("Test Folder already exist in:"+basePath);
		}
	}

	public static void selectDateFromDatePicker(WebDriver driver,String date,int monthCount){

		String dayOfMonth = date.split("-")[0];
		String  month = date.split("-")[1];
		String year = date.split("-")[2];
		String caption = month+" "+year;
		//By daypicker = By.xpath("//div[@class='DayPicker-Body']//div[@aria-disabled='false']");
		By daypickerValue = By.xpath("//div[@class='DayPicker-Body']//div[@aria-disabled='false']/div[contains(text(),'"+dayOfMonth+"')]");
		By nextButton = By.xpath("//span[contains(@class,'DayPicker-NavButton--next')]");
		By dateCaption = By.className("DayPicker-Caption");
		for(int count = 0; count<=monthCount;count++){

			if(driver.findElement(dateCaption).getAttribute("innerText").contentEquals(caption)){
				click(driver,driver.findElement(daypickerValue));
				System.out.println("Date selected");
				break;
			}
			click(driver,driver.findElement(nextButton));
		}

	}

	public static void click(WebDriver driver, WebElement elementToClick){
		try{
			awaitVisibilityOfElement(driver,elementToClick);	
			elementToClick.click();
			System.out.println("clicked successfull on: "+elementToClick);
		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}

	}
	public static void enterText(WebDriver driver, WebElement element,String valueToEnter){
		try{
			awaitVisibilityOfElement(driver,element);	
			element.click();
			element.sendKeys(valueToEnter);
			System.out.println("Text entered successfull on: "+valueToEnter);
		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}
	}
	public static void awaitVisibilityOfElement(WebDriver driver, WebElement element){
		element = new WebDriverWait(driver,PAGELOAD_MAX_TIMEOUT)
				.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void awaitVisibilityOfElements(WebDriver driver, List<WebElement> element){
		element = new WebDriverWait(driver,PAGELOAD_MAX_TIMEOUT)
				.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	public static void hardwait() throws InterruptedException{
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
			e.getCause();
			e.getMessage();
		}
		
	}
	
	public static void pressDownKeyandEnter(){
		try{
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		
	}
	
	public static void scrollIntoView(WebDriver driver, WebElement element){
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static void scrollDownToCertainPosition(WebDriver driver){
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,500);");
	}
/*	public static void main(String arg[]){
		String date = "16/july/2019";
		String day = date.split("/")[0];
		String month = date.split("/")[1];
		String yr = date.split("/")[2];
		String caption = month+" "+yr;
		System.out.println(day);
		System.out.println(month);
		System.out.println(yr);
		System.out.println(caption);
	}*/
}
