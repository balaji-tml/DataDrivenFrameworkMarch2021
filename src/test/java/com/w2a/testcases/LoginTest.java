package com.w2a.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class LoginTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void loginTest(Hashtable<String, String> data) {

		// (String username,String password,String runmode) {

		String username = data.get("username");
		String password = data.get("password");
		String runmode = data.get("runmode");

		if (!(runmode.equals("Y"))) {
			throw new SkipException("Skipping the testcase as the Runmode is No");//
		}

		log.debug("Inside LoginTest Test!!!");
		Reporter.log("Inside LoginTest Test!!!");
		click("login_LINK");
		type("user_ID", username);
		type("pass_ID", password);
		click("login_NAME");
		Assert.assertTrue(isElementPresent(By.linkText(or.getProperty("logout_LINK"))), "Login test is not successful");
		click("logout_LINK");
		log.debug("Login Test executed successfully!!!");
		Reporter.log("Login Test executed successfully!!!");

	}

}
