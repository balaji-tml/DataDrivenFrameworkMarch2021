package com.w2a.utilities;


/*
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
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
			row=sheet.getRow(0);
			cell=row.getCell(0);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		//return sheet.getLastRowNum() + 1;
		return sheet.getLastRowNum()+1;
	}

	public int getColCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		return sheet.getRow(0).getLastCellNum();
	}

	public String getCellData(String sheetName, int rowNum, int colNum) {
			
		
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
			
		/*
		try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            if (index == -1)
                return "";


            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            if (cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cellText;


                }


                return cellText;
            } else if (cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
        */


      /* 
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

*/

//-----------------------------------------------------------------------------------------------------------------



import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class ExcelReader {

    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    public ExcelReader(String path) {

        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            row=sheet.getRow(0);
            fis.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /*
    // to run this on stand alone
    public static void main(String arg[]) throws IOException {


        ExcelReader datatable = null;


        datatable = new ExcelReader("C:\\CM3.0\\app\\test\\Framework\\AutomationBvt\\src\\config\\xlfiles\\Controller.xlsx");
        for (int col = 0; col < datatable.getColumnCount("TC5"); col++) {
            System.out.println(datatable.getCellData("TC5", col, 1));
        }
    }
*/
    // returns the row count in a sheet
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }

    }
    
    public int getColCount(String sheetName) {
    	int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            int number = row.getLastCellNum();
            return number;
        }
    }

    // returns the data from a cell
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(col_Num);

            if (cell == null)
                return "";

            if (cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {

                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.MONTH) + 1 + "/" +
                            cellText;


                }


                return cellText;
            } else if (cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }

    // returns the data from a cell
    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            if (index == -1)
                return "";


            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            if (cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cellText;


                }


                return cellText;
            } else if (cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
    }
}