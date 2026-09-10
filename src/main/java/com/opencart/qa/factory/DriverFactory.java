package com.opencart.qa.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.opencart.qa.exceptions.BrowserExceptions;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	
	private OptionsManager optionsManager;
	
	/**
	 * This method is used to init the driver on the basis of given browserName.
	 * 
	 * @param browserName
	 * @return it returns the driver value
	 */
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		String browserName=prop.getProperty("browser");
		System.out.println("browser name : " +browserName);
		optionsManager=new OptionsManager();
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver());
			//driver=new FirefoxDriver();
			break;
		case "edge":
			tlDriver.set(new EdgeDriver());
			//driver=new EdgeDriver();
			break;
default:
	System.out.println("=============Invalid browser======================" +prop);
	throw new BrowserExceptions ("===Invalid Browser");
			
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	/**
	 * this will return one local copy of driver for a specific thread
	 */
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		try {
			FileInputStream ip=new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			prop=new Properties();
			prop.load(ip);
		}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return prop;
	}

}
