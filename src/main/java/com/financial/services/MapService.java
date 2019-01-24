package com.financial.services;

import com.financial.expense.Expense;


import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MapService {

    public MapService()  {
    }

    //this method checks for the date in the array and edd expense
    public void addToMap(Expense expense, Map<Date, ArrayList<Expense>> map) {
        /*
         * if this date is present - we addToMap to this date new expense
         * if this date is absent - we addToMap new date with expense
         */

        for (Map.Entry<Date, ArrayList<Expense>> entry : map.entrySet()) {
            if (entry.getKey().equals(expense.getDate())) {
                ArrayList<Expense> list = map.get(expense.getDate());
                list.add(expense);
                map.put(expense.getDate(), list);
                printMap(map);
                return;
            }
        }
        ArrayList<Expense> list = new ArrayList<>();
        list.add(expense);
        map.put(expense.getDate(), list);
        printMap(map);

    }

    public void printMap(Map<Date, ArrayList<Expense>> map) {

        for (Map.Entry<Date, ArrayList<Expense>> entry : map.entrySet()) {
            System.out.println(DateService.dateToString(entry.getKey()));
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
