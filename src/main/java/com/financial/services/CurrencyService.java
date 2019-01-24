package com.financial.services;

import com.financial.expense.Expense;
import com.financial.menu.CheckExit;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class CurrencyService {

    private Map<Date, ArrayList<Expense>> map = (new JsonService()).readJson();

    public static String checkCurrency(String currency) {
        try {
            if (DataFixerService.parseCurrentApiJson(currency) != null) {
                return currency;
            } else {
                System.out.println("This currency does not exist. Try again.");
                new CheckExit();
                return null;
            }

        } catch (NullPointerException e) {
            System.out.println("This currency does not exist. Try again.");
            new CheckExit();
            return null;
        }
    }

    public double calculateCurrencyEur() {

        double eur=0.0;

        for (Map.Entry<Date, ArrayList<Expense>> entry : map.entrySet()) {
            for (Expense item : entry.getValue()) {
                Double coef;
                if (!item.getCurrency().equals(DataFixerService.LOCAL_CURRENCY)) {
                    coef = DataFixerService.parseCurrentApiJson(item.getCurrency());
                } else coef = 1.0;

                eur=eur+item.getPrice()/coef;
            }

        }
        return eur;
    }
}
