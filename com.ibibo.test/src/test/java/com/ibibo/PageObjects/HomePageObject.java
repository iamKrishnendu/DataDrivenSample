package com.ibibo.PageObjects;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class HomePageObject {

	@FindBy(xpath="//h1[contains(text(),'Flight Ticket Booking')]")
	public WebElement homePageHeader;
	@FindBy(id="gosuggest_inputSrc")
	public WebElement inputSource;
	@FindBy(id="gosuggest_inputDest")
	public WebElement inputDestination;
	@FindBy(id="gi_search_btn")
	public WebElement buttonSearch;
	@FindBy(xpath="//div[@id='header']//following::ul[@class='mainLinks']/li")
	public List<WebElement>pageMenuOptions;
}
