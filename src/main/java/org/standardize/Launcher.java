package org.standardize;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.simple.JSONObject;

import static org.standardize.Standardize.getJSONFromFile;
import static org.standardize.Standardize.getStandardJSON;

public class Launcher {

    public static void main(String[] args) throws Exception {
        ArrayList<JSONObject> jsonOrigin = getJSONFromFile(args[0]);
        ArrayList<JSONObject> jsonStandardizedArray = getStandardJSON(jsonOrigin);

        String outputFileName = "std-jmh-results.json";
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(outputFileName), jsonStandardizedArray);
    }

}
