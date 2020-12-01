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
import com.zeon.UserPortable.Model.UserPlane;

public class PDFListReportUserPlaneView extends AbstractPdfView{

	
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition","attachment; filename=\"UserPlaneRegister.pdf\"");
		
	
		@SuppressWarnings("unchecked")
		List<UserPlane> list = (List<UserPlane>) model.get("userPlaneList");
		
		
		Table table = new Table(16);

		table.addCell("ID");
		table.addCell("PlanelId");
		table.addCell("UserName");
		table.addCell("PlaneType");
		table.addCell("Avalible");
		table.addCell("TotalCost");
		table.addCell("Canceled");
		table.addCell("AccountId");
		table.addCell("Startdate");
		table.addCell("Degree");
		table.addCell("Num");
		table.addCell("Location");
		table.addCell("Destination");
		table.addCell("Date");
		table.addCell("ArriveTime");
		table.addCell("taxiReserve");
		
		
		for(UserPlane user : list) {
			

			table.addCell(String.valueOf(user.getId()));
			table.addCell(String.valueOf(user.getPlanelId()));
			table.addCell(user.getUserName());
			table.addCell(user.getPlaneType());
			table.addCell(String.valueOf(user.getAvalible()));
			table.addCell(String.valueOf(user.getTotalCost()));
			table.addCell(String.valueOf(user.isCanceled()));
			table.addCell(String.valueOf(user.getAccountId()));
			table.addCell(String.valueOf(user.getStartdate()));
			table.addCell(user.getDegree());
			table.addCell(String.valueOf(user.getNum()));
			table.addCell(user.getLocation());
			table.addCell(user.getDestination());
			table.addCell(String.valueOf(user.getDate()));
			table.addCell(user.getArriveTime());
			table.addCell(String.valueOf(user.getTaxiReserve()));
		}
		
		document.add(table); 
		
	}



}
