package org.acme.utilities;

import java.util.HashMap;
import java.util.Map;


public class convertStringToMap {
	
	public static Map<String, Object> transformToMap(String input) {
        String[] parts = input.split("\\.");
        Map<String, Object> root = new HashMap<>();
        Map<String, Object> currentLevel = root;

        for (int i = 0; i < parts.length - 1; i++) {
            Map<String, Object> nextLevel = new HashMap<>();
            currentLevel.put(parts[i], nextLevel);
            currentLevel = nextLevel;
        }

        currentLevel.put(parts[parts.length - 1], parts[parts.length - 1]);

        return root;
    }

}
