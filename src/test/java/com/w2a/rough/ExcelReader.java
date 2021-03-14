package com.w2a.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;

	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			row = sheet.getRow(0);
			cell = row.getCell(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		return sheet.getLastRowNum() + 1;
	}

	public int getColCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		return sheet.getRow(0).getLastCellNum();
	}

	public String getTestData(String sheetName, int rowNum, int colNum) {
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum-1);
		cell = row.getCell(colNum);
		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == CellType.BLANK) {
			return "";
		} else
			return "";
	}
	
	public String getTestData(String sheetName, int rowNum, String colName) {
		int colNum = 0;
		try {
		sheet = workbook.getSheet(sheetName);		
		row = sheet.getRow(0);
		for(int c=0;c<row.getLastCellNum();c++)
		{			
			if (row.getCell(c).getStringCellValue().trim().equals(colName)) {
				colNum = c;
				// break;
			}
		}
		row = sheet.getRow(rowNum-1);
		cell = row.getCell(colNum);
		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == CellType.BLANK) {
			return "";
		} else
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "row: " + rowNum + " or column: " + colNum + " does not exist in excel file";
		}
	}
	
	
}
