package com.zeon.UserPortable.Export;

import java.util.List; 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.zeon.UserPortable.Model.UserHotel;
import com.zeon.UserPortable.Model.UserPlane;

public class ExcelListReportUserPlaneView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"UserPlaneRegister.xls\"");

		@SuppressWarnings("unchecked") // just for remove warring :3
		List<UserPlane> list = (List<UserPlane>) model.get("userPlaneList");

		Sheet sheet = workbook.createSheet("User List");

		// here i create header row and set cells:3
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("PlanelId");
		header.createCell(2).setCellValue("UserName");
		header.createCell(3).setCellValue("PlaneType");
		header.createCell(4).setCellValue("Avalible");
		header.createCell(5).setCellValue("TotalCost");
		header.createCell(6).setCellValue("Canceled");
		header.createCell(7).setCellValue("AccountId");
		header.createCell(9).setCellValue("Startdate");
		header.createCell(10).setCellValue("Degree");
		header.createCell(11).setCellValue("Num");
		header.createCell(12).setCellValue("Location");
		header.createCell(13).setCellValue("Destination");
		header.createCell(14).setCellValue("Date");
		header.createCell(15).setCellValue("ArriveTime");
		header.createCell(16).setCellValue("taxiReserve");
	

		int rowNum = 1;

		for (UserPlane user : list) {

			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(String.valueOf(user.getId()));
			row.createCell(1).setCellValue(String.valueOf(user.getPlanelId()));
			row.createCell(2).setCellValue(user.getUserName());
			row.createCell(3).setCellValue(user.getPlaneType());
			row.createCell(5).setCellValue(String.valueOf(user.getAvalible()));
			row.createCell(6).setCellValue(String.valueOf(user.getTotalCost()));
			row.createCell(7).setCellValue(String.valueOf(user.isCanceled()));
			row.createCell(8).setCellValue(String.valueOf(user.getAccountId()));
			row.createCell(9).setCellValue(String.valueOf(user.getStartdate()));
			row.createCell(10).setCellValue(user.getDegree());
			row.createCell(11).setCellValue(String.valueOf(user.getNum()));
			row.createCell(12).setCellValue(user.getLocation());
			row.createCell(13).setCellValue(user.getDestination());
			row.createCell(14).setCellValue(String.valueOf(user.getDate()));
			row.createCell(15).setCellValue(user.getArriveTime());
			row.createCell(15).setCellValue(user.getTaxiReserve());

		}

	}

}
