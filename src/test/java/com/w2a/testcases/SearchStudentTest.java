package com.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class SearchStudentTest extends TestBase{

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void searchStudentTest(Hashtable<String,String> data) {
	
		String sno = data.get("sno");
		String runmode = data.get("runmode");
		
		
	//(String sno,String runmode){		
		
		if (!(runmode.equals("Y"))) {
			throw new SkipException("Skipping the testcase as the Runmode is No");//
		}
		
		log.debug("Inside SearchStudentTest Test");
		Reporter.log("Inside SearchStudentTest Test!!!");	
		click("search_LINK");		
		type("search_ID",sno);
		click("search_CSS");
		Assert.assertTrue(isElementPresent(By.linkText(or.getProperty("logout_LINK"))), "SearchStudent test is not successful");
		log.debug("SearchStudent Test executed successfully");
		Reporter.log("SearchStudentTest Test executed successfully!!!");	
		
	}
}
