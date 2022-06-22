package org.standardize;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Standardize {

    public static ArrayList<JSONObject> getStandardJSON(ArrayList<JSONObject> jsonOrigin) {
        String key = "params";
        String anchorKey = "anchor";
        String anchorValue = "true";
        HashMap<String,String> value = new HashMap<>();
        value.put(anchorKey, anchorValue);
        ArrayList<JSONObject> standardizedJson = new ArrayList<>();

        jsonOrigin.forEach(item -> {
            if (!item.containsKey(key)) {
                item.put(key, value);
            }
            standardizedJson.add(item);
        });

        return standardizedJson;
    }

    public static ArrayList<JSONObject> getJSONFromFile(String filePath) {
        JSONParser jsonParser = new JSONParser();
        ArrayList<JSONObject> resultArray = new ArrayList<>();
        FileReader reader;

        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File ["+filePath+"] not found.", e);
        }

        JSONArray obj;
        try {
            obj = (JSONArray) jsonParser.parse(reader);
        } catch (IOException e) {
            throw new RuntimeException("I/O Exception.", e);
        } catch (ParseException e) {
            throw new RuntimeException("JSON parse exception.",e);
        }

        for (Object o : obj) {
            if (o instanceof JSONObject) {
                resultArray.add((JSONObject) o);
            }
        }

        return resultArray;
    }
}
