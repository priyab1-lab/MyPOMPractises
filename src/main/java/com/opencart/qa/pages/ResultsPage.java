package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;

import seleniumSessions.ElementUtil1;

public class ResultsPage {
	//1. initi driver and elementUtil
			private WebDriver driver;
			private ElementUtil1 eleUtil;
			
			//2. Page class constructor...
			public ResultsPage(WebDriver driver) {
				this.driver=driver;
				eleUtil=new ElementUtil1(driver);
			}
			
			//3. Private by locators
			private final By searchResults=By.cssSelector("div.product-thumb");
			
			
			//4. Public page actions/methods
			public int getSearchResultsCount() {
				int resultsCount=eleUtil.waitForAllElementsVisible(searchResults, AppConstants.MEDIUM_TIMEOUT).size();
				System.out.println("Total no of results: " +resultsCount);
				return resultsCount;
			}
			
			public ProductInfoPage selectProduct(String productName) {
				System.out.println("Select Productname: "+productName);
				eleUtil.doClick(By.linkText(productName));
				return new ProductInfoPage(driver);
			}
			
}
