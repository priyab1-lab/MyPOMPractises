package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.opencart.qa.utils.AppConstants;

import seleniumSessions.ElementUtil1;

public class LoginPage {
	
	//1. initi driver and elementUtil
	private WebDriver driver;
	private ElementUtil1 eleUtil;
	
	//2. Page class constructor...
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil1(driver);
	}
	
	//Private by locators
	private final By emailId = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");
	
	/// 3. public page actions/methods
	
	public String getLoginPageTitle() {
	String actTitle=eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIMEOUT);
	System.out.println("Login page title is: " +actTitle);
	return actTitle;
//	Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	public String getLoginPageURL() {
		String actUrl=eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_URL, AppConstants.SHORT_TIMEOUT);
		System.out.println("Login page URL is: " +actUrl);
		return actUrl;
//		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		}
	
	public boolean isForgotPwdLinkExits() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIMEOUT).isDisplayed();
	}
	
	public HomePage doLogin(String username, String pwd) {
		System.out.println("App credentials: "+username + ":" +pwd);
		eleUtil.doSendKeys(emailId, username, AppConstants.MEDIUM_TIMEOUT);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
	return new HomePage(driver);
}
}
