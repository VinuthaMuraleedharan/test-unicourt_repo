package com.unicourt.ui.search;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.unicourt.ui.base.BaseClass;

import junit.framework.Assert;

public class SearchCaseTest{
	
	public static WebDriver driver;
	
	@Test
	public void searchCourtCase()
	{
	int count=0;
	
	//launching the app
	System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://unicourt.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	System.out.println("Launched the url successfully");
	
	//Verify the title of homepage
	String title=driver.getTitle();
	System.out.println("Title of the page is "+title);
	Assert.assertEquals("Your Single Source for Legal Data & Analytics | UniCourt", title);
	
	//Perform search with keyword Google
	driver.findElement(By.id("query")).sendKeys("Google");
	driver.findElement(By.xpath("//*[@type='submit']")).click();
	
	//Wait for the search result page load
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement ele =driver.findElement(By.xpath("//*[text()='Jurisdiction ']"));
	wait.until(ExpectedConditions.visibilityOf(ele));
	
	//Get the list of search results
	List<WebElement> searchList = driver.findElements(By.xpath("//*[@class='case-title']"));
	System.out.println("No of search results are "+searchList.size());
	
	//Iterate over the search result and verify that the search result contains Google.
	for(WebElement e: searchList)
	{
		while(count<5)
		{
		String caseTitle= e.getText().toLowerCase();
		if(caseTitle.contains("google"))
		{
			System.out.println("Search result "+(count+1)+" contains : google");
		}
		else
		{
			System.out.println("Incorrect results");
		}
		count++;
		}
	}
	
	driver.quit();
	}

}
