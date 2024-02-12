package utils;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel{

	public static String[][] getSheet(String dataSheetName,String sheetName) {

		String[][] data = null ;

		Workbook workbook=null;
		Sheet sheet = null;
		FileInputStream fis=null;

		try {
			String[] format = dataSheetName.split("\\.");

			if(format[1].equalsIgnoreCase("xlsx")){
				 fis = new FileInputStream("./datasheet/"+dataSheetName);
				 workbook = new XSSFWorkbook(fis);
				 //sheet = workbook.getSheetAt(sheetName);
			} else if (format[1].equalsIgnoreCase("xls")) {
				 fis = new FileInputStream("./datasheet/"+dataSheetName);
				 workbook = new HSSFWorkbook(fis);
				//sheet = workbook.getSheetAt(sheetName);
			}

			sheet = workbook.getSheet(sheetName);

			// get the number of rows
			int rowCount = sheet.getLastRowNum();

			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][columnCount];
			DataFormatter df = new DataFormatter();
			// loop through the rows
			for(int i=1; i <rowCount+1; i++){
				try {
					Row row = sheet.getRow(i);
					for(int j=0; j <columnCount; j++){ // loop through the columns
						try {
							String cellValue = "";
							try{
								// Based on Cell Type reading the content from Cell				
									cellValue = df.formatCellValue(row.getCell(j));
							}catch(NullPointerException e){
								e.printStackTrace();
							}
							data[i-1][j]  = cellValue; // add to the data array
						} catch (Exception e) {
							e.printStackTrace();
						}				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			fis.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}
}
