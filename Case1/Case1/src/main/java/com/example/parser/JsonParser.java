package com.example.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class JsonParser {
    public static String parseDestinationValue(Path jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonFilePath.toFile());

        String destinationValue = findDestination(rootNode);
        if (destinationValue == null) {
            throw new IOException("Destination key not found in the JSON file.");
        }
        return destinationValue;
    }

    private static String findDestination(JsonNode node) {
        if (node.has("destination")) {
            return node.get("destination").asText();
        }

        for (JsonNode child : node) {
            String result = findDestination(child);
            if (result != null) {
                return result;
            }
        }

        return null;
    }
}
