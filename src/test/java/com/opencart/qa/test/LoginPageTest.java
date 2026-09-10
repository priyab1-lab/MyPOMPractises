package com.opencart.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.qa.basetest.BaseTest;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.utils.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginPageTitleTest() {
		Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageURLTest() {
		Assert.assertTrue(loginPage.getLoginPageURL().contains(AppConstants.LOGIN_PAGE_URL));
	}
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExits());
	}
	
	@Test(priority=Integer.MAX_VALUE)
	public void loginTest() {
		HomePage homePage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String actHomePageTitle=homePage.getHomePageTitle();
		Assert.assertEquals(actHomePageTitle, AppConstants.HOME_PAGE_TITLE);
	}
}
