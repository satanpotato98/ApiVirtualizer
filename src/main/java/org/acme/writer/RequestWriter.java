package org.acme.writer;

import java.io.IOException;
import java.util.List;

import org.acme.entity.Api;
import org.acme.entity.Product;
import org.acme.utilities.Identifier;
import org.acme.utilities.JsonKeyFinder;
import org.acme.utilities.JsonStringtoObject;
import org.acme.utilities.XmlStringToJson;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import jakarta.json.JsonObject;

public class RequestWriter {

	public static void reuqestWriter(Workbook workbook, Product product) {
		
		String productName=product.getProductName();
		List<Api> apiList=product.getApiList();
		Sheet sheet = workbook.getSheet("Request");
		JsonObject requestJson = null;
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
			List<String> requestKeys=JsonKeyFinder.gatherKeys(requestJson);
			
			
	       
	 
	        for(String structure:requestKeys) {
	        	int rowCount = sheet.getLastRowNum();
		        Row row = sheet.createRow(++rowCount);
		        int colCount=0;
	        	Cell productNameCell=row.createCell(colCount++);
	        	productNameCell.setCellValue(productName);
	        	Cell apiNameCell=row.createCell(colCount++);
	        	apiNameCell.setCellValue(api.getApiName());
	        	Cell keyCell=row.createCell(colCount++);
	        	String[] x=structure.split("\\.");
	        	keyCell.setCellValue(x[x.length-1]);
	        	Cell structureCell=row.createCell(colCount++);
	        	structureCell.setCellValue(structure.toString());
	        }
		}
	}
}