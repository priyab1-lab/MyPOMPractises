package com.opencart.qa.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.opencart.qa.factory.DriverFactory;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.pages.LoginPage;
import com.opencart.qa.pages.ProductInfoPage;
import com.opencart.qa.pages.ResultsPage;

public class BaseTest {

	protected WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName) {
		df=new DriverFactory();
		prop=df.initProp();
	
		if (browserName !=null) {
			prop.setProperty("browser", browserName);
		}
		driver=df.initDriver(prop);
		loginPage=new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
