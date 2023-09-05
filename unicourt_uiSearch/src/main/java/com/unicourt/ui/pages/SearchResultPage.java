package com.unicourt.ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.unicourt.ui.base.BaseClass;

public class SearchResultPage extends BaseClass{
	
	@FindBy(xpath="//*[text()='Jurisdiction ']")
	WebElement checkSearchResultPageLoad;
	
	@FindBy(xpath="//*[@class='case-title']")
	List<WebElement> searchResultList;
	
	public SearchResultPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void verifySearchResultPageLoad()
	{
		BaseClass.waitForPageLoad(driver, checkSearchResultPageLoad, Duration.ofSeconds(1000));
	}
	
	public List<WebElement> getSearchList()
	{
		return searchResultList;
		
	}
	public void verifySearchResult(String text)
	{
		int count=1;
		for(WebElement e: searchResultList)
		{
			
			String caseTitle= e.getText().toLowerCase();
			String searchText = text.toLowerCase();
			if(caseTitle.contains(searchText))
			{
				System.out.println("Search result "+count+" contains : "+text);
			}
			else
			{
				System.out.println("Incorrect results");
			}
			count++;
			
	}
	}

}
