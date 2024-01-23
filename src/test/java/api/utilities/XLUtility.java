package api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLUtility {
	
	
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	
	public XLUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws IOException {
		
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		int count=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return count;
	}
	
	public int getCellCount(String sheetname , int rownumber) throws IOException {
		
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownumber);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
		
	}
	
	
	public String getCellData(String sheetname, int rownumber , int column) throws IOException {
		
		
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownumber);
		cell=row.getCell(column);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e){
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	
}
