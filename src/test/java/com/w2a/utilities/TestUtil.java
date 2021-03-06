package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotPath, screenshotName;

	public static void captureScreenshot() {
		Date d = new Date();		
		// screenshotName = TestUtil.screenshotName;
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(userDir + "\\target\\surefire-reports\\html\\" + screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screenshotName = userDir + "\\target\\surefire-reports\\html\\" + screenshotName;	//Remove after dry-run
		System.out.println("screenshotName: "+screenshotName);   //Remove after dry-run
	}

	/*
	@DataProvider(name="dp")
	public Object[][] getData(Method m)
	{		
		String sheetName = null;
		try {
		sheetName=m.getName();
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
		int rows=excel.getRowCount(sheetName);		
		int cols=excel.getColCount(sheetName);		
		
		Object[][] data=new Object[rows-1][cols];
		
		for(int rowNum=2;rowNum<=rows;rowNum++)
		{
			for(int colNum=0;colNum<cols;colNum++)
			{
				data[rowNum-2][colNum]=excel.getCellData(sheetName, rowNum, colNum);
			}
		}
		return data;
	}
	*/
	
	
	
	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		String sheetName=null;
	try {
		sheetName = m.getName();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String,String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}
		}
		return data;
	}
	
	
	/*
	public static boolean isTestRunnable(String testname,ExcelReader excel)
	{
		String sheetName = "test_suite";
		String testcase,runmode;
		int rows = excel.getRowCount(sheetName);
		for(int rowNum =2;rowNum<=rows;rowNum++)
		{
			testcase = excel.getTestData(sheetName, rowNum, "tc_id");			
			if(testcase.equalsIgnoreCase(testname))
			{
				runmode = excel.getTestData(sheetName, rowNum, "runmode");				
				if(runmode.equalsIgnoreCase("Y"))
				
					return true;
				else
					
					return false;
			}
		}
		return false;
	}
	*/
	
	public static boolean isTestRunnable(String testname,ExcelReader excel)
	{
		String sheetName = "test_suite";
		String testcase,runmode;
		int rows = excel.getRowCount(sheetName);
		for(int rowNum =2;rowNum<=rows;rowNum++)
		{
			testcase = excel.getCellData(sheetName, "tc_id", rowNum);
			System.out.println("testcase: "+testcase);                          //Remove after dry-run
			if(testcase.equalsIgnoreCase(testname))
			{
				runmode = excel.getCellData(sheetName, "runmode", rowNum);
				System.out.println("runmode: "+runmode);                          //Remove after dry-run
				if(runmode.equalsIgnoreCase("Y"))
				
					return true;
				else
					
					return false;
			}
		}
		return false;
	}
	
	
}// End of TestUtil Class
