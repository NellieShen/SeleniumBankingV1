package com.inetbanking.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	/**
	 * FindBy
	 * Used to mark a field on a Page Object to indicate an alternative mechanism 
	 * for locating the element or a list of elements. 
	 * Used in conjunction with PageFactory this allows users to quickly and easily create PageObjects.
	 * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
	 */
	/**
	 * CacheLookup
	 * https://www.toolsqa.com/selenium-webdriver/cachelookup-in-pageobjectmodel/
	 */
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	//driver.findElement(By.name(“Element NAME”));

	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	//@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@FindBy(how = How.LINK_TEXT, using ="Log out")
	@CacheLookup
	WebElement lnkLogout;
	
	public void setUserName(String userName) {
		txtUserName.sendKeys(userName);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickSubmit() {
		btnLogin.click();
	}
	
	//<a href="Logout.php">Log out</a>
	public void clickLogout() {
		lnkLogout.click();
	}
}
