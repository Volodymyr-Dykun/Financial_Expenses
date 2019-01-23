package com.financial.services;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.expense.Expense;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class JsonService {


    public static final String LINK = "src/main/resources/list.json";   // path, where will be stored list.json
    static Map<Date, ArrayList<Expense>> map;

    public JsonService() throws IOException {
        map = new TreeMap<>();
        File file = new File(LINK);
        if (!file.exists() || file.isDirectory()) {
            Map<Date , ArrayList<Expense>> map = new TreeMap();
            writeJson(LINK,map);
        } else {
            map = readJson();
        }
    }

    public static void writeJson(String link, Map data) throws IOException {
        JsonFactory jfactory = new JsonFactory();
        JsonGenerator jsonGenerator = jfactory.createGenerator(new File(link), JsonEncoding.UTF8);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(jsonGenerator, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map readJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(new File(LINK), new TypeReference<Map<Date,ArrayList<Expense>>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }
}
