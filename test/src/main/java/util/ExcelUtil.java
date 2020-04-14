package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;

public class ExcelUtil {
	String excelpath;
	FileInputStream fis;
	Workbook workbook;
	Sheet sheet;
	Row row;
	Cell cell;
	DataFormatter format = new DataFormatter();
	
	//Constructor to initialize Excel File
	public ExcelUtil(String excelPath) {
		try {
			//this.excelpath = new File(classLoader.getResource("config.properties").getFile();
			this.excelpath = excelPath;
			fis = new FileInputStream(excelPath);
			String extension = excelPath.split("\\.")[1];
			//System.out.println(extension);
			if (extension.equalsIgnoreCase("xls")) {  
				workbook = new HSSFWorkbook(fis);
			} else if (extension.equalsIgnoreCase("xlsx")) { 
				workbook = new XSSFWorkbook(fis);
			}
			//sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//get row count of the excel file for mentioned sheetName
	public int getRowCount(String sheetName) {
		System.out.println(sheetName);
		sheet = workbook.getSheet(sheetName);
		int index = workbook.getSheetIndex(sheetName);
		System.out.println("sheet Index = " + index);
		if (index == -1) {
			return 0;
		} else {
			sheet = workbook.getSheetAt(index);
			int rowCount = sheet.getLastRowNum() + 1;
			return rowCount;
		}
	}
	
	public String getExcelPath() {
		return this.excelpath;
	}
	//get column count of the excel file for mentioned sheetName
		public int getColCount(String sheetName) {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1 ) {
				return 0;
			} else {		
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(0);
				int columnCount = row.getLastCellNum() + 1;
				return columnCount;
			}
		}
	
	//get cell Data for excel file for mentioned sheetName
	public String getCellData(String sheetName, String colName, int rNum) {
		try {
			if (rNum == -1) {
				return "";
			}
			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1 ) {
				return "";
			}
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
		
			//iterate over col to get colNum for corresponding colName
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
					colNum = i;
					break;
				}
			}
			
			if (colNum == -1) {
				return "";
			}
			
			row = sheet.getRow(rNum - 1);
			if (row == null) {
				return "";
			}
			
			cell = row.getCell(colNum);
			String cellText = format.formatCellValue(cell);
			return cellText;
		} catch(Exception e) {
			e.getMessage();
		}
		return "";
	}
	
	
	//get cell Data for excel file for mentioned sheetName
		public String getCellData(String sheetName, int colNum, int rNum) {
			try {
				if (rNum == -1) {
					return "";
				}
				int index = workbook.getSheetIndex(sheetName);
				//int colNum = -1;
				if (index == -1 ) {
					return "";
				}
				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(0);
				
				if (colNum == -1) {
					return "";
				}
				
				row = sheet.getRow(rNum - 1);
				if (row == null) {
					return "";
				}
				
				cell = row.getCell(colNum);
				String cellText = format.formatCellValue(cell);
				return cellText;
			} catch(Exception e) {
				e.getMessage();
			}
			return "";
		}
		
	//write 
	public boolean writeToExcel(String sheetName, String value, int rowNum, int colNum) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			return false;
		} else {
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.createCell(colNum);
			cell.setCellValue(value);
			return true;
			
		}
		
	}
	
	public boolean writeExcelHeader(String sheetName, String[] header) {
		int index = workbook.getSheetIndex(sheetName);
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(headerFont);
		if (index == -1) {
			return false;
		} else {
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < header.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(header[i]);
				cell.setCellStyle(headerStyle);
			}
			return true;
		}
	}

}
