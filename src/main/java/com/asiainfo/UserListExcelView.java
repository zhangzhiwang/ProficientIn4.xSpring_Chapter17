package com.asiainfo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.asiainfo.entity.User;

@SuppressWarnings("deprecation")
public class UserListExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "inline;filename=" + new String("用户列表".getBytes(), "iso8859-1"));

		List<User> userList = (List<User>) model.get("userList");
		HSSFSheet sheet = workbook.createSheet("users");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("用户ID");
		row.createCell(1).setCellValue("姓名");
		row.createCell(2).setCellValue("密码");

		int rowNum = 1;
		for (User user : userList) {
			HSSFRow hssfRow = sheet.createRow(rowNum++);
			hssfRow.createCell(0).setCellValue(user.getUserId());
			hssfRow.createCell(1).setCellValue(user.getUserName());
			hssfRow.createCell(2).setCellValue(user.getPassword());
		}
	}
}
