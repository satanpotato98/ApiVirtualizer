package org.acme;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.acme.entity.Api;
import org.acme.entity.Product;
import org.acme.service.ExcelWriter;
import org.acme.utilities.JsonKeyFinder;
import org.acme.utilities.JsonStringtoObject;
import org.acme.utilities.XmlStringToJson;
import org.apache.poi.ss.usermodel.Workbook;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class ExampleResource {

	@Inject
	ExcelWriter excelWriter;
	
    @POST
    @Path("/1")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response jsonKeys(Product product) throws IOException {
    	
    	return excelWriter.requestWriter(product);
    	
    	
    	
    }
    @POST
    @Path("/2")
    public List xmlKeys(Product product) throws IOException {
    	
    	JsonObject jsonObject=JsonStringtoObject.JsonStringtoObject(product.getApiList().get(0).getRequestBody());
    	List keys=JsonKeyFinder.gatherKeys(jsonObject);
    	return keys;
    }
    
    @POST
    @Path("/3")
    public Map convertStringToMap(Product product) {
    	return org.acme.utilities.convertStringToMap.transformToMap(product.getApiList().get(0).getRequestBody());
    }
}
