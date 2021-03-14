package com.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class RegisterTest extends TestBase{

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void registerTest(Hashtable<String,String> data) {
		
		String sno = data.get("sno");
		String firstname = data.get("firstname");
		String lastname = data.get("lastname");
		String email = data.get("email");
		String runmode = data.get("runmode");
	
	
	//(String sno,String firstname,String lasatname,String email,String runmode){
			
		if (!(runmode.equals("Y"))) {
			throw new SkipException("Skipping the testcase as the Runmode is No");//
		}
		log.debug("Inside RegisterTest Test!!!");
		Reporter.log("Inside RegisterTest Test!!!");
		click("register_LINK");		
		type("sno_ID",sno);
		type("fname_ID",firstname);
		type("lname_ID",lastname);
		type("email_ID",email);
		click("register_NAME");
		Assert.assertTrue(isElementPresent(By.linkText(or.getProperty("register_LINK"))), "Register test is not successful");
		log.debug("RegisterTest executed successfully!!!");
		Reporter.log("RegisterTest Test executed successfully!!!");
	}
}
