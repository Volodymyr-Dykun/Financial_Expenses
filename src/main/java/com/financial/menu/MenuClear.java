package com.financial.menu;

import com.financial.expense.Expense;

import java.util.ArrayList;
import java.util.Map;

public class MenuClear {

//    private CurrencyService currencyService = new CurrencyService();

    private Map<String, ArrayList<Expense>> map = Menu.mapService.map;



    public void menuClear(String[] arr) {
        clear(arr[1]);
    }

    // clear all expense for this Date

    private void clear(String date) {

        MenuCheck menuCheck = new MenuCheck();
        // if you have not entered more expenses
        if (map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
            menuCheck.check();
        }
        int a = 0;
        // if you have entered at least one expense
        for (Map.Entry<String, ArrayList<Expense>> entry : map.entrySet()) {
            if (entry.getKey().equals(date)) {
                a++;
                for (int i = 0; i < entry.getValue().size(); i++) {
                }
                map.remove(date);

                if (map.size()!=0) {
                    System.out.println("Map after remote:");
                    Menu.mapService.printMap();
                } else System.out.println("Map after deletion is empty");
                return;
            }

            if (a == 0) {
                System.out.println(date + " is absent in map, try clear other date!");
                menuCheck.check();
            }
        }
    }
}
