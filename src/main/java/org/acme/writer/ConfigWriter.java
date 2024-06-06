package org.acme.writer;

import java.util.List;

import org.acme.entity.Api;
import org.acme.entity.Product;
import org.acme.utilities.Identifier;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ConfigWriter {

	
	public static void configWriter(Workbook workbook, Product product) {
		String productName=product.getProductName();
		List<Api> apiList=product.getApiList();
		Sheet sheet = workbook.getSheet("Config");
		
		for(Api api:apiList) {
			int rowCount = sheet.getLastRowNum();
	        Row row = sheet.createRow(++rowCount);
	        int colCount=0;
        	Cell productNameCell=row.createCell(colCount++);
        	productNameCell.setCellValue(productName);
        	Cell apiNameCell=row.createCell(colCount++);
        	apiNameCell.setCellValue(api.getApiName());
        	Cell reqIsJsonCell=row.createCell(colCount++);
        	if(Identifier.identifyContent(api.getRequestBody()).equalsIgnoreCase("json")) {
        		reqIsJsonCell.setCellValue("true");
        	}
        	else {
        		reqIsJsonCell.setCellValue("false");
        	}
        	Cell resIsJsonCell=row.createCell(colCount++);
        	if(Identifier.identifyContent(api.getResponseBody().get(0)).equalsIgnoreCase("json")) {
        		resIsJsonCell.setCellValue("true");
        	}
        	else {
        		resIsJsonCell.setCellValue("false");
        	}
        	Cell resIsString=row.createCell(colCount++);
        	if(Identifier.identifyContent(api.getResponseBody().get(0)).equalsIgnoreCase("String")) {
        		resIsString.setCellValue("true");
        	}
        	else {
        		resIsString.setCellValue("false");
        	}
        	Cell req=row.createCell(colCount++);
        	req.setCellValue(api.getRequestBody());
        	for(String x:api.getResponseBody()) {
        		Cell res=row.createCell(colCount++);
        		res.setCellValue(x);
        	}
		}
	}
}
