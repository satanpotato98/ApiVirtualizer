package org.acme.writer;

import java.util.List;

import org.acme.entity.Api;
import org.acme.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class TransformWriter {

	public static void transformWriter(Workbook workbook, Product product) {
		String productName=product.getProductName();
		List<Api> apiList=product.getApiList();
		Sheet sheet = workbook.getSheet("Transform");
		
		for(Api api:apiList) {
			int rowCount = sheet.getLastRowNum();
	        Row row = sheet.createRow(++rowCount);
	        int colCount=0;
        	Cell productNameCell=row.createCell(colCount++);
        	productNameCell.setCellValue(productName);
        	Cell apiNameCell=row.createCell(colCount++);
        	apiNameCell.setCellValue(api.getApiName());
		}
	}
	
}
