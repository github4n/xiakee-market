package com.xiakee.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperaterUtil {

	public static List<List<String>> read(InputStream is) throws IOException {
		List<List<String>> results = new ArrayList<List<String>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(is);
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		// 定义 row、cell
		XSSFRow row;
		String cell;
		// 循环输出表格中的内容
		for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			List<String> rowList = new ArrayList<String>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				// 通过 row.getCell(j).toString() 获取单元格内容，
				if (row.getCell(j) == null) {
					cell = "";
				} else {
					cell = row.getCell(j).toString();
				}
				rowList.add(cell);
			}
			results.add(rowList);
		}
		return results;
	}

}
