package com.financial.menu;

import com.financial.services.ApiService;
import com.financial.expense.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class MenuClear {

//    private CurrencyService currencyService = new CurrencyService();

    private Map<LocalDate, ArrayList<Expense>> map = Menu.mapService.map;
    private double currencyEur = Menu.mapService.currencyEur;


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
        for (Map.Entry<LocalDate, ArrayList<Expense>> entry : map.entrySet()) {
            if (entry.getKey().equals(Menu.dateService.checkDate(date))) {
                a++;
                for (int i = 0; i < entry.getValue().size(); i++) {
                    currencyEur = currencyEur - ApiService.convertCurrencyToEur(entry.getValue().get(i).getPrice(), entry.getValue().get(i).getCurrency());
                }
                map.remove(Menu.dateService.checkDate(date));

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
