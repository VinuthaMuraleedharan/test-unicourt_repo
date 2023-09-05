package com.unicourt.ui.search;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.unicourt.ui.base.BaseClass;
import com.unicourt.ui.pages.HomePage;
import com.unicourt.ui.pages.SearchResultPage;

import junit.framework.Assert;

public class CaseSearchTest extends BaseClass{
	public HomePage homePage;
	public SearchResultPage searchPage;
	
	/*@BeforeMethod
	public void setUp()
	{
		launchApp("Chrome");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}*/
	
	@Test
	public void caseSearch() throws Throwable
	{
		String text = "Google";
		homePage = new HomePage();
		homePage.performCaseSearch("Google");
		String actualHomePageTitle = homePage.getSearchPageTitle();
		System.out.println("Title of the page is "+actualHomePageTitle);
		Assert.assertEquals("Search | UniCourt", actualHomePageTitle);
	
		searchPage = new SearchResultPage();
		System.out.println("check search result page load");
		searchPage.verifySearchResultPageLoad();
		System.out.println("Search result page loaded");
		List<WebElement> resultList = searchPage.getSearchList();
		for(WebElement e: resultList)
		{
			System.out.println(e.getText());
			String caseTitle= e.getText().toLowerCase();
			//System.out.println("Case title is "+caseTitle);
			String searchText = text.toLowerCase();
			if(caseTitle.contains(searchText))
			{
				Assert.assertTrue(true);
				System.out.println("Search result "+e.getText().toString()+" contains : "+text);
			}
			else
			{
				Assert.assertTrue(false);
				System.out.println("Incorrect results");
			}
		/*searchPage.verifySearchResultPageLoad();
		System.out.println("Search result page loaded");
		searchPage.verifySearchResult("Google");
		System.out.println("Verified search results");*/
	
	
	}

}
}
