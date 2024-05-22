package org.acme.utilities;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class JsonStringtoObject {

	public static JsonObject JsonStringtoObject(String jsonString) {

		   
		JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();

	   
	    return jsonObject;
	 }
}
