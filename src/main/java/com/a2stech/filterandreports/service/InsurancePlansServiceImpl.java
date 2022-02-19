package com.a2stech.filterandreports.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.a2stech.filterandreports.entities.InsurancePlans;
import com.a2stech.filterandreports.model.SearchRequest;
import com.a2stech.filterandreports.model.SearchResponce;
import com.a2stech.filterandreports.repository.InsurancePlansRepo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Font;

@Service
public class InsurancePlansServiceImpl implements InsurancePlansService {

	@Autowired
	InsurancePlansRepo insurancePlansRepo;
	
	@Override
	public List<String> getUniquePlanNames() {
		return insurancePlansRepo.getUniquePlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		return insurancePlansRepo.getUniquePlanStatus();
	}

	@Override
	public List<SearchResponce> filterInsurancePlans(SearchRequest request) {
		
		InsurancePlans entity=new InsurancePlans();
		
		if(request!=null && request.getPlanName()!=null && !request.getPlanName().equals(""))
			entity.setPlanName(request.getPlanName());
		if(request!=null && request.getPlansStatus()!=null && !request.getPlansStatus().equals(""))
			entity.setPlansStatus(request.getPlansStatus());
		
		//System.out.println("Plan name :"+entity.getPlanName());
		//System.out.println("Plan Status :"+entity.getPlansStatus());
		
		Example<InsurancePlans> example=Example.of(entity);
		
		//System.out.println(example.toString());
		List<InsurancePlans> plansList=insurancePlansRepo.findAll(example);
		//List<InsurancePlans> plansList=insurancePlansRepo.findAll();
		
		System.out.println("Total Records :"+plansList.size());
		List<SearchResponce> responceList=new ArrayList<>();
		
		for(InsurancePlans plan:plansList) {
			SearchResponce searchResp=new SearchResponce();//is it any better code instead of this(create 10,000 objects for 10,000 records)?
			BeanUtils.copyProperties(plan, searchResp);//10,000 times mapping?
			responceList.add(searchResp);
		}
		return responceList;
	}

	@Override
	public void getPdfReport(List<SearchResponce> planlist,HttpServletResponse response) {
		
		Document document = new Document(PageSize.A4);
        try {
			PdfWriter.getInstance(document, response.getOutputStream());
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
         
        document.open();
        Font font = (Font) FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        ((com.lowagie.text.Font) font).setSize(10);
       // font.setColor(Color.BLUE);
       
       // Paragraph paragraph = new Paragraph("List of Users", font);
       // paragraph.setAlignment(Paragraph.ALIGN_CENTER);
         
        //document.add((Element) paragraph);
         
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);
	         
	    //write Table Header
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(null);
        cell.setPadding(5);
         
        //Font font = (Font) FontFactory.getFont(FontFactory.HELVETICA);
        //font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Plan ID"));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Holder Name"));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plan Name"));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plan Status"));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("SSN No"));
        table.addCell(cell);    
        
        cell.setPhrase(new Phrase("Created Date"));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Updated Date"));
        table.addCell(cell);  
        
	   //write Table Data
        for (SearchResponce plan : planlist) {
            table.addCell(String.valueOf(plan.getId()));
            table.addCell(plan.getCustomerName());
            table.addCell(plan.getPlanName());
            table.addCell(plan.getPlansStatus());
            table.addCell(plan.getSsnNumber());
            table.addCell(plan.getCreatedDate()+"");
            table.addCell(plan.getUpdatedDate()+"");
        }  
	    document.add(table);
	    document.close();
	}

	@Override
	public ByteArrayInputStream getExcelReport(List<SearchResponce> planlist) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = (Sheet) workbook.createSheet("Insurance Plans");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	       
	        // Creating header
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Plan Id");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Plan HolderName");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("Plan Name");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(3);
	        cell.setCellValue("Plan Status");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(4);
	        cell.setCellValue("SSN No");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(5);
	        cell.setCellValue("Created Date");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(6);
	        cell.setCellValue("Updated Date");
	        cell.setCellStyle(headerCellStyle);
	        
	        // Creating data rows for each customer
	        for(int i = 0; i < planlist.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(planlist.get(i).getId());
	        	dataRow.createCell(1).setCellValue(planlist.get(i).getCustomerName());
	        	dataRow.createCell(2).setCellValue(planlist.get(i).getPlanName());
	        	dataRow.createCell(3).setCellValue(planlist.get(i).getPlansStatus());
	        	dataRow.createCell(4).setCellValue(planlist.get(i).getSsnNumber());
	        	dataRow.createCell(5).setCellValue(planlist.get(i).getCreatedDate()+"");
	        	dataRow.createCell(6).setCellValue(planlist.get(i).getUpdatedDate()+"");
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        sheet.autoSizeColumn(6);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}