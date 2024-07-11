package org.acme.writer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

public class ResponseWriter {

	public static void responseWriter(Workbook workbook, Product product) {
		String productName=product.getProductName();
		List<Api> apiList=product.getApiList();
		Sheet sheet = workbook.getSheet("Response");
		JsonObject responseJson = null;
		for(Api api: apiList) {
			int responseIndex=-1;
			List<String> responses=api.getResponseBody();
			for(String response:responses) {
				responseIndex++;
				if(Identifier.identifyContent(response).equalsIgnoreCase("json")) {
					responseJson=JsonStringtoObject.JsonStringtoObject(response);
				}
				else if(Identifier.identifyContent(response).equalsIgnoreCase("xml")) {
					try {
						responseJson=XmlStringToJson.convertXmlStringToJson(response);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			
			List<String> responseKeys=JsonKeyFinder.gatherKeys(responseJson);
			
			for(String structure:responseKeys) {
	        	int rowCount = sheet.getLastRowNum();
		        Row row = sheet.createRow(++rowCount);
		        int colCount=0;
		        Cell index=row.createCell(colCount++);
		        index.setCellValue(responseIndex);
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
}