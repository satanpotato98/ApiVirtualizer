package org.acme.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.acme.entity.Api;
import org.acme.entity.Product;
import org.acme.utilities.Identifier;
import org.acme.utilities.JsonKeyFinder;
import org.acme.utilities.JsonStringtoObject;
import org.acme.utilities.XmlStringToJson;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ExcelWriter {
	
	public Response requestWriter(Product product) throws IOException {
		String productName=product.getProductName();
		List<Api> apiList=product.getApiList();
		JsonObject requestJson = null;
		FileInputStream fileInputStream = new FileInputStream("Resources/ExcelTemplate.xlsx");
		Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("Request");
		for(Api api: apiList) {
			
			if(Identifier.identifyContent(api.getRequestBody()).equalsIgnoreCase("json")) {
				requestJson=JsonStringtoObject.JsonStringtoObject(api.getRequestBody());
			}
			else if(Identifier.identifyContent(api.getRequestBody()).equalsIgnoreCase("xml")) {
				try {
					requestJson=XmlStringToJson.convertXmlStringToJson(api.getRequestBody());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<List<String>> requestKeys=JsonKeyFinder.getAllKeys(requestJson);
			
			
	       
	 
	        for(List<String> structure:requestKeys) {
	        	int rowCount = sheet.getLastRowNum();
		        Row row = sheet.createRow(++rowCount);
		        int colCount=0;
	        	Cell productNameCell=row.createCell(colCount++);
	        	productNameCell.setCellValue(productName);
	        	Cell apiNameCell=row.createCell(colCount++);
	        	apiNameCell.setCellValue(api.getApiName());
	        	Cell keyCell=row.createCell(colCount++);
	        	keyCell.setCellValue(structure.get(structure.size()-1));
	        	Cell structureCell=row.createCell(colCount++);
	        	structureCell.setCellValue(structure.toString());
	        	
	        }
			
	        
	        
	        
			           
        }
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
		workbook.close();
        fileInputStream.close();
        byte[] excelBytes = outputStream.toByteArray();

        // Create response with the Excel file
        return Response.ok(excelBytes)
                .header("Content-Disposition", "attachment; filename=\"ApiVirtualizer.xlsx\"")
                .build();
			 }
}
	
