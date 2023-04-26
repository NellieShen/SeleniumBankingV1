package com.inetbanking.testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.AddCustomerPage;
import com.inetbanking.pageObject.LoginPage;

import org.openqa.selenium.JavascriptExecutor;

public class TC_AddCustomerTest_003 extends BaseClass
{

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(userName);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);

		Thread.sleep(3000);
		addcust.clickAddNewCustomer();
		Thread.sleep(1000);
		
		//By executing a java script
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are " + numberOfFrames);

		//By finding all the web elements using iframe tag
		List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
		System.out.println("The total number of iframes are " + iframeElements.size());
		System.out.println("iframeElements: " + iframeElements);
		
		List<WebElement> elements = driver.findElements(By.tagName("iframe"));
		String iframeId = "";
		for(WebElement element:elements) {
			if (element.getAttribute("id").length() > 0) {
				iframeId = element.getAttribute("id");
			}
			//System.out.println("element.getAttribute(\"id\"):"+element.getAttribute("id"));			
		}
		System.out.println("iframeId: " + iframeId);
		Thread.sleep(1000);
		//Switch to the frame
		driver.switchTo().frame(iframeId);

		Thread.sleep(2000);
		driver.findElement(By.id("dismiss-button")).click();
		//driver.findElement(By.xpath("//span[text()= 'Close']")).click();
		//driver.close();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		
		logger.info("providing customer details....");
		Thread.sleep(5000);
		addcust.custName(randomestring());
		Thread.sleep(1000);
		addcust.custgender("male");
		Thread.sleep(1000);
		//yyyy-mm--dd
		//addcust.custdob("10","15","1985");
		String mm = getRandomNumber(1,12);
		String dd = getRandomNumber(1,28);
		addcust.custdob("001985"+mm+dd);
		Thread.sleep(5000);
		addcust.custaddress("Canada");
		Thread.sleep(1000);
		addcust.custcity("Vancouver");
		Thread.sleep(1000);
		addcust.custstate("BC");
		Thread.sleep(1000);
		addcust.custpinno("5000074");
		Thread.sleep(1000);
		addcust.custtelephoneno("123456789");
		Thread.sleep(1000);
		
		String email=randomestring()+"@gmail.com";
		Thread.sleep(1000);
		addcust.custemailid(email);
		Thread.sleep(1000);
		addcust.custpassword("abcdef");
		Thread.sleep(1000);
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");
			
		}
		else
		{
			logger.info("test case failed....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
			
	}
	
	
}
