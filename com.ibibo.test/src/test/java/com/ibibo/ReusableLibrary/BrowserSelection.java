package com.ibibo.ReusableLibrary;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSelection {

	GlobalProperties properties = new GlobalProperties();

	private WebDriver driver;

	private static BrowserSelection instanceOfBrowserSelection = null;

	@SuppressWarnings("deprecation")
	private BrowserSelection(){
		String browserName = properties.readFile().getProperty("BROWSER_NAME");
		String applicationUrl = properties.readFile().getProperty("URL");
		String imageDisableFlag = properties.readFile().getProperty("IMAGE_DISABLE_FLAG");
		BrowserOptions browserOptions = BrowserOptions.valueOf(browserName);
		DesiredCapabilities capabilities = null;
		ChromeOptions option =null;
		switch(browserOptions){
		case CHROME:
			if(imageDisableFlag.equalsIgnoreCase("Y")){
			 option = new ChromeOptions();
			 option.addExtensions(new File(System.getProperty("user.dir")+"/Extension/Block-image_v1.0.crx"));
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(option);
			}else{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case IE:
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("ignoreZoomSetting", true);
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver(capabilities);
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().to(applicationUrl);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	public static BrowserSelection getinstanceOfBrowserSelection(){

		if(instanceOfBrowserSelection == null)

			instanceOfBrowserSelection = new BrowserSelection();
		return instanceOfBrowserSelection;
	}

	public WebDriver getDriver(){
		return driver;
	}

}
