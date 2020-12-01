package com.zeon.UserPortable.Export;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.zeon.UserPortable.Model.UserHotel;

public class PDFListReportUserHotelView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"UserHotelRegister.pdf\"");

		@SuppressWarnings("unchecked")
		List<UserHotel> list = (List<UserHotel>) model.get("userHotelList");

		Table table = new Table(12);

		table.addCell("ID");
		table.addCell("HotelId");
		table.addCell("RoomId");
		table.addCell("CityName");
		table.addCell("UserName");
		table.addCell("StartDate");
		table.addCell("EndDate");
		table.addCell("RoomType");
		table.addCell("Avalible");
		table.addCell("TotalCost");
		table.addCell("IsCanceled");
		table.addCell("AccountId");

		for (UserHotel userHotel : list) {

			table.addCell(String.valueOf(userHotel.getId()));
			table.addCell(String.valueOf(userHotel.getHotelId()));
			table.addCell(String.valueOf(userHotel.getRoomId()));
			table.addCell(userHotel.getCityName());
			table.addCell(userHotel.getUserName());
			table.addCell(userHotel.getStartdate().toString());
			table.addCell(userHotel.getEndDate().toString());
			table.addCell(userHotel.getRoomType());
			table.addCell(String.valueOf(userHotel.getAvalible()));
			table.addCell(String.valueOf(userHotel.getTotalCost()));
			table.addCell(String.valueOf(userHotel.isCanceled()));
			table.addCell(String.valueOf(userHotel.getAccountId()));

		}

		document.add(table); // to add the above table to the documt

	}

}
