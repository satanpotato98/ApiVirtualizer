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
import org.acme.writer.ConfigWriter;
import org.acme.writer.RequestWriter;
import org.acme.writer.ResponseWriter;
import org.acme.writer.TransformWriter;
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
		
		FileInputStream fileInputStream = new FileInputStream("Resources/template.xlsx");
		Workbook workbook = new XSSFWorkbook(fileInputStream);
        
		RequestWriter.reuqestWriter(workbook, product);
	    ResponseWriter.responseWriter(workbook, product);
	    TransformWriter.transformWriter(workbook, product);
	    ConfigWriter.configWriter(workbook, product);
	
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
	
