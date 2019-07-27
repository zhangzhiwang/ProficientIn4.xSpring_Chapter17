package com.asiainfo;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.asiainfo.entity.User;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class UserListPDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "inline;filename=" + new String("用户列表".getBytes(), "iso8859-1"));
		
		List<User> userList = (List<User>) model.get("userList");
		Table table = new Table(3);
		table.setWidth(80);
		table.setBorder(1);
		
		BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
		Font font = new Font(baseFont, 10, Font.NORMAL, Color.blue);
		
		table.addCell(buildCell("用户ID", font));
		table.addCell(buildCell("姓名", font));
		table.addCell(buildCell("密码", font));
		
		for(User user : userList) {
			table.addCell(String.valueOf(user.getUserId()));
			table.addCell(user.getUserName());
			table.addCell(user.getPassword());
		}
		
		document.add(table);
	}

	private Cell buildCell(String context, Font font) throws Exception {
		Phrase phrase = new Phrase(context, font);
		return new Cell(phrase);
	}
}
