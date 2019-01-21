package com.financial.menu;

import com.financial.services.ApiService;
import com.financial.expense.Expense;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class MenuTotal {

    private Map<String, ArrayList<Expense>> map = Menu.mapService.map;
    private MenuCheck menuCheck = new MenuCheck();



    public void menuTotal(String[] arr) {
        try {
            total(arr[1].toUpperCase());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You have not entered the currency, please, try again!! ");
            menuCheck.check();
        }
    }

    public double calculateCurrencyEur(Map<String, ArrayList<Expense>> map) {

        double eur=0.0;

        for (Map.Entry<String, ArrayList<Expense>> entry : map.entrySet()) {
            for (Expense item : entry.getValue()) {
                Double coef;
                if (!item.getCurrency().equals(ApiService.LOCAL_CURRENCY)) {
                    coef = ApiService.parseCurrentApiJson(item.getCurrency());
                } else coef = 1.0;

                eur=eur+item.getPrice()/coef;
            }

        }
        return eur;
    }



    // after all convertCurrencyToEur we convert currencyEur to the chosen currency
    private void total(String currency) {
        if (map.size() != 0) {
            try {
                Double coef;
                if (!currency.equals(ApiService.LOCAL_CURRENCY)) {
                    coef = ApiService.parseCurrentApiJson(currency);
                } else coef = 1.0;

                Double total = calculateCurrencyEur(map) * coef;
                String formattedTotal = new DecimalFormat("#0.00").format(total);
                System.out.println(formattedTotal + " " + currency);
            } catch (NullPointerException e) {
                System.out.println("No currency with this name was found, please, try again!!");
                menuCheck.check();
            }
        } else {
            System.out.println("Expense list is empty!");
            System.out.println("Add Expenses first");
            menuCheck.check();
        }

    }
}
