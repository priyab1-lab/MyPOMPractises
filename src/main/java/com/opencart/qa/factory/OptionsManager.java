package com.opencart.qa.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsManager {
private ChromeOptions co;

private Properties prop;

public void OptionsManager(Properties prop) {
	this.prop=prop;
}

public ChromeOptions getChromeOptions() {
	co=new ChromeOptions();
	if(Boolean.parseBoolean(prop.getProperty("headless"))) {
		co.addArguments("--headless");
	}
	if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
		co.addArguments("--incognito");
	}
	return co;
}
}
