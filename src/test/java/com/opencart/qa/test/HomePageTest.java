package com.opencart.qa.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.qa.basetest.BaseTest;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.utils.AppConstants;

public class HomePageTest extends BaseTest {
	
	HomePage homePage;
	@BeforeClass
	public void homePageSetup() {
		homePage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	@Test
	public void homePageTitleTest() {
		String homePageTitle=homePage.getHomePageTitle();
		Assert.assertEquals(homePageTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@Test
	public void logoutLinkExists() {
		Assert.assertTrue(homePage.isLogoutLinkExist());
	}
	
	@Test
	public void headersTest() {
		List<String> actHeadersList=homePage.getHomePageHeaders();
		Assert.assertEquals(actHeadersList, AppConstants.EXP_HEADERS_LIST);
	}
	
	
	@DataProvider
	public Object[][] getSearchTestData() {
		return new Object[][] {
			{"mackbook", 3},
			{"imac", 1},
			{"canon", 1}
		};
	}
	@Test(dataProvider="getSearchTestData")
	public void searchTest(String searchKey, int expResultsCount) {
		resultsPage=homePage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultsCount(), expResultsCount);
	}
	
}
