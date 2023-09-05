package com.unicourt.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.unicourt.ui.base.BaseClass;

public class HomePage extends BaseClass{
	
	@FindBy(id="query")
	WebElement searchBox;
	
	@FindBy(xpath="//*[@type='submit']")
	WebElement searchBtn;
	
	public HomePage()
	{
		//this.driver=ldriver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getSearchPageTitle()
	{
		String searchPageTitle = driver.getTitle();
		return searchPageTitle;
	}
	
	public SearchResultPage performCaseSearch(String text) throws Throwable
	{
		System.out.println("search criteria is "+text);
		searchBox.sendKeys(text);
		searchBtn.click();
		return new SearchResultPage();
	}
	
	

}
