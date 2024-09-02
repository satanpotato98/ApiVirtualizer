package org.acme.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class RawDataToExcel {

    public static Response rawDataToMap(List<Map<String, Object>> dataList) {

        try {
            // Initialize ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // Prepare to store key-value pairs
            Map<String, String> keyValueMap = new LinkedHashMap<>();

            // Iterate over each map in the list
            for (Map<String, Object> dataMap : dataList) {
                // Convert each map to a JSON string and then to a JsonNode
                String jsonString = mapper.writeValueAsString(dataMap);
                JsonNode rootNode = mapper.readTree(jsonString);

                // Parse the JSON node and add it to the keyValueMap
                keyValueMap.putAll(parseJsonNode(rootNode, ""));
            }

            // Write the key-value map to an Excel file
            return writeMapToExcel(keyValueMap, "RawDataToExcel.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Function to parse the JSON nodes and return a map of key-value pairs
    private static Map<String, String> parseJsonNode(JsonNode node, String path) {
        Map<String, String> keyValueMap = new LinkedHashMap<>();

        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                keyValueMap.putAll(parseJsonNode(field.getValue(), buildPath(path, field.getKey())));
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                keyValueMap.putAll(parseJsonNode(node.get(i), buildPath(path, "[" + i + "]")));
            }
        } else {
            keyValueMap.put(path, node.asText());
        }

        return keyValueMap;
    }

    // Helper function to build the JSON path
    private static String buildPath(String path, String key) {
        if (path.isEmpty()) {
            return key;
        } else {
            return path + "." + key;
        }
    }

    private static Response writeMapToExcel(Map<String, String> map, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Raw Data");

        // Create the first row for keys
        Row keyRow = sheet.createRow(0);
        // Create the second row for values
        Row valueRow = sheet.createRow(1);

        int col = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            keyRow.createCell(col).setCellValue(entry.getKey());
            valueRow.createCell(col).setCellValue(entry.getValue());
            col++;
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }

        // Convert the file content to a byte array and return as a Response
        byte[] fileContent = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(fileName));

        return Response.ok(fileContent)
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .build();
    }
}
