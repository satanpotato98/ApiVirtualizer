package org.acme.utilities;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.json.JsonObject;

public class XmlStringToJson {

	public static JsonObject convertXmlStringToJson(String xmlString) throws IOException {

		ObjectMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(xmlString);      
        JsonObject jsonObject=JsonStringtoObject.JsonStringtoObject(jsonNode.toString());
        return jsonObject;
    }
}
