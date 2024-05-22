package org.acme.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

public class JsonKeyFinder {

	public static List<List<String>> getAllKeys(JsonObject jsonObject) {
        List<List<String>> keys = new ArrayList<>();
        extractNestedKeys(jsonObject, new ArrayList<>(), keys);
        return keys;
    }

    private static void extractNestedKeys(JsonObject jsonObject, List<String> parentKeys, List<List<String>> keys) {
        for (Map.Entry<String, JsonValue> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonValue value = entry.getValue();
            List<String> currentKeys = new ArrayList<>(parentKeys);
            currentKeys.add(key);
            keys.add(currentKeys);
            if (value instanceof JsonObject) {
                extractNestedKeys((JsonObject) value, currentKeys, keys);
            }
        }
    }
}

