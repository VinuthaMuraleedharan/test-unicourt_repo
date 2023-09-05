package com.unicourt.ui.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public class BaseClass {
	
	public static WebDriver driver;
	public static Properties prop;
	
	@BeforeSuite
	public void readConfig()
	{
		 prop = new Properties();
		
		try {
			 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/Configuration/Config.properties");
			 prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	

	@BeforeMethod
	public static void launchApp()
	{
		String br=prop.getProperty("browser");
		if(br.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromepath"));
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
		}
	}
	
	
	public static void waitForPageLoad(WebDriver ldriver, WebElement ele, Duration timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	
}
