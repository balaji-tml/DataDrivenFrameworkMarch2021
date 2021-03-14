package com.w2a.rough;

import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class TestBaseTest extends TestBase{

	@Test
	public void testBaseTest()
	{
		
		System.out.println("In test method");	      //Remove after dry-run
		ExcelReader excel = new ExcelReader("C:\\Users\\USER\\LearnSelenium\\DataDrivenFramework\\src\\test\\resources\\com\\w2a\\excel\\testdata.xlsx");
		System.out.println("Row Count: "+excel.getRowCount("LoginTest"));                  //Remove after dry-run
		System.out.println("Column Count: "+excel.getColCount("LoginTest"));                  //Remove after dry-run
		System.out.println("CellData: "+excel.getTestData("LoginTest", 1, 0));                  //Remove after dry-run
		System.out.println("CellData: "+excel.getTestData("LoginTest", 1, 1));                 //Remove after dry-run
	}
	
}
