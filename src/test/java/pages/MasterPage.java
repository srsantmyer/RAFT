/*
 * UPDATED 2023 BY SCOTT SANTMYER
 * SUPPORT FOR "CURRENT VERSIONS" OF POM DEPENDENCIES
 * COMMENTING OUT OR UPDATING DEPRECATED METHODS
 * ALSO NEW CODE FOR HELPER METHODS
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
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import com.cognizant.core.DriverScript;
import com.cognizant.core.ReusableLibrary;
import com.cognizant.core.ScriptHelper;
import com.cognizant.framework.Status;

/**
 * MasterPage Abstract class
 * 
 * @author Cognizant
 */
abstract class MasterPage extends ReusableLibrary {
	// elements
	private final By preloader = By.id("preloader");
	
	// UI Map object definitions
	// Links
	protected final By lnkSignOff = By.linkText("SIGN-OFF");
	protected final By lnkRegister = By.linkText("REGISTER");

	/**
	 * Constructor to initialize the functional library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	protected MasterPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		PageFactory.initElements(driver.getWebDriver(), this);
	}

	public UserRegistrationPage clickRegister() {
		report.updateTestLog("Click Register", "Click on the REGISTER link", Status.DONE);
		driver.findElement(lnkRegister).click();
		return new UserRegistrationPage(scriptHelper);
	}

	public SignOnPage logout() {
		report.updateTestLog("Logout", "Click the sign-off link", Status.DONE);
		driver.findElement(lnkSignOff).click();
		return new SignOnPage(scriptHelper);
	}

	// helper methods
	
	/**
	 * return a javascript executor object
	 * 
	 * @return the javascript executor for the current WebDriver
	 */
	public JavascriptExecutor getJavaScriptExecutor() {
		var d = driver.getWebDriver();
		JavascriptExecutor js = (JavascriptExecutor) d;
		return js;
	}

	/**
	 * wait for the preloader to go away
	 * and then wait another second
	 */
	public void waitForPreloader() {
		driverUtil.waitUntilElementInvisible(preloader, Duration.ofSeconds(240));
		driverUtil.waitFor(1000);
	}

	/**
	 * wait for a number of seconds
	 * 
	 * @param seconds
	 */
	public void waitForSeconds(int seconds) {
		driverUtil.waitFor(seconds * 1000);
	}


}