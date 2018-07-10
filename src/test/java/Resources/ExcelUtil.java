package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	static CommonUtility util = new CommonUtility();
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	static Random random = new Random();

	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {
			throw (e);
		}
	}

	public static Object[][] getTableArray(String FilePath, String SheetName, String testName) throws IOException {
		String moduleName = null;
		int rowStart = 0;
		int rowEnd = 0;
		Object[][] tabArray = null;
		FileInputStream ExcelFile = new FileInputStream(FilePath);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		for (int i = 0; i <= ExcelWSheet.getLastRowNum(); i++) {
			if (ExcelWSheet.getRow(i).getCell(0).getStringCellValue().equals(testName)) {
				moduleName = ExcelWSheet.getRow(i).getCell(1).getStringCellValue();
				rowStart = (int) ExcelWSheet.getRow(i).getCell(2).getNumericCellValue();
				rowEnd = (int) ExcelWSheet.getRow(i).getCell(3).getNumericCellValue();
				break;
			}
		}
		int totalRow = rowEnd - 1;
		int i = 0;
		tabArray = new Object[totalRow][2];
		while (rowStart <= rowEnd) {
			tabArray[i][0] = moduleName;
			tabArray[i][1] = rowStart;
			rowStart++;
			i++;
		}
		return tabArray;
	}

	public static Map<String, String> TestData(String sheetName, int rownum) throws IOException {
		Map<String, String> testDataMap = new HashMap<String, String>();
		String filepath = FileLookup.DATA_FILE_MASTER_EXCEL;
		FileInputStream ExcelFile = new FileInputStream(filepath);
		String Key, value = null;
		
		// Access the required test data sheet
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		int numOfCols = ExcelWSheet.getRow(0).getLastCellNum();
		for (int i = 0; i < numOfCols; i++) {
			Cell cell1 = ExcelWSheet.getRow(0).getCell(i);
			cell1.setCellType(Cell.CELL_TYPE_STRING);
			Key = cell1.getStringCellValue();
			Cell cell2 = ExcelWSheet.getRow(rownum - 1).getCell(i);
			if (cell2.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(cell2)) {

					Date temp1 = cell2.getDateCellValue();
					DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
					value = df.format(temp1);
				} else {
					int temp = (int) cell2.getNumericCellValue();
					value = Integer.toString(temp);
					// value = Integer.toString(temp);
				}
			} else if (cell2.getCellType() == Cell.CELL_TYPE_STRING) {
				value = cell2.getStringCellValue();
			}
			testDataMap.put(Key, value);
		}
		return testDataMap;
	}
}