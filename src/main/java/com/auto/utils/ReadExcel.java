package com.auto.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	private Workbook wb;
	private Sheet sheet;
	private Row row;
	LogUtil log = new LogUtil(ReadExcel.class);
	private Object[][] testcase_data_arrays;

	public ReadExcel(String filepath) {
		if (filepath == null) {
			return;
		}
		String ext = filepath.substring(filepath.lastIndexOf("."));
		try {

			InputStream is = new FileInputStream(filepath);
			if (ext.equals(".xls")) {
				wb = new HSSFWorkbook(is);
			} else if (ext.equals(".xlsx")) {
				wb = new XSSFWorkbook(is);
			} else {
				wb = null;
			}
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException" + e);
		} catch (IOException e) {
			log.error("IOException" + e);
		}
	}

	public Object[][] case_data_excel(String sheet_name, String filepath) throws Exception {

		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}

		sheet = wb.getSheet(sheet_name);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		// 获取到列数
		int colNum = row.getLastCellNum();
		System.out.println("row:" + rowNum + "  " + "col:" + colNum);
		testcase_data_arrays = new String[rowNum + 1][colNum];
		for (int i = 0; i <= rowNum; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				int j = 0;
				while (j < colNum) {
					String cellvalue = getCellFormatValue(row.getCell(j));
			//		System.out.println("[" + i + "]" + "[" + j + "]：" + cellvalue);
					testcase_data_arrays[i][j] = cellvalue;
					j++;
				}
			}else{
				int j = 0;
				while (j < colNum) {
			//		System.out.println("[" + i + "]" + "[" + j + "]：" + "");
					testcase_data_arrays[i][j] = "";
					j++;
				}
			}
				
		}
		return testcase_data_arrays;
	}

	public String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				// 将科学计数转换成字符串
				DecimalFormat df = new DecimalFormat("#");
				cellvalue = String.valueOf(df.format(cell.getNumericCellValue()));
				break;
			case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_BLANK:
				cellvalue = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellvalue = String.valueOf(cell.getBooleanCellValue());
				break;
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

}
