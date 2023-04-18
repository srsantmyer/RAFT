/*
 * UPDATED 2023 BY SCOTT SANTMYER
 * SUPPORT FOR "CURRENT VERSIONS" OF POM DEPENDENCIES
 * COMMENTING OUT OR UPDATING DEPRECATED METHODS
 * ALSO NEW CODE TO SUPPORT HEADLESS BROWSERS AND CONFIG BASED OPTIONS
 */

/*
 *  © [2020] Cognizant. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cognizant.framework.selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

/**
 * Class containing useful WebDriver utility functions
 * @author Cognizant
 */
public class WebDriverUtil {
	
	private CustomDriver driver;
	
	/**
	 * Constructor to initialize the {@link WebDriverUtil} object
	 * @param driver The {@link WebDriver} object
	 */
	public WebDriverUtil(CustomDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Function to pause the execution for the specified time period
	 * @param milliSeconds The wait time in milliseconds
	 */
	public void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
	
	/**
	 * Function to wait until the page loads completely
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilPageLoaded(Duration timeOutInSeconds) {
		WebElement oldPage = driver.findElement(By.tagName("html"));
		
		(new WebDriverWait(driver.getWebDriver(), timeOutInSeconds))
									.until(ExpectedConditions.stalenessOf(oldPage));
		
	}
	
	/**
	 * Function to wait until the page readyState equals 'complete'
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilPageReadyStateComplete(Duration timeOutInSeconds) {
		ExpectedCondition<Boolean> pageReadyStateComplete =
			new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
		    
		(new WebDriverWait(driver.getWebDriver(), timeOutInSeconds)).until(pageReadyStateComplete);
	}
	
	/**
	 * Function to wait until the specified element is located
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds DURATION: The wait timeout in seconds
	 */
	public void waitUntilElementLocated(By by, Duration timeOutInSeconds) {
		(new WebDriverWait(driver.getWebDriver(), timeOutInSeconds))
							.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * wait in seconds until element is located
	 * @param by by locator
	 * @param timeOutInSeconds INTEGER: wait time in seconds
	 */
	public void waitUntilElementLocated(By by, Integer timeOutInSeconds){
		waitUntilElementLocated(by, Duration.ofSeconds(timeOutInSeconds));
	}

	/**
	 * Function to wait until the specified element is visible
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementVisible(By by, int seconds){
		waitUntilElementVisible(by, Duration.ofSeconds(seconds));
	}
	
	/**
	 * Function to wait until the specified element is visible - USES DURATION
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementVisible(By by, Duration timeOutInSeconds) {
		(new WebDriverWait(driver.getWebDriver(), timeOutInSeconds))
							.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	/**
	 * Function to wait until the specified element is invisible
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementInvisible(By by, Duration timeOutInSeconds) {
		(new WebDriverWait(driver.getWebDriver(), timeOutInSeconds))
							.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	/**
	 * Function to wait until the specified element is enabled
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementEnabled(By by, Duration timeOutInSeconds) {
		(new WebDriverWait(driver.getWebDriver(), timeOutInSeconds))
							.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	/**
	 * Function to wait until the specified element is disabled
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementDisabled(By by, Duration timeOutInSeconds) {
		(new WebDriverWait(driver.getWebDriver(), timeOutInSeconds))
			.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
	}
	
	/**
	 * Function to select the specified value from a listbox
	 * @param by The {@link WebDriver} locator used to identify the listbox
	 * @param item The value to be selected within the listbox
	 */
	public void selectListItem(By by, String item) {
		Select dropDownList = new Select(driver.findElement(by));
		dropDownList.selectByVisibleText(item);
	}
	
	/**
	 * Function to select the specified value from a listbox
	 * @param by The {@link WebDriver} locator used to identify the listbox
	 * @param item The value to be selected within the listbox
	 */
	public void selectListItem(WebElement e, String item) {
		Select dropDownList = new Select(e);
		dropDownList.selectByVisibleText(item);
	}
	
	/**
	 * Function to do a mouseover on top of the specified element
	 * @param by The {@link WebDriver} locator used to identify the element
	 */
	public void mouseOver(By by) {
		Actions actions = new Actions(driver.getWebDriver());
		actions.moveToElement(driver.findElement(by)).build().perform();
	}
	
	/**
	 * Function to verify whether the specified object exists within the current page
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @return Boolean value indicating whether the specified object exists
	 */
	public Boolean objectExists(By by) {
		return !driver.findElements(by).isEmpty();
	}
	
	/**
	 * Function to verify whether the specified text is present within the current page
	 * @param textPattern The text to be verified
	 * @return Boolean value indicating whether the specified test is present
	 */
	public Boolean isTextPresent(String textPattern) {
		return driver.findElement(By.cssSelector("BODY")).getText().matches(textPattern);
	}

	public Boolean isTextInSource(String textPattern){
		return driver.getPageSource().contains(textPattern);
	}
	
	/**
	 * Function to check if an alert is present on the current page
	 * @param timeOutInSeconds The number of seconds to wait while checking for the alert
	 * @return Boolean value indicating whether an alert is present
	 */
	public Boolean isAlertPresent(Duration timeOutInSeconds) {
		try {
			new WebDriverWait(driver.getWebDriver(), timeOutInSeconds).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}

	/**
	 * helper method to click an element with JavaScript
	 * @param element the element to click
	 */
	public void javaScriptClick(WebElement element){
        var d = driver.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor)d;
		js.executeScript("arguments[0].click()", element);
	}

	/**
	 * helper method to scroll an element into view AND WAIT 1 SECOND
	 * @param element the element to scroll to
	 */
	public void scrollToElement(WebElement element){
        var d = driver.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor)d;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
		waitFor(1000);
	}
}