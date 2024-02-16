package com.entrata.automation.entrata.base;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EntrataTest {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() // will Executed first for every testcase
	
	{
		// to select the browser
		String browserName ="chrome";
		if(browserName.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browserName.equals("firefox")) {
			driver= new FirefoxDriver();
		}
		else if(browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("https://www.entrata.com/");
			driver.findElement(By.xpath("//*[@id=\"rcc-confirm-button\"]")).click();	
	}
	
	//to quit the browser
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	@Test(priority=1)
	public void propertyManagerLogin() throws InterruptedException
	{
	
		
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.linkText("Property Manager Login")).click();
		driver.findElement(By.xpath("//*[@id=\"entrata-username\"]")).sendKeys("Vinayak");
		driver.findElement(By.xpath("//*[@id=\"entrata-password\"]")).sendKeys("******");
	    Thread.sleep(30);
		
	    // Asserting to find submit button
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"fsm_request_demo\"]/ul/li[3]/button")).isDisplayed());
		Assert.assertTrue(true);
	}
	
	@Test(priority=2)
	public void mouseHover() throws InterruptedException
	{
		
		//Doing hover over to an element
		WebElement e = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/div[1]/div/div/div[2]/div[2]/div[1]"));
		  Actions act= new Actions(driver);
	     act.moveToElement(e).perform();
	     Thread.sleep(30);
	     driver.findElement(By.linkText("Multifamily")).click();
	     Thread.sleep(30);
	    
	     
	}

	@Test(priority=3)
	public void navigationToOtherTab() throws InterruptedException
	{
	    // for navigation purpose it has been written	
		String oldTab= driver.getWindowHandle();
		driver.findElement(By.linkText("Base Camp")).click();
		ArrayList<String> newTab= new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);// change focus to new tab
		driver.switchTo().window(newTab.get(0));// new tab
		Thread.sleep(2000);
		driver.switchTo().window(oldTab);// switching back to old tab
}
}
