package com.inetbanking.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.inetbanking.utilities.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseClass {
	/**
	 * UserID: mngr494950
	   Password: bYsEgYj
	 */
	ReadConfig readconfig = new ReadConfig();
	
//	public String baseURL = "https://demo.guru99.com/v3/index.php";
//	public String userName = "mngr494950";
//	public String password = "bYsEgYj";
//	public static WebDriver driver;
	public String baseURL = readconfig.getApplicationURL();
	public String userName = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {

		logger = Logger.getLogger(this.getClass().getName());
		PropertyConfigurator.configure("log4j.properties");
		
		if (br.equals("chrome")) {
			//System.getProperty("usre.dir") --> Get project directory
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver=new ChromeDriver();	
			
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver=new FirefoxDriver();	
		}
		driver.get(baseURL);
		driver.manage().window().maximize();
		logger.info("URL is opened");		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	public String getRandomNumber(int min, int max) {
		int randomNumber = (int) ((Math.random() * (max - min)) + min);
	    return String.format("%02d", randomNumber) ;
	}
}
