package org.standardize;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.standardize.Standardize.getStandardJSON;

public class StandardizeTest {

    private static final String json2Items1Param = "[{\"benchmark\" : \"buildtime\",\"params\" : {\"anchor\" : \"true\"}}, {\"benchmark\" : \"runtime\"}]";
    private static final String json2Items0Param = "[{\"benchmark\" : \"buildtime\"}, {\"benchmark\" : \"runtime\"}]";
    private static final String json2Items2Param = "[{\"benchmark\" : \"buildtime\",\"params\" : {\"anchor\" : \"true\"}}, {\"benchmark\" : \"runtime\",\"params\" : {\"anchor\" : \"true\"}}]";

    @Test
    public void testJson2Items1Param(){
        ArrayList<JSONObject> jsonOrigin = getJsonArray(json2Items1Param);
        ArrayList<JSONObject> jsonExpected = getJsonArray(json2Items2Param);
        ArrayList<JSONObject> jsonStandardizedArray = getStandardJSON(jsonOrigin);
        assertThat(jsonStandardizedArray).isEqualTo(jsonExpected);
    }

    @Test
    public void testJson2Items0Param(){
        ArrayList<JSONObject> jsonOrigin = getJsonArray(json2Items0Param);
        ArrayList<JSONObject> jsonExpected = getJsonArray(json2Items2Param);
        ArrayList<JSONObject> jsonStandardizedArray = getStandardJSON(jsonOrigin);
        assertThat(jsonStandardizedArray).isEqualTo(jsonExpected);
    }

    @Test
    public void testJson2Items2Param(){
        ArrayList<JSONObject> jsonOrigin = getJsonArray(json2Items2Param);
        ArrayList<JSONObject> jsonExpected = getJsonArray(json2Items2Param);
        ArrayList<JSONObject> jsonStandardizedArray = getStandardJSON(jsonOrigin);
        assertThat(jsonStandardizedArray).isEqualTo(jsonExpected);
    }

    @Test
    public void testJson2Items1ParamVsJson2Items1Param(){
        ArrayList<JSONObject> jsonOrigin = getJsonArray(json2Items1Param);
        ArrayList<JSONObject> jsonExpected = getJsonArray(json2Items1Param);
        ArrayList<JSONObject> jsonStandardizedArray = getStandardJSON(jsonOrigin);
        assertThat(jsonStandardizedArray).isNotEqualTo(jsonExpected);
    }

    @Test
    public void testJson2Items0ParamVsJson2Items0Param(){
        ArrayList<JSONObject> jsonOrigin = getJsonArray(json2Items0Param);
        ArrayList<JSONObject> jsonExpected = getJsonArray(json2Items0Param);
        ArrayList<JSONObject> jsonStandardizedArray = getStandardJSON(jsonOrigin);
        assertThat(jsonStandardizedArray).isNotEqualTo(jsonExpected);
    }

    private ArrayList<JSONObject> getJsonArray(String jsonText) {
        JSONParser jsonParser = new JSONParser();
        ArrayList<JSONObject> resultArray = new ArrayList<>();

        JSONArray obj;
        try {
            obj = (JSONArray) jsonParser.parse(jsonText);
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
