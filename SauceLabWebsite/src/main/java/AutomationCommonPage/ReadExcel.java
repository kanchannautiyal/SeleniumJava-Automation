package AutomationCommonPage;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcel {
	
private  static Workbook workbook;
private static Sheet sheet1;

//load the Excel and open workbook
public static void LoadExcel(String filepath , String sheetname) {
	
	try {
		FileInputStream file = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(file);
		sheet1 = workbook.getSheet(sheetname);  // dynamic workbook
		if (sheet1 == null) {
            System.out.println("Error: Sheet '" + sheetname + "' not found!");
        } else {
            System.out.println("Loaded Sheet: " + sheetname);
        }

	}
	catch(IOException e){
		System.out.println("Error loading Excel file: " + e.getMessage());
	}
}

//Get cell data from specific cell
public static String getCellData(int rowNum , int colNum) {
	
	try {
	
	Row row = sheet1.getRow(rowNum);
	if(row == null) {
		return " ";
	}
	
	Cell cell = row.getCell(colNum);
	if(cell == null) {
		return " ";
	}
	
     cell = sheet1.getRow(rowNum).getCell(colNum);
	if(cell.getCellType()==CellType.STRING) {
		return cell.getStringCellValue();
	}
	if(cell.getCellType()== CellType.NUMERIC) {
		return String.valueOf((int)cell.getNumericCellValue());
	}
	}catch(Exception e) {
	
	System.out.println("Error reading cell [" + rowNum + "," + colNum + "]: " + e.getMessage());
	}
	
	return " ";
}

//Returns the total number of rows that contain data (ignores completely empty rows). -getphysicalNoOfRows()
//Get Total Row Count
	public static int TotalRowCount() {
		if(sheet1!=null) {
		return sheet1.getPhysicalNumberOfRows();
	}
	return 0;
	}
	
	


public static void CloseWorkbook() {
	try {
		if(sheet1 != null) {
			workbook.close();
			System.out.println("Closing workbook");
		}
	}
	catch(IOException e) {
		System.out.println("Error closing workbook" +e.getMessage());
	}
}






//Step 5: Implement Logging with Log4j for Better Test Execution Tracking



























	
	
	

}
