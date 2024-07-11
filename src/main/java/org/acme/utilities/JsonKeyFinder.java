package org.acme.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

public class JsonKeyFinder {

    public static List<String> gatherKeys(JsonObject jsonObject) {
        return gatherKeys(jsonObject, "");
    }

    private static List<String> gatherKeys(JsonObject jsonObject, String parentKey) {
        List<String> keysList = new ArrayList<>();
        Set<Entry<String, JsonValue>> entries = jsonObject.entrySet();

        for (Entry<String, JsonValue> entry : entries) {
            String key = entry.getKey();
            JsonValue value = entry.getValue();
            String fullKey = parentKey.isEmpty() ? key : parentKey + "." + key;

            switch (value.getValueType()) {
                case OBJECT:
                    JsonObject nestedObject = value.asJsonObject();
                    if (nestedObject.isEmpty()) {
                        keysList.add(fullKey);
                    } else {
                        keysList.addAll(gatherKeys(nestedObject, fullKey));
                    }
                    break;
                case ARRAY:
                    JsonArray jsonArray = value.asJsonArray();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonValue arrayElement = jsonArray.get(i);
                        if (arrayElement.getValueType() == JsonValue.ValueType.OBJECT) {
                            JsonObject arrayObject = arrayElement.asJsonObject();
                            if (arrayObject.isEmpty()) {
                                keysList.add(fullKey + "[" + i + "]");
                            } else {
                                keysList.addAll(gatherKeys(arrayObject, fullKey + "[" + i + "]"));
                            }
                        } else {
                            keysList.add(fullKey + "[" + i + "]");
                        }
                    }
                    break;
                default:
                    keysList.add(fullKey);
                    break;
            }
        }

        return keysList;
    }
}
