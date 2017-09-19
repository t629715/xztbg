package com.fx.xzt.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;

public class POIUtils {
	public void doExport(HttpServletRequest request, HttpServletResponse response, List<?> dataList, String titleName,
			String excelName, String[] heads, String[] columns) {
		OutputStream os = null;
		HSSFWorkbook hssfWorkbook;
		try {
			String explorerType = request.getHeader("User-Agent");
			if (null != explorerType && StringUtils.contains(explorerType, "MSIE")) {// IE浏览器
				excelName = URLEncoder.encode(excelName, "UTF8");
			} else if (null != explorerType && StringUtils.contains(explorerType, "Mozilla")) {// google,火狐浏览器
				excelName = new String(excelName.getBytes(), "ISO8859-1");
			} else {
				excelName = URLEncoder.encode(excelName, "UTF8");// 其他浏览器
			}
			hssfWorkbook = this.decorateExcel(heads, columns, dataList, titleName);
			response.addHeader("Content-Disposition", "attachment;filename=" + excelName + ".xls");

			if (explorerType != null && !"".equals(explorerType) && explorerType.indexOf("MSIE") > 0) {
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "public");
			}
			os = response.getOutputStream();
			hssfWorkbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public HSSFWorkbook decorateExcel(String[] head, String[] column, List<?> data, String title) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		if (data == null || data.size() == 0) {
			workbook.createSheet();
			return workbook;
		}
		HSSFFont headfont = workbook.createFont();
		headfont.setFontName("黑体");
		headfont.setFontHeightInPoints((short) 22);// 字体大小
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗

		HSSFCellStyle headstyle = workbook.createCellStyle();
		headstyle.setFont(headfont);
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		headstyle.setLocked(true);
		headstyle.setWrapText(true);// 自动换行

		HSSFFont columnHeadFont3 = workbook.createFont();
		columnHeadFont3.setFontName("黑体");
		columnHeadFont3.setFontHeightInPoints((short) 10);
		columnHeadFont3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFCellStyle headstyle3 = workbook.createCellStyle();
		headstyle3.setFont(columnHeadFont3);
		headstyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		headstyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

		HSSFSheet sheet = workbook.createSheet(); // 创建工作薄
		// 创建第一行 索引从0开始
		HSSFRow row0 = sheet.createRow(0);
		row0.setHeight((short) 900);
		// 合并行 四个参数分别为：开始行 开始列 结束行 结束列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, column.length - 1));
		// 在row0行里创建第一列cell0
		HSSFCell cell1 = row0.createCell(0);
		// 为第一列设置样式
		cell1.setCellStyle(headstyle);
		// 为第一列赋值
		cell1.setCellValue(new HSSFRichTextString(title));

		HSSFRow row = null; // 行计录
		HSSFCell cell = null; // 单元格
		int rowIndex = 1;
		// 增加头文件信息
		if (head != null) {
			row = sheet.createRow(rowIndex);
			for (int i = 0; i < head.length; i++) {
				// 设置指定列宽 第一个参数表示 第几列 第二个参数表示 列宽
				sheet.setColumnWidth(i, 4000);
				cell = row.createCell(i);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				// 为第一列设置样式
				cell.setCellStyle(headstyle3);
				cell.setCellValue(head[i]);
			}
			rowIndex++;
		}
		// 写入内容
		try {
			for (int i = 0, iSize = data.size(); i < iSize; i++) {
				row = sheet.createRow(rowIndex++);
				Object obj = data.get(i);
				for (int j = 0; j < column.length; j++) {
					HSSFCell cellj = row.createCell(j);
					cellj.setCellType(Cell.CELL_TYPE_STRING);
					cellj.setCellValue(BeanUtils.getProperty(obj, column[j]));
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return workbook;
	}
}
