package com.financial.services;

import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class ApiService {

    private static final String LINK = "http://data.fixer.io/api/latest";
    private static final String KEY = "d811952d23c06d8d5634689d3ebeb2f9";
    public static final String LOCAL_CURRENCY = "EUR";

    private static URL buildUrl() throws URISyntaxException, MalformedURLException {
        String link = LINK + "?" + "access_key=" + KEY;
        URIBuilder b = new URIBuilder(link);
        System.out.println(link);
        return b.build().toURL();
    }

    private static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        // open connect from URL
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            // read object to StringBuilder
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static Double parseCurrentApiJson(String currency) {
        try {
            // convert string with Json to JSONObject for next parsing
            JSONObject ApiJsonObject = (JSONObject) JSONValue.parseWithException(parseUrl(buildUrl()));
            Map currencyMap = (Map) ApiJsonObject.get("rates");
            if (!currency.toUpperCase().equals(LOCAL_CURRENCY)) {
                return (Double) currencyMap.get(currency.toUpperCase());
            } else return 1.0;

        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            return null;
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static Double convertCurrencyToEur(Double price, String currency) {
        /* this API can not convert USD to PLN (or any other currency)
         * so, we convert all currency to EUR and add to currencyEur
         * coefficient we get with API
         */
        Double coef;
        if (!currency.toUpperCase().equals(LOCAL_CURRENCY)) {
            coef = parseCurrentApiJson(currency);
        } else coef = 1.0;
        return price / coef;
    }
}
