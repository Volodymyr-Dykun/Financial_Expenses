package com.financial.services;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.expense.Expense;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class JsonService {


    public String LINK = "src/main/resources/"+"list.json";   // path, where will be stored list.json

    public JsonService() {
        Map<Date, ArrayList<Expense>> map;
        File file = new File(LINK);
        if (!file.exists() || file.isDirectory()) {
            map = new TreeMap();
            writeJson(map);
        }
//        else{
//            map = readJson();
//        }
    }

    public void writeJson(Map<Date, ArrayList<Expense>> map) {

        try {
            JsonFactory jfactory = new JsonFactory();
            JsonGenerator jsonGenerator = jfactory.createGenerator(new File(LINK), JsonEncoding.UTF8);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(jsonGenerator, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map readJson() {
        Map map;
        ObjectMapper mapper = new ObjectMapper();
        try {
          map = mapper.readValue(new File(LINK), new TypeReference<Map<Date,ArrayList<Expense>>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;

    }
}
