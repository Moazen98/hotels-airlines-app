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


public class ExcelListReportUserHotelView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition","attachment; filename=\"UserHotelRegister.xls\"");

		
		@SuppressWarnings("unchecked")
		List<UserHotel> list = (List<UserHotel>) model.get("userHotelList");
		
		Sheet sheet = workbook.createSheet("User List");

		
		
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("HotelId");
		header.createCell(2).setCellValue("RoomId");
		header.createCell(3).setCellValue("CityName");
		header.createCell(4).setCellValue("UserName");
		header.createCell(5).setCellValue("StartDate");
		header.createCell(6).setCellValue("EndDate");
		header.createCell(7).setCellValue("RoomType");
		header.createCell(8).setCellValue("Avalible");
		header.createCell(9).setCellValue("TotalCost");
		header.createCell(10).setCellValue("IsCanceled");
		header.createCell(11).setCellValue("AccountId");
		int rowNum = 1;
		
		//Here I creat Row and Set Elements :3
		for(UserHotel userHotel : list) {
			
			Row row = sheet.createRow(rowNum++);
			
			
			row.createCell(0).setCellValue(String.valueOf(userHotel.getId()));

			row.createCell(0).setCellValue(String.valueOf(userHotel.getId()));
			row.createCell(1).setCellValue(String.valueOf(userHotel.getHotelId()));
			row.createCell(2).setCellValue(String.valueOf(userHotel.getRoomId()));
			row.createCell(3).setCellValue(userHotel.getCityName());
			row.createCell(4).setCellValue(userHotel.getUserName());
			row.createCell(5).setCellValue(userHotel.getStartdate().toString());
			row.createCell(6).setCellValue(userHotel.getEndDate().toString());
			row.createCell(7).setCellValue(userHotel.getRoomType());
			row.createCell(8).setCellValue(String.valueOf(userHotel.getAvalible()));
			row.createCell(9).setCellValue(String.valueOf(userHotel.getTotalCost()));
			row.createCell(10).setCellValue(String.valueOf(userHotel.isCanceled()));
			row.createCell(11).setCellValue(String.valueOf(userHotel.getAccountId()));
			


		}
	
	}

}
