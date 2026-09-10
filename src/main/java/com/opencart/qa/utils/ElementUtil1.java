package com.opencart.qa.utils;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil1 {
	
	private WebDriver driver;
	private Actions act;

	public ElementUtil1(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doClick(By locator, long timeout) {
		getElement(locator, timeout).click();
	}

	public void doClear(By locator) {
		getElement(locator).clear();
	}
	public void doSendKeys(By locator, String value) {
		doClear(locator);
		getElement(locator).sendKeys(value);
	}
	
	public void doSendKeys(By locator, String value, long timeout) {
		doClear(locator);
		getElement(locator, timeout).sendKeys(value);
	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	public String getElementAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not found using this locator: " + locator);
			e.printStackTrace();
			return false;
		}
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public WebElement getElement(By locator, long timeout) {
		try {
			return driver.findElement(locator);
		}
		catch(NoSuchElementException e) {
			System.out.println("Element is not found using : " + locator);
			e.printStackTrace();
			return waitForElementVisible(locator, timeout);
		}
	}
	

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();// vc=10;pc=0 ; []

		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}

		return eleTextList;
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	// *********************Select Tag Drop Down Utils******************//

	private Select getSelect(By locator) {
		return new Select(getElement(locator));
	}

	public void doDropDownSelectByIndex(By locator, int index) {
		getSelect(locator).selectByIndex(index);
	}

	public void doDropDownSelectByVisibleText(By locator, String visibleText) {
		getSelect(locator).selectByVisibleText(visibleText);
	}

	public void doDropDownSelectByValue(By locator, String optionValue) {
		getSelect(locator).selectByValue(optionValue);
	}

	public int getDropDownOptionsCount(By locator) {
		return getSelect(locator).getOptions().size();
	}

	public List<String> getDropDownOptionsTextList(By locator) {
		List<WebElement> optionsList = getSelect(locator).getOptions();
		List<String> optionsTextList = new ArrayList<String>();// pc=0, vc=10; []

		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}

		return optionsTextList;

	}

	// *********************Actions Class Utils******************//

	public void doActionsClick(By locator) {
		act.click(getElement(locator)).perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		act.sendKeys(getElement(locator), value).perform();
	}

	public void handleMenuItemsLevel2(By parentLocator, By childLocator) {
		act.moveToElement(getElement(parentLocator)).perform();
		doClick(childLocator);
	}

	public void handleMenuItemsLevel3(By menu1, By menu2, By menu3) throws InterruptedException {
		doClick(menu1);
		act.moveToElement(getElement(menu2)).perform();
		doClick(menu3);
	}

	public void handleMenuItemsLevel4(By menu1, By menu2, By menu3, By menu4) throws InterruptedException {
		doClick(menu1);
		act.moveToElement(getElement(menu2)).perform();
		act.moveToElement(getElement(menu3)).perform();
		doClick(menu4);
	}

	public void sendKeysWithPause(By locator, String value, long pauseTime) {
		char val[] = value.toCharArray();
		for (char e : val) {
			act.sendKeys(getElement(locator), String.valueOf(e)).pause(pauseTime).perform();
		}
	}

	// ***********************Wait Utils********************//
	
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForAllElementsPresence(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. 
	 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForAllElementsVisible(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public void waitForElementReadyAndClick(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitForElementVisible(By locator, long timeOut, long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait
		.pollingEvery(Duration.ofSeconds(pollingTime))
			.ignoring(NoSuchElementException.class)
				.withMessage("---element is not found using -----" + locator);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public WebElement waitForElementVisibleWithFluentWait(By locator, long timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(pollingTime))
						.ignoring(NoSuchElementException.class)
							.withMessage("---element is not found using -----" + locator);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	

	
	/**
	 * An expectation for the URL of the current page to contain specific text.
	 * @param urlValue
	 * @param timeOut
	 * @return
	 */
	public String waitForURLContains(String urlValue, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlContains(urlValue));

		return driver.getCurrentUrl();
	}

	public String waitForURLToBe(String urlValue, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlToBe(urlValue));

		return driver.getCurrentUrl();
	}

	public String waitForTitleContains(String titleValue, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleContains(titleValue));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println(titleValue + " is not found");
			e.printStackTrace();
			return null;
		}

	}

	public String waitForTitleIs(String titleValue, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleIs(titleValue));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println(titleValue + " is not found");
			e.printStackTrace();
			return null;
		}

	}
	
	
	private Alert waitForAlert(long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	
	public String waitForJSAlertAndAccept(long timeOut) {
		Alert alert = waitForAlert(timeOut);
		String text = alert.getText();
		alert.accept();
		return text;
	}
	
	public String waitForJSAlertAndDismiss(long timeOut) {
		Alert alert = waitForAlert(timeOut);
		String text = alert.getText();
		alert.dismiss();
		return text;
	}
	
	public String waitForJSPromptAlertAndEnterValue(String value, long timeOut) {
		Alert alert = waitForAlert(timeOut);
		String text = alert.getText();
		alert.sendKeys(value);
		alert.accept();
		return text;
	}
		
	public void waitForFrameAndSwitchToIt(By frameLocator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public void waitForFrameAndSwitchToIt(int frameIndex, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public void waitForFrameAndSwitchToIt(String frameIDOrName, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDOrName));
	}
	
	public void waitForFrameAndSwitchToIt(WebElement frameElement, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
}
