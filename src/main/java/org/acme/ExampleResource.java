package org.acme;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Product;
import org.acme.service.ExcelWriter;
import org.acme.service.RawDataToExcel;
import org.acme.utilities.JsonKeyFinder;
import org.acme.utilities.XmlStringToJson;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api")
public class ExampleResource {

	@Inject
	ExcelWriter excelWriter;
	
    @POST
    @Path("/1")
    public Response jsonKeys(Product product) throws IOException {
    	
    	return excelWriter.requestWriter(product);
    	
    	
    	
    }
    @POST
    @Path("/2")
    public List xmlKeys(Product product) throws IOException {
    	
    	JsonObject jsonObject=XmlStringToJson.convertXmlStringToJson(product.getApiList().get(0).getResponseBody().get(0));
    	List keys=JsonKeyFinder.getAllKeys(jsonObject);
    	return keys;
    }

    @GET
    @Path("/3")
    public Response rawDatatoExcel() throws IOException {

        List<Map<String, Object>> list = Arrays.asList(
                new HashMap<>() {{
                    put("menu", new HashMap<String, Object>() {{
                        put("id", "file111");
                        put("value", "File222");

                        put("popup", new HashMap<String, Object>() {{
                            put("menuitem", Arrays.asList(
                                    new HashMap<String, Object>() {{
                                        put("value", "New");
                                        put("onclick", "CreateNewDoc()");
                                    }},
                                    new HashMap<String, Object>() {{
                                        put("value", "Open");
                                        put("onclick", "OpenDoc()");
                                    }},
                                    new HashMap<String, Object>() {{
                                        put("value", "Close");
                                        put("onclick", "CloseDoc()");
                                    }}
                            ));
                        }});
                    }});
                }}
        );

        return RawDataToExcel.rawDataToMap(list);
    	
    	
    	
    }
    
}
