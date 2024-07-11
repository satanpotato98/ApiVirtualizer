package org.acme;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.entity.Api;
import org.acme.entity.Product;
import org.acme.service.ExcelWriter;
import org.acme.utilities.JsonKeyFinder;
import org.acme.utilities.JsonStringtoObject;
import org.acme.utilities.XmlStringToJson;
import org.acme.writer.RequestWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import upload.ExcelService;
import upload.fileUploadForm;

@Path("/api")
public class ExampleResource {

	@Inject
	ExcelWriter excelWriter;
	
	@Inject
    ExcelService excelService;
	
    @POST
    @Path("/1")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response jsonKeys(Product product) throws IOException {
    	
    	return excelWriter.requestWriter(product);
    	
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadExcelFile(@MultipartForm MultipartFormDataInput input) throws IOException {
        if (input.getFormDataMap() == null) {
            System.out.println("File input stream is null");
            return Response.status(Response.Status.BAD_REQUEST).entity("File input stream is null").build();
        }
        else {
        	Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        	List<InputPart> inputParts=uploadForm.get("file");
        	for (InputPart inputPart : inputParts) {
                
                	InputStream fileInputStream;
					try {
						fileInputStream = inputPart.getBody(InputStream.class, null);
						Workbook workbook = new XSSFWorkbook(fileInputStream);
	                	 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                     workbook.write(outputStream);
	                     workbook.close();
	                     fileInputStream.close();
	                    
	                     
	                     Map<String, String> responseMap = new HashMap<>();
	                     responseMap.put("message", "File processed successfully");
	                     ObjectMapper objectMapper = new ObjectMapper();
	                     String jsonString = objectMapper.writeValueAsString(responseMap);

	                     // Write JSON string to file
	                     File jsonFile = new File("/tmp/response.json");
	                     try (FileOutputStream fos = new FileOutputStream(jsonFile)) {
	                         fos.write(jsonString.getBytes());
	                     }
	                     return Response.ok(jsonFile)
	                             .header("Content-Disposition", "attachment; filename=\"APIDEF.json\"")
	                             .build();
	                     
	                    
	                     
	                  
					} catch (IOException e) {
						return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error processing file").build();
						
					}
        	}
        }
       
        return Response.status(Response.Status.BAD_REQUEST).entity("No file provided").build();
    }
    
    @POST @Path("/3")
    public List<String> keys(Product product){
    	
    	JsonObject y=JsonStringtoObject.JsonStringtoObject(product.getApiList().get(0).getRequestBody());
    	return JsonKeyFinder.gatherKeys(y);
    }
        
    }
 
