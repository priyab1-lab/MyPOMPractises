package com.opencart.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.qa.utils.AppConstants;

import seleniumSessions.ElementUtil1;

public class HomePage {
	//1. initi driver and elementUtil
		private WebDriver driver;
		private ElementUtil1 eleUtil;
		
		//2. Page class constructor...
		public HomePage(WebDriver driver) {
			this.driver=driver;
			eleUtil=new ElementUtil1(driver);
		}
		
		//Private by locators
	private final By LogoutLink=By.linkText("Logout");
	private final By headers=By.cssSelector("div#content h2");
	private final By searchTextField=By.name("search");
	private final By searchIcon=By.cssSelector("div#search button");
	
	/// 3. public page actions/methods
	
	public String getHomePageTitle() {
		String actTitle=eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.SHORT_TIMEOUT);
		System.out.println("Home page title: " +actTitle);
		return actTitle;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.isElementDisplayed(LogoutLink);
	}
	
	public List<String> getHomePageHeaders() {
		List<WebElement> headersList=eleUtil.waitForAllElementsPresence(headers, AppConstants.SHORT_TIMEOUT);
		List<String>headersValueList=new ArrayList<String>();
		for(WebElement e:headersList) {
		String text=	e.getText();
		headersValueList.add(text);
		}
		return headersValueList;
	}
	
	public ResultsPage doSearch(String searchKey) {
		driver.findElement(searchTextField).clear();
		eleUtil.doSendKeys(searchTextField, searchKey, AppConstants.SHORT_TIMEOUT);
		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);
		
	}
}
