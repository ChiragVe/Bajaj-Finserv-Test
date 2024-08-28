package com.example;

import com.example.parser.JsonParser;
import com.example.hash.MD5HashGenerator;
import com.example.util.RandomStringGenerator;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar DestinationHashGenerator.jar <PRN> <JSON file path>");
            return;
        }

        String prn = args[0].toLowerCase().replace(" ", "");
        String jsonFilePath = args[1];

        try {
            String destinationValue = JsonParser.parseDestinationValue(Paths.get(jsonFilePath));
            if (destinationValue == null) {
                System.out.println("Destination key not found in the JSON file.");
                return;
            }
            String randomString = RandomStringGenerator.generateRandomString(8);
            String concatenatedString = prn + destinationValue + randomString;
            String hash = MD5HashGenerator.generateHash(concatenatedString);

            System.out.println(hash + ";" + randomString);
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }
}
