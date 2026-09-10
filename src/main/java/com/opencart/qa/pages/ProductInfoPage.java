package com.opencart.qa.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.qa.utils.AppConstants;

import seleniumSessions.ElementUtil1;

public class ProductInfoPage {
	//1. initi driver and elementUtil
	private WebDriver driver;
	private ElementUtil1 eleUtil;
	private Map <String, String> productMap;
	
	//2. Page class constructor...
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil1(driver);
	}
	
	//Private by locators
	private final By header=By.cssSelector("div#content h1");
	private final By images=By.cssSelector("ul.thumbnails img");
	private final By button=By.xpath("//button[text()='Add to Cart']");
	private final By quantity=By.name("quantity");
	private final By metaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By priceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	// 3. public page actions/methods
	public String getProductHeader() {
		return eleUtil.doElementGetText(header);
	}
	
	public int getProductImagesCount() {
		int imagesCount=eleUtil.waitForAllElementsVisible(images, AppConstants.MEDIUM_TIMEOUT).size();
		System.out.println("Total images count: "+imagesCount);
		return imagesCount;
	}
	
	public  Map<String, String> getProductInfoData() {
	//	productMap = new HashMap<String, String>();
		productMap=new HashMap<String, String>();
		
		productMap.put("productname", getProductHeader());
		productMap.put("imagescount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("Product Information : \n" + productMap);
		return productMap;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	private void getProductMetaData() {
		List<WebElement> metaDataList=eleUtil.getElements(metaData);
	
		for(WebElement e:metaDataList) {
			String metaText=e.getText();
			String meta[]=metaText.split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
		
}
//	$2,000.00 //0
//	Ex Tax: $2,000.00 //1
	
private void getProductPriceData() {
List<WebElement> priceList=eleUtil.getElements(priceData);
String productPrice=priceList.get(0).getText().trim();
String productExTaxPrice = priceList.get(1).getText().split(":")[1].trim();
productMap.put("productprice", productPrice);
productMap.put("extaxprice", productExTaxPrice);
}
}
	

