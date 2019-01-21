package com.financial.services;

import com.financial.expense.Expense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MapService {

    public static Map<String, ArrayList<Expense>> map;
    private JsonService jsonService;
    public double currencyEur;

    public MapService()  {
        try {
            jsonService = new JsonService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map = jsonService.map;
        currencyEur = 0.0;
    }

    //this method checks for the date in the array and edd expense
    public void add(Expense expense) {
        /*
         * if this date is present - we add to this date new expense
         * if this date is absent - we add new date with expense
         */

        currencyEur = currencyEur + ApiService.convertCurrencyToEur(expense.getPrice(), expense.getCurrency());

        for (Map.Entry<String, ArrayList<Expense>> entry : map.entrySet()) {
            if (entry.getKey().equals(expense.getDate())) {
                map.get(expense.getDate()).add(expense);
                printMap();
                return;
            }
        }
        ArrayList<Expense> list = new ArrayList<>();
        list.add(expense);
        map.put(expense.getDate(), list);
        try {
            jsonService.writeJson(JsonService.LINK,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printMap();

    }

    public void printMap() {

        for (Map.Entry<String, ArrayList<Expense>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            printList(entry.getValue());

        }
    }

    private void printList(ArrayList<Expense> list) {
        for (Expense item : list) {
            System.out.println(item.getName() + " " + item.getPrice() + " " + item.getCurrency());
        }
        System.out.println();
    }
}
